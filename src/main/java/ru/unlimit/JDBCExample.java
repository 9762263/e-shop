package ru.unlimit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCExample {
	
	  public void insertRadiator(String name,double price,int count,double sum,int idsf) {
	        try {
	            Class.forName("com.mysql.jdbc.Driver");// загружаем драйвер
	            String url = "jdbc:mysql://localhost:3306/RADIATORS";
	            String login = "root";
	            String password = "";
	            Connection con = DriverManager.getConnection(url, login, password); //создаём коннекшн
	            try {
	                Statement stmt = con.createStatement();// стэйтмент для запросов вставки и редактирования
	                stmt.executeUpdate( "INSERT INTO lkaa (name,price,count,sum,idsf) VALUES ('"+name+"',"+price+","+count+","+sum+","+idsf+")");
	                
	                stmt.close();
	            } finally {
	                con.close();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	  public void insertBuyer(String fio,String email,String telno,String adres,String passport,String idsf) {
	        try {
	            Class.forName("com.mysql.jdbc.Driver");// загружаем драйвер
	            String url = "jdbc:mysql://localhost:3306/RADIATORS";
	            String login = "root";
	            String password = "";
	            Connection con = DriverManager.getConnection(url, login, password); //создаём коннекшн
	            try {
	                Statement stmt = con.createStatement();// стэйтмент для запросов вставки и редактирования
	                stmt.executeUpdate( "INSERT INTO buyers (fio,email,telno,adres,passport,idsf) VALUES"
	                		+ "('"+fio+"','"+email+"','"+telno+"','"+adres+"','"+passport+"','"+idsf+"')");
	 
	                stmt.close();
	            } finally {
	                con.close();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	  
	  
	  public double  getPrice(String size)  {
		  double price=0;
		    try {
		  		
	            Class.forName("com.mysql.jdbc.Driver");
	            String url = "jdbc:mysql://localhost:3306/RADIATORS";
	            String login = "root";
	            String password = "";
	            Connection con = DriverManager.getConnection(url, login, password); 

	            try {
	                Statement stmt = con.createStatement();
	                ResultSet rs = stmt.executeQuery("SELECT price FROM catalog WHERE name='lk 10-"+size+"'");
	                while (rs.next()) {
	                 price = rs.getDouble("price");
	                }
	                stmt.close();
	            } finally {
	                con.close();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		  
	             
	        return price;
		    
	        }
	  
	  public void insertUser(String name,String email,String phone,String adres,String passport,String parol) {
	        try {
	            Class.forName("com.mysql.jdbc.Driver");// загружаем драйвер
	            String url = "jdbc:mysql://localhost:3306/RADIATORS";
	            String login = "root";
	            String password = "";
	            Connection con = DriverManager.getConnection(url, login, password); //создаём коннекшн
	            try {
	                Statement stmt = con.createStatement();// стэйтмент для запросов вставки и редактирования
	                stmt.executeUpdate( "INSERT INTO users (name,email,phone,adres,passport,parol) VALUES ('"
	                +name+"','"+email+"','"+phone+"','"+adres+"','"+passport+"','"+parol+"')");
	                
	                stmt.close();
	            } finally {
	                con.close();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	  
	  public User  getDataUser(String email)  {
		  User user=new User();
		    try {
		  		
	            Class.forName("com.mysql.jdbc.Driver");
	            String url = "jdbc:mysql://localhost:3306/RADIATORS";
	            String login = "root";
	            String password = "";
	            Connection con = DriverManager.getConnection(url, login, password); 

	            try {
	                Statement stmt = con.createStatement();
	                ResultSet rs = stmt.executeQuery("SELECT name,phone FROM users WHERE email='"+email+"'");
	                while (rs.next()) {
	                String name = rs.getString("name");
	                String phone = rs.getString("phone");
	                user.name=name;
	                user.phone=phone;
	                }
	                
	                stmt.close();
	            } finally {
	                con.close();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		  
	             
	        return user;
		    
	        }
	  
	  public List<Integer>  getOrdersUser(String email)  {
		  List<Integer> orders = new ArrayList<Integer>();
		    try {
		  		
	            Class.forName("com.mysql.jdbc.Driver");
	            String url = "jdbc:mysql://localhost:3306/RADIATORS";
	            String login = "root";
	            String password = "";
	            Connection con = DriverManager.getConnection(url, login, password); 

	            try {
	                Statement stmt = con.createStatement();
	                ResultSet rs = stmt.executeQuery("SELECT idsf FROM buyers WHERE email='"+email+"'");
	                while (rs.next()) {
	                int idsf = rs.getInt("idsf");
	                orders.add(idsf);
	                }
	                
	                stmt.close();
	            } finally {
	                con.close();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		  
	             
	        return orders;
		    
	        }
	  
	  public int  getLastidSF()  {
		  int idSF = 0;
		    try {
		  		
	            Class.forName("com.mysql.jdbc.Driver");
	            String url = "jdbc:mysql://localhost:3306/RADIATORS";
	            String login = "root";
	            String password = "";
	            Connection con = DriverManager.getConnection(url, login, password); 

	            try {
	                Statement stmt = con.createStatement();
	                ResultSet rs = stmt.executeQuery("SELECT MAX(idsf) FROM buyers ");
	                while (rs.next()) {
	                idSF = rs.getInt(1);
	                }
	                
	                stmt.close();
	            } finally {
	                con.close();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		  
	             
	        return idSF;
		    
	        }
	  
	  public List<Radiator>  getCatalog()  {
		  List<Radiator> list = new ArrayList<Radiator>();
		    try {
		  		
	            Class.forName("com.mysql.jdbc.Driver");
	            String url = "jdbc:mysql://localhost:3306/RADIATORS";
	            String login = "root";
	            String password = "";
	            Connection con = DriverManager.getConnection(url, login, password); 

	            try {
	                Statement stmt = con.createStatement();
	                ResultSet rs = stmt.executeQuery("SELECT name,price FROM catalog ");
	                while (rs.next()) {
	                Radiator rad = new Radiator();
	                rad.setType(rs.getString("name"));
	                rad.setPrice(rs.getDouble("price"));
	                list.add(rad);
	                }
	                
	                stmt.close();
	            } finally {
	                con.close();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		  
	             
	        return list;
		    
	        }
}
