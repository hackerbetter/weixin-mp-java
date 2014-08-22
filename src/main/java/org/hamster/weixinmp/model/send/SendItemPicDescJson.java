/**
 * 
 */
package org.hamster.weixinmp.model.send;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.hamster.weixinmp.model.send.base.AbstractCustomSendJson;
import org.hamster.weixinmp.model.send.item.wrapper.WxSendItemArticleWrapper;

/**
 * @author grossopaforever@gmail.com
 * @version Dec 30, 2013
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
public class SendItemPicDescJson extends AbstractCustomSendJson {
    public SendItemPicDescJson(){
        this.setMsgtype("news");
    }
	private WxSendItemArticleWrapper news;
}
