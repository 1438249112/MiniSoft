package com.zylon.soft;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.cache.CachingHttpClient;

/**
 *
 * “¿¿µ commons-httpclient-3.1.jar commons-codec-1.4.jar
 * 
 * @author tianjun
 *
 */
public class PostTest {

	public static void main(String[] args)

	{

		CachingHttpClient httpClient = new CachingHttpClient();
		try {
			HttpResponse response= httpClient.execute(new HttpGet("https://soa-test.lenovo.com/esb/services/test_get_poc_struct/CustomerBP__c"));
			BufferedReader  br  =  new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			 while((line = br.readLine())!=null){
				 System.out.println(line);
			 };
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
