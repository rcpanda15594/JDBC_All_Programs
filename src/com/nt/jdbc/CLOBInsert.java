package com.nt.jdbc;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/*SQL> create table studentALL(sno number(5) primary key,sname varchar2(20),sadd varchar2(20),resume CLOB);
 * SQL>create sequence STNO_SEQ1 start with 1 increment by 1;
*/
public class CLOBInsert {
   private static final String STUDENT_CLOB_INSERT="INSERT INTO STUDENTALL(SNO,SNAME,SADD,RESUME) VALUES(STNO_SEQ1.NEXTVAL,?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		String name=null,addrs=null, resumePath=null;
		Connection con=null;
		PreparedStatement ps=null;
		File file=null;
		Reader reader=null;
		long length=0;
		int count=0;
		try{
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter student Name::");
				name=sc.next();
				System.out.println("Enter Student Address::");
				addrs=sc.next();
				System.out.println("Enter Resume Path");
				resumePath=sc.next();
			}
			
			  //register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
			//Locate CLOB file and get length
			file=new File(resumePath);
			length=file.length();
			//create Reader object
			reader=new FileReader(file);
			
			//create PrpearedStatement object
			if(con!=null)
				ps=con.prepareStatement(STUDENT_CLOB_INSERT);
			//set values to Query params
			if(ps!=null){
				ps.setString(1,name);
				ps.setString(2,addrs);
				ps.setCharacterStream(3, reader,length);
			}
			//execute the SQL Query
			if(ps!=null){
				count=ps.executeUpdate();
			}
			//process the Result
			if(count==0)
				  System.out.println("Record not inserted");
			else
				  System.out.println("Record  inserted");
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
				if(reader!=null)
					reader.close();
			}
			catch(IOException se){
				se.printStackTrace();
			}
			
			}//finally

	}//main
}//class
