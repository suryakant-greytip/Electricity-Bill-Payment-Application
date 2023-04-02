package com.paytric.ui;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

import com.paytric.colors.UIColors;
import com.paytric.dao.AdminDao;
import com.paytric.dao.AdminDaoImpl;
import com.paytric.dao.ComplaintDao;
import com.paytric.dao.ComplaintDaoImpl;
import com.paytric.dao.ConsumerDao;
import com.paytric.dao.ConsumerDaoImpl;
import com.paytric.dao.TransactionDao;
import com.paytric.dao.TransactionDaoImpl;
import com.paytric.dao.Validation;
import com.paytric.dto.BillDto;
import com.paytric.dto.ComplaintDto;
import com.paytric.dto.ComplaintDtoImpl;
import com.paytric.dto.ConsumerDto;
import com.paytric.dto.ConsumerDtoImpl;
import com.paytric.dto.TransactionDto;
import com.paytric.dto.TransactionDtoImpl;
import com.paytric.exceptions.InvalidInputException;
import com.paytric.exceptions.InvalidSecurityCredentails;
import com.paytric.exceptions.InvalidUsernameOrPasswordException;
import com.paytric.exceptions.RecordIsAlreadyUptoDate;
import com.paytric.exceptions.RecordNotFoundException;
import com.paytric.exceptions.SomethingWentWrongException;

public class ConsumerUI {

	public static void consumerLogin(Scanner sc) {
		
			System.out.print(UIColors.BLUE_BOLD+"  Enter Consumer's Username : "+UIColors.RESET);
			String userName=sc.next();
			System.out.println();
			System.out.print(UIColors.BLUE_BOLD+"  Enter Consumer's Password : "+UIColors.RESET);
			sc.nextLine();
			String password=sc.nextLine();
			System.out.println();
			
			if(userName.contains(" ") || password.contains(" ")) {
				System.out.println();
				System.out.println(UIColors.RED_BOLD+"  ***Username or Password cannot contain space***"+UIColors.RESET);
				
			}
			else {
				ConsumerDao cDao=new ConsumerDaoImpl();
				try {
					ConsumerDto cDto=cDao.consumerLoginData(userName, password);
					
					System.out.println();
					System.out.println(UIColors.GREEN_BOLD+"  ***"+UIColors.RESET+UIColors.YELLOW_BOLD+cDto.getFirstName()+" "+cDto.getLastName()+UIColors.RESET+UIColors.GREEN_BOLD+" LoggedIn Successfully***"+UIColors.RESET);
					consumerMenu(sc,cDto);
				} catch (InvalidUsernameOrPasswordException | InvalidInputException | SomethingWentWrongException e) {
					System.out.println(UIColors.RED_BOLD+"  ***"+e.getMessage()+"***"+UIColors.RESET);
				}
			}
			System.out.println(UIColors.BLACK_BOLD+"---x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x----"+UIColors.RESET);
	}
	
	
	
	public static void displaySecurityQuestion() {
		System.out.println(UIColors.BLACK_BOLD+"  Select 1 for : What is your favourite Game?");
		System.out.println("  Select 2 for : What is your High School name?");
		System.out.println("  Select 3 for : What is your favourite Food?");
		System.out.println("  Select 4 for : What is your favourite Color?");
		System.out.println("  Select 5 for : What is your favourite Place?"+UIColors.RESET);
	    System.out.println();
	}
	
	
	
	public static String selectSecurityQuestion(Scanner sc) throws InvalidInputException {
		displaySecurityQuestion();
		String one="What is your favourite Game";
		String two="What is your High School name";
		String three="What is your favourite Food";
		String four="What is your favourite Color";
		String five="What is your favourite Place";
		
        int choice=0;
        System.out.print(UIColors.YELLOW_BOLD+"  Select Your Security Question from above : "+UIColors.RESET);
        choice=Integer.parseInt(sc.next());
        System.out.println();
        
		if(choice>=1 && choice<=5) {
			switch(choice) {
			case 1 :
				return one;
			case 2 :
				return two;
			case 3 :
				return three;
			case 4 :
				return four;
			case 5 :
				return five;
			}
		}
		else {
			System.out.println();
			throw new InvalidInputException(UIColors.RED_BOLD+"  ***Invalid Input please try again later***"+UIColors.RESET);
		}
		return "";
	}
	
	
	
