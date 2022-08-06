package com.ssg.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.ssg.controller.Controller;
import com.ssg.entity.Entity;
import com.ssg.util.SystemFlag;
import com.ssg.view.View;

public class Net {

	private static Net net;
	/**
	 * �������ģʽ��һ����ֻ��һ������ʵ��
	 * @return
	 */
	public static Net createNet(){
		//��������
		if(net==null){
			net=new Net();
		}
		return net;
	}
	Socket socket;
	ObjectOutputStream out;
	ObjectInputStream in;
	private Net(){
		try {
			socket=new Socket(SystemFlag.IP,SystemFlag.PORT);
			out=new ObjectOutputStream(socket.getOutputStream());
			in=new ObjectInputStream(socket.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		//�¿�һ���߳̽�������
		new Thread(){
			public void run(){
				try {
					while(true){
						Entity e=(Entity) in.readObject();
						//controller�㴦������
						new Controller().action(e);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
	public void send(final Entity e){
		//��e���͵�������
		new Thread(){
			public void run() {
				try {
					out.writeObject(e);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}.start();
		
	}
}
