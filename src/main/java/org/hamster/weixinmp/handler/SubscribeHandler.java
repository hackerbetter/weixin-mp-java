package org.hamster.weixinmp.handler;

import org.hamster.weixinmp.constant.WxMsgRespType;
import org.hamster.weixinmp.dao.entity.base.WxBaseRespEntity;
import org.hamster.weixinmp.dao.entity.item.WxItemPicDescEntity;
import org.hamster.weixinmp.dao.entity.msg.WxMsgEventEntity;
import org.hamster.weixinmp.dao.entity.resp.WxRespPicDescEntity;
import org.hamster.weixinmp.dao.entity.user.WxUserEntity;
import org.hamster.weixinmp.dao.repository.user.WxUserDao;
import org.hamster.weixinmp.exception.WxException;
import org.hamster.weixinmp.protocol.EventHandler;
import org.hamster.weixinmp.service.WxAuthService;
import org.hamster.weixinmp.service.WxUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by hacker on 2014/8/18.
 * 关注关注事件
 */
@Service("subscribe")
public class SubscribeHandler implements EventHandler.EventHandlerIfc{
    private Logger logger = LoggerFactory.getLogger(SubscribeHandler.class);
    @Autowired(required = false)
    private WxUserDao userDao;
    @Autowired
    private WxUserService userService;
    @Autowired
    private WxAuthService authService;
    @Override
    @Transactional
    public WxBaseRespEntity execute(WxMsgEventEntity msg) throws WxException {
        logger.info("关注公众账号事件",msg);
        List<WxItemPicDescEntity> list=new ArrayList<WxItemPicDescEntity>();
        WxItemPicDescEntity picDescEntity=new WxItemPicDescEntity();
        picDescEntity.setPicUrl("http://img.boyacai.com/img/adspics/20140821132439.png");
        picDescEntity.setTitle("友情提示");
        picDescEntity.setUrl("http://m.boyacai.com?r=wx");
        picDescEntity.setDescription("绑定账号赢500万");
        list.add(picDescEntity);
        WxRespPicDescEntity result=new WxRespPicDescEntity();
        result.setFromUserName(msg.getToUserName());
        result.setToUserName(msg.getFromUserName());
        result.setMsgType(WxMsgRespType.NEWS);
        result.setArticles(list);
        saveOrUpdateUser(msg.getFromUserName());
        return result;
    }

    private void saveOrUpdateUser(String openId) throws WxException {
        if(null==userDao){
            return;
        }
        WxUserEntity user=userDao.findByOpenId(openId);
        if(null==user){//首次关注
            String accessToken=authService.getAccessToken().getAccessToken();
            String lang= Locale.getDefault().getLanguage();//本地语言
            user = userService.remoteUserInfo(accessToken,openId,lang);
        }
        user.setState(1);//重新关注
        userDao.save(user);
    }

}
