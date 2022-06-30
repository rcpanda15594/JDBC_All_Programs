package com.nt.jdbc;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CLOBRetrieve {
  private static final String GET_STUDENT="SELECT SNO,SNAME,SADD,RESUME FROM STUDENTALL WHERE SNO=?";
	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Reader reader=null;
		Writer writer=null;
		char [] buffer=null;
		int charsRead=0;
		try{
		//read inputs
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter student Id::");
				no=sc.nextInt();
			}
			//register JDBc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
			//create PreparedStatement object
			if(con!=null)
				ps=con.prepareStatement(GET_STUDENT);
			//set values to Query params
			if(ps!=null)
				ps.setInt(1,no);
			//execute the Query
			if(ps!=null){
				rs=ps.executeQuery();
			}
			//process the ResultSEt
			 if(rs!=null){
				 if(rs.next()){
					 System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
					 //get Reader object representing CLOB data
					 reader=rs.getCharacterStream(4);
					 //create Writer object pointing to dest file
					 writer=new FileWriter("newresume.txt");
					 //writer buffer based logic 
					 buffer=new char[1024];
					 if(reader!=null && writer!=null){
						 while((charsRead=reader.read(buffer))!=-1){
							 writer.write(buffer,0,charsRead);
						 }//while
						 System.out.println("CLOB Value retrieved to a file..");
					 }//if
					 
				 }//if
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
				if(writer!=null)
					writer.close();
			}
			catch(IOException se){
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

	}

}
