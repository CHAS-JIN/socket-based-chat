package com.ssg.util;

import java.io.FileInputStream;
import java.util.Properties;

public class SystemFlag {

	static Properties p;
	static {
		try{
			//�������Լ��϶���
			p=new Properties();
			//��ȡ�ļ�������
			FileInputStream in=new FileInputStream("flag.properties");
			//��io�����ݷ���properties��
			p.load(in);
			//�ر�io��
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
