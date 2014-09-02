/**
 * 
 */
package org.hamster.weixinmp.service;

import java.util.Date;
import java.util.List;


import org.apache.commons.lang3.StringUtils;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.hamster.weixinmp.config.WxConfig;
import org.hamster.weixinmp.constant.WxMsgRespType;
import org.hamster.weixinmp.controller.util.WxXmlUtil;
import org.hamster.weixinmp.dao.entity.base.WxBaseMsgEntity;
import org.hamster.weixinmp.dao.entity.item.WxItemMusicEntity;
import org.hamster.weixinmp.dao.entity.item.WxItemPicDescEntity;
import org.hamster.weixinmp.dao.entity.msg.*;
import org.hamster.weixinmp.dao.entity.resp.WxRespMusicEntity;
import org.hamster.weixinmp.dao.entity.resp.WxRespPicDescEntity;
import org.hamster.weixinmp.dao.entity.resp.WxRespTransforEntity;
import org.hamster.weixinmp.dao.entity.resp.WxRespTextEntity;
import org.hamster.weixinmp.dao.entity.user.WxUserEntity;
import org.hamster.weixinmp.dao.repository.auth.WxAuthDao;
import org.hamster.weixinmp.dao.repository.auth.WxAuthReqDao;
import org.hamster.weixinmp.dao.repository.item.WxItemImageDao;
import org.hamster.weixinmp.dao.repository.item.WxItemMusicDao;
import org.hamster.weixinmp.dao.repository.item.WxItemPicDescDao;
import org.hamster.weixinmp.dao.repository.item.WxItemThumbDao;
import org.hamster.weixinmp.dao.repository.item.WxItemVideoDao;
import org.hamster.weixinmp.dao.repository.item.WxItemVoiceDao;
import org.hamster.weixinmp.dao.repository.menu.WxMenuBtnDao;
import org.hamster.weixinmp.dao.repository.msg.*;
import org.hamster.weixinmp.dao.repository.resp.WxRespImageDao;
import org.hamster.weixinmp.dao.repository.resp.WxRespMusicDao;
import org.hamster.weixinmp.dao.repository.resp.WxRespPicDescDao;
import org.hamster.weixinmp.dao.repository.resp.WxRespTextDao;
import org.hamster.weixinmp.dao.repository.resp.WxRespVideoDao;
import org.hamster.weixinmp.dao.repository.resp.WxRespVoiceDao;
import org.hamster.weixinmp.dao.repository.user.WxGroupDao;
import org.hamster.weixinmp.dao.repository.user.WxUserDao;
import org.hamster.weixinmp.model.oauth.WxOAuthTokenJson;
import org.hamster.weixinmp.model.send.base.AbstractCustomSendJson;
import org.hamster.weixinmp.model.user.WxUserInfoJson;
import org.hamster.weixinmp.util.WxUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;

/**
 * @author grossopaforever@gmail.com
 * @version Jul 28, 2013
 * 
 */
@Service
@Transactional(readOnly = true)
public class WxStorageService {

	public static final Logger log = LoggerFactory.getLogger(WxStorageService.class);

	@Autowired(required = false)
	protected WxAuthDao authDao;
	@Autowired(required = false)
	protected WxAuthReqDao authReqDao;
    @Autowired(required = false)
    protected WxBaseMsgDao msgBaseDao;
	@Autowired(required = false)
	protected WxMsgTextDao msgTextDao;
	@Autowired(required = false)
	protected WxMsgImageDao msgImgDao;
	@Autowired(required = false)
	protected WxMsgLinkDao msgLinkDao;
	@Autowired(required = false)
	protected WxMsgLocDao msgLocDao;
	@Autowired(required = false)
	protected WxMsgEventDao msgEventDao;
	@Autowired(required = false)
	protected WxMsgVideoDao msgVideoDao;
	@Autowired(required = false)
	protected WxMsgVoiceDao msgVoiceDao;
    @Autowired(required = false)
    protected WxMsgCustomDao msgCustomDao;
	@Autowired(required = false)
	protected WxRespTextDao respTextDao;
	@Autowired(required = false)
	protected WxRespPicDescDao respPicDescDao;
	@Autowired(required = false)
	protected WxRespMusicDao respMusicDao;
	@Autowired(required = false)
	protected WxRespImageDao respImageDao;
	@Autowired(required = false)
	protected WxRespVideoDao respVideoDao;
	@Autowired(required = false)
	protected WxRespVoiceDao respVoiceDao;
	
