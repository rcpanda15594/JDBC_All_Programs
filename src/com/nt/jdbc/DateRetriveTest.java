package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DateRetriveTest {
  private static final String  GET_PERSON_DETAILS_BY_NO="SELECT PID,PNAME,DOB,DOJ,DOM FROM PERSON_DATE_TEST WHERE PID=?";
	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		java.sql.Date sqdob=null,sqdoj=null,sqdom=null;
		java.util.Date udob=null,udoj=null,udom=null;
		String name=null;
		SimpleDateFormat sdf1=null;
		String dob=null,doj=null,dom=null;
		try{
		//read inputs
		sc=new Scanner(System.in);
		if(sc!=null){
			System.out.println("Enter Person Id");
			no=sc.nextInt();
		}
	/*	//register JDBC driver
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//establish the connection
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");*/
		
		//register JDBC driver
		Class.forName("com.mysql.jdbc.Driver");
		//establish the connection
		con=DriverManager.getConnection("jdbc:mysql:///NTAJ991DB1","root","root");
		
		//create Jdbc PreparedStatement object
		if(con!=null)
			ps=con.prepareStatement(GET_PERSON_DETAILS_BY_NO);
		//set values to Query params
		if(ps!=null){
			ps.setInt(1,no);
		}
		//execute the Query
		if(ps!=null)
			rs=ps.executeQuery();
		//process the ResultSet
		if(rs!=null){
			if(rs.next()){
				no=rs.getInt(1);
				name=rs.getString(2);
				sqdob=rs.getDate(3);
				sqdoj=rs.getDate(4);
				sqdom=rs.getDate(5);
				//Convert java.sql.Date class objs to java.util.Date class objs
				udob=(java.util.Date)sqdob;
				udoj=(java.util.Date)sqdoj;
				udom=(java.util.Date)sqdom;
				//Convert java.util.Date class objs to String date vlaues
				sdf1=new SimpleDateFormat("yyyy-dd-MMM");
				dob=sdf1.format(udob);
				doj=sdf1.format(udoj);
				dom=sdf1.format(udom);
				System.out.println(no+"  "+name+"  "+dob+" "+doj+" "+dom);
				System.out.println(no+" "+name+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5));
			}//if
			else{
				System.out.println("Record not found");
			}//else
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
		}//finally
	}//main
}//class
