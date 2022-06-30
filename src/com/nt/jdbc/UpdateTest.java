package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTest {

	public static void main(String[] args) {
      Scanner sc=null;
      int no=0;
      String newName=null,newAddrs=null;
      Statement st=null;
      Connection con=null;
      String query=null;
      int count=0;
		try{
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter existing number::");
				no=sc.nextInt();
				System.out.println("Enter new name::");
				sc.nextLine();
				newName=sc.nextLine();  //new raja
				System.out.println("Enter new Addrss::");
				newAddrs=sc.nextLine();  //new hyd
			  }
			//Convert input values as required for the SQL Query
			newName="'"+newName+"'"; //gives 'new raja'
			newAddrs="'"+newAddrs+"'"; //gives  'new hyd'
			//register JDBC driver
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//establish the connection
			 con=DriverManager.getConnection("jdbc:odbc:oradsn","system","manager");
			//create STatement object
			if(con!=null)
			   st=con.createStatement();
			//prepare SQL query
			    //update student set sadd='new delhi',sname='rajesh' where sno=1002
			   query="UPDATE STUDENT SET SADD="+newAddrs+",SNAME="+newName+" WHERE SNO="+no;
			   System.out.println(query);
			   
			   //send and execute SQL Query
			   if(st!=null)
				   count=st.executeUpdate(query);
			   //process the ResultSet
			   if(count==0)
				    System.out.println("Record not found for updation");
			   else
				   System.out.println("Record  found for updation");
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
