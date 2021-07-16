package com.reece.addressbook.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reece.addressbook.entities.Contact;

public interface ContactBaseRepository extends JpaRepository<Contact, Integer> 
{

	List<Contact> findAll();

	@SuppressWarnings("unchecked")
	Contact save(Contact contact);

	void deleteById(Integer id);

	List<Contact> findByAddressBookId(Integer id);

	Optional<Contact> findById(Integer id); 
	
	void deleteAll();
}
