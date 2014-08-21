/**
 * 
 */
package org.hamster.weixinmp.model.menu;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
import org.hamster.weixinmp.annotation.GsonIgnore;
import org.hamster.weixinmp.dao.entity.menu.WxMenuBtnEntity;
import org.hamster.weixinmp.gson.MyExclusionStrategy;

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
    @GsonIgnore
    private static Gson gson = null;

    static {
        if (gson == null) {
            gson=new GsonBuilder().disableHtmlEscaping().setExclusionStrategies(new MyExclusionStrategy()).create();
        }
    }

    public String toJson(){
        return gson.toJson(this);
    }

    public WxMenuCreateJson addButton(WxMenuBtnEntity bu){
        if(button==null){
            button=new ArrayList<WxMenuBtnEntity>();
        }
        button.add(bu);
        return this;
    }
}
