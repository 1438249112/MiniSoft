package com.zylon.soft.POC_SFDC2TELE.base;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.cache.CachingHttpClient;

/**
 *
 * 依赖 commons-httpclient-3.1.jar commons-codec-1.4.jar
 * 
 * @author tianjun
 *
 */
public class Helper {
	public static HashMap<String,HashSet<String>> results = new HashMap<String,HashSet<String>> ();
	public static HashSet<String> getFieldNamesFromSalesForceByTableName(
			String tableName) {
		 HashSet<String> result = new  HashSet<String>();
          if(results.containsKey(tableName)){
        	  return results.get(tableName);
          }
		try {
			String path = "https://soa-test.lenovo.com/esb/services/test_get_poc_struct/"
					+ tableName;
			CachingHttpClient httpClient = new CachingHttpClient();
			HttpResponse response = httpClient.execute(new HttpGet(path));
			System.out.println(Helper.class.getCanonicalName() +"  StatusCode:"+response.getStatusLine().getStatusCode());
			if(response.getStatusLine().getStatusCode()==200){
				System.out.println(Helper.class.getCanonicalName() + ":" + path);
				BufferedReader br = new BufferedReader(new InputStreamReader(
						response.getEntity().getContent()));
				String line = "";
				while ((line = br.readLine()) != null) {
					System.out
							.println(Helper.class.getCanonicalName() + ":" + line);
					result = dealWithString(line);
				}
				;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		results.put(tableName, result);
		return result;

	}

	private static HashSet<String> dealWithString(String sql)
			throws IOException {
		HashSet<String> resultSet = new HashSet<String>();
		if (!sql.matches("(?i)Select\\sfrom")) {

			sql = sql.replace("<SOQL>", "").replace("</SOQL>", "");
			String xmlString = "";

			sql = sql.trim();
			// System.out.println(sql);
			// 去掉 select
			sql = sql.replaceFirst("^(?i)select\\s", "");
			// System.out.println(sql);
			// 获取表名
			String[] filesAtableName = sql.split("(?i)\\sfrom\\s");
			System.out.println("filesAtableName:"+Arrays.toString(filesAtableName));
			String tableName = filesAtableName[1].trim();
			// System.out.println(tableName);
			// 获取字段值
			String fields[] = filesAtableName[0].split(",");
			Arrays.sort(fields);
			// System.out.println(Arrays.toString(fields));
			String template = "<xsl:value-of  select=\"id\" />";
			for (String filed : fields) {
				resultSet.add(filed.trim());
			}
		}
		return resultSet;

	}

}
