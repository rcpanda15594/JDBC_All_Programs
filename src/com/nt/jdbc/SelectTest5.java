package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*  App to get Student details based on given Student number
 *  author :: Team-S
 *   version:: 1.0
 *  */

public class SelectTest5 {

	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		Connection con=null;
		Statement st=null;
		String query=null;
		ResultSet rs=null;
		try{
		//read inputs
		  sc=new Scanner(System.in);
		  if(sc!=null){
			   System.out.println("Enter student number::");
			   no=sc.nextInt();
		  }
		  //register jdbc driver
		  Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		  //establish the connection
		  con=DriverManager.getConnection("jdbc:odbc:oradsn","system","manager");
		  //create Statement object
		  if(con!=null)
			  st=con.createStatement();
		  //prepare SQL Query
		         //SELECT SNO,SNAME,SADD FROM STUDENT WHERE SNO=1001
		   query="SELECT SNO,SNAME,SADD FROM STUDENT WHERE SNO="+no;
		   System.out.println(query);
		   //send and execute SQL Query in Db s/w
		   if(st!=null)
			   rs=st.executeQuery(query);
		   //process the ResultSet
		   if(rs!=null){
			   if(rs.next()){
				   System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));   
			   }
			   else{
				   System.out.println("Record not found..");
			   }
		   }//if
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
