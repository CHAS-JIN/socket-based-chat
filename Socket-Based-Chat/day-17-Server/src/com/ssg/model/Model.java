package com.ssg.model;

import java.util.ArrayList;
import java.util.List;

import com.ssg.dao.Dao;
import com.ssg.entity.Entity;
import com.ssg.entity.User;

//model��,����ҵ���߼�
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
	//��¼
	private Entity login(Entity e){
		User u=(User)e.getObj();
		List<User> ulist=Dao.getUser();//��ulist����dao�ķ���ֵ
		for (int i = 0; i < ulist.size(); i++) {
			User user=ulist.get(i);
			//user�����ݿ⣨�ļ����е��û�����  u��net����յ����û�����
			if(user.getUsername().equals(u.getUsername())){
				if(user.getPassword().equals(u.getPassword())){
					//�ж��˺������Ƿ�ƥ��
					e.setMessage("��¼�ɹ���");
					e.setResult(true);
					return e;
				}else{
					e.setMessage("�˺����벻ƥ�䣡");
					e.setResult(false);
					return e;
				}
			}
		}
		e.setMessage("�˺Ų����ڣ�����ע�ᣡ");
		e.setResult(false);
		return e;
	}
	private Entity regedit(Entity e){
		User u=(User) e.getObj();
		//�ж��˺��Ƿ�ע��
		//�������ݵ�dao��
		Dao.saveUser(u);
		e.setMessage("ע��ɹ�");
		e.setResult(true);
		return e;
	}
}
