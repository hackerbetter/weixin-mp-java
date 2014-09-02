package org.hamster.weixinmp.protocol;

import com.google.common.collect.Lists;
import org.hamster.weixinmp.cache.CacheService;
import org.hamster.weixinmp.config.WxConfig;
import org.hamster.weixinmp.constant.WxMsgTypeEnum;
import org.hamster.weixinmp.dao.entity.base.WxBaseMsgEntity;
import org.hamster.weixinmp.dao.entity.base.WxBaseRespEntity;
import org.hamster.weixinmp.dao.entity.msg.*;
import org.hamster.weixinmp.dao.repository.msg.WxBaseMsgDao;
import org.hamster.weixinmp.exception.WxException;
import org.hamster.weixinmp.service.WxStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by hacker on 2014/8/14.
 * 消息处理
 */
@Component
public class MsgProtocol implements ProtocolIfc {
    private Logger logger = LoggerFactory.getLogger(MsgProtocol.class);
    @Autowired
    WxConfig config;
    @Autowired
    WxStorageService storageService;
    @Autowired
    protected WxBaseMsgDao msgBaseDao;
    @Autowired
    private transient CacheService cacheService;


    @Override
    public Collection<WxMsgTypeEnum> listIntetestedMessageType() {
        return Lists.newArrayList(
            WxMsgTypeEnum.IMAGE,
            WxMsgTypeEnum.TEXT,
            WxMsgTypeEnum.LINK,
            WxMsgTypeEnum.LOCATION,
            WxMsgTypeEnum.VIDEO,
            WxMsgTypeEnum.VOICE
        );
    }

    @Override
    public WxBaseRespEntity handle(WxBaseMsgEntity msg, Map<String, Object> context) throws WxException {
        WxMsgTypeEnum type=WxMsgTypeEnum.inst(msg.getMsgType());
        WxBaseRespEntity result=null;
        if(!listIntetestedMessageType().contains(type)){
            return (WxBaseRespEntity) context.get("result");
        };
        String lockKey="weixin_msgprotocol_findByMsgId";
        lockThread(lockKey);
        result= storageService.createTransforCustomer(msg.getToUserName(), msg.getFromUserName());
        context.put("result",result);
        unLockThread(lockKey);
        return result;
    }

    @Override
    public Integer priority() {
        return 2;
    }

    public void lockThread(String key){
        while(!cacheService.add(key,1)){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                logger.error("线程休眠",e);
            }
        }
    }

    public void unLockThread(String key){
        cacheService.delete(key);
    }
}
