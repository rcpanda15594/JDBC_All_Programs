package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlAppTest {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		boolean isRSEmpty=true;
	  try{
		  //register JDBC driver
		 // Class.forName("com.mysql.jdbc.Driver");
		  Class.forName("org.gjt.mm.mysql.Driver");
		  //establish the connection
		  //con=DriverManager.getConnection("jdbc:mysql:///NTAJ991DB","root","root");
		  con=DriverManager.getConnection("jdbc:mysql://localhost:3306/NTAJ991DB1","root","root");
		  //create Statement object
		  if(con!=null)
			  st=con.createStatement();
		  //send and execute SQL Query in DB s/w
		  if(st!=null)
			  rs=st.executeQuery("SELECT  PID,PNAME,PRICE FROM PRODUCT");
		  //gather and process the results
		  if(rs!=null){
			  while(rs.next()){
				  isRSEmpty=false;
				  System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getFloat(3));
			  }
		  }
		  
		  if(isRSEmpty==true)
			  System.out.println("No records found");
	  }//try
	  catch(SQLException se){
		  se.printStackTrace();
	  }
	  catch(ClassNotFoundException cnf){
		  cnf.printStackTrace();
	  }
	  catch(Exception e){
		  e.printStackTrace();
	  }
	  finally{
		  //close jdbc  objs
		  try{
			  if(rs!=null)
				  rs.close();
		  }
		  catch(SQLException se){
			  se.printStackTrace();
		  }
		  try{
			  if(st!=null)
				  st.close();
		  }
		  catch(SQLException se){
			  se.printStackTrace();
		  }
		  
		  try{
			  if(con!=null)
				  con.close();
		  }
		  catch(SQLException se){
			  se.printStackTrace();
		  }

	  }//finally

	}//main
}//class
