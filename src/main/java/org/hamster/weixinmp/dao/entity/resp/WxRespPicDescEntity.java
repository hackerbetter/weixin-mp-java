/**
 * 
 */
package org.hamster.weixinmp.dao.entity.resp;

import java.util.List;

import javax.persistence.*;

import org.hamster.weixinmp.config.WxConfig;
import org.hamster.weixinmp.dao.entity.base.WxBaseRespEntity;
import org.hamster.weixinmp.dao.entity.item.WxItemPicDescEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * @author grossopaforever@gmail.com
 * @version Jul 28, 2013
 * 
 */
@Entity
@Table(name = WxConfig.TABLE_PREFIX + "resp_pic_desc")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WxRespPicDescEntity extends WxBaseRespEntity {

	@ManyToMany
	@JoinTable(
    name = WxConfig.TABLE_PREFIX+"pic_desc_item",
    joinColumns = @JoinColumn(name="resp_pic_id"),
    inverseJoinColumns = @JoinColumn(name="item_pic_id"))
	private List<WxItemPicDescEntity> articles;
}
