package org.hamster.weixinmp.gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import org.hamster.weixinmp.annotation.GsonIgnore;

/**
 * Created by hacker on 2014/8/20.
 */
public class MyExclusionStrategy implements ExclusionStrategy {

    public boolean shouldSkipClass(Class<?> clazz) {
        return clazz.getAnnotation(GsonIgnore.class)!=null;
    }

    public boolean shouldSkipField(FieldAttributes f) {
        return f.getAnnotation(GsonIgnore.class) != null;
    }
}