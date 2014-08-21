package org.hamster.weixinmp.handler;

import com.google.common.collect.Maps;
import org.hamster.weixinmp.config.WxConfig;
import org.hamster.weixinmp.constant.WxMsgRespType;
import org.hamster.weixinmp.dao.entity.base.WxBaseRespEntity;
import org.hamster.weixinmp.dao.entity.item.WxItemPicDescEntity;
import org.hamster.weixinmp.dao.entity.msg.WxMsgEventEntity;
import org.hamster.weixinmp.dao.entity.resp.WxRespPicDescEntity;
import org.hamster.weixinmp.dao.entity.resp.WxRespTextEntity;
import org.hamster.weixinmp.exception.WxException;
import org.hamster.weixinmp.protocol.EventHandler;
import org.hamster.weixinmp.util.WxUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by hacker on 2014/8/18.
 */
@Service("userBind")
public class UserBindHandler implements EventHandler.EventHandlerIfc{
    private Logger logger = LoggerFactory.getLogger(UserBindHandler.class);
    @Autowired
    private WxConfig config;
    @Override
    public WxBaseRespEntity execute(WxMsgEventEntity msg) {
        String fromUserName=msg.getToUserName();
        String toUserName=msg.getFromUserName();
        if(isBind(fromUserName)){//已经绑定
            WxRespTextEntity result = new WxRespTextEntity();
            result.setContent("已绑定");
            result.setMsgType(WxMsgRespType.TEXT);
            result.setFromUserName(fromUserName);
            result.setToUserName(toUserName);
            return result;
        }
        //未绑定
        List<WxItemPicDescEntity> list=new ArrayList<WxItemPicDescEntity>();
        WxItemPicDescEntity picDescEntity=new WxItemPicDescEntity();
        picDescEntity.setPicUrl("http://img.boyacai.com/img/adspics/20140303142004.png");
        picDescEntity.setTitle("账户绑定");
        picDescEntity.setUrl("http://m.boyacai.com");
        picDescEntity.setDescription("绑定账号，体验中大奖乐趣");
        list.add(picDescEntity);
        WxRespPicDescEntity result=new WxRespPicDescEntity();
        result.setFromUserName(fromUserName);
        result.setToUserName(toUserName);
        result.setMsgType(WxMsgRespType.NEWS);
        result.setArticles(list);
        return result;
    }

    private boolean isBind(String openid){
        String url= config.getUnionLoginCenterUrl()+"query/loginuser";
        Map<String, String> params = Maps.newHashMap();
        params.put("source", "weixin");
        params.put("openid", openid);
        try {
            UnionLoginCenterResponse result=WxUtil.sendRequest(url, HttpMethod.GET,params,null,UnionLoginCenterResponse.class);
        } catch (WxException e) {
            logger.error(e.getMessage());
        }
        return false;
    }
    class UnionLoginCenterResponse{
        private String errorCode;
        private String message;

    }
}
