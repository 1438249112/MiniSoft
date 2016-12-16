package com.zylon.soft.POC_SFDC2TELE;


import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

// From structure

//INSERT INTO [mds_order_st](Seq_Num, id, service_orders_so_number, vendors_name, stations_country, stations_station_id, stations_name, service_orders_brand, service_machines_model, service_machines_imei, service_machines_imei2, service_machines_sn, service_order_imei_new_imei1, service_order_imei_new_imei2, service_order_imei_new_sn, service_orders_status, service_orders_warranty_status, service_orders_parts_status_sum, service_order_register_doa, service_orders_cid, na_ntf, service_order_register_carry_in_time, service_orders_created_at, service_order_repair_start_repair_datetime, service_orders_apply_time, service_order_repair_end_repair_datetime, service_order_register_pickup_time, service_orders_so_close_time, service_orders_trans_cp_sent_datetime, service_orders_trans_cci_received_datetime, service_orders_trans_cci_sent_datetime, service_orders_trans_cp_received_datetime, service_orders_parts_in_country, service_orders_arrival_time, transaction_code, service_order_register_complaint_code, service_order_register_complaint_desc, service_order_repair_problem_code, na_problem_desc, service_order_repair_repair_code, na_repair_desc, service_order_repair_repair_type, scenarios_scenario_name, service_orders_r_tat, service_orders_t_tat, stations_state, stations_city, service_machines_mtm, service_machines_machine_type, service_order_register_sales_channel, service_order_register_carrier_name, service_order_logs_user_id_create_by, service_order_logs_user_id_repair_by, service_order_logs_user_id_close_by, service_customers_customer_name, service_customers_phone, service_customers_email, service_customers_address, service_customers_city, service_pops_purchase_date, service_pops_activation_date, key_account_key_account_name, service_orders_escalations_description_1, service_orders_escalations_status_1, service_orders_escalations_description_2, service_orders_escalations_status_2, service_orders_escalations_description_3, service_orders_escalations_status_3, defect_part1, defective_part1_category, defective_part1_name, replace_part1, replacement_part1_name, replace_part1_status, substitute_part1_in_central_hub, defect_part2, defective_part2_category, defective_part2_name, replace_part2, replacement_part2_name, replace_part2_status, substitute_part2_in_central_hub, defect_part3, defective_part3_category, defective_part3_name, replace_part3, replacement_part3_name, replace_part3_status, substitute_part3_in_central_hub, na_replacement_part_number, service_order_repair_old_version, service_order_repair_new_version, service_order_register_tracking_number, service_orders_maitrox_number, service_order_repair_lst_reference_id, Batch_Number) 
//	VALUES(0, 0, '', '', '', '', '', '', '', 0, 0, '', 0, 0, '', '', '', '', '', '', '', '2016-8-19 10:8:9', '2016-8-19 10:8:9', '2016-8-19 10:8:9', '2016-8-19 10:8:9', '2016-8-19 10:8:9', '2016-8-19 10:8:9', '2016-8-19 10:8:9', '2016-8-19 10:8:9', '2016-8-19 10:8:9', '2016-8-19 10:8:9', '2016-8-19 10:8:9', '2016-8-19 10:8:9', '2016-8-19 10:8:9', '', '', '', '', '', '', '', '', '', 0, 0, '', '', '', '', 0, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', 0, '', '', '', '', '', '')
//GO

