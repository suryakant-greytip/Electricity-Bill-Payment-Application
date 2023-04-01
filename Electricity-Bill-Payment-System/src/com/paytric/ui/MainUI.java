package com.paytric.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.paytric.colors.UIColors;

public class MainUI {

	
	public static void main(String[] args) {
	    Scanner sc=new Scanner(System.in);
		
		System.out.println("   "+UIColors.BLACK_BOLD+"_________________________________________________________");
		System.out.println("  "+UIColors.BLACK_BOLD+"|"+UIColors.TEAL_BACKGROUND+"                                                         "+UIColors.RESET+UIColors.BLACK_BOLD+"|");
		System.out.println("  "+UIColors.BLACK_BOLD+"|"+UIColors.TEAL_BACKGROUND+"  "+UIColors.BLACK_BOLD+"Welcome to "+UIColors.WHITE_BOLD+UIColors.BLACK_BACKGROUND+"PAYTRIC"+UIColors.RESET+UIColors.TEAL_BACKGROUND+UIColors.BLACK_BOLD+" -> Electricity Bill Payment System  "+UIColors.RESET+UIColors.BLACK_BOLD+"|");
		System.out.println("  "+UIColors.BLACK_BOLD+"|"+UIColors.TEAL_BACKGROUND+"_________________________________________________________"+UIColors.RESET+UIColors.BLACK_BOLD+"|"+UIColors.RESET);
		System.out.println();
		System.out.println("  "+UIColors.PURPLE_BOLD+"**************************"+UIColors.RESET+UIColors.BLACK_BOLD+"WELCOME"+UIColors.RESET+UIColors.PURPLE_BOLD+"**************************"+UIColors.RESET);
		System.out.println();
		
		int choice=0;
		
		try {
			do {
				System.out.println(UIColors.BLACK_BOLD+"  Select 0 -> for Exit from PAYTRIC.");
				System.out.println("  Select 1 -> for Admin's Login.");
				System.out.println("  Select 2 -> for Consumer's Login.");
				System.out.println("  Select 3 -> for Consumer's Registration.");
				System.out.println("  Select 4 -> for Consumer's Forget Password."+UIColors.RESET);
				System.out.println();
				
				System.out.print(UIColors.YELLOW_BOLD+"  Please provide any selection from above: "+UIColors.RESET);
				choice=sc.nextInt();
				System.out.println();
				
				switch(choice) {
				case 0 :
					System.out.println("  "+UIColors.BLACK_BOLD+UIColors.TEAL_BACKGROUND+"***Thanks for visiting PAYTRIC, keep visiting***"+UIColors.RESET);
					System.out.println(UIColors.BLACK_BOLD+"-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-"+UIColors.RESET);
					System.out.println();
					break;
				case 1 :
					AdminUI.adminLogin(sc);
					break;
				case 2 :
					ConsumerUI.consumerLogin(sc);
					break;
				case 3 :
					ConsumerUI.consumerRegistration(sc);
					break;
				case 4 :	
					ConsumerUI.forgotPassword(sc);
					break;
				default :
					System.out.println(UIColors.RED_BOLD+"  ***Invalid Input please enter selection in given menu range***"+UIColors.RESET);
					System.out.println(UIColors.BLACK_BOLD+"-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-"+UIColors.RESET);
				    System.out.println();
				}
			}
			while(choice!=0);
		 } catch (InputMismatchException e) {
	 		  System.out.println("\n");
			  System.out.println(UIColors.RED_BOLD+"  ***Invalid Input type please enter numerical input***"+UIColors.RESET);
	          System.out.println(UIColors.BLACK_BOLD+"-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-"+UIColors.RESET);
		 }
		
		sc.close();
	}
}
