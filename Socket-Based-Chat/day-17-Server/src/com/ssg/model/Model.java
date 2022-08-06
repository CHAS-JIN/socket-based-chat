package com.ssg.model;

import java.util.ArrayList;
import java.util.List;

import com.ssg.dao.Dao;
import com.ssg.entity.Entity;
import com.ssg.entity.User;

//model层,处理业务逻辑
public class Model {

	public Entity doCmd(Entity e){
		if(e!=null){
			switch (e.getFlag()) {
			case 0:
				return login(e);
			case 1:
				return regedit(e);
			case 2:
				return e;
			}
			
		}
		return e;
	}
	//登录
	private Entity login(Entity e){
		User u=(User)e.getObj();
		List<User> ulist=Dao.getUser();//用ulist接收dao的返回值
		for (int i = 0; i < ulist.size(); i++) {
			User user=ulist.get(i);
			//user是数据库（文件）中的用户数据  u是net层接收到的用户数据
			if(user.getUsername().equals(u.getUsername())){
				if(user.getPassword().equals(u.getPassword())){
					//判断账号密码是否匹配
					e.setMessage("登录成功！");
					e.setResult(true);
					return e;
				}else{
					e.setMessage("账号密码不匹配！");
					e.setResult(false);
					return e;
				}
			}
		}
		e.setMessage("账号不存在，请先注册！");
		e.setResult(false);
		return e;
	}
	private Entity regedit(Entity e){
		User u=(User) e.getObj();
		//判断账号是否被注册
		//发送数据到dao层
		Dao.saveUser(u);
		e.setMessage("注册成功");
		e.setResult(true);
		return e;
	}
}
