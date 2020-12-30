package com.lza.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtil {

	public static void main(String[] args) {
		File s = new File("C:\\Users\\Hunter\\Pictures\\Saved Pictures\\123.jpg");
		File t = new File("D:\\TEMP\\a.jpg");
		copy(s,t);
	}
	
	
	public static void copy(File s, File t) {
		InputStream fis = null;
		OutputStream fos = null;
		try {
			fis = new FileInputStream(s);
			fos = new FileOutputStream(t);
			byte[] buf = new byte[1024];
			int i=0;
			while((i=fis.read(buf))!=-1) {
//				System.out.println(i);
				fos.write(buf, 0, i);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(fis!=null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fos!=null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
