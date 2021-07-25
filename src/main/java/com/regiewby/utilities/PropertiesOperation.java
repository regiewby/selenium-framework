package com.regiewby.utilities;

import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesOperation {


    static Properties prop = new Properties();

    public static String getPropertyValueByKey(String key) {

        //1. load data from properties file
        String propFilePath = System.getProperty("user.dir")+"/src/test/java/com/regiewby/properties/config.properties";
        FileInputStream fis;
        try {
            fis = new FileInputStream(propFilePath);
            prop.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //2. read data
        String value = prop.get(key).toString();

        if(StringUtils.isEmpty(value)) {
            try {
                throw new Exception("Value is not specified for key: "+key + " in properties file.");
            }catch(Exception e) {}
        }

        return value.trim();
    }
}
