package com.kone.framework.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.commons.lang3.StringUtils;


public class PropertiesLoader extends Properties{
	
	public static PropertiesLoader instance = new PropertiesLoader();
	
	public String getProperty(String prop) {
        return this.getProperty(prop, "");
    }
	
	public String getProperty(String prop, 
			                  String defaulVal) {
        String propVal;
        if (StringUtils.isEmpty(propVal = System.getProperty(prop))) {
        	propVal = super.getProperty(prop);	
        }

        return propVal == null ? defaulVal : propVal;
    }
	
    static {
        try {
            instance.load(new FileInputStream(new File("properties/config.properties")));
            System.out.println("==PropertiesLoader static method=="+instance);
        } catch (IOException e) {
            Log.error("Failed to read `" 
                               + System.getProperty("user.dir") 
                               + "/properties/config.properties`. Please make sure it exists!");
            System.exit(1);
        }
    }

}
