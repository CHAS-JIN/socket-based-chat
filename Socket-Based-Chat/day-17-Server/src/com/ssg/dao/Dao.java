package com.ssg.dao;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.ssg.entity.User;

public class Dao {

	public static List<User> getUser(){
		StringBuffer sb=new StringBuffer();
		List<User> ulist=new ArrayList<User>();
		try {
			BufferedInputStream in=new BufferedInputStream(new FileInputStream("G:\\a.txt"));
			byte []b=new byte[1024];
			int len=-1;
			while((len=in.read(b))!=-1){
				sb.append(new String(b, 0, len));
			}
			String []users=sb.toString().split("-");
			for (int i = 0; i < users.length; i++) {
				String username=users[i].split(",")[0];
				String password=users[i].split(",")[1];
				String nikename=users[i].split(",")[2];
				String sex=users[i].split(",")[3];
				String phone=users[i].split(",")[4];
				User u=new User(username, password, nikename, sex, phone);
				ulist.add(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ulist;
	}
	
	public static void saveUser(User u){
		try {
			OutputStream out=new FileOutputStream("G:\\a.txt",true);
			StringBuffer sb=new StringBuffer();
			sb.append(u.getUsername());
			sb.append(",");
			sb.append(u.getPassword());
			sb.append(",");
			sb.append(u.getNikename());
			sb.append(",");
			sb.append(u.getSex());
			sb.append(",");
			sb.append(u.getPhone());
			sb.append("-");
			out.write(sb.toString().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
