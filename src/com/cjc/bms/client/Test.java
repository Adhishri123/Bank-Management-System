package com.cjc.bms.client;
import com.cjc.bms.service.*;
import com.cjc.bms.serviceimpl.*;
import java.util.Scanner;
public class Test {
	 public static void main(String[] args)throws Exception {
			Scanner sc = new Scanner(System.in);
			   Rbi r = new Sbi();
			
			boolean b = true;   
			while(b)
			{
				System.out.println("Enter your choice perform bank operation:");
				int i = sc.nextInt();
				switch(i)
				{
				case 1 :
					  r.createAccountantTbl();
				case 2 :
				      r.createAccount();
				      break;
				case 3 :
					  r.displayAllDetails();
					  break;
				case 4 :
					  r.depositeMoney();
					  break;
				case 5 :
					  r.withdrawal();
					  break;
				case 6 :
					  r.balanceCheck();
					  break;
			    default :
			    	b = false;
			    	System.out.println("Invalid choice");
			    	break;
				}
			}
		}
}
