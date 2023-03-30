package com.paytric.ui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.paytric.dao.AdminDao;
import com.paytric.dao.AdminDaoImpl;
import com.paytric.dto.AdminDto;
import com.paytric.dto.BillDto;
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
				System.out.println("  Consumer-> [ConsumerFirstName: " + s.getFirstName()+ "  |  ConsumerLastName: " + s.getLastName() +"  |  consumerId: " + s.getConsumerId() + "  |  consumerUserName: " + s.getConsumerUserName()
						+ "  |  consumerEmail: " + s.getConsumerEmail() + "  |  isActive: " + s.getIsActive()
						+ "  |  address=" + s.getAddress() + "  |  mobileNum=" + s.getMobileNum() + "]");
			});
		}
		System.out.println();
		System.out.println("---x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x----");
		System.out.println();
	}

	
	public static void displayAdminMenu() {
		System.out.println("  **ROLE OF THE ADMINISTRATOR :- ");
		System.out.println("  Select 0 -> To Log Out.");
		System.out.println("  Select 1 -> To View all Consumers.");
		System.out.println("  Select 2 -> To View the bill of the consumer by there Id. ");
		System.out.println("  Select 3 -> To View all the bills.");
		System.out.println("  Select 4 -> To View all paid bills.");
		System.out.println("  Select 5 -> To View all pending bills.");
		System.out.println("  Select 6 -> To Delete Consumer.");
		System.out.println();
		System.out.print("  Enter a selection from above: ");
	}

	
	public static void viewConsumerBillById(Scanner sc) {
		System.out.print("  Enter consumerId: ");
		String conId=sc.next();
		System.out.println();
		
		AdminDao aDao=new AdminDaoImpl();
		List<BillDto> list=null;
		try {
			list=aDao.viewConsumerBillDataById(conId);
			
			if(list!=null) {
				list.forEach(s->{
					
					String name=null;
					try {
						name=aDao.getNameById(s.getConsumerId());
					} catch (SomethingWentWrongException | RecordNotFoundException e) {
						System.out.println(e.getMessage());
					}
					
					System.out.println("**********************************************************************************************************************************************************");
					System.out.println();
					System.out.println("  ***Electricity Bill of "+name+" :- ");
					System.out.println();
					System.out.println("  ConsumerId : "+s.getConsumerId());
					System.out.println("  BillingId : "+s.getBillId());
					System.out.println("  Previous Reading : "+s.getPrevReading());
					System.out.println("  Current Reading : "+s.getCurrReading());
					System.out.println("  Units Consumed : "+s.getUnitConsumed());
					System.out.println("  Unit Rate : "+s.getUnitRate());
					System.out.println("  Total Amount : "+s.getTotalAmount());
					System.out.println("  tax : "+s.getTax());
					System.out.println("  Bill Starting Date : "+s.getStartDate());
					System.out.println("  Bill Ending Date : "+s.getEndDate());
					System.out.println("  Billing Date : "+s.getBilling_date());
					System.out.println("  Last Payment Date : "+s.getDueDate());
					System.out.println("  Payment Status : "+(s.getIsPaid() == 0 ? "Payment Is Pending" : "Payment Is Paid"));

				});
			}
		} catch (SomethingWentWrongException | RecordNotFoundException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("**********************************************************************************************************************************************************");
	}

	
	
	public static void viewAllBills() {
		
		AdminDao aDao=new AdminDaoImpl();
		List<BillDto> list=null;
		try {
			list=aDao.viewAllBillsData();
			
			if(list!=null) {
				list.forEach(s->{
					
					String name=null;
					try {
						name=aDao.getNameById(s.getConsumerId());
					} catch (SomethingWentWrongException | RecordNotFoundException e) {
						System.out.println(e.getMessage());
					}
					
					System.out.println("**********************************************************************************************************************************************************");
					System.out.println();
					System.out.println("  ***Bill for "+name+" :- ");
					System.out.println();
					System.out.println("  ConsumerId : "+s.getConsumerId());
					System.out.println("  BillingId : "+s.getBillId());
					System.out.println("  Previous Reading : "+s.getPrevReading());
					System.out.println("  Current Reading : "+s.getCurrReading());
					System.out.println("  Units Consumed : "+s.getUnitConsumed());
					System.out.println("  Unit Rate : "+s.getUnitRate());
					System.out.println("  Total Amount : "+s.getTotalAmount());
					System.out.println("  tax : "+s.getTax());
					System.out.println("  Bill Starting Date : "+s.getStartDate());
					System.out.println("  Bill Ending Date : "+s.getEndDate());
					System.out.println("  Billing Date : "+s.getBilling_date());
					System.out.println("  Last Payment Date : "+s.getDueDate());
					System.out.println("  Bill Payment Status : "+(s.getIsPaid() == 0 ? "Payment Is Pending" : "Payment Is Paid"));
				});
			}
		} catch (SomethingWentWrongException | RecordNotFoundException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("**********************************************************************************************************************************************************");
	}
	
	
	
    public static void viewAllPaidBills() {
		
		AdminDao aDao=new AdminDaoImpl();
		List<BillDto> list=null;
		try {
			list=aDao.viewAllPaidBillsData();
			
			if(list!=null) {
				list.forEach(s->{
					
					String name=null;
					try {
						name=aDao.getNameById(s.getConsumerId());
					} catch (SomethingWentWrongException | RecordNotFoundException e) {
						System.out.println(e.getMessage());
					}
					
					System.out.println("**********************************************************************************************************************************************************");
					System.out.println();
					System.out.println("  ***Bill for "+name+" :- ");
					System.out.println();
					System.out.println("  ConsumerId : "+s.getConsumerId());
					System.out.println("  BillingId : "+s.getBillId());
					System.out.println("  Previous Reading : "+s.getPrevReading());
					System.out.println("  Current Reading : "+s.getCurrReading());
					System.out.println("  Units Consumed : "+s.getUnitConsumed());
					System.out.println("  Unit Rate : "+s.getUnitRate());
					System.out.println("  Total Amount : "+s.getTotalAmount());
					System.out.println("  tax : "+s.getTax());
					System.out.println("  Bill Starting Date : "+s.getStartDate());
					System.out.println("  Bill Ending Date : "+s.getEndDate());
					System.out.println("  Billing Date : "+s.getBilling_date());
					System.out.println("  Last Payment Date : "+s.getDueDate());
					System.out.println("  Bill Payment Status : "+(s.getIsPaid() == 0 ? "Payment Is Pending" : "Payment Is Paid"));
					
				});
			}
		} catch (SomethingWentWrongException | RecordNotFoundException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("**********************************************************************************************************************************************************");
	}
	
    
    
    public static void viewAllPendingBills() {
		
		AdminDao aDao=new AdminDaoImpl();
		List<BillDto> list=null;
		try {
			list=aDao.viewAllPendingBillsData();
			
			if(list!=null) {
				list.forEach(s->{
					
					String name=null;
					try {
						name=aDao.getNameById(s.getConsumerId());
					} catch (SomethingWentWrongException | RecordNotFoundException e) {
						System.out.println(e.getMessage());
					}
					
					System.out.println("**********************************************************************************************************************************************************");
					System.out.println();
					System.out.println("  ***Bill for "+name+" :- ");
					System.out.println();
					System.out.println("  ConsumerId : "+s.getConsumerId());
					System.out.println("  BillingId : "+s.getBillId());
					System.out.println("  Previous Reading : "+s.getPrevReading());
					System.out.println("  Current Reading : "+s.getCurrReading());
					System.out.println("  Units Consumed : "+s.getUnitConsumed());
					System.out.println("  Unit Rate : "+s.getUnitRate());
					System.out.println("  Total Amount : "+s.getTotalAmount());
					System.out.println("  tax : "+s.getTax());
					System.out.println("  Bill Starting Date : "+s.getStartDate());
					System.out.println("  Bill Ending Date : "+s.getEndDate());
					System.out.println("  Billing Date : "+s.getBilling_date());
					System.out.println("  Last Payment Date : "+s.getDueDate());
					System.out.println("  Bill Payment Status : "+(s.getIsPaid() == 0 ? "Payment Is Pending" : "Payment Is Paid"));
					
				});
			}
		} catch (SomethingWentWrongException | RecordNotFoundException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("**********************************************************************************************************************************************************");
	}
    
	
    
    public static void deleteConsumerById(Scanner sc) {
    	System.out.print("  Enter consumerId : ");
    	String consId=sc.next();
    	
    	AdminDao aDao=new AdminDaoImpl();
		try {
			aDao.deleteConsumerDataById(consId);
			
			System.out.println("  Consumer with consumerId '"+consId+"' is deleted successfully.");
		} catch (SomethingWentWrongException | RecordNotFoundException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("**********************************************************************************************************************************************************");
    }
    
    public static void adminMenu(Scanner sc) {
		int choice=0;
		do {
			try {
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
				    viewConsumerBillById(sc);
					break;
				case 3 :
					viewAllBills();
					break;
				case 4 :
					viewAllPaidBills();
					break;
				case 5 :
					viewAllPendingBills();
					break;
				case 6 :
					deleteConsumerById(sc);
					break;
				default :
					System.out.println("  ***Invalid Input please try again***");
					System.out.println();
				}
			} catch (InputMismatchException e) {
				
				System.out.println("  ***Invalid Input type please enter numerical input***");
				System.out.println();
			}
		}
		while(choice!=0);
	}
}
