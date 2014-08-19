package org.hamster.weixinmp.constant;

import lombok.*;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hamster.weixinmp.model.WxRespCode;

/**
 * Created by hacker on 2014/8/18.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Response {
    private String errorCode;
    private String msg;
    private Object value;
    private Response(String errorCode,String msg){
        this.errorCode=errorCode;
        this.msg=msg;
    }
    public static Response paramError(){
        return new Response("9999","参数错误");
    }
    public static Response error(WxRespCode error){
        return new Response(error.getErrcode()+"",error.getErrmsg());
    }
    public static Response success(Object value){
        return new Response("0000","成功",value);
    }
}
