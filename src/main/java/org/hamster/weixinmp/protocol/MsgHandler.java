package org.hamster.weixinmp.protocol;

import com.google.common.collect.Lists;
import org.hamster.weixinmp.config.WxConfig;
import org.hamster.weixinmp.constant.WxMsgRespType;
import org.hamster.weixinmp.constant.WxMsgTypeEnum;
import org.hamster.weixinmp.dao.entity.base.WxBaseMsgEntity;
import org.hamster.weixinmp.dao.entity.base.WxBaseRespEntity;
import org.hamster.weixinmp.dao.entity.resp.WxRespTextEntity;
import org.hamster.weixinmp.exception.WxException;
import org.hamster.weixinmp.service.WxStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * Created by hacker on 2014/8/14.
 * 消息处理
 */
@Component
public class MsgHandler implements WxMessageHandlerIfc {
    @Autowired
    WxConfig config;
    @Autowired
    WxStorageService storageService;
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
        String type=msg.getMsgType();
        WxMsgTypeEnum typeEnum=WxMsgTypeEnum.inst(type);
        if(!listIntetestedMessageType().contains(typeEnum)){
            return null;
        };
        storageService.saveMsgBase(msg);
        return storageService.createRespText("您好,您的消息已收到",msg.getToUserName(),msg.getFromUserName());
    }

    @Override
    public Integer priority() {
        return 2;
    }
}
