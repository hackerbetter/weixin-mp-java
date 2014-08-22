/**
 * 
 */
package org.hamster.weixinmp.model.menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hamster.weixinmp.dao.entity.menu.WxMenuBtnEntity;

/**
 * @author grossopaforever@gmail.com
 * @version Aug 4, 2013
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxMenuCreateJson {

	private List<WxMenuBtnEntity> button;

    public String toJson(){
        try {
            ObjectMapper om = new ObjectMapper();
            om.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
            return om.writeValueAsString(this);
        } catch (IOException e) {
        }
        return "";
    }

    public WxMenuCreateJson addButton(WxMenuBtnEntity bu){
        if(button==null){
            button=new ArrayList<WxMenuBtnEntity>();
        }
        button.add(bu);
        return this;
    }
}
