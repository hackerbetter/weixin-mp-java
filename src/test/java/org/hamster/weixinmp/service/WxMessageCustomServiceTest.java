package org.hamster.weixinmp.service;

import org.hamster.weixinmp.base.AbstractWxServiceTest;
import org.hamster.weixinmp.dao.entity.user.WxUserEntity;
import org.hamster.weixinmp.dao.repository.user.WxUserDao;
import org.hamster.weixinmp.model.send.item.SendItemArticleJson;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by hacker on 2014/8/20.
 */
public class WxMessageCustomServiceTest extends AbstractWxServiceTest {
    @Autowired
    WxUserDao userDao;
    @Autowired
    WxMessageCustomService messageCustomService;

    @Test
    public void testSendText() throws Exception {
        for(WxUserEntity user:userDao.findAllSubscribeUser()){
            messageCustomService.sendText(accessToken,user.getOpenId(),"徐桂杉");
        }
    }

    @Test
    public void testSendImage() throws Exception {

    }

    @Test
    public void testSendVoice() throws Exception {

    }

    @Test
    public void testSendVideo() throws Exception {

    }

    @Test
    public void testSendMusic() throws Exception {

    }

    @Test
    public void testSendArticles() throws Exception {
        for(WxUserEntity user:userDao.findAllSubscribeUser()){
            List<SendItemArticleJson> articleJsonList=new ArrayList<SendItemArticleJson>();
            SendItemArticleJson json=new SendItemArticleJson();
            json.setTitle("测试图文消息");
            json.setDescription("徐桂杉");
            json.setPicurl("http://img.boyacai.com/img/adspics/20140821132439.png");
            json.setUrl("http://m.boyacai.com?r=wx");
            articleJsonList.add(json);
            messageCustomService.sendArticles(accessToken,user.getOpenId(),articleJsonList);
        }
    }

    @Test
    public void testSend() throws Exception {

    }
}
