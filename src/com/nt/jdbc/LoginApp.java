package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*SQL> desc userlist;
Name                                      Null?    Type
----------------------------------------- -------- ----------------------------
UNAME                                     NOT NULL VARCHAR2(20)
PWD                                                VARCHAR2(20)
*/
public class LoginApp {

	public static void main(String[] args) {
	     Scanner sc=null;
	     String user=null,pass=null;
	     Connection con=null;
	     Statement st=null;
	     String query=null;
	     ResultSet rs=null;
	     int count=0;
	     try{
	    	 //read inputs
	    	 sc=new Scanner(System.in);
	    	 if(sc!=null){
	    		 System.out.println("Enter username:::");
	    		 user=sc.nextLine(); //gives raja
	    		 System.out.println("Enter password::");
	    		 pass=sc.nextLine(); //gives rani
	    	 }//if
	    	 //Convert input values as required for the  SQL Query
	    	 user="'"+user+"'"; //gives 'raja'
	    	 pass="'"+pass+"'"; //gives  'rani'
	    	 //register jdbc driver
	    	 Class.forName("oracle.jdbc.driver.OracleDriver");
	    	 //establish the connection
	    	 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");
	    	 //create Statement object
	    	 if(con!=null)
	    		 st=con.createStatement();
	    	 //prepare SQL Query
	    	       //select count(*) from userlist where uname='raja' and pwd='rani'
	    	      query="SELECT COUNT(*) FROM USERLIST WHERE UNAME="+user+" AND PWD="+pass;
	    	   //query="SELECT COUNT(*) FROM USERLIST WHERE PWD="+pass+" AND UNAME="+user;
	    	     System.out.println(query);
          //send and execute SQL Query in Db s/w
	    	     if(st!=null)
	    	    	 rs=st.executeQuery(query);
	    	   //process the ResultSet
	    	     if(rs!=null){
	    	    	 rs.next();
	    	    	 count=rs.getInt(1);
	    	     }
	    	   if(count==0)
	    		   System.out.println("INVALID CREDENTIALS");
	    	   else
	    		   System.out.println("VALID CREDENTIALS");
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
