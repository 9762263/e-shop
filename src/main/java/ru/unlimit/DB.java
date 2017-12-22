package ru.unlimit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
	private Connection cn;
	private Statement st;
	public Connection getCn(){
		return cn;
	}
	
	public DB(String url,String user,String password) throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		cn=(Connection)DriverManager.getConnection(url,user,password);
		st=(Statement)cn.createStatement();
	}

}
