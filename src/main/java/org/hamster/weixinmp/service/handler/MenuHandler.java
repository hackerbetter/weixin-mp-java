package org.hamster.weixinmp.service.handler;

import org.hamster.weixinmp.config.WxConfig;
import org.hamster.weixinmp.constant.WxMsgRespType;
import org.hamster.weixinmp.constant.WxMsgType;
import org.hamster.weixinmp.constant.WxMsgTypeEnum;
import org.hamster.weixinmp.dao.entity.base.WxBaseMsgEntity;
import org.hamster.weixinmp.dao.entity.base.WxBaseRespEntity;
import org.hamster.weixinmp.dao.entity.msg.WxMsgEventEntity;
import org.hamster.weixinmp.dao.entity.resp.WxRespTextEntity;
import org.hamster.weixinmp.util.WxUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by hacker on 2014/8/14.
 * 菜单事件处理
 */
@Component
public class MenuHandler implements WxMessageHandlerIfc {
    @Autowired
    WxConfig config;
    @Override
    public WxMsgTypeEnum[] listIntetestedMessageType() {
        return new WxMsgTypeEnum[]{WxMsgTypeEnum.EVENT};
    }

    @Override
    public WxBaseRespEntity handle(WxBaseMsgEntity msg, Map<String, Object> context) {
        if(!msg.getMsgType().equals(WxMsgType.EVENT)){
            return null;
        }
        String fromUserName=msg.getToUserName();
        String toUserName=msg.getFromUserName();
        if(((WxMsgEventEntity)msg).getEventKey().equals("appdownload")){
            return WxUtil.sendText(fromUserName,toUserName,config.getAppUrl());
        }
        return null;
    }

    @Override
    public Integer priority() {
        return 1;
    }

}
