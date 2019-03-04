package com.forestry.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;

public class CommonUtil {
    public static String generateCode() {
        return String.valueOf((int)((Math.random() * 9 + 1) * 100000));
    }

    public static final Logger Logger(Class currentClass) {
        return LoggerFactory.getLogger(currentClass);
    }

    public static ArrayList formatReqMapItem2ArrayList(Object reqMapItem, Class entityClass) {
        ArrayList arrayList = new ArrayList();
        JSONArray jsonArray = JSONArray.parseArray(JSONObject.toJSONString(reqMapItem));

        Iterator<Object> iterator = jsonArray.iterator();
        while(iterator.hasNext()) {
            Object object = JSON.toJavaObject((JSONObject)iterator.next(), entityClass);
            arrayList.add(object);
        }

        return arrayList;
    }
}
