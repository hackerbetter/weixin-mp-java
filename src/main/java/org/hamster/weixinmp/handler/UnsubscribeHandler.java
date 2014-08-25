package org.hamster.weixinmp.handler;

import org.hamster.weixinmp.dao.entity.base.WxBaseRespEntity;
import org.hamster.weixinmp.dao.entity.msg.WxMsgEventEntity;
import org.hamster.weixinmp.dao.entity.user.WxUserEntity;
import org.hamster.weixinmp.dao.repository.user.WxUserDao;
import org.hamster.weixinmp.protocol.EventProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by hacker on 2014/8/18.
 * 取消关注事件
 */
@Service("unsubscribe")
public class UnsubscribeHandler implements EventProtocol.EventHandlerIfc{
    private Logger logger= LoggerFactory.getLogger(UnsubscribeHandler.class);
    @Autowired(required = false)
    protected WxUserDao userDao;
    @Override
    @Transactional
    public WxBaseRespEntity execute(WxMsgEventEntity msg) {
        logger.info("取消关注{}",msg);
        String openId=msg.getFromUserName();
        if(null==userDao){
            return null;
        }
        WxUserEntity user=userDao.findByOpenId(openId);
        if(user==null){
            return null;
        }
        user.setState(0);//取消关注
        userDao.save(user);
        return null;
    }
}
