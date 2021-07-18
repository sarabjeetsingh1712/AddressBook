package com.reece.addressbook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reece.addressbook.entities.Contact;
import com.reece.addressbook.entities.Response;
import com.reece.addressbook.service.impl.ContactServiceImpl;


/**
 * Service class for ContactController. It will simply delegate request to IMPL class
 * 
 * @author Sarabjeet
 *
 */
@Service
public class ContactService
{

	@Autowired
	private ContactServiceImpl contactServiceImpl;
	
	public Response getAllContacts() 
	{
		return contactServiceImpl.getAllContacts();
	}
	
	public Response addOrUpdateContacts(List<Contact> contacts) 
	{
		return contactServiceImpl.addOrUpdateContacts(contacts);
	}
	
	public Response addOrUpdateContact(Contact contact) 
	{
		return contactServiceImpl.addOrUpdateContact(contact);
	}
		
	public Response deleteContact(Integer id)
	{
		return contactServiceImpl.deleteContact(id);
	}
	
	public Response getContactById(Integer contactId) 
	{
		return contactServiceImpl.getContactById(contactId);
	}
		
	public Response printUniqueContacts() 
	{ 
		return  contactServiceImpl.printUniqueContacts(); 
	}
	 
}
