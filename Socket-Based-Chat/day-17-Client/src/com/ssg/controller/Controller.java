package com.ssg.controller;

import com.ssg.entity.Entity;
import com.ssg.net.Net;
import com.ssg.util.SystemFlag;
import com.ssg.view.View;


public class Controller {

	Net n;
	public Controller(){
		n=Net.createNet();
	}
	public void send(Entity e){
		n.send(e);
	}
	public void action(Entity e){
		System.out.println(e.getMessage());
		if(e.getFlag()==SystemFlag.LOGIN){
			//登录
			if(e.isResult()){
				//登陆成功进入聊天主界面
				System.out.println("登录成功 开始聊天");
				new View().main();
			}else{
				//登录失败重新登陆
				System.out.println("请重新输入账号密码");
				new View().login();
			}
		}
		if(e.getFlag()==SystemFlag.REGEDIT){
			//注册
			if(e.isResult()){
				//注册成功进行登录
				new View().login();
			}else{
				System.out.println("请重新注册");
				new View().regedit();
			}
		}
		if(e.getFlag()==SystemFlag.TALK){
			//聊天
			new View().main();
		}
	}
}
