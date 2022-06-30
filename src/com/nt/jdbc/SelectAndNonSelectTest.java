package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectAndNonSelectTest {

	public static void main(String[] args) {
		Scanner sc=null;
		boolean registerFlag=false;
		int no=0;
		String name=null,addrs=null;
		Connection con=null;
		Statement st=null; 
		String query=null;
		boolean flag=false;
		ResultSet rs=null;
		int count=0;
		//read inputs
		try{
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Are u the registered student");
				registerFlag=sc.nextBoolean();
				if(registerFlag){
					System.out.println("Enter student number::");
					no=sc.nextInt();
				  }
				else{
					System.out.println("Enter student number::");
					no=sc.nextInt();
					System.out.println("Enter student name::");
					name=sc.next();  //raja
					System.out.println("Enter student address");
					addrs=sc.next(); //hyd
					//convert input vlaues as required for the  SQL Query
					name="'"+name+"'";  //'raja'
					addrs="'"+addrs+"'";  //'hyd'
				}
			}//if
			//register JDBC driver
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//establish the connection
			 con=DriverManager.getConnection("jdbc:odbc:oradsn","system","manager");
			//create STatement object
			if(con!=null)
			   st=con.createStatement();
			//prepare query
			if(registerFlag)
			   query="SELECT SNO,SNAME,SADD FROM STUDENT WHERE SNO="+no;
			else{
				//INSERT INTO STUDENT(SNO,SNAME,SADD) VALUES(101,'raja','hyd')
				query="INSERT INTO STUDENT(SNO,SNAME,SADD) VALUES("+no+","+name+","+addrs+")";     
			}
			//execute Query
			if(st!=null)
			   flag=st.execute(query);
			
			if(flag){
				System.out.println("SElect Query executed");
				rs=st.getResultSet();
				if(rs!=null){
					while(rs.next()){
						System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
					}//while
				}//if
			}//if
			else{
				System.out.println("Non-SElect Query executed");
				count=st.getUpdateCount();
				if(count==0)
					System.out.println("Record not inserted");
				else
					System.out.println("Record  inserted");
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