	public static void consumerRegistration(Scanner sc) {
		
		try {
			System.out.print(UIColors.BLUE_BOLD+"  Enter Your Username : "+UIColors.RESET);
			String userName=sc.next();
			sc.nextLine();
			System.out.println();
			System.out.print(UIColors.BLUE_BOLD+"  Enter Your Email : "+UIColors.RESET);
			String email=sc.nextLine();
			System.out.println();
			System.out.print(UIColors.BLUE_BOLD+"  Enter Your Password : "+UIColors.RESET);
			String password=sc.nextLine();
			System.out.println();
			String secQues=selectSecurityQuestion(sc);
			System.out.print(UIColors.BLUE_BOLD+"  Enter Your Security Answer : "+UIColors.RESET); 
			sc.nextLine();
			String secAns=sc.nextLine();
			System.out.println();
			System.out.print(UIColors.BLUE_BOLD+"  Enter Your FirstName : "+UIColors.RESET);
			String fName=sc.nextLine();
			System.out.println();
			System.out.print(UIColors.BLUE_BOLD+"  Enter Your LastName : "+UIColors.RESET);
			String lName=sc.nextLine();
			System.out.println();
			System.out.print(UIColors.BLUE_BOLD+"  Enter Your Address (city)  : "+UIColors.RESET);
			String address=sc.nextLine();
			System.out.println();
			System.out.print(UIColors.BLUE_BOLD+"  Enter Your Mobile No. : "+UIColors.RESET);
			String mob=sc.nextLine();
			System.out.println();
			String conId=null;
			
			ConsumerDao cDao=new ConsumerDaoImpl();
			
			int lastId;
			lastId = cDao.getLastDataId()+1;
			conId="C"+lastId;
			
			if(userName.contains(" ")) {
				System.out.println(UIColors.RED_BOLD+"  ***Username cannot contain space, please try again***"+UIColors.RESET);
			}
			else if(Validation.checkSameUsername(userName)) {
				System.out.println(UIColors.RED_BOLD+"  ***Username already exist***"+UIColors.RESET);
			}
			else if(!email.contains("@gmail.com")) {
				System.out.println(UIColors.RED_BOLD+"  ***Invalid Email Id, please enter valid email Id(for example: xyz@gmail.com )***"+UIColors.RESET);
			}
			else if(Validation.checkSameEmail(email)) {
				System.out.println(UIColors.RED_BOLD+"  ***This email Id is already registered***"+UIColors.RESET);
			}
			else {
				ConsumerDto cDto=new ConsumerDtoImpl(conId, userName, email, password, secQues, secAns, 1, fName, lName, address, mob);
				cDao.registerConsumerData(cDto);
				System.out.println();
				System.out.println(UIColors.GREEN_BOLD+"  ***Consumer Registered Successfully***"+UIColors.RESET);
				
				System.out.print(UIColors.YELLOW_BOLD+"  Do you want to Log In[Yes/No] ? "+UIColors.RESET);
				char ch=sc.next().toLowerCase().charAt(0);
				System.out.println();
				if(ch=='y') {
					consumerLogin(sc);
				}
			}
			
		} catch (InvalidInputException | SomethingWentWrongException | RecordNotFoundException e) {
			System.out.println(UIColors.RED_BOLD+"  xx> "+e.getMessage()+" <xx"+UIColors.RESET);
		}
		
		System.out.println();
		System.out.println(UIColors.BLACK_BOLD+"---x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x----"+UIColors.RESET);
		System.out.println();
	}
	
	
	
