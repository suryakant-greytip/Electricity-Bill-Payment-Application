package com.paytric.ui;

import java.util.Scanner;

import com.paytric.colors.UIColors;

public class MainUI {

	
	public static void main(String[] args) {
	    Scanner sc=new Scanner(System.in);
		
		System.out.println("   "+UIColors.BLACK_BOLD+"_________________________________________________________");
		System.out.println("  "+UIColors.BLACK_BOLD+"|"+UIColors.TEAL_BACKGROUND+"                                                         "+UIColors.RESET+UIColors.BLACK_BOLD+"|");
		System.out.println("  "+UIColors.BLACK_BOLD+"|"+UIColors.TEAL_BACKGROUND+"  "+UIColors.BLACK_BOLD+"Welcome to "+UIColors.DARK_BLUE+"PAYTRIC"+UIColors.RESET+UIColors.TEAL_BACKGROUND+UIColors.BLACK_BOLD+" - Electricity Bill Payment System   "+UIColors.RESET+UIColors.BLACK_BOLD+"|");
		System.out.println("  "+UIColors.BLACK_BOLD+"|"+UIColors.TEAL_BACKGROUND+"_________________________________________________________"+UIColors.RESET+UIColors.BLACK_BOLD+"|"+UIColors.RESET);
		System.out.println();
		System.out.println("  "+UIColors.RED_BOLD+"*************************WELCOME*************************"+UIColors.RESET);
		System.out.println();
		
		int choice=0;
		do {
			System.out.println("  Select 0 -> for Exit from PAYTRIC.");
			System.out.println("  Select 1 -> for Admin's Login.");
			System.out.println("  Select 2 -> for Consumer's Login.");
			System.out.println("  Select 3 -> for Consumer's Registration.");
			System.out.println("  Select 4 -> for Consumer's Forget Password.");
			System.out.println();
			
			System.out.print("  Please provide any selection from above: ");
			choice=sc.nextInt();
			System.out.println();
			
			switch(choice) {
			case 0 :
				System.out.println("  ***Thanks for visiting PAYTRIC, keep visiting***");
				break;
			case 1 :
				AdminUI.adminLogin(sc);
				break;
			case 2 :
				
				break;
			case 3 :
				
				break;
			case 4 :	
				
				break;
			default :
				System.out.println("  ***Invalid Input please try again***");
			}
		
			
		}
		while(choice!=0);
		
		
		sc.close();
	}
}
