package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PsSelectTest {
  private static final String  EMP_COUNT_QUERY="SELECT COUNT(*) FROM EMP WHERE JOB='CLERK'";
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		String query=null;
		ResultSet rs=null;
		try{
			//register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create Statement object
			if(con!=null)
			  ps=con.prepareStatement(EMP_COUNT_QUERY);
			  // execute SQL Query
			  if(ps!=null){
				  rs=ps.executeQuery();
			  }
			  //prcoess the ResultSet object
			  if(rs!=null){
				  rs.next();
				  System.out.println("Records count:::"+rs.getInt(1));
			  }
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
				if(con!=null)
					con.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			
		}//finally
	}//main
}//class
