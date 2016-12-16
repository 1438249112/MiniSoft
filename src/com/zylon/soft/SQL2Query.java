package com.zylon.soft;

import java.util.ArrayList;
import java.util.HashMap;

// From structure
//CREATE TABLE dbo.MDS_ORDER_ST  ( 
//		Seq_Num                                   	int NOT NULL,
//		id                                        	int NOT NULL,
//		service_orders_so_number                  	varchar(100) NOT NULL CONSTRAINT DF__MDS_ORDER__servi__5DC0CDC3  DEFAULT (''),
//		vendors_name                              	varchar(100) NOT NULL CONSTRAINT DF__MDS_ORDER__vendo__5EB4F1FC  DEFAULT (''),
//		stations_country                          	varchar(5) NOT NULL CONSTRAINT DF__MDS_ORDER__stati__5FA91635  DEFAULT (''),
//		stations_station_id                       	varchar(100) NOT NULL,
//		stations_name                             	varchar(255) NOT NULL,
//		service_orders_brand                      	varchar(10) NOT NULL CONSTRAINT DF__MDS_ORDER__servi__609D3A6E  DEFAULT ((-1)),
//		service_machines_model                    	varchar(100) NOT NULL CONSTRAINT DF__MDS_ORDER__servi__61915EA7  DEFAULT (''),
//		service_machines_imei                     	bigint NOT NULL CONSTRAINT DF__MDS_ORDER__servi__628582E0  DEFAULT ((0)),
//		service_machines_imei2                    	bigint NOT NULL CONSTRAINT DF__MDS_ORDER__servi__6379A719  DEFAULT ((0)),
//		service_machines_sn                       	varchar(100) NOT NULL CONSTRAINT DF__MDS_ORDER__servi__646DCB52  DEFAULT (''),
//		service_order_imei_new_imei1              	bigint NOT NULL CONSTRAINT DF__MDS_ORDER__servi__6561EF8B  DEFAULT ((0)),
//		service_order_imei_new_imei2              	bigint NOT NULL CONSTRAINT DF__MDS_ORDER__servi__665613C4  DEFAULT ((0)),
//		service_order_imei_new_sn                 	varchar(100) NOT NULL CONSTRAINT DF__MDS_ORDER__servi__674A37FD  DEFAULT (''),
//		service_orders_status                     	varchar(20) NOT NULL CONSTRAINT DF__MDS_ORDER__servi__683E5C36  DEFAULT ((-1)),
//		service_orders_cid                        	varchar(20) NULL,
//		na_ntf                                    	varchar(50) NOT NULL CONSTRAINT DF__MDS_ORDER__na_nt__6C0EED1A  DEFAULT (''),
//		service_order_register_carry_in_time      	datetime NULL,
//		service_orders_created_at                 	datetime NOT NULL,
//		service_order_repair_start_repair_datetime	datetime NULL,
//		transaction_code                          	varchar(50) NULL,
//		service_order_register_complaint_code     	varchar(255) NOT NULL CONSTRAINT DF__MDS_ORDER__servi__6D031153  DEFAULT (''),
//		service_order_register_complaint_desc     	varchar(500) NOT NULL CONSTRAINT DF__MDS_ORDER__servi__6DF7358C  DEFAULT (''),
//		service_order_repair_problem_code         	varchar(255) NOT NULL CONSTRAINT DF__MDS_ORDER__servi__6EEB59C5  DEFAULT (''),
//		na_problem_desc                           	varchar(500) NOT NULL CONSTRAINT DF__MDS_ORDER__na_pr__6FDF7DFE  DEFAULT (''),
//		service_order_repair_repair_code          	varchar(255) NOT NULL CONSTRAINT DF__MDS_ORDER__servi__70D3A237  DEFAULT (''),
//		na_repair_desc                            	varchar(500) NOT NULL CONSTRAINT DF__MDS_ORDER__na_re__71C7C670  DEFAULT (''),
//		service_order_repair_repair_type          	varchar(255) NOT NULL CONSTRAINT DF__MDS_ORDER__servi__72BBEAA9  DEFAULT ((-1)),
//		scenarios_scenario_name                   	varchar(255) NOT NULL CONSTRAINT DF__MDS_ORDER__scena__73B00EE2  DEFAULT (''),
//		service_orders_r_tat                      	int NOT NULL CONSTRAINT DF__MDS_ORDER__servi__74A4331B  DEFAULT ((-1)),
//		service_orders_t_tat                      	int NOT NULL CONSTRAINT DF__MDS_ORDER__servi__75985754  DEFAULT ((-1)),
//		stations_state                            	varchar(100) NOT NULL CONSTRAINT DF__MDS_ORDER__stati__768C7B8D  DEFAULT (''),
//		stations_city                             	varchar(50) NOT NULL CONSTRAINT DF__MDS_ORDER__stati__77809FC6  DEFAULT (''),
//		service_machines_mtm                      	varchar(255) NULL,
//		service_machines_machine_type             	varchar(100) NULL,
//		service_order_register_sales_channel      	smallint NOT NULL CONSTRAINT DF__MDS_ORDER__servi__7874C3FF  DEFAULT ((0)),
//		service_order_register_carrier_name       	varchar(100) NOT NULL CONSTRAINT DF__MDS_ORDER__servi__7968E838  DEFAULT (''),
//		na_replacement_part_number                	int NULL,
//		service_order_repair_old_version          	varchar(255) NULL,
//		service_order_repair_new_version          	varchar(255) NULL,
//		service_order_register_tracking_number    	varchar(255) NOT NULL CONSTRAINT DF__MDS_ORDER__servi__7A5D0C71  DEFAULT (''),
//		service_orders_maitrox_number             	varchar(100) NOT NULL CONSTRAINT DF__MDS_ORDER__servi__7B5130AA  DEFAULT (''),
//		service_order_repair_lst_reference_id     	varchar(5) NULL,
//		Batch_Number                              	varchar(50) NOT NULL,
//		CONSTRAINT PK_MDS_ORDER_ST PRIMARY KEY CLUSTERED(Seq_Num,Batch_Number)
//	)

