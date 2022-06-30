package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsDropTableTest {
  private static final String  DROP_TAB_QUERY="DROP TABLE ?";
	public static void main(String[] args) {
		Scanner sc=null;
		String tabName=null;
		Connection con=null;
		PreparedStatement ps=null;
		int count=0;
		String query=null;
		try{
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter db table name::");
				tabName=sc.next();
			}
			//register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//prepare SQL query
			query="DROP TABLE "+tabName;
			//create PrpearedStatement object
			if(con!=null)
				ps=con.prepareStatement(query);

			///execute the Query
			if(ps!=null){
				count=ps.executeUpdate();
			}
			//process the result
			if(count==0)
				System.out.println(" table not dropped");
			else
				System.out.println(" table  dropped");
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
		
		try{
			if(ps!=null)
				ps.close();
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
		finally{
		try{
			if(sc!=null)
				sc.close();
		}
		catch(Exception e){
			 e.printStackTrace();
		}
	}//finally

	}//main
}//class