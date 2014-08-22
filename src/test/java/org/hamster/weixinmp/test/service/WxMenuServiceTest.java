/**
 * 
 */
package org.hamster.weixinmp.test.service;

import org.hamster.weixinmp.dao.entity.menu.WxMenuBtnEntity;
import org.hamster.weixinmp.exception.WxException;
import org.hamster.weixinmp.model.menu.WxMenuCreateJson;
import org.hamster.weixinmp.service.WxAuthService;
import org.hamster.weixinmp.service.WxMenuService;
import org.hamster.weixinmp.test.base.AbstractWxServiceTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import java.util.List;

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

	@Test
	public void testAll() throws WxException {
        WxMenuCreateJson menu =new WxMenuCreateJson().addButton(
                        new WxMenuBtnEntity("购买彩票")
                                .addSubButton(new WxMenuBtnEntity("click", "财神选号", "select_bet_ssq"))
                                .addSubButton(new WxMenuBtnEntity("view","购彩大厅","http://m.boyacai.com/index.html?r=wx",null))
                                .addSubButton(new WxMenuBtnEntity("view","绑定账号","http://m.boyacai.com/index.html?r=wx",null))
                                .addSubButton(new WxMenuBtnEntity("view", "合买大厅","http://m.boyacai.com/html/tog.html", null))
                ).addButton(
                new WxMenuBtnEntity("开奖查询")
                        .addSubButton(new WxMenuBtnEntity("click","双色球","openPrize_ssq"))
                        .addSubButton(new WxMenuBtnEntity("click", "大乐透","openPrize_dlt"))
                        .addSubButton(new WxMenuBtnEntity("click","福彩3D","openPrize_3d"))
                        .addSubButton(new WxMenuBtnEntity("click","时时彩","openPrize_ssc"))
        ).addButton(
                new WxMenuBtnEntity("我的彩票")
                        .addSubButton(new WxMenuBtnEntity("view", "开奖公告","http://m.boyacai.com/html/lottery.html", null))
                        .addSubButton(new WxMenuBtnEntity("view", "投注记录","http://m.boyacai.com/html/user/cathecticlist.html?r=wx", null))
                        .addSubButton(new WxMenuBtnEntity("view", "彩票推荐","http://m.boyacai.com/html/more/newslist.html", null))
                        .addSubButton(new WxMenuBtnEntity("view", "优惠活动","http://m.boyacai.com/html/more/active.html", null))
                        .addSubButton(new WxMenuBtnEntity("click","安卓客户端下载", "appdownload"))
        );

		menuService.menuCreate(accessToken,menu);//菜单创建
		System.out.println(menuService.menuGet(accessToken));//菜单查询

		menuService.menuDelete(accessToken);//菜单清空
	}

}
