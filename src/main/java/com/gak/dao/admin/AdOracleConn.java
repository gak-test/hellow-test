package com.gak.dao.admin;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.*;
import java.util.Properties;


public class AdOracleConn {
	
	static private AdOracleConn adOracleConn = new AdOracleConn();
	
	private AdOracleConn() {
		
	}
	
	
	public static AdOracleConn getInstance() {
		return adOracleConn;
	}
		
	public Connection getConnect(){
		
		Properties prop = new Properties();
		String path = AdOracleConn.class.getResource("gak.properties").getPath();
		
		
		
		Connection conn = null;
		try {
			path = URLDecoder.decode(path,"utf-8");
			prop.load(new FileReader(path));
			Class.forName(prop.getProperty("driver"));
			conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("pw"));
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
