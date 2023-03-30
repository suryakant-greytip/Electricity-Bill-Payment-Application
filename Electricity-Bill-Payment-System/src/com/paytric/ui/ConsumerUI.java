package com.paytric.ui;

import java.util.Scanner;

import com.paytric.dao.ConsumerDao;
import com.paytric.dao.ConsumerDaoImpl;
import com.paytric.dto.ConsumerDto;
import com.paytric.exceptions.InvalidUsernameOrPasswordException;
import com.paytric.exceptions.SomethingWentWrongException;

public class ConsumerUI {

	public static void consumerLogin(Scanner sc) {
		System.out.print("  Enter Consumer's Username : ");
		sc.nextLine();
		String userName=sc.nextLine();
		System.out.println();
		System.out.print("  Enter Consumer's Password : ");
		String password=sc.nextLine();
		System.out.println();
		
		if(userName.contains(" ") || password.contains(" ")) {
			System.out.println();
			System.out.println("  ***Username or Password cannot contain space***");
			System.out.println("************************************************************************************************************************************");
			
		}
		else {
			ConsumerDao cDao=new ConsumerDaoImpl();
			try {
				ConsumerDto cDto=cDao.consumerLoginData(userName, password);
				
				System.out.println();
				System.out.println("  ..."+cDto.getFirstName()+" "+cDto.getLastName()+" LoggedIn Successfully...");
			} catch (SomethingWentWrongException | InvalidUsernameOrPasswordException e) {
				System.out.println(e.getMessage());
			}
			System.out.println(".......................................................................................................................................");
		}
	}
}