// To structure
//<sql>INSERT INTO MDS_ORDER_ST (Seq_Num, id, service_orders_so_number, vendors_name, stations_country, stations_station_id, stations_name, service_orders_brand, service_machines_model, service_machines_imei, service_machines_imei2, service_machines_sn, service_order_imei_new_imei1, service_order_imei_new_imei2, service_order_imei_new_sn, service_orders_status, service_orders_warranty_status, service_orders_parts_status_sum, service_order_register_doa, service_orders_cid, na_ntf, service_order_register_carry_in_time, service_orders_created_at, service_order_repair_start_repair_datetime, service_orders_apply_time, service_order_repair_end_repair_datetime, service_order_register_pickup_time, service_orders_so_close_time, service_orders_trans_cp_sent_datetime, service_orders_trans_cci_received_datetime, service_orders_trans_cci_sent_datetime, service_orders_trans_cp_received_datetime, service_orders_parts_in_country, service_orders_arrival_time, transaction_code, service_order_register_complaint_code, service_order_register_complaint_desc, service_order_repair_problem_code, na_problem_desc, service_order_repair_repair_code, na_repair_desc, service_order_repair_repair_type, scenarios_scenario_name, service_orders_r_tat, service_orders_t_tat, stations_state, stations_city, service_machines_mtm, service_machines_machine_type, service_order_register_sales_channel, service_order_register_carrier_name, service_order_logs_user_id_create_by, service_order_logs_user_id_repair_by, service_order_logs_user_id_close_by, service_customers_customer_name, service_customers_phone, service_customers_email, service_customers_address, service_customers_city, service_pops_purchase_date, service_pops_activation_date, key_account_key_account_name, service_orders_escalations_description_1, service_orders_escalations_status_1, service_orders_escalations_description_2, service_orders_escalations_status_2, service_orders_escalations_description_3, service_orders_escalations_status_3, defect_part1, defective_part1_category, defective_part1_name, replace_part1, replacement_part1_name, replace_part1_status, substitute_part1_in_central_hub, defect_part2, defective_part2_category, defective_part2_name, replace_part2, replacement_part2_name, replace_part2_status, substitute_part2_in_central_hub, defect_part3, defective_part3_category, defective_part3_name, replace_part3, replacement_part3_name, replace_part3_status, substitute_part3_in_central_hub, na_replacement_part_number, service_order_repair_old_version, service_order_repair_new_version, service_order_register_tracking_number, service_orders_maitrox_number, service_order_repair_lst_reference_id, Batch_Number) 	VALUES(:Seq_Num, :id, :service_orders_so_number, :vendors_name, :stations_country, :stations_station_id, :stations_name, :service_orders_brand, :service_machines_model, :service_machines_imei, :service_machines_imei2, :service_machines_sn, :service_order_imei_new_imei1, :service_order_imei_new_imei2, :service_order_imei_new_sn, :service_orders_status, :service_orders_warranty_status, :service_orders_parts_status_sum, :service_order_register_doa, :service_orders_cid, :na_ntf, :service_order_register_carry_in_time, :service_orders_created_at, :service_order_repair_start_repair_datetime, :service_orders_apply_time, :service_order_repair_end_repair_datetime, :service_order_register_pickup_time, :service_orders_so_close_time, :service_orders_trans_cp_sent_datetime, :service_orders_trans_cci_received_datetime, :service_orders_trans_cci_sent_datetime, :service_orders_trans_cp_received_datetime, :service_orders_parts_in_country, :service_orders_arrival_time, :transaction_code, :service_order_register_complaint_code, :service_order_register_complaint_desc, :service_order_repair_problem_code, :na_problem_desc, :service_order_repair_repair_code, :na_repair_desc, :service_order_repair_repair_type, :scenarios_scenario_name, :service_orders_r_tat, :service_orders_t_tat, :stations_state, :stations_city, :service_machines_mtm, :service_machines_machine_type, :service_order_register_sales_channel, :service_order_register_carrier_name, :service_order_logs_user_id_create_by, :service_order_logs_user_id_repair_by, :service_order_logs_user_id_close_by, :service_customers_customer_name, :service_customers_phone, :service_customers_email, :service_customers_address, :service_customers_city, :service_pops_purchase_date, :service_pops_activation_date, :key_account_key_account_name, :service_orders_escalations_description_1, :service_orders_escalations_status_1, :service_orders_escalations_description_2, :service_orders_escalations_status_2, :service_orders_escalations_description_3, :service_orders_escalations_status_3, :defect_part1, :defective_part1_category, :defective_part1_name, :replace_part1, :replacement_part1_name, :replace_part1_status, :substitute_part1_in_central_hub, :defect_part2, :defective_part2_category, :defective_part2_name, :replace_part2, :replacement_part2_name, :replace_part2_status, :substitute_part2_in_central_hub, :defect_part3, :defective_part3_category, :defective_part3_name, :replace_part3, :replacement_part3_name, :replace_part3_status, :substitute_part3_in_central_hub, :na_replacement_part_number, :service_order_repair_old_version, :service_order_repair_new_version, :service_order_register_tracking_number, :service_orders_maitrox_number, :service_order_repair_lst_reference_id, :Batch_Number)</sql>
public class Insert2XML {
	
  public static void main(String[] args) throws Exception {
      //1.read from clipboard
		Clipboard  clipboard = Toolkit.getDefaultToolkit().getSystemClipboard(); //获得系统粘贴板   
	    DataFlavor	stringDataContent = DataFlavor.stringFlavor;
		Object   data = clipboard.getData(stringDataContent);
		String sql = data.toString();//.replaceAll("\\s", "");
		String tableName = deleteSpecialChar(getTableName(sql));
		ArrayList<String>/*field*/ fieldNames = getFieldNames(sql);
		String result = "no result";
		FieldProcess fieldProcess = new FieldProcess(fieldNames,args);
			result = fieldProcess.getValuesString();
		
      //2.write to clipboard
		clipboard.setContents(new StringSelection(result.toLowerCase()), null);
		

}
private static ArrayList<String> getFieldNames(String sql) {
	//fieldInfos
	ArrayList<String>/*field*/ fieldNames   = new ArrayList<String>();
	
	String fieldPart =  sql.substring(sql.indexOf("(")+1,sql.indexOf(")"));
	System.out.println("fieldPart = " + fieldPart);
	String fields[] =  fieldPart.split("\\,");
	System.out.println("fields count = " + fields.length);
	System.out.println("fields = " + Arrays.toString(fields));
	for (String field: fields) {
		fieldNames.add(deleteSpecialChar(field));
	}
	System.out.println("fieldNames size = " + fieldNames.size());
	return fieldNames;
}
private static String deleteSpecialChar(String string) {
	return string.trim().replace("[", "").replace("]", "");
}
static class FieldProcess{
    private String valuesString = "";
    private String filedsString = "";
	public FieldProcess(ArrayList<String>/*field*/ fieldNames, String[] args ) {
		
		if(args==null || args.length<1){
			for (String filed : fieldNames) {
				valuesString +=":"+filed+",";
				filedsString +=filed+",";
			}
			valuesString ="("+ valuesString.substring(0, valuesString.length()-1)+")";
			filedsString ="("+  filedsString.substring(0, filedsString.length()-1)+")";
		}else if(args[0].equalsIgnoreCase("xml")){
			
//			<id>
//			<xsl:value-of  select="id" />
//		</id>
			
			String template = "<id><xsl:value-of  select=\"id\" /></id>";
			for (String filed : fieldNames) {
				valuesString +=template.replace("id", filed);
				
			}
		}
	
	}
	public String getValuesString() {
		return valuesString;
	}
	public void setValuesString(String valuesString) {
		this.valuesString = valuesString;
	}
	public String getFiledsString() {
		return filedsString;
	}
	public void setFiledsString(String filedsString) {
		this.filedsString = filedsString;
	}
	
}
private static String getTableName(String sql) {
	String tableinfo = sql.split("\\(")[0].trim();
	String tableName = tableinfo.split("\\s")[tableinfo.split("\\s").length-1];
	System.out.println("tableName = " + tableName);
	return tableName;
}
}

