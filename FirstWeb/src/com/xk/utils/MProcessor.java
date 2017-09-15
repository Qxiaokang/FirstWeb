package com.xk.utils;

import java.text.SimpleDateFormat;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
/**
 * @author Admin
 *	json dateformat tool
 */

public class MProcessor implements JsonValueProcessor{
	private String formatString="yyyy-MM-dd";
	public void setFormatString(String ss){
		this.formatString=ss;
	}
	
	@Override
	public Object processArrayValue(Object arg0, JsonConfig arg1) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf=new SimpleDateFormat(formatString);
		return sdf.format(arg0);
	}

	@Override
	public Object processObjectValue(String arg0, Object arg1, JsonConfig arg2) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf=new SimpleDateFormat(formatString);
		return sdf.format(arg1);
	}

}
