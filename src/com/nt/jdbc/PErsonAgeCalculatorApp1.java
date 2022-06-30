package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PErsonAgeCalculatorApp1 {
   private static final  String  GET_AGE_QUERY="SELECT DOB FROM PERSON_DATE_TEST WHERE PID=?";
	public static void main(String[] args) {
		Scanner sc=null;
		int pid=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		java.sql.Date sdob=null;
		java.util.Date udob=null;
		float age=0;
		try{
		//read inputs
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter person Id");
				pid=sc.nextInt();
			}
			/*//register jdbc Driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");
		*/
			//register jdbc Driver
			Class.forName("com.mysql.jdbc.Driver");
			//establish the connnection
			con=DriverManager.getConnection("jdbc:mysql:///NTAJ991DB1", "root","root");

			
			//create PreparedSTatement object
			if(con!=null)
				ps=con.prepareStatement(GET_AGE_QUERY);
			 //set Param value to query
			if(ps!=null)
				ps.setInt(1,pid);
			
			//execute the Query
			if(ps!=null)
				rs=ps.executeQuery();
			
			//Process the ResultSEt
			if(rs!=null){
				if(rs.next()){
					sdob=rs.getDate(1);
					udob=(java.util.Date)sdob;
					age=((new java.util.Date().getTime())-udob.getTime())/(1000*60*60*24*365.0f);
					System.out.println("Person age:::"+age);
				}
				else{
					System.out.println("Record not found");
				}
			 }//if
			}//try
			catch(SQLException		se){
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
			
			try{
				  if(sc!=null)
					 sc.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
			
			
			
		}
	}

}
