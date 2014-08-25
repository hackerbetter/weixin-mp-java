/**
 * 
 */
package org.hamster.weixinmp.protocol;

import java.util.Collection;
import java.util.Map;

import org.hamster.weixinmp.constant.WxMsgTypeEnum;
import org.hamster.weixinmp.dao.entity.base.WxBaseMsgEntity;
import org.hamster.weixinmp.dao.entity.base.WxBaseRespEntity;
import org.hamster.weixinmp.exception.WxException;

/**
 * @author grossopaforever@gmail.com
 * @version Jan 5, 2014
 *
 */
public interface ProtocolIfc {
	
	Collection<WxMsgTypeEnum> listIntetestedMessageType();
	
	WxBaseRespEntity handle(WxBaseMsgEntity msg, Map<String, Object> context) throws WxException;
	
	Integer priority();
}
