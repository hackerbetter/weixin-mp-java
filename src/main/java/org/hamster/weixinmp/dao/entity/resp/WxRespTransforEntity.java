/**
 * 
 */
package org.hamster.weixinmp.dao.entity.resp;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hamster.weixinmp.config.WxConfig;
import org.hamster.weixinmp.dao.entity.base.WxBaseRespEntity;

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * @author hackerbetter@gmail.com
 * @version Jul 28, 2013
 * 
 */
@Entity
@Table(name = WxConfig.TABLE_PREFIX + "resp_service")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WxRespTransforEntity extends WxBaseRespEntity {
}
