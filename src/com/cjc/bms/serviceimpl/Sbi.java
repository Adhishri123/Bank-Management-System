package com.cjc.bms.serviceimpl;
import com.cjc.bms.model.Accbankdetails;
import com.cjc.bms.service.Rbi;
import java.sql.*;
import java.util.Scanner;
public class Sbi implements Rbi {
	Accbankdetails a = new Accbankdetails();
	Scanner sc = new Scanner(System.in);
	
	public void createAccountantTbl() throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankproject","root","root");
		String sql = "Create table Accbankdetails(Useraccno varchar(20) primary key,Useraccbankname varchar(20),Useraccmobno varchar(20),Useraccadharno varchar(20),Useraccgender varchar(20),Useraccage int,Useraccbalance double)";
		Statement smt = con.createStatement();
		    smt.execute(sql);
		System.out.println("Table create succesfully");
	}
	public void insertAccData() throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankproject","root","root");
		String sql2 = "insert into Accbankdetails values('"+a.getUseraccno()+"','"+a.getUseraccbankname()+"','"+a.getUseraccmobno()+"','"+a.getUseraccadharno()+"','"+a.getUseraccgender()+"',"+a.getUseraccage()+","+a.getUseraccbalance()+")";
		Statement smt = con.createStatement();
		    smt.execute(sql2);
		System.out.println("User Data insert successfully");
	}
	public void setAccNo() throws Exception {
		while(true)
		{ 
			System.out.println("Enter user Account No:");
			String y = sc.next();
			int x = y.length(); 
			if(x==8) {
				a.setUseraccno(y);
				break;
			}else
			{
				System.out.println("Account No is Incorrect");
				setAccNo();
			}
		}
	}
	public void setBankName() throws Exception {
		System.out.println("Enter Bank Name:");
     	String p = sc.next();
     	for(int j=0; j<p.length(); j++)
     	{
     		char ch = p.charAt(j);

     		if((ch>='a' && ch<='z') || (ch>='A' && ch<='Z') || (ch==' '))
     		{
     			a.setUseraccbankname(p);
     			if(j==p.length()-1)
				break;
     		}else
     		{
     			System.out.println("Name is Incorrect");
     			setBankName();
     		}
     	}
		
	}
	public void setMobileNo() throws Exception {
		System.out.println("Enter Mob No:");
     	String s1 = sc.next();
     	int s2 = s1.length();
     	if(s2==10)
     	{
     	    a.setUseraccmobno(s1);
     	}else
     	{
     		System.out.println("Mob No is Incorrect");
     		setMobileNo();
     	}
	}
	public void setAdharNo() throws Exception
	{
		System.out.println("Enter Adhar No:");
     	String s3 = sc.next();
     	int s4 = s3.length();
     	if(s4==12)
     	{
     		a.setUseraccadharno(s3);
     	}else
     	{
     		System.out.println("Adhar No is Incorrect");
     		setAdharNo();
     	}
	}
	public void setGender() throws Exception
	{
		System.out.println("Enter Gender:");
     	String s = sc.next();
     	if(s.equals("M") || s.equals("m") || s.equals("F") || s.equals("f") || s.equals("MALE") || s.equals("FEMALE") || s.equals("Male") || s.equals("Female") || s.equals("male") || s.equals("female") )
     	{
     		a.setUseraccgender(s);
     	}else
     	{
     		System.out.println("Gender is incorrect");
     		setGender();
     	}
	}
	public void setAge() throws Exception
	{
		System.out.println("Enter Age:");
     	int l = sc.nextInt();
     	if(l>=18 && l<=100)
     	{
     		a.setUseraccage(l);
     	}else
     	{
     		System.out.println("Account Holder Age is not valid");
     		setAge();
     	}
	}
    public void createAccount() throws Exception
    {
    	setAccNo();
    	setBankName();
    	setMobileNo();
    	setAdharNo();
    	setGender();
    	setAge();
    	System.out.println("Enter Balance");
     	a.setUseraccbalance(sc.nextDouble());
     	insertAccData();
    }
    public void displayAllDetails() throws Exception
    {
    	Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankproject","root","root");
		String sql3 = "select * from Accbankdetails";
		Statement ps = con.createStatement();
		ResultSet rs = ps.executeQuery(sql3);
		while(rs.next())
		{
			System.out.println("User Account No = "+rs.getString(1));
			System.out.println("User Bank Name = "+rs.getString(2));
			System.out.println("User Mobile No = "+rs.getString(3));
			System.out.println("User Adhar No = "+rs.getString(4));
			System.out.println("User Gender = "+rs.getString(5));
			System.out.println("User Age = "+rs.getInt(6));
			System.out.println("User bank balance = "+rs.getDouble(7));
		}
    }
    public void depositeMoney() throws Exception 
    {
    	Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankproject","root","root");
		System.out.println("Enter Account no:");
		String accno = sc.next();
    	System.out.println("Enter Deposite Money:");
   	    double d = sc.nextDouble();
   	    String  sql = "select Useraccbalance from Accbankdetails where Useraccno ='"+accno+"'";
   	    Statement ps = con.createStatement();
		ResultSet rs = ps.executeQuery(sql);
		if(rs.next())
		{
			d=d+rs.getDouble(1);
			String sql1 = "update Accbankdetails set Useraccbalance="+d+" where Useraccno ='"+accno+"'"; 
			ps.execute(sql1);
		}
    }
    public void withdrawal() throws Exception
    {
   	    Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankproject","root","root");
		System.out.println("Enter Account no:");
		String accno = sc.next();
 	    System.out.println("Enter Withdrawal Money:");
	    double c = sc.nextDouble();
	    String  sql = "select Useraccbalance from Accbankdetails where Useraccno ='"+accno+"'";
	    Statement ps = con.createStatement();
		ResultSet rs = ps.executeQuery(sql);
		if(rs.next())
		{
			c=rs.getDouble(1)-c;
			String sql2 = "update Accbankdetails set Useraccbalance="+c+" where Useraccno ='"+accno+"'"; 
			ps.execute(sql2);
		}
		
    }
    public void balanceCheck() throws Exception
    {
    	Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankproject","root","root");
		System.out.println("Enter Account no:");
		String accno = sc.next();
	    String  sql = "select Useraccbalance from Accbankdetails where Useraccno ='"+accno+"'";
	    Statement ps = con.createStatement();
		ResultSet rs = ps.executeQuery(sql);
		if(rs.next())
		{
			System.out.println("User account balance = "+rs.getDouble(1));
			
		}
    }
}
