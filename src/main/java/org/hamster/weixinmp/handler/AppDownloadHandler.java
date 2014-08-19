package org.hamster.weixinmp.handler;

import org.hamster.weixinmp.config.WxConfig;
import org.hamster.weixinmp.constant.WxMsgRespType;
import org.hamster.weixinmp.dao.entity.base.WxBaseRespEntity;
import org.hamster.weixinmp.dao.entity.item.WxItemPicDescEntity;
import org.hamster.weixinmp.dao.entity.msg.WxMsgEventEntity;
import org.hamster.weixinmp.dao.entity.resp.WxRespPicDescEntity;
import org.hamster.weixinmp.dao.entity.resp.WxRespTextEntity;
import org.hamster.weixinmp.protocol.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hacker on 2014/8/18.
 * 软件下载事件
 */
@Service("appdownload")
public class AppDownloadHandler implements EventHandler.EventHandlerIfc{
    @Autowired
    private WxConfig config;
    @Override
    public WxBaseRespEntity execute(WxMsgEventEntity msg) {
        List<WxItemPicDescEntity> list=new ArrayList<WxItemPicDescEntity>();
        WxItemPicDescEntity picDescEntity=new WxItemPicDescEntity();
        picDescEntity.setPicUrl("http://www.boyacai.com/rchlw/recs/images/public/logo.png");
        picDescEntity.setTitle("软件下载");
        picDescEntity.setUrl(config.getAppUrl());
        picDescEntity.setDescription("安装博雅彩客户端，500万大奖等着你");
        list.add(picDescEntity);
        WxRespPicDescEntity result=new WxRespPicDescEntity();
        result.setFromUserName(msg.getToUserName());
        result.setToUserName(msg.getFromUserName());
        result.setMsgType(WxMsgRespType.NEWS);
        result.setArticles(list);
        return result;
    }
}
