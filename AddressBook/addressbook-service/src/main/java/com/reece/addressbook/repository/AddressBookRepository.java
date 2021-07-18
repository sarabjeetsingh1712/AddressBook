package com.reece.addressbook.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.reece.addressbook.entities.AddressBook;

/**
 * Repository class with methods for CRUD operations to be performed on an AddressBook
 * It will use AddressBookBaseRepository to use JPA
 * 
 * @author Sarabjeet
 *
 */
@Repository
public class AddressBookRepository 
{
	@Autowired
	private AddressBookBaseRepository addressBookBaseRepository;
	
	@Autowired
	private ContactBaseRepository contactBaseRepository;

	public List<AddressBook> getAllAddressBooks() 
	{
		return addressBookBaseRepository.findAll();
	}

	public List<AddressBook> addOrUpdateAddressBooks(List<AddressBook> addressBook) 
	{
		return addressBookBaseRepository.saveAll(addressBook);
	}

	public AddressBook addOrUpdateAddressBook(AddressBook addressBook) 
	{
		return addressBookBaseRepository.save(addressBook);
	}

	public void deleteAllAddressBooks()
	{
		addressBookBaseRepository.deleteAll();
		
		contactBaseRepository.deleteAll();
		
		
	}

	public void deleteAddressBook(Integer id) 
	{
		addressBookBaseRepository.deleteById(id);
	}

	public Optional<AddressBook> getAddressBookById(Integer addressBookId)
	{
		return addressBookBaseRepository.findById(addressBookId);
	}
}
