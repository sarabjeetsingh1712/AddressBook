package com.reece.addressbook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reece.addressbook.entities.AddressBook;
import com.reece.addressbook.entities.Response;
import com.reece.addressbook.service.impl.AddressBookServiceImpl;


/**
 * Service class for AddressBookController. It will simply delegate request to IMPL class
 * 
 * @author Sarabjeet
 *
 */
@Service
public class AddressBookService 
{
	@Autowired
	private AddressBookServiceImpl addressBookServiceImpl;

	public Response getAllAddressBooks() 
	{
		return addressBookServiceImpl.getAllAddressBooks();
	}
	
	public Response updateAddressBooks(List<AddressBook> addressBook) 
	{
		return addressBookServiceImpl.updateAddressBooks(addressBook);
	}
	
	public Response updateAddressBook(AddressBook addressBook) 
	{
		return addressBookServiceImpl.updateAddressBook(addressBook);
	}
	
	public Response deleteAddressBook(Integer addressBookId)
	{
		return addressBookServiceImpl.deleteAddressBook(addressBookId);
	}
	
	
	public Response getAddressBookContacts(Integer addressBookId)
	{
		  return  addressBookServiceImpl.getAddressBookContacts(addressBookId); 
	}
	 
	public Response getAddressBookById(Integer addressBookId) 
	{
		return addressBookServiceImpl.getAddressBookById(addressBookId);
	}
	
	public Response printAddressBook(Integer addressBookId) 
	{
		return addressBookServiceImpl.printAddressBook(addressBookId);
	}
	
}
