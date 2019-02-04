package com.forestry.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonUtil {
    public static String generateCode() {
        return String.valueOf((int)((Math.random()*9+1)*100000));
    }

    public static final Logger Logger(Class currentClass) {
        return LoggerFactory.getLogger(currentClass);
    }
}
