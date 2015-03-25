package cn.edu.ahpu.mail.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;

import org.junit.Test;

import cn.edu.ahpu.mail.demo.Demo2;

public class FileUtils {
	
	public static InputStream readFile(String filename){
		File file = new File(filename);
		try {
			InputStream input = new FileInputStream(file);
			return input;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
//		readFile(System.getProperty("user.dir")+"/src/main/resources/eml文件 发送邮件;.eml");
		
		new FileUtils().testFilePath();
	}
	
	
	@Test
	public void testFilePath(){
		
	/*	Properties props = System.getProperties();
		Iterator iterator = props.entrySet().iterator();
		while(iterator.hasNext()){
			Entry entry = (Entry) iterator.next();
			System.out.println(entry.getKey() +":"+entry.getValue());
		}
		*/
//		System.out.println(System.getProperty("user.dir"));
		System.out.println(this.getClass().getClassLoader().getResource("").getPath());
	}
}
