package com.reece.addressbook.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reece.addressbook.entities.AddressBook;

public interface AddressBookBaseRepository extends JpaRepository<AddressBook, Integer> 
{

	List<AddressBook> findAll();

	@SuppressWarnings("unchecked")
	AddressBook save(AddressBook addressBook);

	void deleteById(Integer id);

	Optional<AddressBook> findById(Integer id); 	

	void deleteAll();

}
