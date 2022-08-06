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
		//���췽�����Խ��г�ʼ��
		s=new Scanner(System.in);
		e=new Entity();
		c=new Controller();
	}
	//��View�д�����¼����
	public void login(){
		System.out.println("��¼�˻�");
		System.out.print("�˺ţ�");
		String username=s.next();
		System.out.print("���룺");
		String password=s.next();
		//���˺������װ��User��
		User u=new User(username, password);
		//����u��������Entity��
		e.setObj(u);
		e.setFlag(0);//����״̬��0Ϊ��¼
		c.send(e);
		
	}
	
	//ע��
	public void regedit(){
		System.out.println("�˻�ע��");
		System.out.println("�˺ţ�");
		String username=s.next();
		System.out.println("���룺");
		String password=s.next();
		System.out.println("�ǳƣ�");
		String nikename=s.next();
		System.out.println("�Ա�");
		String sex=s.next();
		System.out.println("�ֻ���");
		String phone=s.next();
		User u=new User(username, password, nikename, sex, phone);
		e.setObj(u);
		e.setFlag(1);//1Ϊע��
		c.send(e);
	}
	/**
	 * ����������
	 */
	public void main() {
		
		System.out.print("�뷢��:");
		String str=s.next();
		e.setMessage(str);
		e.setFlag(2);//2Ϊ����
		c.send(e);
		
	}
	
	
	public void choose(){
		System.out.println("1-��¼");
		System.out.println("2-ע��");
		System.out.println("0-�˳�");
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
