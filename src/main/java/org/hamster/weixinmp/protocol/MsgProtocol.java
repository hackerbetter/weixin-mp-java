package org.hamster.weixinmp.protocol;

import com.google.common.collect.Lists;
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
        if(!listIntetestedMessageType().contains(type)){
            return null;
        };
        WxBaseMsgEntity dbEntity= msgBaseDao.findByMsgId(msg.getMsgId());
        if(dbEntity!=null){
            logger.info("该消息{}已经被处理过",msg);
            return null;
        }
        switch (type){
            case TEXT:
                storageService.saveMsgText((WxMsgTextEntity)msg);
                break;
            case IMAGE:
                storageService.saveMsgImg((WxMsgImageEntity) msg);
                break;
            case VOICE:
                storageService.saveMsgVoice((WxMsgVoiceEntity) msg);
                break;
            case VIDEO:
                storageService.saveMsgVideo((WxMsgVideoEntity) msg);
                break;
            case LINK:
                storageService.saveMsgLink((WxMsgLinkEntity) msg);
                break;
            case LOCATION:
                break;
            default:
                return null;
        }
        return storageService.createRespText("您好,您的消息已收到",msg.getToUserName(),msg.getFromUserName());
    }

    @Override
    public Integer priority() {
        return 2;
    }
}
