/**
 * 
 */
package org.hamster.weixinmp.service;

import org.hamster.weixinmp.dao.entity.user.WxUserEntity;
import org.hamster.weixinmp.dao.repository.user.WxUserDao;
import org.hamster.weixinmp.exception.WxException;
import org.hamster.weixinmp.model.user.WxUserGetJson;
import org.hamster.weixinmp.base.AbstractWxServiceTest;
import org.hamster.weixinmp.util.EmojiFilterUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Locale;

/**
 * @author grossopaforever@gmail.com
 * @version Jan 4, 2014
 *
 */
public class WxUserServiceTest extends AbstractWxServiceTest {

	@Autowired
	WxAuthService authService;
	@Autowired
	WxUserService userService;
    @Autowired
    @Qualifier("wxUserDao")
    WxUserDao wxUserDao;

    @Test
	public void testAll() throws WxException {
		WxUserGetJson userGet = userService.remoteUserGet(accessToken);
        if(userGet==null||userGet.getCount()==0){
            return;
        }
        List<String> users=userGet.getData().getOpenid();
        for(String openId:users){
            WxUserEntity user= wxUserDao.findByOpenId(openId);
            if(user==null){
                user= userService.remoteUserInfo(accessToken, openId, Locale.getDefault().getLanguage());
                user.setNickName(EmojiFilterUtils.filterEmoji(user.getNickName()));
                wxUserDao.save(user);
            }
        }
		System.out.println(userGet);
	}
}
