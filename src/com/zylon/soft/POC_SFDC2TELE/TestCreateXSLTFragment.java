package com.zylon.soft.POC_SFDC2TELE;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileFilter;

import com.zylon.soft.POC_SFDC2TELE.base.Helper;
/**
 * 
 * @author create sql to xml
 *
 */
public class TestCreateXSLTFragment extends JFrame implements ActionListener {
	JButton open = null;
	private static String result = "";
	private static String input = "";
	private static String resultFilePath = "";

	public static void main(String[] args) throws Exception {
		new TestCreateXSLTFragment();
		
	}

	public TestCreateXSLTFragment() {
		open = new JButton("open");
		this.add(open);
		this.setBounds(400, 200, 100, 100);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		open.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//E:\lenovo-work\work\Salesforce2DB\Database_SQL
		
		JFileChooser jfc = new JFileChooser();
		jfc.setDefaultLocale(new Locale("E:/lenovo-work/work/Salesforce2DB/Database_SQL"));
		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		jfc.setFileFilter(new FileFilter() {

			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean accept(File f) {
//				System.err.println(f.getPath());
				if (f.isDirectory()) {
					
					return true;
				}
				try {
					if (f.getName().matches("(?i).*\\.sql")) {
						return true;
					}
				} catch (Exception e2) {
					return false;
				}

				return false;
			}
		});

		jfc.showDialog(new JLabel(), "选择");
		File file = jfc.getSelectedFile();
		try {
			if (file.isDirectory()) {
				resultFilePath = file.getPath() + "/"
						+ System.currentTimeMillis() + ".xml";
				System.out.println("文件夹:" + file.getAbsolutePath());
				;
				for (File f : file.listFiles(new FilenameFilter() {
					@Override
					public boolean accept(File dir, String name) {
						if (name.matches("(?i).*\\.sql")) {
							return true;
						}
						return false;
					}
				})) {
				
					dealWithFile(f);
				}
			} else if (file.isFile()) {
				resultFilePath = file.getParent() + "/"
						+ System.currentTimeMillis() + ".xml";
				System.out.println("文件:" + file.getAbsolutePath());
				dealWithFile(file);
			}
//			String table_names = "";
//			for (String string : tableNames) {
//				table_names += string + ",";
//			}
//			System.err.println(table_names);
			fileWriter.flush();
			fileWriter.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public HashSet<String> getTableNames() {
		return tableNames;
	}

	public void setTableNames(HashSet<String> tableNames) {
		this.tableNames = tableNames;
	}
	public static HashSet<String> getFieldNamesFromSalesForceByIndistinctTableName(String tableName){
		   tableName = tableName.replace("dbo.SFDC_", "").replace("_Temp", "").trim();
	       HashSet<String> resultSet = new HashSet<String>();
     
                     resultSet.addAll( Helper.getFieldNamesFromSalesForceByTableName(tableName));

       resultSet.addAll( Helper.getFieldNamesFromSalesForceByTableName(tableName+"__c"));
       return   resultSet;
}
	private HashSet<String> tableNames = new HashSet<String>();
	private static FileWriter fileWriter = null;

	private void dealWithFile(File file) throws IOException {
		try {
			File resultFile = new File(resultFilePath);
			if (!resultFile.exists()) {
				resultFile.createNewFile();
				fileWriter = new FileWriter(resultFile);
			}
			BufferedReader br = new BufferedReader(new FileReader(file));
			String createSqls = "";
			String xmlString = "";
			String line = "";
			while ((line = br.readLine()) != null) {
				createSqls += " " + line;
			}
			;
			createSqls = createSqls.trim();
			System.out.println(createSqls);
			// 去掉 select
			String[] arrayCreateSqls = createSqls.split("CREATE TABLE");
			System.out.println(createSqls);
			fileWriter.append("<results>");
			for (String creatSqlString : arrayCreateSqls) {
				try {
					fileWriter.append(getXMLString(creatSqlString));
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			fileWriter.append("</results>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private String getXMLString(String creatSqlString) {
		if(creatSqlString.contains("NULL")){
			
		creatSqlString = creatSqlString.substring(0,
				creatSqlString.lastIndexOf("NULL")).trim();
		// 获取表名
		String tableName = creatSqlString.split("\\s")[0].trim();
		System.out.println("table name = " + tableName);
		// 获取字段
		ArrayList<String>/* field */fieldNames = new ArrayList<String>();

		String fieldPart = creatSqlString.substring(
				creatSqlString.indexOf("(") + 1, creatSqlString.length())
				.trim();
	
		String fields[] = fieldPart.split("NULL\\,");
		System.out.println("fields count = " + fields.length);
//		System.out.println("fields = " + Arrays.toString(fields));
		for (String field : fields) {
			fieldNames.add(field.trim().split("\\s")[0]);
		}
		fields = fieldNames.toArray(fields);
//		System.out.println(Arrays.toString(fields));

		// 字段排序

		Arrays.sort(fields);
		// 返回结果
		
		String xmlString = "";
		String template = "<fieldNameInDB><xsl:value-of  select=\"fieldNameInSalesforce\" /></fieldNameInDB>";
		for (String filed : fields) {
			filed = filed.trim();
			String fieldNameInDB = filed.trim().toLowerCase();
			String fieldNameInSalesforce = getFieldNameInSalesforce(fieldNameInDB,tableName);
	
				xmlString += template.replace("fieldNameInDB", fieldNameInDB).replace("fieldNameInSalesforce", fieldNameInSalesforce);
			
		}
		tableName = "Insert2" + tableName.replace("dbo.", "");
		String result = "<" + tableName + ">" + xmlString + "</" + tableName
				+ ">";

		return result;
		}
		return "";
	}

	private String getFieldNameInSalesforce(String fieldNameInDB,
			String tableName) {
		//1.1获取salesforce数据结构 
        HashSet<String>    filedsInSalesforce = getFieldNamesFromSalesForceByIndistinctTableName(tableName);
		for (String filedInSalesforce : filedsInSalesforce) {
			if(filedInSalesforce.trim().equalsIgnoreCase(fieldNameInDB)){
				return filedInSalesforce.trim();
			}
			if(filedInSalesforce.trim().equalsIgnoreCase(fieldNameInDB+"__c")){
				return filedInSalesforce.trim();
			}
			if(filedInSalesforce.trim().equalsIgnoreCase(fieldNameInDB+"id")){
				return filedInSalesforce.trim();
			}
		}
		return "无匹配字段";
	}


}