	public static void displayConsumerMenu() {
		System.out.println();
		System.out.println(UIColors.BLUE_BOLD+"  ***ROLE OF CONSUMER : "+UIColors.RESET);
		System.out.println(UIColors.BLACK_BOLD+"  Select 0 : To Log Out");
		System.out.println("  Select 1 : To Pay Bills");
		System.out.println("  Select 2 : To View transaction history.");
		System.out.println("  Select 3 : To File a Complaint."+UIColors.RESET);
		System.out.println();
	}
	
	
	
	public static void consumerMenu(Scanner sc,ConsumerDto consumer) throws InvalidInputException {
		try {
			int choice=0;
			do {
				displayConsumerMenu();
				System.out.print(UIColors.YELLOW_BOLD+"  Enter a selection from above : "+UIColors.RESET);
				choice=Integer.parseInt(sc.next());
				System.out.println();
				
				switch(choice) {
				case 0 :
					System.out.println(UIColors.GREEN_BOLD+"  ***"+consumer.getFirstName()+" "+consumer.getLastName()+" Logged Out Successfully***"+UIColors.RESET);
					break;
				case 1 :
					payBills(sc,consumer);
					break;
				case 2 :
					viewTransactionHistory(sc,consumer);
					break;
				case 3 :
					fileComplaint(sc,consumer);
					break;	
				default :
					System.out.println(UIColors.RED_BOLD+"  ***Invalid Input please try again***"+UIColors.RESET);
				}
				
			}
			while(choice!=0);
		} catch (Exception e) {
			System.out.println("  ***"+e.getMessage()+"***");
			throw new InvalidInputException("Invalid Input! please enter valid input");
		}
	}
	
	
	
