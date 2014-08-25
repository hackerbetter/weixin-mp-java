package org.hamster.weixinmp.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by hacker on 2014/8/14.
 */
public class RequestUtil {
    private static Logger logger= LoggerFactory.getLogger(RequestUtil.class);
    /**
     * 获取参数
     * @param request
     * @return
     * @throws Exception
     */
    public static String getParameterString(HttpServletRequest request){
        try {
            StringBuilder builder = new StringBuilder();
            InputStream inStream = request.getInputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len=inStream.read(buffer)) != -1) {
                builder.append(new String(buffer, 0, len));
            }
            inStream.close();
            return builder.toString();
        } catch (IOException e) {
            logger.error("getParameterBody error:",e);
        }
        return "";
    }
}
