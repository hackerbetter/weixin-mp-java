package org.hamster.weixinmp.util;

/**
 * Created by hacker on 2014/8/21.
 * 过滤Emoji表情
 */
public class EmojiFilterUtils {
    public static String filterEmoji(String source) {
        StringBuilder sb = new StringBuilder(source);
        for (int len = source.length(), i = len - 1; i >= 0; --i) {
            int codePoint = source.codePointAt(i);
            // Emoji表情所在码位为U+1F300 – U+1F64F
            if (codePoint >= 127744) {
                sb.deleteCharAt(i);
            }
        }
        return sb.toString();
    }
}
