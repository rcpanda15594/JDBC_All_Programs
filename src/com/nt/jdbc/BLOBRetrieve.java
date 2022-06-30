package com.nt.jdbc;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BLOBRetrieve {
  private static final  String GET_EMP_DETAILS="SELECT EID,ENAME,ESALARY,EPHOTO  FROM EMPALL WHERE  EID=?";
	public static void main(String[] args) {
		Scanner sc=null;
		int eid=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		InputStream is=null;
		OutputStream os=null;
		byte[] buffer=null;
		int bytesRead=0;
		
		try{
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter Employee ID::");
				eid=sc.nextInt();
			  }//if
	/*		  //register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");*/
			
			//register jdbc driver
			Class.forName("com.mysql.jdbc.Driver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:mysql:///NTAJ991db1","root", "root");
			
			//create PreparedStatement object
			if(con!=null)
				ps=con.prepareStatement(GET_EMP_DETAILS);
			//set values to Query params
			if(ps!=null)
				ps.setInt(1,eid);
			//execute the query
			if(ps!=null)
				rs=ps.executeQuery();
			//process the Result
			if(rs!=null){
				 if(rs.next()){
					 System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3));
					 is=rs.getBinaryStream(4);
					 //create output strema pointing Dest file.
					 os=new FileOutputStream("newPhoto.jpg");
					 //write bufferred based to copy content
					 buffer=new byte[4096];
					 if(is!=null &os!=null){
						 while((bytesRead=is.read(buffer))!=-1){
							 os.write(buffer,0,bytesRead);
						 }//while
				 }//if
					 System.out.println("BLOB Value retrived and stored into file");
				
			}//if
				 else{
					 System.out.println("Record not found");
				 }
			}//if
		}//try
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf){
			cnf.printStackTrace();
		}
		catch(IOException ioe){
			ioe.printStackTrace();
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
			catch(Exception se){
				se.printStackTrace();
			}
			
			try{
				if(os!=null)
					os.close();
			}
			catch(IOException se){
				se.printStackTrace();
			}
			
			try{
				if(is!=null)
					is.close();
			}
			catch(IOException se){
				se.printStackTrace();
			}
		}//finally
	}//main
}//class
