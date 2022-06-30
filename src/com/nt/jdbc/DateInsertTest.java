package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/*SQL> create table person_date_test(pid number(5),pname varchar2(20),DOB date,DOJ date,DOM date);
*/
public class DateInsertTest {
  private static final String  INSERT_DATE_QUERY="INSERT INTO PERSON_DATE_TEST(PID,PNAME,DOB,DOJ,DOM) VALUES(?,?,?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		String name=null,dob=null,doj=null,dom=null;
		Connection con=null;
		PreparedStatement ps=null;
		java.util.Date udob=null,udoj=null;
		SimpleDateFormat sdf1=null,sdf2=null;
		java.sql.Date sdob=null,sdoj=null,sdom=null;
		int result=0;
		try{
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter Person Id::");
				no=sc.nextInt();
				System.out.println("Enter Person name::");
				name=sc.next();
				System.out.println("Enter DOB(dd-MM-yyyy)");
				dob=sc.next();
				System.out.println("Enter DOJ(MM-dd-yyyy)");
				doj=sc.next();
				System.out.println("Enter DOM(yyyy-MM-dd)");
				dom=sc.next();
			}//if
			//Convert String Date values to java.util.Date class objs
			   //for DOB
			 sdf1=new SimpleDateFormat("dd-MM-yyyy");
			 udob=sdf1.parse(dob);
			 //for DOJ
			 sdf2=new SimpleDateFormat("MM-dd-yyyy");
			 udoj=sdf2.parse(doj);
			 //convert java.util.Date class objs to java.sql.Date class objs
			 sdob=new java.sql.Date(udob.getTime());
			 sdoj=new java.sql.Date(udoj.getTime());
			 sdom=java.sql.Date.valueOf(dom);
			
	/*		//register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");*/
			 
			 //register JDBC driver
			 Class.forName("com.mysql.jdbc.Driver");
			 //establish the connection
			 con=DriverManager.getConnection("jdbc:mysql:///NTAJ991DB1","root","root");
			 
			 
			//create PreparedStatemen object
			if(con!=null)
				ps=con.prepareStatement(INSERT_DATE_QUERY);
			//set values to query params
			if(ps!=null){
				ps.setInt(1,no);
				ps.setString(2,name)	;
				ps.setDate(3,sdob);
				ps.setDate(4,sdoj);
				ps.setDate(5,sdom);
			}
			//execute the Query
			if(ps!=null){
				result=ps.executeUpdate();
			}
			
			//process the result
			if(result==0)
				System.out.println("Record not inserted");
			else
				System.out.println("Record  inserted");
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
		}//finally
	
	}//main
}//class

