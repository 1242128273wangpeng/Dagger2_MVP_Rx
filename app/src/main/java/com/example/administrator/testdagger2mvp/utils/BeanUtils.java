package com.example.administrator.testdagger2mvp.utils;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

/**
 * Created by Administrator on 2017/4/7 0007.
 */

public class BeanUtils {
    public static String toString(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        String dataString = "";
        for (int i = 0; i < fields.length; i++) {
            String filedName = fields[i].getName();
            String filedString = "";
            try {
                filedString = (String) fields[i].get(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            dataString += "  " + filedName + "--->" + filedString;
        }
        return dataString;
    }

    public static boolean allNotNull(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        //设置所有属性方法可访问
        AccessibleObject.setAccessible(fields, true);
        for (int i = 0; i < fields.length; i++) {
            String filedString = "";
            try {
                filedString = (String) fields[i].get(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (filedString == null || "".equals(filedString)) {
                return false;
            }
        }
        return true;
    }
}
