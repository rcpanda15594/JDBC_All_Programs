package com.nt.basics;

import java.text.SimpleDateFormat;

public class DateConversionsTest {
	
	public static void main(String[] args) throws Exception{
		String s1="45-20-1989";  //dd-MM-yyyy
		SimpleDateFormat sdf=null;
		
		//converting String date to java.util.Date class object
		sdf=new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date ud1=sdf.parse(s1);
		System.out.println("Util Date:::"+ud1);
		
		//converting java.util.Date calss obj to java.sql.Date calss object
		long ms=ud1.getTime();
		java.sql.Date sd1=new java.sql.Date(ms);
		System.out.println(sd1);
		
		// if given Date values is there in the form of  yyyy-dd-MM pattern
		//then it can be converted into  java.sql.Date class obj directly with 
		//out converting it to  java.util.Date class obj
		String  s2="1987-12-22";  //yyyy-MM-dd
		java.sql.Date sd2=java.sql.Date.valueOf(s2);
		System.out.println("sql date2::"+sd2);
		
		//Converting java.sql.Date class obj to java.util.Date class obj
		java.util.Date ud2=(java.util.Date)sd2;
		System.out.println("util Date ::"+ud2);
		//Converting  java.util.Date class obj to String date value
		SimpleDateFormat sdf2=new SimpleDateFormat("MM-yyyy-dd");
		String  s3=sdf2.format(ud2);
		System.out.println("String date value:::"+s3);
		
		
		
		
	}

}
