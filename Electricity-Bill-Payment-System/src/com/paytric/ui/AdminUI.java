package com.paytric.ui;

import java.awt.Color;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.paytric.colors.UIColors;
import com.paytric.dao.AdminDao;
import com.paytric.dao.AdminDaoImpl;
import com.paytric.dto.AdminDto;
import com.paytric.dto.BillDto;
import com.paytric.dto.BillDtoImpl;
import com.paytric.dto.ConsumerDto;
import com.paytric.exceptions.InvalidUsernameOrPasswordException;
import com.paytric.exceptions.RecordNotFoundException;
import com.paytric.exceptions.SomethingWentWrongException;

public class AdminUI {

	public static void adminLogin(Scanner sc) {
		System.out.print(UIColors.BLUE_BOLD+"  Enter admin's User-Name : "+UIColors.RESET);
		String username=sc.next();
		System.out.println();
		System.out.print(UIColors.BLUE_BOLD+"  Enter admin's Password : "+UIColors.RESET);
		String Password=sc.next();
		System.out.println();
		
		
		AdminDao adminDao=new AdminDaoImpl();
		try {
			AdminDto adminDto=adminDao.adminLoginData(username, Password);
			System.out.println(UIColors.GREEN_BOLD+"  ***"+adminDto.getAdminUserName()+" Logged in Successfully***"+UIColors.RESET);
			System.out.println();
			adminMenu(sc);
		} catch (InvalidUsernameOrPasswordException | SomethingWentWrongException e) {
			System.out.println(UIColors.RED_BOLD+"  ***"+e.getMessage()+"***"+UIColors.RESET);
			System.out.println();
		}
	}

	
	
	public static void viewAllConsumers() {
		
		AdminDao adminDao=new AdminDaoImpl();
		List<ConsumerDto> list=null;
		try {
			list=adminDao.viewAllConsumerData();
			
			if(list!=null) {
				list.forEach(s->{
					System.out.println(UIColors.BLACK_BOLD+"  Consumer-> [ConsumerFirstName: " + s.getFirstName()+ "  |  ConsumerLastName: " + s.getLastName() +"  |  consumerId: " + s.getConsumerId() + "  |  consumerUserName: " + s.getConsumerUserName()
							+ "  |  consumerEmail: " + s.getConsumerEmail() + "  |  isActive: " + s.getIsActive()
							+ "  |  address=" + s.getAddress() + "  |  mobileNum=" + s.getMobileNum() + "]"+UIColors.RESET);
				});
			}
		} catch (SomethingWentWrongException | RecordNotFoundException e) {
			System.out.println(UIColors.RED_BOLD+"  xx> "+e.getMessage()+" <xx"+UIColors.RESET);
		}
		
		System.out.println();
		System.out.println(UIColors.BLACK_BOLD+"---x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x----"+UIColors.RESET);
		System.out.println();
	}

	
	
	public static void displayAdminMenu() {
		System.out.println(UIColors.BLUE_BOLD+"  **ROLE OF THE ADMINISTRATOR :- "+UIColors.RESET);
		System.out.println(UIColors.BLACK_BOLD+"  Select 0 -> To Log Out.");
		System.out.println("  Select 1 -> To View all Consumers.");
		System.out.println("  Select 2 -> To View the bill of the consumer by there Id. ");
		System.out.println("  Select 3 -> To View all the bills.");
		System.out.println("  Select 4 -> To View all paid bills.");
		System.out.println("  Select 5 -> To View all pending bills.");
		System.out.println("  Select 6 -> To Delete Consumer.");
		System.out.println("  Select 7 -> To Generate Consumers Bill."+UIColors.RESET);
		System.out.println();
		System.out.print(UIColors.YELLOW_BOLD+"  Enter a selection from above: "+UIColors.RESET);
	}

	
	
