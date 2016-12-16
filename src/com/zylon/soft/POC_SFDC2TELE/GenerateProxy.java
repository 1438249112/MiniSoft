package com.zylon.soft.POC_SFDC2TELE;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JButton;

public class GenerateProxy  {
	JButton open = null;
	private static String basePath = "E:/lenovo-work/work/Salesforce2DB/POC_SFDC2TELE";

	public static void main(String[] args) throws Exception {
		//1.处理sql语句
		File sqlFiles = new File(basePath+"/Salesforce_SQL");
	
		       HashMap<String,String>  sqlMap = new 	HashMap<String,String>();
				System.out.println("文件夹:" + sqlFiles.getAbsolutePath());
				
				for (File f : sqlFiles.listFiles(new FilenameFilter() {
					@Override
					public boolean accept(File dir, String name) {
						if (name.matches("(?i).*\\.sql")) {
							return true;
						}
						return false;
					}
				})) {
				
					putInSqlMap(sqlMap,f);
				}	
				System.out.println(sqlMap);
	     //2.处理proxy模板
				//2.1.获取模板文件
				File templateFile = new File(basePath+"/SFDC2TELE_Account.xml");
				//2.2.读取模板文件
				BufferedReader br = new BufferedReader(new FileReader(templateFile));
				String templateString = "";
				String line = "";
				while ((line = br.readLine()) != null) {
					templateString += " " + line;
				}
				;
				templateString = templateString.trim();
		 //3.根据sql生成proxy
				for (Entry<String, String> tableNameAndSql : sqlMap.entrySet()) {
					String templateStringCopy = templateString;
					String SalesforceTableName = tableNameAndSql.getKey();
					File resultFile = new File(basePath+"/Proxy/SFDC2TELE_"+SalesforceTableName+".xml");
					if (resultFile.exists()) {
						resultFile.delete();
						resultFile.createNewFile();
					}
					FileWriter fileWriter = new FileWriter(resultFile);
					templateStringCopy = templateStringCopy.replace("Account", SalesforceTableName);
//					templateStringCopy = templateStringCopy.replace("POC_SFDC2TELE_", "SFDC2TELE_");
					
				    String basicSoql = " <property name=\"soql.var.basic\" value=\""+tableNameAndSql.getValue()+"\"/>"	;
					
				    String[] newXmlStrings = templateStringCopy.split("soql\\.var\\.basic",2);
				    String newXmlString1 = newXmlStrings[0].substring(0,newXmlStrings[0].lastIndexOf("<"));
				    String newXmlString2 = newXmlStrings[1].substring(newXmlStrings[1].indexOf(">")+1,newXmlStrings[1].length());
//					 <property name="soql.var.basic"
//			                   value="Select id, CDBID__c, DTcount__c, DTbrand__c, NBcount__c, NBbrand__c, PCcount__c, lastmodifieddate, StateEnable__c, TempID__c, ServerBrand__c, ServerCount__c, OutEquipBrand__c, OutEquipCount__c, Update_State__c, CustomerName__c, CreatedById, LastModifiedById, IsSych__c, CreatedDate from CustomerHasIT__c"/>？
					fileWriter.append(newXmlString1+basicSoql+newXmlString2);
					fileWriter.flush();
					fileWriter.close();			
				}
			
	}

	private static void putInSqlMap(HashMap<String, String> sqlMap, File file) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String selectSql = "";
		String line = "";
		while ((line = br.readLine()) != null) {
			selectSql += " " + line;
		}
		selectSql = selectSql.trim();
		
		String tableName = selectSql.trim().substring(selectSql.trim().lastIndexOf(" ")).trim();
		if(tableName.endsWith("__c")){
			tableName = tableName.substring(0,tableName.length()-3);
		}
		sqlMap.put(tableName, selectSql);
	}




	
}