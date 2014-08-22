/**
 * 
 */
package org.hamster.weixinmp.dao.entity.menu;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.hamster.weixinmp.config.WxConfig;
import org.hamster.weixinmp.dao.entity.base.WxBaseEntity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author grossopaforever@gmail.com
 * @version Aug 4, 2013
 * 
 */
@Entity
@Table(name = WxConfig.TABLE_PREFIX + "menu_btn")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class WxMenuBtnEntity extends WxBaseEntity {
	@Column(name = "key_", length = 128, nullable = false)
	private String key;
	@Column(name = "url", length = 256, nullable = false)
	private String url;
	@Column(name = "name", length = 80, nullable = false)
	private String name;
	@Column(name = "type", length = WxConfig.COL_LEN_INDICATOR, nullable = false)
	private String type;
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "parent_button_id")
	@Expose(serialize = false, deserialize = false)
	private WxMenuBtnEntity parentButton;
	@OneToMany(mappedBy = "parentButton")
	@SerializedName("sub_button")
	private List<WxMenuBtnEntity> sub_button;

    public WxMenuBtnEntity(String name) {
        this.name = name;
    }

    public WxMenuBtnEntity(String name, String key) {
        this.name = name;
        this.key = key;
    }

    public WxMenuBtnEntity(String type, String name, String key) {
        this.type = type;
        this.name = name;
        this.key = key;
    }
    public WxMenuBtnEntity(String type, String name,String url, String key) {
        this.type = type;
        this.name = name;
        this.url=url;
        this.key = key;
    }

    public WxMenuBtnEntity addSubButton(WxMenuBtnEntity button){
        if(sub_button==null){
            sub_button=new ArrayList<WxMenuBtnEntity>();
        }
        sub_button.add(button);
        return this;
    }


}
