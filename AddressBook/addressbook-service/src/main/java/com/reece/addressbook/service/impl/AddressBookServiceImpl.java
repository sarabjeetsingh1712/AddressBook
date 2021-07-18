package com.reece.addressbook.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.reece.addressbook.common.PrintUtil;
import com.reece.addressbook.common.ResponseUtil;
import com.reece.addressbook.entities.AddressBook;
import com.reece.addressbook.entities.Contact;
import com.reece.addressbook.entities.Response;
import com.reece.addressbook.repository.AddressBookRepository;
import com.reece.addressbook.repository.ContactRepository;

/**
 * Implementation Class for AddressBookService. It will interact with repositories for CRUD operations
 * 
 * @author Sarabjeet
 */
@Component
public class AddressBookServiceImpl 
{
	@Autowired
	private AddressBookRepository addressBookRepository;

	@Autowired
	private ContactRepository contactRepository;


	/**
	 * The method will return all the contacts belonging to all the AddressBooks
	 */
	public Response getAllAddressBooks() 
	{	
		List<AddressBook> lstAddressBook = addressBookRepository.getAllAddressBooks();

		if(lstAddressBook!=null && !lstAddressBook.isEmpty())
		{
			return ResponseUtil.createResponse
					(
							0, 
							"", 
							"", 
							addressBookRepository.getAllAddressBooks(), 
							null
							);	
		}
		else
		{
			return ResponseUtil.createResponse(
					1,
					"Address Book does not exist",
					"",
					null,
					null
					);
		}

	}

	public Response updateAddressBooks(List<AddressBook> addressBooks) 
	{
		addressBookRepository.addOrUpdateAddressBooks(addressBooks);

		List<AddressBook> lstAddressBooks =addressBookRepository.getAllAddressBooks();

		return ResponseUtil.createResponse
				(
						0, 
						"", 
						"", 
						lstAddressBooks,
						null
						);
	}

	public Response updateAddressBook(AddressBook addressBook) 
	{
		addressBookRepository.addOrUpdateAddressBook(addressBook);

		List<AddressBook> lstAddressBook =addressBookRepository.getAllAddressBooks();

		return ResponseUtil.createResponse(0, "", "", lstAddressBook, null);
	}

	public Response deleteAddressBook(Integer addressBookId)
	{	
		Optional<AddressBook> optAddressBookToBeDeleted = addressBookRepository.getAddressBookById
				(
						addressBookId
						);

		List<Contact> lstContact =   contactRepository.getContactByAddressBookId(addressBookId);

		AddressBook addressBookToBeDeleted = null;

		if(optAddressBookToBeDeleted.isPresent())
		{
			addressBookToBeDeleted = optAddressBookToBeDeleted.get();

			addressBookRepository.deleteAddressBook(addressBookToBeDeleted.getId());

			//All the contacts within address book will also be deleted
			if(lstContact!=null && !lstContact.isEmpty())
			{
				deleteAllContactsForAddressBook(lstContact);
			}

			List<AddressBook> lstAddressBook =addressBookRepository.getAllAddressBooks();

			return ResponseUtil.createResponse(
					0,
					"",
					addressBookToBeDeleted.getAddressBookName() +" is Deleted",
					lstAddressBook,
					null
					);
		}
		else
		{
			return ResponseUtil.createResponse(
					1,
					"Address Book with "+ addressBookId +" does no exist",
					"",
					null,
					null
					);
		}
	}


	public Response getAddressBookById(Integer addressBookId) 
	{

		Optional<AddressBook> optAddressBook = addressBookRepository.getAddressBookById
				(
						addressBookId	
						);

		if(optAddressBook.isPresent())
		{
			AddressBook fetchedAddress = optAddressBook.get();

			List<AddressBook> lstAddressBook = new ArrayList<AddressBook>();

			lstAddressBook.add(fetchedAddress);

			return ResponseUtil.createResponse(
					0,
					"",
					"",
					lstAddressBook,
					null
					);
		}
		else
		{
			return ResponseUtil.createResponse(
					1,
					"Address Book with id "+ addressBookId +" does no exist",
					"",
					null,
					null
					);
		}
	}

	/**
	 * The method will invoke a all to ContactRepository and 
	 * return all the contact belongs to the AddressBook
	 * 
	 * @param addressBookId
	 * 
	 */
	public Response getAddressBookContacts(Integer addressBookId) 
	{ 	  
		List<AddressBook> lstAddressBook  = null;

		Optional<AddressBook> optAddressBook = addressBookRepository.getAddressBookById(addressBookId);

		if(!optAddressBook.isPresent())
		{
			return ResponseUtil.createResponse
					(
							1,
							"",
							"Address Book does not exist with Id " + addressBookId,
							null,
							null
							);
		}
		else
		{
			lstAddressBook = new ArrayList<AddressBook>();

			lstAddressBook.add(optAddressBook.get());
		}

		List<Contact> lstContact =   contactRepository.getContactByAddressBookId(addressBookId);

		if(lstContact!=null)
		{
			return ResponseUtil.createResponse
					(
							0,
							"",
							"",
							lstAddressBook,
							lstContact
							);
		}
		else
		{
			return ResponseUtil.createResponse
					(
							1,
							"",
							"There are no contacts in the address book",
							null,
							null
							);
		}
	}

	/**
	 * The method will print AddressBook information along with all the contacts 
	 * belongs to it.
	 * 
	 * @param addressBookId
	 * 
	 */
	public Response printAddressBook(Integer addressBookId) 
	{
		Optional<AddressBook> optAddressBook = addressBookRepository.getAddressBookById
				(
						addressBookId	
						);



		if(optAddressBook.isPresent())
		{
			List<Contact> lstContact =   contactRepository.getContactByAddressBookId(addressBookId);

			AddressBook fetchedAddress = optAddressBook.get();

			List<AddressBook> lstAddressBook = new ArrayList<AddressBook>();

			lstAddressBook.add(fetchedAddress);

			PrintUtil.printAddressBook(fetchedAddress, lstContact);

			return ResponseUtil.createResponse(
					0,
					"",
					"Address Book Printed",
					lstAddressBook,
					lstContact
					);
		}
		else
		{
			return ResponseUtil.createResponse(
					1,
					"Address Book with id "+ addressBookId +" does no exist",
					"",
					null,
					null
					);
		}
	}

	/**
	 * The method will call ContactRepository.deleteContact method for deleting
	 * all the record belongs to address book
	 * 
	 * @param lstContacts
	 * 
	 */
	private void deleteAllContactsForAddressBook(List<Contact> lstContacts)
	{
		for(Contact contact: lstContacts)
		{
			contactRepository.deleteContact(contact.getId());
		}
	}

}
