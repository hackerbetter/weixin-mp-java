package org.hamster.weixinmp.protocol;

import com.google.common.collect.Lists;
import org.hamster.weixinmp.config.WxConfig;
import org.hamster.weixinmp.constant.*;
import org.hamster.weixinmp.dao.entity.base.WxBaseMsgEntity;
import org.hamster.weixinmp.dao.entity.base.WxBaseRespEntity;
import org.hamster.weixinmp.dao.entity.msg.WxMsgEventEntity;
import org.hamster.weixinmp.exception.WxException;
import org.hamster.weixinmp.util.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

/**
 * Created by hacker on 2014/8/14.
 * 事件推送处理
 */
@Component
public class EventProtocol implements ProtocolIfc {
    @Autowired
    WxConfig config;
    @Override
    public Collection<WxMsgTypeEnum> listIntetestedMessageType() {
        return Lists.newArrayList(WxMsgTypeEnum.EVENT);
    }

    @Override
    public WxBaseRespEntity handle(WxBaseMsgEntity msg, Map<String, Object> context) throws WxException {
        if(!msg.getMsgType().equals(WxMsgType.EVENT)){
            return null;
        }
        WxMsgEventEntity entity=(WxMsgEventEntity) msg;
        WxMsgEventTypeEnum event = WxMsgEventTypeEnum.inst(entity.getEvent());
        EventHandlerIfc handler=null;
        WxBaseRespEntity result=null;
        switch (event) {
            case CLICK://点击菜单时间
                handler=SpringUtils.getBean(entity.getEventKey());
                result=handler==null?null:handler.execute(entity);
                break;
            case SUBSCRIBE://关注公众号事件
            case UNSUBSCRIBE://取消关注公众号事件
                handler=SpringUtils.getBean(entity.getEvent());
                result=handler==null?null:handler.execute(entity);
                break;
            case LOCATION://上报地理位置事件
                return null;
            case SCAN://扫描二维码事件
                return null;
            default:
                break;
        }
        context.put("result",result);
        return result;
    }

    @Override
    public Integer priority() {
        return 1;
    }

    public interface EventHandlerIfc {
        public WxBaseRespEntity execute(WxMsgEventEntity msg) throws WxException;
    }
}