	public static void viewConsumerBillById(Scanner sc) {
		System.out.print(UIColors.BLUE_BOLD+"  Enter consumerId: "+UIColors.RESET);
		String conId=sc.next();
		System.out.println();
		
		AdminDao aDao=new AdminDaoImpl();
		List<BillDto> list=null;
		try {
			list=aDao.viewConsumerBillDataById(conId);
			
			String name=null;
			try {
				name=aDao.getNameById(list.get(0).getConsumerId());
			} catch (SomethingWentWrongException | RecordNotFoundException e) {
				System.out.println(UIColors.RED_BOLD+"  xx> "+e.getMessage()+" <xx"+UIColors.RESET);
			}
			
			
			System.out.println();
			System.out.println(UIColors.BLUE_BOLD+"  ***Electricity Bill of "+UIColors.YELLOW_BOLD+name+UIColors.RESET+UIColors.BLACK+" :- "+UIColors.RESET);
			System.out.println();
			
			if(list!=null) {
				list.forEach(s->{
					
					System.out.println(UIColors.BLACK_BOLD+"  ConsumerId : "+s.getConsumerId());
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
					System.out.println("  Payment Status : "+(s.getIsPaid() == 0 ? "Payment Is Pending" : "Payment Is Paid"+UIColors.RESET));

					System.out.println(UIColors.PURPLE_BOLD+"**********************************************************************************************************************************************************"+UIColors.RESET);
				});
			}
		} catch (SomethingWentWrongException | RecordNotFoundException e) {
			System.out.println(UIColors.RED_BOLD+"  xx> "+e.getMessage()+" <xx"+UIColors.RESET);
		}
		System.out.println(UIColors.BLACK_BOLD+"---x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x---"+UIColors.RESET);
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
					
					System.out.println();
					System.out.println(UIColors.BLUE_BOLD+"  ***Bill for "+UIColors.RESET+UIColors.YELLOW_BOLD+name+UIColors.RESET+UIColors.BLACK+" :- "+UIColors.RESET);
					System.out.println();
					
					System.out.println(UIColors.BLACK_BOLD+"  ConsumerId : "+s.getConsumerId());
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
					System.out.println("  Bill Payment Status : "+(s.getIsPaid() == 0 ? "Payment Is Pending" : "Payment Is Paid"+UIColors.RESET));
				
					System.out.println(UIColors.PURPLE_BOLD+"**********************************************************************************************************************************************************"+UIColors.RESET);
				});
			}
		} catch (SomethingWentWrongException | RecordNotFoundException e) {
			System.out.println(UIColors.RED_BOLD+"  xx> "+e.getMessage()+" <xx"+UIColors.RESET);
		}
		System.out.println(UIColors.BLACK_BOLD+"---x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x---"+UIColors.RESET);
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
						System.out.println(UIColors.RED_BOLD+"  xx>"+e.getMessage()+UIColors.RESET);
					}
					
					System.out.println();
					System.out.println(UIColors.BLUE_BOLD+"  ***Bill for "+UIColors.YELLOW_BOLD+name+UIColors.RESET+UIColors.BLACK+" :- "+UIColors.RESET);
					System.out.println();
					System.out.println(UIColors.BLACK_BOLD+"  ConsumerId : "+s.getConsumerId());
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
					System.out.println("  Bill Payment Status : "+(s.getIsPaid() == 0 ? "Payment Is Pending" : "Payment Is Paid"+UIColors.RESET));
					
					System.out.println(UIColors.PURPLE_BOLD+"**********************************************************************************************************************************************************"+UIColors.RESET);
				});
			}
		} catch (SomethingWentWrongException | RecordNotFoundException e) {
			System.out.println(UIColors.RED_BOLD+"  xx>"+e.getMessage()+UIColors.RESET);
		}
		System.out.println(UIColors.BLACK_BOLD+"---x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x---"+UIColors.RESET);
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
					
					System.out.println();
					System.out.println(UIColors.BLUE_BOLD+"  ***Bill for "+UIColors.YELLOW_BOLD+name+UIColors.RESET+UIColors.BLACK+" :- "+UIColors.RESET);
					System.out.println();
					System.out.println(UIColors.BLACK_BOLD+"  ConsumerId : "+s.getConsumerId());
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
					System.out.println("  Bill Payment Status : "+(s.getIsPaid() == 0 ? "Payment Is Pending" : "Payment Is Paid"+UIColors.RESET));
					
					System.out.println(UIColors.PURPLE_BOLD+"**********************************************************************************************************************************************************"+UIColors.RESET);
				});
			}
		} catch (SomethingWentWrongException | RecordNotFoundException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(UIColors.BLACK_BOLD+"---x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x---"+UIColors.RESET);
	}
    
	
    
    public static void deleteConsumerById(Scanner sc) {
    	System.out.print(UIColors.BLUE_BOLD+"  Enter consumerId : "+UIColors.RESET);
    	String consId=sc.next();
    	
    	AdminDao aDao=new AdminDaoImpl();
		try {
			aDao.deleteConsumerDataById(consId);
			
			System.out.println(UIColors.GREEN_BOLD+"  ***Consumer with consumerId '"+consId+"' is deleted successfully***"+UIColors.RESET);
		} catch (SomethingWentWrongException | RecordNotFoundException e) {
			System.out.println(UIColors.RED_BOLD+"  xx>"+e.getMessage()+UIColors.RESET);
		}
		System.out.println(UIColors.BLACK_BOLD+"---x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x---"+UIColors.RESET);
    }
    
    
    
    public static void generateConsumersBill(Scanner sc) {
    	
    	try {
    		System.out.print(UIColors.BLUE_BOLD+"  Enter ConsumerId : "+UIColors.RESET);
        	String consId=sc.next();
        	System.out.println();
        	
        	if(!consId.contains("c")&& !consId.contains("C")) {
        		System.out.println();
        		System.out.println(UIColors.RED_BOLD+"  ***Invalid ConsumerId! please enter valid cosumerId***"+UIColors.RESET);
        		System.out.println();
        		return;
        	}
        	
        	AdminDao aDao=new AdminDaoImpl();   	
        	String billId="B"+(aDao.getLastBillId()+1);
        	
        	System.out.print(UIColors.BLUE_BOLD+"  Enter Previous Reading : "+UIColors.RESET);
        	double prevRead=sc.nextDouble();
        	System.out.println();
        	
        	System.out.print(UIColors.BLUE_BOLD+"  Enter Current Reading : "+UIColors.RESET);
        	double currRead=sc.nextDouble();
        	System.out.println();
        	if(prevRead>currRead) {
        		System.out.println();
        		System.out.println(UIColors.RED_BOLD+"  ***Invalid Reading! Previous Reading can't be greater than Current Reading***"+UIColors.RESET);
        		System.out.println();
        		return;
        	}
        	
        	double unitConsumed=Math.abs(currRead-prevRead);
        	
        	System.out.print(UIColors.BLUE_BOLD+"  Enter Unit Rate : "+UIColors.RESET);
        	int unitRate=sc.nextInt();
        	System.out.println();
        	
        	System.out.print(UIColors.BLUE_BOLD+"  Enter Bill Tax : "+UIColors.RESET);
        	double tax=sc.nextDouble();
        	System.out.println();
        	
        	double totalAmt=(unitConsumed*unitRate)+((unitConsumed*unitRate)*(tax/100));
        	
        	System.out.print(UIColors.BLUE_BOLD+"  Enter StratDate of Reading : "+UIColors.RESET);
        	LocalDate sDate=LocalDate.parse(sc.next());
        	System.out.println();
        	
        	System.out.print(UIColors.BLUE_BOLD+"  Enter EndDate of Reading : "+UIColors.RESET);
        	LocalDate eDate=LocalDate.parse(sc.next());
        	System.out.println();
        	
        	LocalDate billingDate=LocalDate.now();
        	
        	LocalDate dueDate=billingDate.plusDays(10);
        	
        	int isPaid=0;
        	
        	BillDto bDto=new BillDtoImpl(consId,billId,prevRead,currRead,unitConsumed,unitRate,totalAmt
        			,tax,sDate,eDate,billingDate,dueDate,isPaid);
        	
        	aDao.generateBillData(bDto);
        	System.out.println();
        	System.out.println(UIColors.GREEN_BOLD+"  ***Bill for ConsumerId "+consId+" is generated successfully***"+UIColors.RESET);
		} catch (SomethingWentWrongException | RecordNotFoundException e) {
			System.out.println(UIColors.RED_BOLD+"  xx>"+e.getMessage()+UIColors.RESET);
		}
    	System.out.println(UIColors.BLACK_BOLD+"---x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x---"+UIColors.RESET);
    }
    
    
    
    public static void adminMenu(Scanner sc) {
		int choice=0;
		
		try {
			do {
				
				displayAdminMenu();
				choice=sc.nextInt();
				System.out.println();
				
				switch(choice) {
				case 0 :
					System.out.println(UIColors.GREEN_BOLD+"  ***Bye Bye Admin, administrator Logged Out Successfully***"+UIColors.RESET);
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
				case 7 :
					generateConsumersBill(sc);
					break;
				default :
					System.out.println(UIColors.RED_BOLD+"  xx> Invalid Input please try again <xx"+UIColors.RESET);
					System.out.println();
				}
			}
			while(choice!=0);
		} catch (InputMismatchException e) {
			
			System.out.println(UIColors.RED_BOLD+"  xx> Invalid Input type please enter numerical input <xx"+UIColors.RESET);
			System.out.println();
		}
		
	}
    
    
}