// To structure
//<query id="insertUserInfo" useConfig="1">
//<sql>insert into userinfo values (:id,:name,:age,:sex)</sql>
//<param defaultValue="10000" name="id" ordinal="1" sqlType="STRING"/>
//<param defaultValue="defaultValue" name="name" ordinal="2" sqlType="STRING">
//   <validatePattern pattern=".+"/>
//</param>
//<param name="age" ordinal="3" sqlType="INTEGER"/>
//<param defaultValue="male" name="sex" ordinal="4" sqlType="STRING"/>
//</query>
public class SQL2Query {
	private static HashMap<String,ArrayList<String>> dataTypeMapping = new HashMap<String,ArrayList<String>>();
	
//	<option selected="selected" value="STRING">STRING</option>
//	<option value="INTEGER">INTEGER</option>
//	<option value="REAL">REAL</option>
//	<option value="DOUBLE">DOUBLE</option>
//	<option value="NUMERIC">NUMERIC</option>
//	<option value="TINYINT">TINYINT</option>
//	<option value="SMALLINT">SMALLINT</option>
//	<option value="BIGINT">BIGINT</option>
//	<option value="DATE">DATE[yyyy-mm-dd]</option>
//	<option value="TIME">TIME[hh:mm:ss]</option>
//	<option value="TIMESTAMP">TIMESTAMP</option>
//	<option value="BIT">BIT</option>
//	<option value="ORACLE_REF_CURSOR">ORACLE REF CURSOR</option>
//	<option value="BINARY">BINARY</option>
//	<option value="BLOB">BLOB</option>
//	<option value="CLOB">CLOB</option>
//	<option value="STRUCT">STRUCT</option>
//	<option value="ARRAY">ARRAY</option>
//	<option value="UUID">UUID</option>
//	<option value="VARINT">VARINT</option>
//	<option value="INETADDRESS">INETADDRESS</option>
//	<option value="QUERY_STRING">QUERY_STRING</option>
private static void insertToDataTypeMapping(String key,String value){
	if(dataTypeMapping.get(key) == null){
		dataTypeMapping.put(key, new ArrayList<String>());
	}
	dataTypeMapping.get(key).add(value);
}
  static {
	  insertToDataTypeMapping("STRING","varchar");
	  insertToDataTypeMapping("INTEGER","int");
	  insertToDataTypeMapping("REAL","");
	  insertToDataTypeMapping("DOUBLE","");
	  insertToDataTypeMapping("NUMERIC","");
	  insertToDataTypeMapping("TINYINT","");
	  insertToDataTypeMapping("SMALLINT","smallint");
	  insertToDataTypeMapping("BIGINT","bigint");
	  insertToDataTypeMapping("DATE[yyyy-mm-dd]","");
	  insertToDataTypeMapping("TIME[hh:mm:ss]","");
	  insertToDataTypeMapping("TIMESTAMP","");
	  insertToDataTypeMapping("BIT","");
	  insertToDataTypeMapping("ORACLE","");
	  insertToDataTypeMapping("REF","");
	  insertToDataTypeMapping("CURSOR","");
	  insertToDataTypeMapping("BINARY","");
	  insertToDataTypeMapping("BLOB","");
	  insertToDataTypeMapping("CLOB","");
	  insertToDataTypeMapping("STRUCT","");
	  insertToDataTypeMapping("ARRAY","");
	  insertToDataTypeMapping("UUID","");
	  insertToDataTypeMapping("VARINT","");
	  insertToDataTypeMapping("INETADDRESS","");
	  insertToDataTypeMapping("QUERY_STRING","");
	  
	String  QureyTypes =  "INTEGER REAL DOUBLE NUMERIC TINYINT SMALLINT BIGINT DATE[yyyy-mm-dd] TIME[hh:mm:ss] TIMESTAMP BIT ORACLE REF CURSOR BINARY BLOB CLOB STRUCT ARRAY UUID VARINT INETADDRESS QUERY_STRING";
	String  QureyType[] = QureyTypes.split("\\s");
	for (int i = 0; i < QureyType.length; i++) {
		System.out.println("insertToDataTypeMapping(\""+QureyType[i].trim()+"\",\"\");");
	}
  }
  public static void main(String[] args) {
	System.out.println("2");
}
}
