/**
 * 
 */
package org.hamster.weixinmp.dao.entity.msg;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hamster.weixinmp.config.WxConfig;
import org.hamster.weixinmp.constant.WxMsgType;
import org.hamster.weixinmp.dao.entity.base.WxBaseMsgEntity;
import org.hamster.weixinmp.dao.entity.item.WxItemImageEntity;

/**
 * @author grossopaforever@gmail.com
 * @version Jul 28, 2013
 * 
 */
@Entity
@Table(name = WxConfig.TABLE_PREFIX + "msg_image")
@DiscriminatorValue(WxMsgType.IMAGE)
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WxMsgImageEntity extends WxBaseMsgEntity {

	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "image_id")
	private WxItemImageEntity image;
}
