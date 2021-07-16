package com.reece.addressbook.common;

import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.reece.addressbook.entities.AddressBook;
import com.reece.addressbook.entities.Contact;
import com.reece.addressbook.entities.Phone;

public class PrintUtil 
{
	private final static Log logger = LogFactory.getLog(PrintUtil.class);

	public static void printAddressBook(AddressBook addressBook, List<Contact> lstContact)
	{

		String header ="Address Book Name         Address Book Description";

		String details ="Contact Name         Phone No";

		String dash ="";

		Integer headerLenght = getHeaderValue(addressBook,header).length();

		for(int i=0;  i< headerLenght; i++)
		{
			dash = dash +"-";
		}

		logger.info(dash);

		logger.info(header);

		logger.info(getHeaderValue(addressBook, header));

		dash ="";

		for(int i=0;  i< headerLenght; i++)
		{
			dash = dash +"-";
		}

		logger.info(dash);

		logger.info(details);

		logger.info(dash);

		for(Contact contact : lstContact)
		{
			logger.info(getDetailsValue(contact, details));	
		}

		logger.info(dash);
	}

	private static String getHeaderValue(AddressBook addressBook, String header)
	{
		String value=null;

		String spaces = "";

		value = addressBook.getAddressBookName().toString();

		for(int i=value.length(); i<= header.indexOf("Address Book Description") -1; i++)
		{
			spaces = spaces + " ";
		}

		value = value + spaces + addressBook.getAddressBookDesc();

		return value;
	}

	private static String getDetailsValue(Contact contact, String header)
	{

		String value=null;

		String spaces = "";

		value = contact.getContactName();

		for(int i=value.length(); i<= header.indexOf("Phone No") -1; i++)
		{
			spaces = spaces + " ";
		}

		Phone phone = contact.getPhone();

		Set<String> setPhoneNumber = phone.getPhoneNumber(); 

		String phoneNos ="";

		int i=0;

		for(String phoneNumber: setPhoneNumber)
		{
			if(i > 0)
			{
				phoneNos = phoneNos + "," + phoneNumber ;
			}
			else 
			{
				phoneNos =  phoneNos + phoneNumber;
			}

			i++;
		}


		value = value + spaces + phoneNos;

		return value;
	}

	public static void printUniqueContacts(Set<Contact> setContacts)
	{

		String header ="Contact Name         Phone No";

		String dash ="";

		for(int i=0;  i<header.length(); i++)
		{
			dash = dash +"-";
		}

		logger.info(dash);

		logger.info(header);

		logger.info(dash);

		dash ="";

		for (Contact contact: setContacts)
		{					
			logger.info(getDetailsValue(contact,header));
		}

		logger.info(dash);
	}

}
