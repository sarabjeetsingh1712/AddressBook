package com.reece.addressbook.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.reece.addressbook.common.PrintUtil;
import com.reece.addressbook.common.ResponseUtil;
import com.reece.addressbook.entities.Contact;
import com.reece.addressbook.entities.Response;
import com.reece.addressbook.repository.ContactRepository;

@Component
public class ContactServiceImpl
{

	@Autowired
	private ContactRepository contactRepository;

	public Response getAllContacts() 
	{
		List<Contact> lstContacts = contactRepository.getAllContacts();

		if(lstContacts != null && !lstContacts.isEmpty())
		{
			return ResponseUtil.createResponse
					(
							0, 
							"", 
							"", 
							null, 
							contactRepository.getAllContacts()
							);
		}
		else
		{
			return ResponseUtil.createResponse
					(
							1,
							"There is no contacts in any of the address book",
							"",
							null,
							null
							);
		}	

	}

	public Response addOrUpdateContacts(List<Contact> contacts) 
	{
		Optional<Contact> optContactsWithoutAddressIds = contacts.stream().filter(p -> p.getAddressBookId()==null).findAny();		

		if(optContactsWithoutAddressIds.isPresent())
		{	
			return ResponseUtil.createResponse
					(
							1, 
							"Address Book id is missing in the payload for " + optContactsWithoutAddressIds.get().getContactName(),
							"",
							null,
							null
							);

		}

		contactRepository.addOrUpdateContacts(contacts);

		List<Contact> lstContact =contactRepository.getAllContacts();

		return ResponseUtil.createResponse(0, "", "", null, lstContact);

	}

	public Response addOrUpdateContact(Contact contact) 
	{
		contactRepository.addOrUpdateContact(contact);

		if(contact.getAddressBookId() == null)
		{
			return ResponseUtil.createResponse
					(
							1, 
							"Address Book id is missing in the payload for " + contact.getContactName(),
							"",
							null,
							null
							);
		}

		List<Contact> lstContact =contactRepository.getAllContacts();

		return ResponseUtil.createResponse(0, "", "", null, lstContact);
	}

	public Response deleteContact(Integer id)
	{
		Optional<Contact> optContactToBeDeleted = contactRepository.getContactById
				(
						id	
						);

		Contact contactToBeDeleted = null;

		if(optContactToBeDeleted.isPresent())
		{
			contactToBeDeleted = optContactToBeDeleted.get();

			contactRepository.deleteContact(contactToBeDeleted.getId());

			List<Contact> lstAddressBook =contactRepository.getAllContacts();

			return ResponseUtil.createResponse
					(
							0,
							"",
							contactToBeDeleted.getContactName() +" is Deleted",
							null,
							lstAddressBook
							);		
		}
		else
		{
			return ResponseUtil.createResponse
					(
							1,
							"Contact with "+ id +" does no exist",
							"",
							null,
							null
							);
		}		

	}

	public Response getContactById(Integer contactId) 
	{

		Optional<Contact> optContactBook = contactRepository.getContactById
				(
						contactId	
						);

		if(optContactBook.isPresent())
		{
			Contact fetchedContact = optContactBook.get();

			List<Contact> lstContact = new ArrayList<Contact>();

			lstContact.add(fetchedContact);

			return ResponseUtil.createResponse(
					0,
					"",
					"",
					null,
					lstContact
					);
		}
		else
		{
			return ResponseUtil.createResponse(
					1,
					"Contact with id "+ contactId +" does no exist",
					"",
					null,
					null
					);
		}
	}


	public Response printUniqueContacts() 
	{ 
		Set<Contact> setContacts = new HashSet<>();  

		setContacts.addAll(contactRepository.printUniqueContacts());

		if(setContacts != null && !setContacts.isEmpty())
		{
			PrintUtil.printUniqueContacts(setContacts);

			List<Contact> lstContcat = new ArrayList<>();

			lstContcat.addAll(setContacts);

			return ResponseUtil.createResponse
					(
							0,
							"",
							"Unique contact list printed",
							null,
							lstContcat
							);
		}
		else
		{
			return ResponseUtil.createResponse
					(
							1,
							"There are no contacts to be printed",
							"",
							null,
							null
							);
		}
	}


}
