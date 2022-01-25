package com.addressbook;

public class ContactBookApplication {	
	public static void main(String[] args) {
		ContactBookService cbs = ContactBookService.getInstance();
		//cbs.addContact();
		cbs.getAllContact();
	}

}
