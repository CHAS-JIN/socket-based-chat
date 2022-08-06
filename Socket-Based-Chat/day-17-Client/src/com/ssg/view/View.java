package com.ssg.view;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ResourceBundle.Control;
import java.util.Scanner;

import com.ssg.controller.Controller;
import com.ssg.entity.Entity;
import com.ssg.entity.User;
import com.ssg.net.Net;
import com.ssg.util.SystemFlag;

public class View {
	Scanner s;
	Entity e;
	Controller c;
	public View(){
		//构造方法可以进行初始化
		s=new Scanner(System.in);
		e=new Entity();
		c=new Controller();
	}
	//在View中创建登录方法
	public void login(){
		System.out.println("登录账户");
		System.out.print("账号：");
		String username=s.next();
		System.out.print("密码：");
		String password=s.next();
		//将账号密码封装在User中
		User u=new User(username, password);
		//对象u放入大袋子Entity中
		e.setObj(u);
		e.setFlag(0);//设置状态，0为登录
		c.send(e);
		
	}
	
	//注册
	public void regedit(){
		System.out.println("账户注册");
		System.out.println("账号：");
		String username=s.next();
		System.out.println("密码：");
		String password=s.next();
		System.out.println("昵称：");
		String nikename=s.next();
		System.out.println("性别：");
		String sex=s.next();
		System.out.println("手机：");
		String phone=s.next();
		User u=new User(username, password, nikename, sex, phone);
		e.setObj(u);
		e.setFlag(1);//1为注册
		c.send(e);
	}
	/**
	 * 聊天主界面
	 */
	public void main() {
		
		System.out.print("请发言:");
		String str=s.next();
		e.setMessage(str);
		e.setFlag(2);//2为聊天
		c.send(e);
		
	}
	
	
	public void choose(){
		System.out.println("1-登录");
		System.out.println("2-注册");
		System.out.println("0-退出");
		int i=s.nextInt();
		if(i==1){
			login();
		}else if(i==2){
			regedit();
		}else if(i==0){
			System.exit(0);
		}
	}
}
