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

public class CLICKHEART {
 public static void main(String[] args) {

		 try {
			CLICKHEART.http("http://s1.eqxiu.com/eqs/scene/counter", "sceneId=65943035&fieldId=66241726163");
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
		 for (int i = 0; i < 1000; i++) {
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
			 // 读取返回内容  
	        StringBuffer buffer = new StringBuffer();  
	        try {  
	            //一定要有返回值，否则无法把请求发送给server端。  
	            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));  
	            String temp;  
	            while ((temp = br.readLine()) != null) {  
	                buffer.append(temp);  
	                buffer.append("\n");  
	            }  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
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