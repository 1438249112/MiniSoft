package com.zylon.soft.POC_SFDC2TELE;

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
public class GetTableStruct {
	    private static FileWriter fileWriter = null;
	   private static String resultFilePath = "E:/lenovo-work/work/Salesforce2DB/Salesfore_Tables_Structure/Tables_Structure.xml";
	public static void main(String[] args) throws IOException
	

	{   fileWriter = new FileWriter(resultFilePath);
	    fileWriter.write("");
		String tableNames = "WeChatQQMSN__c,LinkmanFamliylink__c,LinkManRelative__c,"
				+ "LinkmanIDnumber__c,CustomerOfficeLink__c,CustomerBP__c,"
				+ "Contact,Account,CustomerRAD__c,CustomerHasIT__c,Topcustomer__c";
		CachingHttpClient httpClient = new CachingHttpClient();
		for (String tableName : tableNames.split(",")) {
			try {
				HttpResponse response = httpClient.execute(new HttpGet(
						"https://soa-test.lenovo.com/esb/services/test_get_poc_struct/"
								+ tableName));
				BufferedReader br = new BufferedReader(new InputStreamReader(
						response.getEntity().getContent()));
				String line = "";
				while ((line = br.readLine()) != null) {
					dealWithString(line);
				}
				;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		fileWriter.flush();
		fileWriter.close();
	}

	private static void dealWithString(String sql) throws IOException {

		sql = sql.replace("<SOQL>", "").replace("</SOQL>", "");
		String xmlString = "";

		sql = sql.trim();
//		System.out.println(sql);
		// 去掉 select
		sql = sql.replaceFirst("^(?i)select\\s", "");
//		System.out.println(sql);
		// 获取表名
		String[] filesAtableName = sql.split("(?i)\\sfrom\\s");
		String tableName = filesAtableName[1].trim();
//		System.out.println(tableName);
		// 获取字段值
		String fields[] = filesAtableName[0].split(",");
		Arrays.sort(fields);
//		System.out.println(Arrays.toString(fields));
		String template = "<xsl:value-of  select=\"id\" />";
		for (String filed : fields) {
			xmlString += template.replace("id", filed.trim());
		}
		String result = "<" + tableName + ">" + xmlString + "</" + tableName
				+ ">";
		fileWriter.append(result);
		System.out.println(result);

	}

}
