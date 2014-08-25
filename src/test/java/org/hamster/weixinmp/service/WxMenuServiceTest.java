/**
 * 
 */
package org.hamster.weixinmp.service;

import org.hamster.weixinmp.dao.entity.menu.WxMenuBtnEntity;
import org.hamster.weixinmp.dao.repository.menu.WxMenuBtnDao;
import org.hamster.weixinmp.exception.WxException;
import org.hamster.weixinmp.model.menu.WxMenuCreateJson;
import org.hamster.weixinmp.base.AbstractWxServiceTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author grossopaforever@gmail.com
 * @version Jan 4, 2014
 * 
 */
public class WxMenuServiceTest extends AbstractWxServiceTest {

	@Autowired
	WxAuthService authService;
	@Autowired
	WxMenuService menuService;
    @Autowired
    WxMenuBtnDao menuBtnDao;

	@Test
	public void testAll() throws WxException {
        WxMenuCreateJson menu =new WxMenuCreateJson().addButton(
                        new WxMenuBtnEntity("购买彩票")
                                .addSubButton(new WxMenuBtnEntity("view", "购彩大厅","http://testiphone.boyacai.com/index.html?r=wx",null))
                                .addSubButton(new WxMenuBtnEntity("view", "合买大厅","http://testiphone.boyacai.com/html/tog.html?r=wx", null))
                                .addSubButton(new WxMenuBtnEntity("view", "彩票推荐","http://testiphone.boyacai.com/html/more/newslist.html?r=wx", null))
                                .addSubButton(new WxMenuBtnEntity("view","客户端下载", "http://testiphone.boyacai.com/html/common/download.html",null))
                ).addButton(
                new WxMenuBtnEntity("账户资金")
                        .addSubButton(new WxMenuBtnEntity("view","账户提现","http://testiphone.boyacai.com/html/user/withdraw.html?r=wx",null))
                        .addSubButton(new WxMenuBtnEntity("view","账户明细","http://testiphone.boyacai.com/html/user/account.html?r=wx",null))
                        .addSubButton(new WxMenuBtnEntity("view","资金详情","http://testiphone.boyacai.com/html/user/remainder.html?r=wx",null))
        ).addButton(
                new WxMenuBtnEntity("我的彩票")
                        .addSubButton(new WxMenuBtnEntity("view", "中奖查询","http://testiphone.boyacai.com/html/user/lotterylist.html?r=wx", null))
                        .addSubButton(new WxMenuBtnEntity("view", "开奖公告", "http://testiphone.boyacai.com/html/lottery.html?r=wx", null))
                        .addSubButton(new WxMenuBtnEntity("view", "投注记录","http://testiphone.boyacai.com/html/user/cathecticlist.html?r=wx", null))
                        .addSubButton(new WxMenuBtnEntity("view", "优惠活动", "http://testiphone.boyacai.com/html/more/active.html?r=wx", null))
        );

		menuService.menuCreate(accessToken,menu);//菜单创建
		System.out.println(menuService.menuGet(accessToken));//菜单查询
        for(WxMenuBtnEntity button:menu.getButton()){
            for(WxMenuBtnEntity subButton:button.getSub_buttons()){
                subButton.setParentButton(button);
            }
            menuBtnDao.save(button);
        }
//		menuService.menuDelete(accessToken);//菜单清空
	}

}
