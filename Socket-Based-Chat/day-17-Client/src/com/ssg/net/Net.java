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
	 * 单例设计模式，一个类只有一个对象实例
	 * @return
	 */
	public static Net createNet(){
		//创建网络
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
		//新开一个线程接收数据
		new Thread(){
			public void run(){
				try {
					while(true){
						Entity e=(Entity) in.readObject();
						//controller层处理数据
						new Controller().action(e);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
	public void send(final Entity e){
		//将e发送到服务器
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
