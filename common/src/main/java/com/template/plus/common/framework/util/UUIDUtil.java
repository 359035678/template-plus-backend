package com.template.plus.common.framework.util;

import java.util.UUID;

/**
 * @author geekidea
 * @date 2018-11-08
 */
public class UUIDUtil {

    public static String getUuid(){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }
    
}
