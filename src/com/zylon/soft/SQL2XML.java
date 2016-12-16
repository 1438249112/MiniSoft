package com.zylon.soft;  
  
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileFilter;

  
public class SQL2XML extends JFrame implements ActionListener{  
    JButton open=null;  
    private static String result = "";
    private static String input = "";
    private static String resultFilePath = "";
    public static void main(String[] args) throws Exception {  
        new SQL2XML();  
    }  
    public SQL2XML(){  
        open=new JButton("open");  
        this.add(open);  
        this.setBounds(400, 200, 100, 100);  
        this.setVisible(true);  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        open.addActionListener(this);  
    }  
    @Override  
    public void actionPerformed(ActionEvent e) {  
        // TODO Auto-generated method stub  
        JFileChooser jfc=new JFileChooser();  
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);  
        
        jfc.setFileFilter(new FileFilter() {
			
			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean accept(File f) {
				if(f.isDirectory()){
					resultFilePath = f.getPath() + "/"+System.currentTimeMillis()+".xml";
					return true;
				}
				try {
					if(f.getName().matches("(?i).*\\.sql")){
						resultFilePath = f.getParent() + "/" + System.currentTimeMillis()+".xml";
						return true;
					}
				} catch (Exception e2) {
					return false;
				}
			
				return false;
			}
		});
        
        jfc.showDialog(new JLabel(), "选择");  
        File file=jfc.getSelectedFile();  
        try {
        	 if(file.isDirectory()){  
                 System.out.println("文件夹:"+file.getAbsolutePath());  
                ;
                 for (File f :  file.listFiles(new FilenameFilter() {
 					@Override
 					public boolean accept(File dir, String name) {
 						if(name.matches("(?i).*\\.sql")){
 							return true;
 						}
 						return false;
 					}
 				})) {
                		dealWithFile(f);
				}
             }else if(file.isFile()){  
                 System.out.println("文件:"+file.getAbsolutePath());  
                 dealWithFile(file);
             }  
        		fileWriter.flush();
        		fileWriter.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    	
          
    }
    private static FileWriter fileWriter = null;
	private void dealWithFile(File file) throws IOException {
		try {
			File resultFile = new File(resultFilePath);
			if(!resultFile.exists()){
				resultFile.createNewFile();
				fileWriter = new FileWriter(resultFile);
			}
		BufferedReader  br  =  new BufferedReader(new FileReader(file));
        String sql = "";
        String xmlString = "";
		String line = "";
		 while((line = br.readLine())!=null){
			 sql +=" "+line;
		 };
		sql =  sql.trim();
		System.out.println(sql);
		//去掉 select
		sql = sql.replaceFirst("^(?i)select\\s", "");
		System.out.println(sql);
		//获取表名
		String [] filesAtableName = sql.split("(?i)\\sfrom\\s");
		String tableName = filesAtableName[1].trim();
		System.out.println(tableName);
		//获取字段值
		String fields[] = filesAtableName[0].split(",");
		System.out.println(Arrays.toString(fields));
		String template = "<id><xsl:value-of  select=\"id\" /></id>";
		for (String filed : fields) {
			xmlString +=template.replace("id", filed.trim());
		}
		String result = "<"+tableName+">"+xmlString+"</"+tableName+">";
		System.out.println(result);
		fileWriter.append(result);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}  
  
	} 