package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsInsertTest {
  private static final String  INSERT_STUDENT_QUERY="INSERT INTO STUDENT VALUES(?,?,?)";
  
	public static void main(String[] args) {
		Scanner sc=null;
		int count=0;
		Connection con=null;
		PreparedStatement ps=null;
		int no=0;
		String name=null;
		String addrs=null;
		try{
		//read inputs
		sc=new Scanner(System.in);
		if(sc!=null){
			System.out.println("Enter Students count::");
			count=sc.nextInt();
		}
		//register JDBC driver
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//establish the connection
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");
		//create PreparedStatement object
		if(con!=null)
			ps=con.prepareStatement(INSERT_STUDENT_QUERY);
		//read each set of student details and insert them into DB table
		if(ps!=null){
			for(int i=1;i<=count;++i){
				//read each student details
				System.out.println("Enter  "+i+" Student number:");
				no=sc.nextInt();
				System.out.println("Enter  "+i+" Student name:");
				name=sc.next();
				System.out.println("Enter "+i+" student address::");
				addrs=sc.next();
				//set each student details to query params
				ps.setInt(1, no); ps.setString(2, name); ps.setString(3,addrs);
				//execute Query
				int result=ps.executeUpdate();
				if(result==0)
					 System.out.println(i+" student record not inserted");
				else
					System.out.println(i+"  student record inserted");
			}//for
		}//if
		}
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
	}//main
}//calss
