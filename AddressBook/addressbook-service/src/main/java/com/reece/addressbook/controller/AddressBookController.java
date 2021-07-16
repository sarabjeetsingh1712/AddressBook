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

import com.reece.addressbook.entities.AddressBook;
import com.reece.addressbook.entities.Response;
import com.reece.addressbook.service.AddressBookService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Controller Class the exposed web-methods to manage an Address Book. 
 * It enables the service consumer to perform CRUD on an address book.
 * 
 * @author Sarabjeet
 *
 */
@RestController
@RequestMapping(value="/AddressBook")
@ComponentScan
@Api(value="AddressBook", description="This API exposes operations to manage an Address Book. It enables the service consumer to perform CRUD on an address book.")
public class AddressBookController 
{
	@Autowired
	private AddressBookService addressBookService;

	@Autowired
	private Response response;	

	@ApiOperation(value = "The operation will return information about the Address Books, such as Address Book Id, name, and description.", response = Response.class)
	@GetMapping("/getAllAddressBooks") 
	public ResponseEntity<Response>  getAllAddressBooks() 
	{ 
		response =  addressBookService.getAllAddressBooks();

		if (null != response) 
		{
			return ResponseEntity.ok().body(response); 
		}
		else 
		{
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
		}	 
	}

	@ApiOperation(value = "The operation will accept Address Book information as request. It is used for both add/update operations on an Address Book. It will perform an update if an Address Book Id is part of the request; otherwise, carry out insert.", response = Response.class)
	@PostMapping("/updateAddressBook")
	public ResponseEntity<Response> updateAddressBook(@RequestBody AddressBook addressBook) 
	{
		response = addressBookService.updateAddressBook(addressBook);

		if (null != response) 
		{
			return ResponseEntity.status(HttpStatus.CREATED).body(response);	
		} 
		else 
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@ApiOperation(value = "This operation is similar to \"updateAddressBook\" but can accept multiple Address Books in a request. Like its sibling, it will update if an Address Book Id is part of the request; otherwise, carry out insert.", response = Response.class)
	@PostMapping("/updateAddressBooks")
	public ResponseEntity<Response> updateAddressBooks(@RequestBody List<AddressBook> addressBooks) 
	{
		response = addressBookService.updateAddressBooks(addressBooks);

		if (null != response) 
		{
			return ResponseEntity.status(HttpStatus.CREATED).body(response);	
		} 
		else 
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@ApiOperation(value = "The operation deletes the address book for the address book id passed in the request as a URL parameter, i.e., addressBookId. It will also delete all the contacts that belong to the deleted address book.", response = Response.class)
	@DeleteMapping("/deleteAddressBook")
	public ResponseEntity<Response> deleteAddressBook(@RequestParam(value = "addressBookId") String addressBookId) 
	{
		response = addressBookService.deleteAddressBook(Integer.parseInt(addressBookId));

		if (null != response) 
		{
			return ResponseEntity.status(HttpStatus.CREATED).body(response);	
		} 
		else 
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}	

	}

	@ApiOperation(value = "The operation returns the contacts in an Address Book for the addressBookId passed in the request as a URL parameter. If the address book does not exist, the response will comprise an error message.", response = Response.class)
	@GetMapping("/getAddressBookContacts")
	public ResponseEntity<Response> getAddressBookContacts(@RequestParam(value = "addressBookId") String addressBookId) 
	{
		response = addressBookService.getAddressBookContacts(Integer.parseInt(addressBookId));

		if (null != response) 
		{
			return ResponseEntity.status(HttpStatus.CREATED).body(response);	
		} 
		else 
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}	

	}

	@ApiOperation(value = "The operation returns Address Book Information such as \"Address Book Name\" and \"Address Book Description\" for addressBookId passed in the request. If addressBookId is invalid, the response will comprise an error message." , response = Response.class)
	@GetMapping("/getAddressBookById")
	public ResponseEntity<Response> getAddressBookById(@RequestParam(value = "addressBookId") String addressBookId) 
	{
		response = addressBookService.getAddressBookById(Integer.parseInt(addressBookId));

		if (null != response) 
		{
			return ResponseEntity.status(HttpStatus.CREATED).body(response);	
		} 
		else 
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}	

	}	

	@ApiOperation(value = "The operation returns all the contacts in the address book for the addressBookId passed in the request as a URL parameter." , response = Response.class)
	@GetMapping("/printAddressBook")
	public ResponseEntity<Response> printAddressBook(@RequestParam(value = "addressBookId") String addressBookId) 
	{
		response = addressBookService.printAddressBook(Integer.parseInt(addressBookId));

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
