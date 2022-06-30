package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteTest {

	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		Connection con=null;
		Statement st=null;
		String query=null;
		int count=0;
		try{
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter student number::");
				no=sc.nextInt();
			}
			
			//register JDBC driver
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:odbc:oradsn","system","manager");
			//create STatement object
			if(con!=null)
			   st=con.createStatement();
			//prpare SQL Query
			query="DELETE FROM STUDENT WHERE SNO="+no;
			System.out.println(query);
			//send and execute SQL Query
			if(st!=null)
				count=st.executeUpdate(query);
			//process the result
			if(count==0)
				System.out.println("Record not found to delete");
			else
				System.out.println("record found and deleted");
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
