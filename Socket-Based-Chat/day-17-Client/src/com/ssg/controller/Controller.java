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
			//��¼
			if(e.isResult()){
				//��½�ɹ���������������
				System.out.println("��¼�ɹ� ��ʼ����");
				new View().main();
			}else{
				//��¼ʧ�����µ�½
				System.out.println("�����������˺�����");
				new View().login();
			}
		}
		if(e.getFlag()==SystemFlag.REGEDIT){
			//ע��
			if(e.isResult()){
				//ע��ɹ����е�¼
				new View().login();
			}else{
				System.out.println("������ע��");
				new View().regedit();
			}
		}
		if(e.getFlag()==SystemFlag.TALK){
			//����
			new View().main();
		}
	}
}