	public static void showAllPendingBills(Scanner sc,ConsumerDto consumer) {
		
		String consId=consumer.getConsumerId();
		System.out.println();
		
		ConsumerDao cDao=new ConsumerDaoImpl();
		
		try {
			List<BillDto> list=cDao.showAllPendingBillsData(consId);
			
			AdminDao aDao=new AdminDaoImpl();
			String name=aDao.getNameById(list.get(0).getConsumerId());
			
			System.out.println();
			System.out.println(UIColors.BLUE_BOLD+"  ***All Pending Electricity Bill of "+UIColors.RESET+UIColors.YELLOW_BOLD+name+UIColors.RESET+" :- ");
			System.out.println();
			
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
				System.out.println();
				
				System.out.println(UIColors.PURPLE_BOLD+"**********************************************************************************************************************************************************"+UIColors.RESET);
			});
		} catch (SomethingWentWrongException | RecordNotFoundException e) {
			System.out.println(UIColors.RED_BOLD+"  xx> "+e.getMessage()+" <xx"+UIColors.RESET);
		}
		System.out.println(UIColors.BLACK_BOLD+"---x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x----"+UIColors.RESET);
	}
	
	
	
	public static String paymentMethod(Scanner sc) throws InvalidInputException {
		String one="BHIM UPI";
		System.out.println(UIColors.BLACK_BOLD+"  Select 1 for BHIM UPI");
		String two="Credit Card";
		System.out.println("  Select 2 for Credit Card");
		String three="Debit Card";
		System.out.println("  Select 3 for Debit Card");
		String four="Internet Banking";
		System.out.println("  Select 4 for Internet Banking"+UIColors.RESET);
		System.out.println();
		System.out.println();
		System.out.print(UIColors.YELLOW_BOLD+"  Select Payment Method from Above : "+UIColors.RESET);
		int choice=Integer.parseInt(sc.next());
		System.out.println();
		switch(choice) {
		case 1 :
			return one;
		case 2 :
			return two;
		case 3 :
		    return three;
		case 4 :	
			return four;
		default :
			throw new InvalidInputException(UIColors.RED_BOLD+"  ***Invalid Input! please try again."+UIColors.RESET);
		}
		
	}
	
	
	
	public static void payBills(Scanner sc,ConsumerDto consumer) throws InvalidInputException {
		showAllPendingBills(sc,consumer);
		
		System.out.print(UIColors.YELLOW_BOLD+"  Enter Bill Id from above which you have to pay : "+UIColors.RESET);
		String BillId=sc.next();
		System.out.println();
		
		if(BillId.contains(" ")) {
			throw new InvalidInputException(UIColors.RED_BOLD+"  ***Invalid Input! please try again."+UIColors.RESET);
		}
		else {
			ConsumerDao cDao=new ConsumerDaoImpl();
			try {
				String payment=paymentMethod(sc);
				cDao.payConsumerBill(BillId);
				
				TransactionDao tDao=new TransactionDaoImpl();
				BillDto bill=tDao.getBillByBillId(BillId);
				String transId="T"+(tDao.getLastTransactionId()+1);
				TransactionDto tDto=new TransactionDtoImpl(transId, bill.getConsumerId(), bill.getBillId(), bill.getTotalAmount(), LocalDate.now(), payment);
				
				tDao.addTransactionData(tDto);
				
				System.out.println(UIColors.GREEN_BOLD+"  ***Payment Successfull!***"+UIColors.RESET);
			} catch (SomethingWentWrongException | InvalidInputException | RecordNotFoundException | RecordIsAlreadyUptoDate e) {
				System.out.println(UIColors.RED_BOLD+"  xx> "+e.getMessage()+" <xx"+UIColors.RESET);
			}
		}
		System.out.println(UIColors.BLACK_BOLD+"---x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x----"+UIColors.RESET);
	}


	
	public static void viewTransactionHistory(Scanner sc,ConsumerDto consumer) {
		String consId=consumer.getConsumerId();
		System.out.println();
		TransactionDao tDao=new TransactionDaoImpl();
		try {
			List<TransactionDto> list=tDao.viewTransactionHistoryData(consId);
			if(list!=null) {
				System.out.println("............................................................................................................");
				System.out.println();
				AdminDao aDao=new AdminDaoImpl();
				System.out.println(UIColors.BLUE_BOLD+"  ***Transaction details of "+UIColors.RESET+UIColors.YELLOW_BOLD+aDao.getNameById(consId)+UIColors.RESET+" : ");
				System.out.println();
				list.forEach(s->{
					System.out.println(UIColors.BLACK_BOLD+"  Consumer Id : "+s.getConsumerId());
					System.out.println("  Bill Id : "+s.getBillId());
					System.out.println("  Transaction Id : "+s.getTransactionId());
					System.out.println("  Amount Paid : "+s.getAmountPaid());
					System.out.println("  Payment Date : "+s.getPaymentDate());
					System.out.println("  Payment Mathod : "+s.getPaymentMethod()+UIColors.RESET);
					System.out.println(UIColors.PURPLE_BOLD+"***********************************************************************************************************"+UIColors.RESET);
				});
			}
		} catch (SomethingWentWrongException | RecordNotFoundException e) {
			System.out.println(UIColors.RED_BOLD+"  xx> "+e.getMessage()+" <xx"+UIColors.RESET);
		}
		System.out.println();
		System.out.println(UIColors.BLACK_BOLD+"---x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x----"+UIColors.RESET);
		System.out.println();
	}

	
	
	public static void forgotPassword(Scanner sc) {
		try {
			String secQues=selectSecurityQuestion(sc);
			System.out.print(UIColors.BLUE_BOLD+"  Enter Your Security Answer : "+UIColors.RESET);
			sc.nextLine();
			String secAns=sc.nextLine();
			System.out.println();
			System.out.print(UIColors.BLUE_BOLD+"  Enter Your ConsumerId : "+UIColors.RESET);
			String consId=sc.nextLine();
			System.out.println();
			
			ConsumerDao cDao=new ConsumerDaoImpl();
			boolean result=cDao.verifySecurityCredentials(secQues, secAns, consId);
			
			if(result) {
				System.out.print(UIColors.BLUE_BOLD+"  Enter New Password : "+UIColors.RESET);
				String pass=sc.nextLine();
				System.out.println();
				System.out.print(UIColors.BLUE_BOLD+"  Confirm New Password : "+UIColors.RESET);
				String conPass=sc.nextLine();
				System.out.println();
				
				if(pass.equals(conPass)) {
					cDao.updatePassword(conPass, consId);
					System.out.println();
					System.out.println(UIColors.GREEN_BOLD+"  ***Password Updated Successfully***"+UIColors.RESET);
					System.out.println();
				}
				else {
					System.out.println(UIColors.RED_BOLD+"  ***Password Mismatched! please try again***"+UIColors.RESET);
					System.out.println();
				}
			}			
		} catch (InvalidInputException | SomethingWentWrongException | InvalidSecurityCredentails e) {
			System.out.println(UIColors.RED_BOLD+"  xx> "+e.getMessage()+" <xx"+UIColors.RESET);
		}
		System.out.println();
		System.out.println(UIColors.BLACK_BOLD+"---x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x----"+UIColors.RESET);
		System.out.println();
	}


	public static void displayComplaintType() {
		System.out.println(UIColors.BLUE_BOLD+"  ***COMPLAINT MENU :- "+UIColors.RESET);
		System.out.println(UIColors.BLACK_BOLD+"  Select 1 : Power outages or interruptions");
		System.out.println("  Select 2 : High or fluctuating electricity bills");
		System.out.println("  Select 3 : Voltage or frequency fluctuations");
		System.out.println("  Select 4 : Power surges");
		System.out.println("  Select 5 : Electrical safety concerns");
		System.out.println("  Select 6 : Meter issues");
		System.out.println("  Select 7 : Connection or disconnection issues"+UIColors.RESET);
		System.out.println();
		System.out.print(UIColors.YELLOW_BOLD+"  Select Your Complaint Type from above : "+UIColors.RESET);
		
	}
	
	
	
	public static String selectComplaint(Scanner sc) {
		String one="Power outages or interruptions";
		String two="High or fluctuating electricity bills";
		String three="Voltage or frequency fluctuations";
		String four="Power surges";
		String five="Electrical safety concerns";
		String six="Meter issues";
		String seven="Connection or disconnection issues";
		
		displayComplaintType();
		int choice=Integer.parseInt(sc.next());
		System.out.println();
		
		switch(choice) {
		case 1 :
			return one;
		case 2 :
			return two;
		case 3 :
			return three;
		case 4 :
			return four;
		case 5 :
			return five;
		case 6 :
			return six;
		case 7 :
			return seven;
		default :
			System.out.println(UIColors.RED_BOLD+"  Invalid Input! please try again later"+UIColors.RESET);
		}
		
		return "";
	}
	
	
	
	public static void fileComplaint(Scanner sc,ConsumerDto consumer) {
		
		try {
			ComplaintDao compDao=new ComplaintDaoImpl();
			String compId="COMP"+(compDao.getLastComplaintId()+1);
			
			String consId=consumer.getConsumerId();
			System.out.println();
			
			LocalDate compDate=LocalDate.now();

			LocalTime comptime=LocalTime.now();
			
			String compType=selectComplaint(sc);
			
			System.out.print(UIColors.BLUE_BOLD+"  Provide a Short Description about your complaint  : "+UIColors.RESET);
			sc.nextLine();
			String compDesc=sc.nextLine();
			System.out.println();
			
			String assignedTo="Admin";
			
			int status=0;
			
			LocalDate resDate=null;
			
			LocalTime resTime=null;
			
			
			ComplaintDto compDto=new ComplaintDtoImpl(compId, consId, compDate, comptime, compType, compDesc, assignedTo, status, resDate, resTime);
			
			compDao.fileComplaintData(compDto);
			System.out.println(UIColors.GREEN_BOLD+"  ***Complaint Filed Successfully***"+UIColors.RESET);
		}catch (SomethingWentWrongException | RecordNotFoundException e) {
			System.out.println(UIColors.RED_BOLD+"  xx> "+e.getMessage()+" <xx"+UIColors.RESET);
		}
		System.out.println();
		System.out.println(UIColors.BLACK_BOLD+"---x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x----"+UIColors.RESET);
		System.out.println();
	}

	
}
