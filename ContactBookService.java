package com.addressbook;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactBookService {
	private static ContactBookService instance;
	ContactBookDao cbd = new ContactBookDao();
	Scanner sc = new Scanner(System.in);
	private ContactBookService() {
		
	}
	
	public static synchronized ContactBookService getInstance() {
		if (instance == null) {
			instance = new ContactBookService();
		}
		return instance;		
	}
	
	public void addContact() {
		ContactBook cb = new ContactBook();
		System.out.println("Enter First name: ");
		cb.setFirstName(sc.next());
		System.out.println("Enter last name: ");
		cb.setLastName(sc.next());
		System.out.println("Enter address: ");
		cb.setAddress(sc.next());
		System.out.println("Enter city: ");
		cb.setCity(sc.next());
		System.out.println("Enter state: ");
		cb.setState(sc.next());
		System.out.println("Enter Zipcode: ");
		cb.setZip(sc.nextInt());
		System.out.println("Enter email id: ");
		String email = sc.next();
		if(checkPattern(email, "email")) {
			cb.setEmail(email);
			System.out.println("Enter mobile number: ");
			String mobile = sc.next();
			if(checkPattern(mobile, "mobile")) {
				cb.setPhoneNumber(Long.valueOf(mobile));
				cbd.addContactToDb(cb);
				System.out.println("Details added");
			}else {
				System.out.println("Invalid mobile number");
			}
		}else {
			System.out.println("Invalid email");
		}
		
	}
	
	public boolean checkPattern(String value,String field) {
		boolean result = false;
		Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");
		Pattern mobPattern = Pattern.compile("[6-9][0-9]{9}");
		if(field.equals("email")) {
			Matcher emailMatch = emailPattern.matcher(value);
			if(emailMatch.matches()) {
				result = true;
			}
		}else if(field.equals("mobile")) {
			Matcher mobileMatch = mobPattern.matcher(value);
			if(mobileMatch.matches()) {
				result = true;
			}		
		}
		return result;		
	}	
	
	public void getAllContact() {
		List<ContactBook> contactlist = cbd.getAllContact();
		for(ContactBook cb : contactlist) {
			System.out.println(cb);
		}
	}
}