	@Autowired(required = false)
	protected WxItemImageDao itemImageDao;
	@Autowired(required = false)
	protected WxItemMusicDao itemMusicDao;
	@Autowired(required = false)
	protected WxItemPicDescDao wxItemPicDescDao;
	@Autowired(required = false)
	protected WxItemThumbDao itemThumbDao;
	@Autowired(required = false)
	protected WxItemVideoDao itemVideoDao;
	@Autowired(required = false)
	protected WxItemVoiceDao itemVoiceDao;

	@Autowired(required = false)
	protected WxMenuBtnDao wxMenuBtnDao;
	
	@Autowired(required = false)
	protected WxGroupDao groupDao;
	@Autowired(required = false)
	protected WxUserDao userDao;

	@Autowired(required = false)
	protected WxConfig wxConfig;

    public WxBaseMsgEntity saveMsgBase(WxBaseMsgEntity entity){
        if(msgBaseDao==null){
            return entity;
        }
        msgBaseDao.save(entity);
        return entity;
    }

    public void saveMsgText(WxMsgTextEntity entity){
        if(msgTextDao==null){
            return;
        }
        msgTextDao.save(entity);
    }

    public void saveMsgImg(WxMsgImageEntity entity){
        if(msgImgDao==null){
            return;
        }
        msgImgDao.save(entity);
    }

    public void saveMsgVoice(WxMsgVoiceEntity entity){
        if(msgVoiceDao==null){
            return;
        }
        msgVoiceDao.save(entity);
    }

    public void saveMsgVideo(WxMsgVideoEntity entity){
        if(msgVideoDao==null){
            return;
        }
        msgVideoDao.save(entity);
    }
    public void saveMsgLink(WxMsgLinkEntity entity){
        if(msgLinkDao==null){
            return;
        }
        msgLinkDao.save(entity);
    }
    public void saveMsgLoc(WxMsgLocEntity entity){
        if(msgLocDao==null){
            return;
        }
        msgLocDao.save(entity);
    }
	public WxMsgTextEntity saveMsgText(Element ele) throws DocumentException {
		WxMsgTextEntity msgText = WxXmlUtil.getMsgText(ele);
		if (msgTextDao != null) {
			msgTextDao.save(msgText);
		} else {

		}
		return msgText;
	}

	public WxMsgImageEntity saveMsgImg(Element ele) throws DocumentException {
		WxMsgImageEntity msgImg = WxXmlUtil.getMsgImage(ele);
		if (msgImgDao != null) {
			msgImgDao.save(msgImg);
		} else {

		}
		return msgImg;
	}

	public WxMsgLinkEntity saveMsgLink(Element ele) throws DocumentException {
		WxMsgLinkEntity msgLink = WxXmlUtil.getMsgLink(ele);
		if (msgLinkDao != null) {
			msgLinkDao.save(msgLink);
		} else {

		}
		return msgLink;
	}

	public WxMsgLocEntity saveMsgLoc(Element ele) throws DocumentException {
		WxMsgLocEntity msgLoc = WxXmlUtil.getMsgLoc(ele);
		if (msgLocDao != null) {
			msgLocDao.save(msgLoc);
		} else {

		}
		return msgLoc;
	}

	public WxMsgEventEntity saveMsgEvent(Element ele) throws DocumentException {
		WxMsgEventEntity msgEvent = WxXmlUtil.getMsgEvent(ele);
		if (msgEventDao != null) {
			msgEventDao.save(msgEvent);
		} else {

		}
		return msgEvent;
	}

    public WxMsgCustomEntity saveJson(AbstractCustomSendJson json, String result) {
        if (msgCustomDao == null) {
            log.debug("Dao not loaded for saving send message json.");
            return null;
        }

        Gson gson = new Gson();
        String jsonString = gson.toJson(json);

        WxMsgCustomEntity entity = new WxMsgCustomEntity();
        entity.setCreatedDate(new Date());
        entity.setJson(jsonString);
        entity.setType(json.getMsgtype());
        entity.setToUser(json.getTouser());
        entity.setResult(result);
        msgCustomDao.save(entity);
        return entity;
    }

    public WxRespTransforEntity createTransforCustomer(String fromUserName,String toUserName) {
        WxRespTransforEntity transforEntity = new WxRespTransforEntity();
        transforEntity.setFromUserName(fromUserName);
        transforEntity.setToUserName(toUserName);
        transforEntity.setMsgType(WxMsgRespType.TRANSFER);
        return transforEntity;
    }

	public WxRespTextEntity createRespText(String content, String fromUserName,
			String toUserName) {
		WxRespTextEntity respText = new WxRespTextEntity();
		respText.setContent(content);
		respText.setCreatedDate(new Date());
		respText.setCreateTime(WxUtil.currentTimeInSec());
		respText.setFromUserName(fromUserName);
		respText.setToUserName(toUserName);
		respText.setMsgType(WxMsgRespType.TEXT);
		if (respTextDao != null) {
			respTextDao.save(respText);
		} else {

		}
		return respText;
	}

