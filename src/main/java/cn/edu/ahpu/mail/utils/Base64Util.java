package cn.edu.ahpu.mail.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import sun.misc.BASE64Encoder;

public class Base64Util {
	public static void main(String[] args) throws IOException {
		BASE64Encoder encoder = new BASE64Encoder();
		System.out.println("�������û���:"); //jhuaishuang ����Ҫ@126.com
		String username = new BufferedReader(new InputStreamReader(System.in)).readLine();
		System.out.println(encoder.encode(username.getBytes())); 
		//amh1YWlzaHVhbmc=
		
		System.out.println("����������:");//Huaishuang0925
		String password = new BufferedReader(new InputStreamReader(System.in)).readLine();
		System.out.println(encoder.encode(password.getBytes()));
		//SHVhaXNodWFuZzA5MjU=
	}
}
