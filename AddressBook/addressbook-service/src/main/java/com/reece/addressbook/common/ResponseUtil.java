package com.reece.addressbook.common;

import java.util.List;

import com.reece.addressbook.entities.AddressBook;
import com.reece.addressbook.entities.Contact;
import com.reece.addressbook.entities.Response;

/**
 * Utility class for creating a response, that will sent to service caller over the wire.
 * 
 * @author Sarabjeet
 *
 */
public class ResponseUtil 
{
	/**
	 * Method for creating a response, it is used by service impl class
	 * 
	 * @param errorNo
	 * @param errorMessage
	 * @param successMessage
	 * @param lstAddressBook
	 * @param listContact
	 * @return
	 */
	public static Response createResponse
	(
			Integer errorNo,
			String errorMessage, 
			String successMessage,
			List<AddressBook> lstAddressBook,
			List<Contact> listContact
 	)
	{
		 Response response = new Response();
		 
		 response.setErrorNo(errorNo);
		 
		 response.setErrorMessage(errorMessage);
		 
		 response.setSuccessMessage(successMessage);
	
		 if(null != lstAddressBook)
		 {
			 response.setAddressBook(lstAddressBook);
		 }
		 
		 if(null != listContact)
		 {
			 response.setContact(listContact);
		 }
		 
		 return response;
	}

}
