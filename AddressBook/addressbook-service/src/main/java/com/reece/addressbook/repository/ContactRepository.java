package com.reece.addressbook.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.reece.addressbook.entities.Contact;

@Repository
public class ContactRepository 
{
	@Autowired
	private ContactBaseRepository contactBaseRepository;

	public List<Contact> getAllContacts() 
	{
		return contactBaseRepository.findAll();
	}

	public List<Contact> addOrUpdateContacts(List<Contact> contacts) 
	{
		return contactBaseRepository.saveAll(contacts);
	}

	public Contact addOrUpdateContact(Contact contact) 
	{
		return contactBaseRepository.save(contact);
	}

	public void deleteContact(Integer id) 
	{
		contactBaseRepository.deleteById(id);
	}

	public Optional<Contact> getContactById(Integer id) 
	{
		return contactBaseRepository.findById(id);
	}

	public List<Contact>  getContactByAddressBookId(Integer id) 
	{
		return contactBaseRepository.findByAddressBookId(id);
	}

	public List<Contact>  printUniqueContacts() 
	{
		return contactBaseRepository.findAll();
	}
}
