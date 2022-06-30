package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLToOracleDBTest {
  private static final String ORACLE_INSERT_QUERY="INSERT INTO KOTAK_BANK_CUSTOMER(ACNO,HOLDERNAME,BALANCE,ADDRESS) VALUES(?,?,?,?)";
  private static final String MYSQL_SELECT_QUERY="SELECT ACNO,HOLDERNAME,BALANCE,ADDRESS FROM ING_BANK_CUSTOMER";
	public static void main(String[] args) {
      Connection oraCon=null,mysqlCon=null;
      Statement st=null;
      PreparedStatement ps=null;
      ResultSet rs=null;
      int acno=0;
      String holder=null, addrs=null;
      float balance=0.0f;
		try{
			//register jdbc drivers
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName("com.mysql.jdbc.Driver");
			//establish the connections
			oraCon=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			mysqlCon=DriverManager.getConnection("jdbc:mysql:///ntaj991db1","root","root");
			//create Simple Statement,PreparedStatement  objects
			if(oraCon!=null)
				ps=oraCon.prepareStatement(ORACLE_INSERT_QUERY);
			
			if(mysqlCon!=null)
				st=mysqlCon.createStatement();
			//execute SELECT SQL Query..
			if(st!=null)
				rs=st.executeQuery(MYSQL_SELECT_QUERY);
			//process the ResultSet and insert the records oracle DB table
			if(rs!=null && ps!=null){
				while(rs.next()){
					//get each record from ResultSet (mysql)
					acno=rs.getInt(1);
					holder=rs.getString(2);
					balance=rs.getFloat(3);
					addrs=rs.getString(4);
					//set each record to  Insert Query params (oracle)
					ps.setInt(1,acno);
					ps.setString(2,holder);
					ps.setFloat(3,balance);
					ps.setString(4, addrs);
					//execute Query
					ps.executeUpdate();
				}//while
				System.out.println("Records are copied from MySQL DB table to Oracle DB table");
			}//if
		}//try
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(ClassNotFoundException  cnf){
			cnf.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			//close jdbc objs
			try{
			  if(rs!=null)
				  rs.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			
			try{
				  if(ps!=null)
					  ps.close();
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
				  if(oraCon!=null)
					  oraCon.close();
				}
				catch(SQLException se){
					se.printStackTrace();
				}
			try{
				  if(mysqlCon!=null)
					  mysqlCon.close();
				}
				catch(SQLException se){
					se.printStackTrace();
				}
			
		}//finally
	}//main
}//class
