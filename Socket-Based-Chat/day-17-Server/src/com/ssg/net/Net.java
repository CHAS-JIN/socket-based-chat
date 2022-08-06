package com.ssg.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.ssg.entity.Entity;
import com.ssg.model.Model;

public class Net {

	List<A> list = new ArrayList<A>();

	public void connection() {
		System.out.println("服务器开启");
		try {
			// 等待客户端连接
			ServerSocket ss = new ServerSocket(6625);
			while (true) {
				final Socket socket = ss.accept();
				// 当客户端连接后，接受数据e
				final ObjectInputStream in = new ObjectInputStream(
						socket.getInputStream());
				ObjectOutputStream out = new ObjectOutputStream(
						socket.getOutputStream());

				list.add(new A(out, socket.getInetAddress().toString()));

				new Thread() {
					public void run() {
						while (true) {
							try {
								Entity e = (Entity) in.readObject();
								// 将接受的"大袋子"传递到model层
								Model m = new Model();
								// model层处理
								e.setIp(socket.getInetAddress().toString());
								e = m.doCmd(e);
								sendMsg(e);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
					}
				}.start();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

	private void sendMsg(Entity e) {
		if (e.getFlag() == 0 || e.getFlag() == 1) {

			System.out.println("发送单个");
			// 发单个 可以根据绑定的ip地址 或者账号发送
			for (int i = 0; i < list.size(); i++) {
				ObjectOutputStream out = list.get(i).out;
				try {
					if (list.get(i).ip.equals(e.getIp())) {
						out.writeObject(e);
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} else {
			// 发多个
			// 每个客户端的连接 都要发送数据

			System.out.println("发送多个");
			for (int i = 0; i < list.size(); i++) {
				ObjectOutputStream out = list.get(i).out;
				try {
					out.writeObject(e);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	};
}

class A {
	ObjectOutputStream out;
	String ip;

	public A(ObjectOutputStream out, String ip) {
		this.out = out;
		this.ip = ip;
	}

}