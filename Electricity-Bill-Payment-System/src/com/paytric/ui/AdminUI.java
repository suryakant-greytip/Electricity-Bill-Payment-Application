package com.paytric.ui;

import java.util.List;
import java.util.Scanner;

import com.paytric.dao.AdminDao;
import com.paytric.dao.AdminDaoImpl;
import com.paytric.dto.AdminDto;
import com.paytric.dto.ConsumerDto;
import com.paytric.exceptions.InvalidUsernameOrPasswordException;
import com.paytric.exceptions.RecordNotFoundException;
import com.paytric.exceptions.SomethingWentWrongException;

public class AdminUI {

	public static void adminLogin(Scanner sc) {
		System.out.print("  Enter admin's User-Name: ");
		String username=sc.next();
		System.out.println();
		System.out.print("  Enter admin's Password: ");
		String Password=sc.next();
		System.out.println();
		
		AdminDao adminDao=new AdminDaoImpl();
		try {
			AdminDto adminDto=adminDao.adminLoginData(username, Password);
			System.out.println("  ***"+adminDto.getAdminUserName()+" Logged in Successfully***");
			System.out.println();
			adminMenu(sc);
		} catch (InvalidUsernameOrPasswordException | SomethingWentWrongException e) {
			System.out.println("***"+e.getMessage()+"***");
			System.out.println();
		}
	}
	
	public static void viewAllConsumers() {
		
		AdminDao adminDao=new AdminDaoImpl();
		List<ConsumerDto> list=null;
		try {
			list=adminDao.viewAllConsumerData();
		} catch (SomethingWentWrongException | RecordNotFoundException e) {
			System.out.println(e.getMessage());
		}
		if(list!=null) {
			list.forEach(s->{
				System.out.println("Consumer-> [ConsumerFirstName: " + s.getFirstName()+ "  |  ConsumerLastName: " + s.getLastName() +"  |  consumerId: " + s.getConsumerId() + "  |  consumerUserName: " + s.getConsumerUserName()
						+ "  |  consumerEmail: " + s.getConsumerEmail() + "  |  isActive: " + s.getIsActive()
						+ "  |  address=" + s.getAddress() + "  |  mobileNum=" + s.getMobileNum() + "]");
			});
		}
		System.out.println();
		System.out.println("---x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x----");
		System.out.println();
	}
	
	public static void displayAdminMenu() {
		System.out.println("  **ROLE OF THE ADMINISTRATOR :- ");
		System.out.println("  Select 0 -> To Log Out.");
		System.out.println("  Select 1 -> To View all Consumers.");
		System.out.println("  Select 2 -> To View the bill of the consumer. ");
		System.out.println("  Select 3 -> To View all the bills.");
		System.out.println("  Select 4 -> To View all paid and pending bills.");
		System.out.println("  Select 5 -> To Delete Consumer.");
		System.out.println();
		System.out.print("  Enter a selection from above: ");
	}
	
	public static void adminMenu(Scanner sc) {
		int choice=0;
		do {
			displayAdminMenu();
			choice=sc.nextInt();
			System.out.println();
			switch(choice) {
			case 0 :
				System.out.println("  ***Bye Bye Admin, administrator Logged Out Successfully***");
				System.out.println();
				break;
			case 1 :
				viewAllConsumers();
				break;
			case 2 :
				
				break;
			case 3 :
				
				break;
			case 4 :
				
				break;
			case 5 :
				
				break;
			default :
				System.out.println("  ***Invalid Input please try again***");
				System.out.println();
			}
		}
		while(choice!=0);
	}
}