	public WxRespPicDescEntity createRespPicDesc(
			List<WxItemPicDescEntity> articles, String fromUserName,
			String toUserName) {
		WxRespPicDescEntity respPicDesc = new WxRespPicDescEntity();
		respPicDesc.setCreatedDate(new Date());
		respPicDesc.setCreateTime(WxUtil.currentTimeInSec());
		respPicDesc.setFromUserName(fromUserName);
		respPicDesc.setToUserName(toUserName);
		respPicDesc.setMsgType(WxMsgRespType.NEWS);
		respPicDesc.setArticles(articles);
		if (respPicDescDao != null) {
			respPicDescDao.save(respPicDesc);
		} else {

		}
		return respPicDesc;
	}

	public WxRespPicDescEntity createRespPicDesc2(List<Long> articleIds,
			String fromUserName, String toUserName) {
		WxRespPicDescEntity respPicDesc = new WxRespPicDescEntity();
		respPicDesc.setCreatedDate(new Date());
		respPicDesc.setCreateTime(WxUtil.currentTimeInSec());
		respPicDesc.setFromUserName(fromUserName);
		respPicDesc.setToUserName(toUserName);
		respPicDesc.setMsgType(WxMsgRespType.NEWS);
		respPicDesc.setArticles(wxItemPicDescDao.findByIdIn(articleIds));
		if (respPicDescDao != null) {
			respPicDescDao.save(respPicDesc);
		} else {

		}
		return respPicDesc;
	}

	public WxRespMusicEntity createRespMusic(String fromUserName,
			String toUserName, WxItemMusicEntity itemMusic) {
		WxRespMusicEntity respMusic = new WxRespMusicEntity();
		respMusic.setCreatedDate(new Date());
		respMusic.setCreateTime(WxUtil.currentTimeInSec());
		respMusic.setFromUserName(fromUserName);
		respMusic.setToUserName(toUserName);
		respMusic.setMsgType(WxMsgRespType.MUSIC);
		// respMusic.setMusic(itemMusic);
		if (respMusicDao != null) {
			respMusicDao.save(respMusic);
		} else {

		}
		return respMusic;
	}
    public WxUserEntity saveOrUpdateUser(WxOAuthTokenJson json) {
        if (userDao == null) {
            WxUserEntity entity = new WxUserEntity();
            entity.setOpenId(json.getOpenid());
            entity.setOauthAccessToken(json.getAccess_token());
            entity.setOauthExpiresIn(json.getExpires_in());
            entity.setOauthRefreshToken(json.getRefresh_token());
            entity.setOauthScope(json.getScope());
            entity.setOauthCreateTime(System.currentTimeMillis());
            return entity;
        }

        WxUserEntity entity = userDao.findByOpenId(json.getOpenid());
        if (entity == null) {
            entity = new WxUserEntity();
        }
        entity.setOpenId(json.getOpenid());
        entity.setOauthAccessToken(json.getAccess_token());
        entity.setOauthExpiresIn(json.getExpires_in());
        entity.setOauthRefreshToken(json.getRefresh_token());
        entity.setOauthScope(json.getScope());
        entity.setOauthCreateTime(System.currentTimeMillis());
        userDao.save(entity);
        return entity;
    }

    public WxUserEntity saveOrUpdateUser(WxUserInfoJson json) {
        if (userDao == null) {
            WxUserEntity entity = new WxUserEntity();
            entity.setOpenId(json.getOpenid());
            entity.setCity(json.getCity());
            entity.setCountry(json.getCountry());
            entity.setHeadImgUrl(json.getHeadimgurl());
            entity.setNickName(json.getNickname());
            entity.setProvince(json.getProvince());
            entity.setPrivileges(StringUtils.join(json.getPrivilege(), ","));
            entity.setSex(json.getSex());
            return entity;
        }

        WxUserEntity entity = userDao.findByOpenId(json.getOpenid());
        if (entity == null) {
            entity = new WxUserEntity();
        }
        entity.setOpenId(json.getOpenid());
        entity.setCity(json.getCity());
        entity.setCountry(json.getCountry());
        entity.setHeadImgUrl(json.getHeadimgurl());
        entity.setNickName(json.getNickname());
        entity.setProvince(json.getProvince());
        entity.setPrivileges(StringUtils.join(json.getPrivilege(), ","));
        entity.setSex(json.getSex());
        userDao.save(entity);
        return entity;
    }
}
