package com.xk.utils;

import com.xk.RealizeIUser;

public class FactoryDao {
    public static Object getObject(String type){
    	Object object=null;
    	String classString=ConfigUtil.getValue(type);
    	System.err.println("configValue:"+classString);
    	try {
			object=Class.forName(classString).newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return object;
    }
}
