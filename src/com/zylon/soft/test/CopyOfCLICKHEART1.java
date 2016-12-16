package com.zylon.soft.test;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class CopyOfCLICKHEART1 {
 public static void main(String[] args) {

		 try {
			CopyOfCLICKHEART1.http("http://s1.eqxiu.com/eqs/scene/counter", "sceneId=65943035&fieldId=66241363459");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


}
	public static void http(String url,String params) throws Exception {
		URL u =  new URL(url);
		HttpURLConnection con = null;
		// 构建请求参数
	
		System.out.println("send_url:" + url);
		System.out.println("send_data:" + params);
		 for (int i = 0; i < 600; i++) {
		// 尝试发送请求
		try {
			
			
				 System.out.println("times = " + i);
			con = (HttpURLConnection) u.openConnection();
			//// POST 只能为大写，严格限制，post会不识别
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			
				 
			OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
			osw.write(params);
			osw.flush();
			osw.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
		 }
	}
}