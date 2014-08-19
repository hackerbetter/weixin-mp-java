package org.hamster.weixinmp.protocol;

import org.hamster.weixinmp.config.WxConfig;
import org.hamster.weixinmp.constant.*;
import org.hamster.weixinmp.dao.entity.base.WxBaseMsgEntity;
import org.hamster.weixinmp.dao.entity.base.WxBaseRespEntity;
import org.hamster.weixinmp.dao.entity.msg.WxMsgEventEntity;
import org.hamster.weixinmp.util.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by hacker on 2014/8/14.
 * 事件推送处理
 */
@Component
public class EventHandler implements WxMessageHandlerIfc {
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
        WxMsgEventEntity entity=(WxMsgEventEntity) msg;
        WxMsgEventTypeEnum event = WxMsgEventTypeEnum.inst(entity.getEvent());
        EventHandlerIfc handler=null;
        switch (event) {
            case CLICK://点击菜单时间
                handler=SpringUtils.getBean(entity.getEventKey());
                return handler==null?null:handler.execute(entity);
            case SUBSCRIBE://关注公众号事件
                handler=SpringUtils.getBean(entity.getEvent());
                return handler==null?null:handler.execute(entity);
            case UNSUBSCRIBE://取消关注公众号事件
                return null;
            case LOCATION://上报地理位置事件
                return null;
            case SCAN://扫描二维码事件
                return null;
            default:
                break;
        }
        return null;
    }

    @Override
    public Integer priority() {
        return 1;
    }

    public interface EventHandlerIfc {
        public WxBaseRespEntity execute(WxMsgEventEntity msg);
    }
}
