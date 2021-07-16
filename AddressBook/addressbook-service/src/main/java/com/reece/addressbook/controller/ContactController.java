package com.reece.addressbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reece.addressbook.entities.Contact;
import com.reece.addressbook.entities.Response;
import com.reece.addressbook.service.ContactService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Controller Class the exposed web-methods to manage contacts within an Address Book. 
 * It enables the service consumer to perform CRUD on Contact.
 * 
 * @author Sarabjeet
 *
 */
@RestController
@RequestMapping(value="/Contact")
@ComponentScan
@Api(value="Contact", description="This API exposes operations to manage contacts within an Address Book. It enables the service consumer to perform CRUD on Contact.")
public class ContactController 
{	
	@Autowired
	private ContactService contactService;

	@Autowired
	private Response response;

	@ApiOperation(value = "The operation will return information about the contacts from all the Address Books, such as Address Book Id, Contact Name, and Phone Numbers.", response = Response.class)
	@GetMapping("/getAllContacts")
	public ResponseEntity<Response> getAllContacts() 
	{
		response = contactService.getAllContacts();

		if (null != response) 
		{
			return ResponseEntity.ok().body(response);	
		} 
		else 
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@ApiOperation(value = " The operation will accept Contact information as request. It is used for both add/update operations on Contact. It will perform an update if Contact Id is part of the request; otherwise, carry out insert.", response = Response.class)
	@PostMapping("/updateContact")
	public ResponseEntity<Response> updateContact(@RequestBody Contact contact) 
	{
		response = contactService.addOrUpdateContact(contact);

		if (null != response) 
		{
			return ResponseEntity.status(HttpStatus.CREATED).body(response);	
		} 
		else 
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@ApiOperation(value = " This operation is similar to \"updateContact\" but can accept multiple contacts in a request. Like its sibling, it will update if an Address Book Id is part of the request; otherwise, carry out insert.", response = Response.class)
	@PostMapping("/updateContacts")
	public ResponseEntity<Response> updateContacts(@RequestBody List<Contact> contacts) 
	{
		response = contactService.addOrUpdateContacts(contacts);

		if (null != response) 
		{
			return ResponseEntity.status(HttpStatus.CREATED).body(response);	
		} 
		else 
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@ApiOperation(value = "The operation deletes the contact from the address book, as per the contact Id passed in the request as a URL parameter, i.e., contactId", response = Response.class)
	@DeleteMapping("/deleteContact")
	public ResponseEntity<Response> deleteContact(@RequestParam(value = "contactId") String contactId) 
	{
		response = contactService.deleteContact(Integer.parseInt(contactId));

		if (null != response) 
		{
			return ResponseEntity.status(HttpStatus.CREATED).body(response);	
		} 
		else 
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@ApiOperation(value = "The operation returns Contact Information such as \"Contact Name\" and \"Phone Number\" for contactId passed in the request. If contactId is invalid, the response will comprise an error message.", response = Response.class)
	@GetMapping("/getContactById")
	public ResponseEntity<Response> getContactById(@RequestParam(value = "contactId") String contactId) 
	{
		response = contactService.getContactById(Integer.parseInt(contactId));

		if (null != response) 
		{
			return ResponseEntity.status(HttpStatus.CREATED).body(response);	
		} 
		else 
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}	

	}

	@ApiOperation(value = "The operation returns the unique contacts across the Address Books; e.g., If John Dere is part of multiple address books with duplicate phone numbers, it will be returned once in the payload. Address Book Id should be ignored if it is used for any processing when getting unique contacts.", response = Response.class)
	@GetMapping("/printUniqueContacts") 
	public ResponseEntity<Response> printUniqueContacts() 
	{
		response = contactService.printUniqueContacts();

		if (null != response) 
		{
			return ResponseEntity.status(HttpStatus.CREATED).body(response);	
		} 
		else 
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}	

	}
}
