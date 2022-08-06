package com.ssg.util;

import java.io.FileInputStream;
import java.util.Properties;

public class SystemFlag {

	static Properties p;
	static {
		try{
			//创建属性集合对象
			p=new Properties();
			//读取文件中数据
			FileInputStream in=new FileInputStream("flag.properties");
			//将io流数据放入properties中
			p.load(in);
			//关闭io流
			in.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public static final int LOGIN=setlogin();
	public static final int REGEDIT=setregedit();
	public static final int TALK=settalk();
	public static final String IP=setip();
	public static final int PORT=setport();
	private static int setlogin() {
		return Integer.parseInt(p.getProperty("login"));
	}
	private static int setport() {
		return Integer.parseInt(p.getProperty("port"));
	}
	private static String setip() {
		return p.getProperty("ip");
	}
	private static int settalk() {
		return Integer.parseInt(p.getProperty("talk"));
	}
	private static int setregedit() {
		return Integer.parseInt(p.getProperty("regedit"));
	}
	
		
	
	
}
