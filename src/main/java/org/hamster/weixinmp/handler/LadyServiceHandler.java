package org.hamster.weixinmp.handler;

import org.hamster.weixinmp.dao.entity.base.WxBaseRespEntity;
import org.hamster.weixinmp.dao.entity.msg.WxMsgEventEntity;
import org.hamster.weixinmp.exception.WxException;
import org.hamster.weixinmp.protocol.EventProtocol;
import org.hamster.weixinmp.service.WxStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by hacker on 2014/8/18.
 * 人工客服申请事件
 */
@Service("ladyService")
public class LadyServiceHandler implements EventProtocol.EventHandlerIfc{
    private Logger logger = LoggerFactory.getLogger(LadyServiceHandler.class);
    @Autowired
    WxStorageService storageService;

    @Override
    @Transactional
    public WxBaseRespEntity execute(WxMsgEventEntity msg) throws WxException {
        logger.info("申请接入人工客服",msg);
        return storageService.createTransforCustomer(msg.getToUserName(),msg.getFromUserName());
    }

}
