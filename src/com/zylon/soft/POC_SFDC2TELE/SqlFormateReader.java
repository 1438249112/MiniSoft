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

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileFilter;

public class SqlFormateReader extends JFrame implements ActionListener {
	JButton open = null;
	private static String result = "";
	private static String input = "";
	private static String resultFilePath = "";

	public static void main(String[] args) throws Exception {
		new SqlFormateReader();
	}

	public SqlFormateReader() {
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
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		jfc.setFileFilter(new FileFilter() {

			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean accept(File f) {
				System.err.println(f.getPath());
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
						+ System.currentTimeMillis() + ".soql";
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
						+ System.currentTimeMillis() + ".soql";
				System.out.println("文件:" + file.getAbsolutePath());
				dealWithFile(file);
			}
			String table_names = "";
			for (String string : tableNames) {
				table_names += string + ",";
			}
			System.err.println(table_names);
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
			String selectSql = "";
			String line = "";
			while ((line = br.readLine()) != null) {
				selectSql += " " + line;
			}
			;
			selectSql = selectSql.trim();
			System.out.println(selectSql);
			// 去掉 select
		
			fileWriter.append(selectSql+"\n......................"+selectSql.trim().substring(selectSql.trim().lastIndexOf(" "))+".............................\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}