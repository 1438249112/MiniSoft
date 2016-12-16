package com.zylon.soft;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

// From structure
//CREATE TABLE dbo.MDS_ORDER_ST  ( 
//		Seq_Num                                   	int NOT NULL,
//		id                                        	int NOT NULL,
//		stations_country                          	varchar(5) NOT NULL CONSTRAINT DF__MDS_ORDER__stati__5FA91635  DEFAULT (''),
//		stations_name                             	varchar(255) NOT NULL,
//		service_orders_brand                      	varchar(10) NOT NULL CONSTRAINT DF__MDS_ORDER__servi__609D3A6E  DEFAULT ((-1)),
//		service_machines_model                    	varchar(100) NOT NULL CONSTRAINT DF__MDS_ORDER__servi__61915EA7  DEFAULT (''),
//		service_machines_imei                     	bigint NOT NULL CONSTRAINT DF__MDS_ORDER__servi__628582E0  DEFAULT ((0)),
//		service_machines_imei2                    	bigint NOT NULL CONSTRAINT DF__MDS_ORDER__servi__6379A719  DEFAULT ((0)),
//		service_machines_sn                       	varchar(100) NOT NULL CONSTRAINT DF__MDS_ORDER__servi__646DCB52  DEFAULT (''),
//		service_orders_status                     	varchar(20) NOT NULL CONSTRAINT DF__MDS_ORDER__servi__683E5C36  DEFAULT ((-1)),
//		service_orders_cid                        	varchar(20) NULL,
//		na_ntf                                    	varchar(50) NOT NULL CONSTRAINT DF__MDS_ORDER__na_nt__6C0EED1A  DEFAULT (''),
//		service_order_register_carry_in_time      	datetime NULL,
//		service_orders_created_at                 	datetime NOT NULL,
//		service_order_repair_start_repair_datetime	datetime NULL,
//		service_order_repair_repair_type          	varchar(255) NOT NULL CONSTRAINT DF__MDS_ORDER__servi__72BBEAA9  DEFAULT ((-1)),
//		stations_city                             	varchar(50) NOT NULL CONSTRAINT DF__MDS_ORDER__stati__77809FC6  DEFAULT (''),
//		service_machines_mtm                      	varchar(255) NULL,
//		service_machines_machine_type             	varchar(100) NULL,
//		service_order_register_sales_channel      	smallint NOT NULL CONSTRAINT DF__MDS_ORDER__servi__7874C3FF  DEFAULT ((0)),
//		na_replacement_part_number                	int NULL,
//		Batch_Number                              	varchar(50) NOT NULL
//	)

// To structure
//<query id="insertUserInfo" useConfig="1">
//<sql>insert into userinfo values (:id,:name,:age,:sex)</sql>
//<param defaultValue="10000" name="id" ordinal="1" sqlType="STRING"/>
//<param defaultValue="defaultValue" name="name" ordinal="2" sqlType="STRING">
//  
//</param>
//<param name="age" ordinal="3" sqlType="INTEGER"/>
//<param defaultValue="male" name="sex" ordinal="4" sqlType="STRING"/>
//</query>
public class SQL2Query1 {
	private static TreeMap<String,ArrayList<String>> extraAttributesMapping = new TreeMap<String,ArrayList<String>>();
private static void insertToExtraAttributesMapping(String key,String value){
	if(extraAttributesMapping.get(key) == null){
		extraAttributesMapping.put(key, new ArrayList<String>());
	}
	extraAttributesMapping.get(key).add(value);
}
  static {
	  insertToExtraAttributesMapping("NOT NULL"," <validatePattern pattern='.+'/>");
	  insertToExtraAttributesMapping("DEFAULT"," defaultValue='10000' ");

  }
  private static void insertString2ArrayMap(String key,String value,TreeMap<String/*fieldName*/,ArrayList<String>/*attribute*/> map){
	  if(map.get(key) == null){
		  map.put(key, new ArrayList<String>());
	  }
	 System.out.println(key +"=" + value);
	  map.get(key).add(value);
  }
  static {
	  insertToExtraAttributesMapping("NOT NULL"," <validatePattern pattern='.+'/>");
	  insertToExtraAttributesMapping("DEFAULT"," defaultValue='10000' ");
	  
  }
  public static void main(String[] args) throws Exception {
      //1.read from clipboard
		Clipboard  clipboard = Toolkit.getDefaultToolkit().getSystemClipboard(); //获得系统粘贴板   
	    DataFlavor	stringDataContent = DataFlavor.stringFlavor;
		Object   data = clipboard.getData(stringDataContent);
		String sql = data.toString();//.replaceAll("\\s", "");
		String tableName = getTableName(sql);
		TreeMap<String/*fieldName*/,ArrayList<String>/*attribute*/> fieldInfos = getFieldInfos(sql);
		System.out.println(tableName);
	System.out.println("2");
}
private static TreeMap<String, ArrayList<String>> getFieldInfos(String sql) {
	//fieldInfos
	TreeMap<String/*fieldName*/,ArrayList<String>/*attribute*/> fieldInfos = new TreeMap<String,ArrayList<String>>();
	
	String fieldDefinationPart =  sql.substring(sql.indexOf("(")+1,sql.lastIndexOf(")"));
	System.out.println("fieldDefinationPart = " + fieldDefinationPart);
	String fieldDefinations[] =  fieldDefinationPart.split("\\,");
	System.out.println("fieldDefinations count = " + fieldDefinations.length);
	System.out.println("fieldDefinations = " + Arrays.toString(fieldDefinations));
	for (String fieldDefination : fieldDefinations) {
		fieldDefination = fieldDefination.trim();
		String filedName = fieldDefination.split("\\s")[0];
		
		// cut fieldDataType
		String filedInfo = (fieldDefination.length()>filedName.length()?fieldDefination.substring(filedName.length()+1):"").trim();
		String fieldDataType = filedInfo.split("\\s")[0];
		filedInfo = filedInfo.length()>fieldDataType.length()?filedInfo.substring(fieldDataType.length()+1):"";
		System.out.println("filedInfo = " + filedInfo);
		//split filed info 
		
		insertString2ArrayMap(filedName,getDefaultValue(filedInfo),fieldInfos);
		insertString2ArrayMap(filedName,isNotNUll(filedInfo),fieldInfos);
	}
	System.out.println("fieldInfos size = " + fieldInfos.size());
	return fieldInfos;
}
private static String getDefaultValue(String filedInfo) {
	// TODO Auto-generated method stub
	return null;
}
private static String isNotNUll(String filedInfo) {
	if(filedInfo.trim().replaceAll("\\s", "").toUpperCase().startsWith("NOTNULL")){
		return "NOT NULL";
	}
	return "NULL";
}
private static String getTableName(String sql) {
	String tableinfo = sql.split("\\(")[0].trim();
	String tableName = tableinfo.split("\\s")[tableinfo.split("\\s").length-1];
	System.out.println("tableName = " + tableName);
	return tableName;
}
}
