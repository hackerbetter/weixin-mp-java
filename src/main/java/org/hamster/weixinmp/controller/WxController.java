/**
 * 
 */
package org.hamster.weixinmp.controller;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.DocumentException;
import org.hamster.weixinmp.constant.Response;
import org.hamster.weixinmp.dao.entity.base.WxBaseMsgEntity;
import org.hamster.weixinmp.dao.entity.base.WxBaseRespEntity;
import org.hamster.weixinmp.dao.entity.user.WxUserEntity;
import org.hamster.weixinmp.exception.WxException;
import org.hamster.weixinmp.service.WxAuthService;
import org.hamster.weixinmp.service.WxMessageService;
import org.hamster.weixinmp.service.WxUserOAuthService;
import org.hamster.weixinmp.util.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;


/**
 * @author grossopaforever@gmail.com
 * @version Jul 28, 2013
 * 
 */
@Controller
@RequestMapping("/rest/weixinmp")
public class WxController {
	private static final Logger log = LoggerFactory.getLogger(WxController.class);
	
	@Autowired
	private WxAuthService authService;
	@Autowired
	private WxMessageService messageService;
    @Autowired
    private WxUserOAuthService wxUserOAuthService;


    @RequestMapping(value="/getOpenId",method = RequestMethod.GET)
    public @ResponseBody Response getOpenId(@RequestParam("code") String code){
        try {
            log.info("微信授权登录根据code:{}获取openid",code);
            if(StringUtils.isBlank(code)){
                log.info("微信授权获取openid 参数错误");
                return Response.paramError();
            }
            WxUserEntity wxUserEntity=wxUserOAuthService.fetchAccessTokenByCode(code);
            return Response.success(wxUserEntity.getOpenId());
        } catch (WxException e) {
            log.error("微信授权登录获取openid",e);
            return Response.error(e.getError());
        }
    }

	@RequestMapping(method = {RequestMethod.GET})
	public @ResponseBody
	String authGet(@RequestParam("signature") String signature,
			@RequestParam("timestamp") String timestamp,
			@RequestParam("nonce") String nonce,
			@RequestParam("echostr") String echostr) throws WxException {
		if (authService.validateAuth(signature, timestamp, nonce, echostr)) {
			log.info("received authentication message from Weixin Server.");
			return echostr;
		}
		return null;
	}

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
	String post(HttpServletRequest request){
        try {
            String uuid= UUID.randomUUID().toString();
            MDC.put("requestId",uuid.replaceAll("-",""));
            String requestBody= RequestUtil.getParameterString(request);
            WxBaseMsgEntity msg = messageService.parseXML(requestBody);
            log.info("received " + msg.getMsgType() + " message.");
            WxBaseRespEntity resp = messageService.handleMessage(msg);
            String result=messageService.parseRespXML(resp).asXML();
            log.info("响应信息：{}",result);
            return result;
        } catch (DocumentException e) {
            log.error("DocumentException:{}",e);
            return e.getMessage();
        } catch (WxException e) {
            log.error("WxException:{}",e);
            return e.getMessage();
        } finally {
            MDC.remove("requestId");
        }
    }

}
