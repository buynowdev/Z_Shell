package cn.zhaoyuening.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Stream2StringTools {
	public static String getString(InputStream in) throws IOException{
		byte[] buffer = new byte[1024];
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int len = 0;
		while((len=in.read(buffer))>0){
			baos.write(buffer, 0, len);
		}
		
		return baos.toString("utf-8");
	}
}
