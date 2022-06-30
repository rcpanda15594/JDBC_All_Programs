package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTableTest {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		int count=0;
		try{
			//register JDBC driver
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//establish the connection
			 con=DriverManager.getConnection("jdbc:odbc:oradsn","system","manager");
			//create STatement object
			if(con!=null)
			   st=con.createStatement();
			//send and execute SQL Query
			if(st!=null)
				count=st.executeUpdate("create table temp(xyz number(5))");
			if(count==0)
				 System.out.println("Table not created");
			else
				System.out.println("Table created");
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
