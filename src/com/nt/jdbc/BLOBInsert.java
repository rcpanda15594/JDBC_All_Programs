package com.nt.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/*SQL> create table EmpAll(eid number(5) primary key,ename varchar2(20),esalary number(10), ephoto blob);*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class BLOBInsert {
  private static final String EMPALL_INSERT_QUERY="INSERT INTO EMPALL(EID,ENAME,ESALARY,EPHOTO) VALUES(?,?,?,?)";
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
        String url="jdbc:oracle:thin:@localhost:1521:xe";
        String usr="System";
        String pass="Manager";
		Scanner sc=null;
		int no=0,salary=0;
		String ename=null,filePath=null;
		File file=null;
		long length=0;
		InputStream is=null;
		int result=0;
		try{
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter Employee ID::");
				no=sc.nextInt();
				System.out.println("Enter Employee Name::");
				ename=sc.next();
				System.out.println("Enter employee Salary::");
				salary=sc.nextInt();
				System.out.println("Enter Employee Photo Path");
				filePath=sc.next();
			}
			
			//register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection(url,pass,pass);

			
	/*		//register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:mysql:///ntaj991db1", "root","root");*/
			
			//create PreparedStatement object
			if(con!=null)
				ps=con.prepareStatement(EMPALL_INSERT_QUERY);
			//locate the file (Photo)
			file=new File(filePath);
			length=file.length();
			//create Stream pointing to photo file
			is=new FileInputStream(file);
				//set values to Query params
			if(ps!=null){
				ps.setInt(1, no);
				ps.setString(2,ename);
				ps.setInt(3,salary);
				ps.setBinaryStream(4,is,length);
			}
			//execuite the Query
			if(ps!=null){
				result=ps.executeUpdate();
			}
			//process the Result
			if(result==0)
				System.out.println("Record not inserted");
			else
				System.out.println("Record inserted");
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
				if(is!=null)
					is.close();
			}
			catch(IOException ioe){
				ioe.printStackTrace();
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
