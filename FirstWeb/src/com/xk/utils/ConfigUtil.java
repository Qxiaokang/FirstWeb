package com.xk.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.hibernate.validator.util.GetClassLoader;
import org.omg.PortableServer.POA;

public class ConfigUtil {
	private static Properties properties=new Properties();
	static{
		ClassLoader classLoader=ConfigUtil.class.getClassLoader();
		InputStream inputStream=classLoader.getResourceAsStream("com/xk/utils/daoconfig.properties");
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String getValue(String key){
		return properties.getProperty(key);
	}
	public static void main(String[] args) {
		System.err.println("value:"+getValue("User"));
	}
}
