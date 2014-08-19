package org.hamster.weixinmp.handler;

import org.hamster.weixinmp.constant.WxMsgRespType;
import org.hamster.weixinmp.dao.entity.base.WxBaseRespEntity;
import org.hamster.weixinmp.dao.entity.item.WxItemPicDescEntity;
import org.hamster.weixinmp.dao.entity.msg.WxMsgEventEntity;
import org.hamster.weixinmp.dao.entity.resp.WxRespPicDescEntity;
import org.hamster.weixinmp.protocol.EventHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hacker on 2014/8/18.
 * 关注、取消关注事件
 */
@Service("subscribe")
public class SubscribeHandler implements EventHandler.EventHandlerIfc{
    @Override
    public WxBaseRespEntity execute(WxMsgEventEntity msg) {
        List<WxItemPicDescEntity> list=new ArrayList<WxItemPicDescEntity>();
        WxItemPicDescEntity picDescEntity=new WxItemPicDescEntity();
        picDescEntity.setPicUrl("http://img.boyacai.com/img/adspics/20140303142004.png");
        picDescEntity.setTitle("友情提示");
        picDescEntity.setUrl("http://m.boyacai.com?r=wx");
        picDescEntity.setDescription("绑定账号赢500万");
        list.add(picDescEntity);
        WxRespPicDescEntity result=new WxRespPicDescEntity();
        result.setFromUserName(msg.getToUserName());
        result.setToUserName(msg.getFromUserName());
        result.setMsgType(WxMsgRespType.NEWS);
        result.setArticles(list);
        return result;
    }
}
