package com.reece.addressbook;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reece.addressbook.entities.AddressBook;
import com.reece.addressbook.entities.Contact;
import com.reece.addressbook.entities.Response;
import com.reece.addressbook.repository.AddressBookRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class AddressBookApplicationTests 
{
	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	Response mockResponse;

	@Autowired 
	MockHttpServletResponse mockHttpServletResponse; 

	@Autowired 
	AddressBookRepository addressBookRepository;

	private String jsonAddressBook;

	/**
	 * testGetAllAddressBooks_WithoutData will try to fetch information about address books. 
	 * 
	 * As there is no address book in the system, it will return an error no1 along with error message
	 * in response  
	 * 
	 * Operation Name :  getAllAddressBooks
	 * 
	 * Context : /AddressBook/getAllAddressBooks
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetAllAddressBooks_WithoutData() throws Exception 
	{
		//Cleaning Up Repository
		addressBookRepository.deleteAllAddressBooks();

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/AddressBook/getAllAddressBooks")
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		mockHttpServletResponse = result.getResponse();

		assertNotNull(mockHttpServletResponse);

		mockResponse  = objectMapper.readValue(mockHttpServletResponse.getContentAsString(), Response.class);

		assertNotNull(mockResponse);

		assertEquals(null, mockResponse.getAddressBook());

		logger.info("testgetAllAddressBooks_WithoutAnyData -> Response" +mockHttpServletResponse.getContentAsString());
	}

	/**
	 * testgetAllAddressBooks_WithData will return the Address Books persisted in system
	 * * 
	 * Operation Name :  getAllAddressBooks
	 * 
	 * Context : /AddressBook/getAllAddressBooks
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetAllAddressBooks_WithData() throws Exception 
	{
		//Cleaning Up Repository
		addressBookRepository.deleteAllAddressBooks();

		List<AddressBook> lstAddressBook = createAddressBooks();

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/AddressBook/getAllAddressBooks")
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		mockHttpServletResponse = result.getResponse();

		assertNotNull(mockHttpServletResponse);

		mockResponse  = objectMapper.readValue(mockHttpServletResponse.getContentAsString(), Response.class);

		assertNotNull(mockResponse);

		assertNotNull(mockResponse.getAddressBook());

		logger.info("testGetAllAddressBooks_WithData -> Response" + mockHttpServletResponse.getContentAsString());
	}

	/**
	 * testUpdateAddressBook_InsertingNewAddressBook will perform a test to create a new Address Book
	 * 
	 * Operation Name : updateAddressBook
	 * 
	 * Context : /AddressBook/updateAddressBook
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdateAddressBook_InsertingNewAddressBook() throws Exception 
	{
		//Cleaning Up Repository
		addressBookRepository.deleteAllAddressBooks();

		jsonAddressBook ="{\n" + 
				"   \"addressBookName\": \"EmployeeContactBooks\",\n" + 
				"    \"addressBookDesc\": \"Holds contact information of Employees\"\n" + 
				"}";

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/AddressBook/updateAddressBook")
				.accept(MediaType.APPLICATION_JSON).content(jsonAddressBook)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		mockHttpServletResponse = result.getResponse();

		assertNotNull(mockHttpServletResponse);

		mockResponse  = objectMapper.readValue(mockHttpServletResponse.getContentAsString(), Response.class);

		assertNotNull(mockResponse);

		assertNotNull(mockResponse.getAddressBook());

		List<AddressBook> lstAddressBook = mockResponse.getAddressBook();

		Integer addressBookId = lstAddressBook.get(0).getId();

		assertNotNull(addressBookId);

		logger.info("testUpdateAddressBook_InsertingNewAddressBook -> Response "+ mockHttpServletResponse.getContentAsString());

	}

	/**
	 * testUpdateAddressBook_UdatingAddressBook will perform a test to update the Address Book.
	 * Service will update the address book if the "Id: of an address book is passed in the payload.
	 * 
	 * Following are the steps performed in test case:
	 * 
	 * 1. Create an address book
	 * 2. Update the name of address book by using the address book id returned after step 1
	 * 
	 * Operation Name : updateAddressBook
	 * 
	 * Context : /AddressBook/updateAddressBook
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdateAddressBook_UdatingAddressBook() throws Exception 
	{
		//Cleaning Up Repository
		addressBookRepository.deleteAllAddressBooks();

		//Creating an Address Book
		jsonAddressBook ="{\n" + 
				"   \"addressBookName\": \"EmployeeContactBooks\",\n" + 
				"    \"addressBookDesc\": \"Holds contact information of Employees\"\n" + 
				"}";

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/AddressBook/updateAddressBook")
				.accept(MediaType.APPLICATION_JSON).content(jsonAddressBook)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		mockHttpServletResponse = result.getResponse();

		assertNotNull(mockHttpServletResponse);

		mockResponse  = objectMapper.readValue(mockHttpServletResponse.getContentAsString(), Response.class);

		assertNotNull(mockResponse);

		assertNotNull(mockResponse.getAddressBook());

		List<AddressBook> lstAddressBook = mockResponse.getAddressBook();

		Integer addressBookId = lstAddressBook.get(0).getId();

		assertNotNull(addressBookId);

		logger.info("testUpdateAddressBook_UdatingAddressBook -> Name Before Update"+ lstAddressBook.get(0).getAddressBookName());

		//Updating Name of the Address Book from "EmployeeContactBooks" to "EmpContactBooks" by passing addressBookId
		jsonAddressBook ="{\n" + 
				" \"id\":\""+addressBookId+"\",\n  "+
				"   \"addressBookName\": \"EmpContactBooks\",\n" + 
				"    \"addressBookDesc\": \"Holds contact information of Employees\"\n" + 
				"}";

		requestBuilder = MockMvcRequestBuilders
				.post("/AddressBook/updateAddressBook")
				.accept(MediaType.APPLICATION_JSON).content(jsonAddressBook)
				.contentType(MediaType.APPLICATION_JSON);

		result = mockMvc.perform(requestBuilder).andReturn();

		mockHttpServletResponse = result.getResponse();

		assertNotNull(mockHttpServletResponse);

		mockResponse  = objectMapper.readValue(mockHttpServletResponse.getContentAsString(), Response.class);

		assertNotNull(mockResponse);

		assertNotNull(mockResponse.getAddressBook());

		lstAddressBook = mockResponse.getAddressBook();

		assertEquals("EmpContactBooks", lstAddressBook.get(0).getAddressBookName());

		logger.info("testUpdateAddressBook_UdatingAddressBook -> Name After Update"+ lstAddressBook.get(0).getAddressBookName());

	}

	/**
	 * testUpdateAddressBooks_InsertingMultipleAddressBooks will perform test of creating multiple Address Books
	 *  
	 * Operation Name : updateAddressBooks
	 * 
	 * Context : /AddressBook/updateAddressBooks
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdateAddressBooks_InsertingMultipleAddressBooks() throws Exception 
	{
		//Cleaning Up Repository
		addressBookRepository.deleteAllAddressBooks();

		String jsonAddressBook ="[ {\n" + 
				"            \"addressBookName\": \"CustomerContactBooks\",\n" + 
				"            \"addressBookDesc\": \"Holds contact information of Customers\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"	            \"addressBookName\": \"DepartmentContactBooks\",\n" + 
				"	            \"addressBookDesc\": \"Holds contact information of departments\"\n" + 
				"        }\n" + 
				"        ]";

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/AddressBook/updateAddressBooks")
				.accept(MediaType.APPLICATION_JSON).content(jsonAddressBook)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		mockHttpServletResponse = result.getResponse();

		assertNotNull(mockHttpServletResponse);

		mockResponse  = objectMapper.readValue(mockHttpServletResponse.getContentAsString(), Response.class);

		assertNotNull(mockResponse);

		assertNotNull(mockResponse.getAddressBook());

		List<AddressBook> lstAddressBook = mockResponse.getAddressBook();

		assertTrue(lstAddressBook.size() == 2);

		logger.info("testUpdateAddressBooks_InsertingMultipleAddressBooks -> Response : " +mockHttpServletResponse.getContentAsString());

	}

	/**
	 * testUpdateAddressBooks_UpdatingMultipleAddressBooks will perform a test to update existing Address Books
	 * and creating a new Address Book. Update is performed by passing Id of existing address book, if the Id is
	 * not passed then service will create a new address book.
	 * 
	 * Following are the steps performed in test case:
	 * 
	 * 1. Create multiple address books
	 * 2. Update the name of 2 address book by using the address book ids returned after step 1
	 *  
	 * Operation Name : updateAddressBooks
	 * 
	 * Context : /AddressBook/updateAddressBooks
	 * 
	 * @throws Exception
	 */
	@Test	
	public void testUpdateAddressBooks_UpdatingMultipleAddressBooks() throws Exception 
	{
		//Cleaning Up Repository
		addressBookRepository.deleteAllAddressBooks();

		List<AddressBook> lstAddressBook = createAddressBooks();

		Integer custAddressBookId = lstAddressBook.get(0).getId();

		Integer empAddressBookId = lstAddressBook.get(1).getId();

		//Update name of "CustomerContactBooks" to "CustContBook" and "EmployeeContactBooks" to "EmpContBook"
		//Create a new address book with name DepartmentContactBooks
		jsonAddressBook ="[ {\n" + 
				" 			\"id\":\""+custAddressBookId+"\",\n  "+
				"            \"addressBookName\": \"CustContBook\",\n" + 
				"            \"addressBookDesc\": \"Holds contact information of Customers\"\n" + 
				"        },\n" + 
				"        {\n" + 
				" 			\"id\":\""+empAddressBookId+"\",\n  "+
				"	            \"addressBookName\": \"EmpContBook\",\n" + 
				"	            \"addressBookDesc\": \"Holds contact information of Employees\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"	            \"addressBookName\": \"DepartmentContactBooks\",\n" + 
				"	            \"addressBookDesc\": \"Holds contact information of departments\"\n" + 
				"        }\n" + 
				"        ]";

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/AddressBook/updateAddressBooks")
				.accept(MediaType.APPLICATION_JSON).content(jsonAddressBook)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		mockHttpServletResponse = result.getResponse();

		assertNotNull(mockHttpServletResponse);

		mockResponse  = objectMapper.readValue(mockHttpServletResponse.getContentAsString(), Response.class);

		assertNotNull(mockResponse);

		assertNotNull(mockResponse.getAddressBook());

		lstAddressBook = mockResponse.getAddressBook();

		assertTrue(lstAddressBook.size() == 3);

		logger.info("testUpdateAddressBooks_UpdatingMultipleAddressBooks -> After Response : " +mockHttpServletResponse.getContentAsString());
	}

	/**
	 * testGetAddressBookById will fetch an Address Book as per the addressBookId passed in the URL. We will created address books
	 * and fetch one of them using getAddressBookById
	 * 
	 * Following are the steps performed in test case:
	 * 
	 * 1. Create multiple address books
	 * 2. Fetch information about second address book
	 * 
	 * Operation Name :  getAddressBookById
	 * 
	 * Context : /AddressBook/getAddressBookById?addressBookId=?
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetAddressBookById() throws Exception 
	{
		//Cleaning Up Repository
		addressBookRepository.deleteAllAddressBooks();		

		List<AddressBook> lstAddressBook = createAddressBooks();

		Integer addressBookId = lstAddressBook.get(1).getId();

		//System should fetch information about EmployeeContactBooks

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/AddressBook/getAddressBookById?addressBookId="+addressBookId)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		mockHttpServletResponse = result.getResponse();

		assertNotNull(mockHttpServletResponse);
	
		mockResponse  = objectMapper.readValue(mockHttpServletResponse.getContentAsString(), Response.class);

		assertNotNull(mockResponse);

		assertNotNull(mockResponse.getAddressBook());

		assertEquals("EmployeeContactBooks", mockResponse.getAddressBook().get(0).getAddressBookName());

		logger.info("testGetAddressBookById -> After Fetching Address Book - Response : " +mockHttpServletResponse.getContentAsString());
	}

	/**
	 * testGetAddressBookById_NegativeScenarioPassingWrongId will return error message as wrong address
	 * book id is passed
	 * 
	 * Following are the steps performed in test case:
	 * 
	 * 1. Create multiple address books
	 * 2. Pass Id which does not exist in the URl to fetch address book
	 *  
	 * 
	 * Operation Name :  getAddressBookById
	 * 
	 * Context : /AddressBook/getAddressBookById?addressBookId=?
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetAddressBookById_NegativeScenarioPassingWrongId() throws Exception 
	{
		//Cleaning Up Repository
		addressBookRepository.deleteAllAddressBooks();		

		List<AddressBook> lstAddressBook = createAddressBooks();

		//As we have only two address books and we are trying to fetch address book id 100

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/AddressBook/getAddressBookById?addressBookId=100")
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		mockHttpServletResponse = result.getResponse();

		assertNotNull(mockHttpServletResponse);

		mockResponse  = objectMapper.readValue(mockHttpServletResponse.getContentAsString(), Response.class);

		assertNotNull(mockResponse);

		assertEquals(null, mockResponse.getAddressBook());

		//Response will have error no as 1
		assertEquals(1, mockResponse.getErrorNo());

		assertNotNull(mockResponse.getErrorMessage());

		logger.info("testGetAddressBookById_NegativeScenarioPassingWrongId -> Response : " +mockHttpServletResponse.getContentAsString());

	}

	/**
	 * testDeleteAddressBook will delete address book for the address book id passed in URL
	 * 
	 * Following are the steps performed in test case:
	 * 
	 * 1. Create multiple address books
	 * 2. Pass Id of first address book for deletion
	 * 
	 * Operation Name :  deleteAddressBook
	 * 
	 * Context : /AddressBook/deleteAddressBook?addressBookId=?
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDeleteAddressBook() throws Exception 
	{
		//Cleaning Up Repository
		addressBookRepository.deleteAllAddressBooks();	

		List<AddressBook> lstAddressBook = createAddressBooks();

		Integer custAddressBookId = lstAddressBook.get(0).getId();

		//Will delete CustomerContactBooks 

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/AddressBook/deleteAddressBook?addressBookId="+ custAddressBookId)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		mockHttpServletResponse = result.getResponse();

		assertNotNull(mockHttpServletResponse);

		mockResponse  = objectMapper.readValue(mockHttpServletResponse.getContentAsString(), Response.class);

		assertNotNull(mockResponse);

		assertTrue(mockResponse.getAddressBook().size() ==1);

		logger.info("testDeleteAddressBook -> Response : " +mockHttpServletResponse.getContentAsString());

	}

	/**
	 * testDeleteAddressBook_AddressBookDoesNotExist will try to delete address book by passing
	 * address book id which does not exist. In such a case, service will return
	 * an error no as 1 along with the error message
	 * 
	 *   Following are the steps performed in test case:
	 * 
	 * 1. Create multiple address books
	 * 2. Pass Id that does not exists in the system
	 * 
	 * Operation Name :  deleteAddressBook
	 * 
	 * Context : /AddressBook/deleteAddressBook?addressBookId=?
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDeleteAddressBook_AddressBookDoesNotExist() throws Exception 
	{
		//Cleaning Up Repository
		addressBookRepository.deleteAllAddressBooks();	

		List<AddressBook> lstAddressBook = createAddressBooks();

		//Will delete CustomerContactBooks 

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/AddressBook/deleteAddressBook?addressBookId=200")
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		mockHttpServletResponse = result.getResponse();

		assertNotNull(mockHttpServletResponse);

		mockResponse  = objectMapper.readValue(mockHttpServletResponse.getContentAsString(), Response.class);

		assertNotNull(mockResponse);

		//Response will have error no as 1
		assertEquals(1, mockResponse.getErrorNo());

		assertNotNull(mockResponse.getErrorMessage());

		logger.info("testDeleteAddressBook_AddressBookDoesNotExist -> Response : " +mockHttpServletResponse.getContentAsString());

	}

	/**
	 * testGetAllContacts_WithContactData will fetch all Contacts from address books. 
	 * Following are the steps performed in test case
	 * 
	 *  1. Create two Address Books
	 *  2. Add Contacts along with Phone Number to both Address Books
	 *  3. Retrieve contacts from both 
	 *  
	 * 
	 * Operation Name :  getAllContacts
	 * 
	 * Context : /Contact/getAllContacts
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetAllContacts_WithContactData() throws Exception 
	{
		//Cleaning Up Repository
		addressBookRepository.deleteAllAddressBooks();	

		List<AddressBook> lstAddressBooks = createAddressBooks();

		List<Contact> lstContact = addContactsToAddressBooks(lstAddressBooks);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/Contact/getAllContacts")
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		mockHttpServletResponse = result.getResponse();

		assertNotNull(mockHttpServletResponse);

		mockResponse  = objectMapper.readValue(mockHttpServletResponse.getContentAsString(), Response.class);

		assertNotNull(mockResponse);

		assertNotNull(mockResponse.getContact());

		logger.info("testGetAllContacts ->  Response : " + mockHttpServletResponse.getContentAsString());
	}

	/**
	 * testGetAllContacts_WithoutCreatingData will try to fetch all Contacts from address books. 
	 * As there is no data of the contacts , the service will return an error no as along with the error message in response.
	 * 
	 * Operation Name :  getAllContacts
	 * 
	 * Context : /Contact/getAllContacts
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetAllContacts_WithoutCreatingData() throws Exception 
	{
		//Cleaning Up Repository
		addressBookRepository.deleteAllAddressBooks();	

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/Contact/getAllContacts")
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		mockHttpServletResponse = result.getResponse();

		assertNotNull(mockHttpServletResponse);

		mockResponse  = objectMapper.readValue(mockHttpServletResponse.getContentAsString(), Response.class);

		assertNotNull(mockResponse);

		assertEquals(null, mockResponse.getContact());

		logger.info("testGetAllContacts_WithoutCreatingData ->  Response : " + mockHttpServletResponse.getContentAsString());
	}


	/**
	 * testUpdateContact will add as single contact in the address Id passed in the 
	 * payload. 
	 * 
	 * Following are the steps performed in test case:
	 * 
	 * 1. Create AddressBooks
	 * 2. Add the user in first address book
	 * 
	 * 
	 * 
	 * Operation Name : updateContact
	 * 
	 * Context : /Contact/updateContact
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdateContact() throws Exception 
	{
		//Cleaning Up Repository
		addressBookRepository.deleteAllAddressBooks();	

		List<AddressBook> lstAddressBooks = createAddressBooks();

		String jsonContact ="{\n" + 
				"    \"contactName\": \"Peter Martin\",\n" + 
				"    \"addressBookId\" : \""+lstAddressBooks.get(0).getId()+"\",\n" + 
				"    \"phone\": {\n" + 
				"         \"phoneNumber\": [\n" + 
				"            \"0234392093\"\n" + 
				"        ]\n" + 
				"    }\n" + 
				"    \n" + 
				"}";

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/Contact/updateContact")
				.accept(MediaType.APPLICATION_JSON).content(jsonContact)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertNotNull(response);

		mockResponse  = objectMapper.readValue(response.getContentAsString(), Response.class);

		assertNotNull(mockResponse);

		assertNotNull(mockResponse.getContact());

		List<Contact> lstContact = mockResponse.getContact();

		assertNotNull(lstContact);

		assertTrue(lstContact.size() > 0);

		Integer contactId = lstContact.get(0).getId();

		assertNotNull(contactId);

		logger.info("testUpdateContact -> Response : " + response.getContentAsString());

	}

	/**
	 * testUpdateContacts will perform test of creating multiple contact in an address book
	 *  
	 * Following are the steps performed in test case:
	 * 
	 * 1. Create multiple AddressBooks
	 * 2. Create Contacts and assign address book id to each contact.
	 * 
	 * Operation Name : updateContacts
	 * 
	 * Context : /Contact/updateContacts
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdateContacts_InsertingMultipleRecords() throws Exception 
	{
		//Cleaning Up Repository
		addressBookRepository.deleteAllAddressBooks();	

		List<AddressBook> lstAddressBooks = createAddressBooks();

		List<Contact> lstContacts = addContactsToAddressBooks(lstAddressBooks);

		logger.info(" testUpdateContacts " + lstContacts);

	}

	/**
	 * testDeleteContact will delete the contact for the Id passed in URL
	 * 
	 * Following are the steps performed in test case:
	 * 
	 * 1. Create multiple AddressBooks
	 * 2. Create Contacts and assign address book id to each contact.
	 * 3. Delete the first contact
	 * 
	 * Operation name : deleteContact
	 * 
	 * Context : /Contact/deleteContact
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDeleteContact() throws Exception 
	{	
		//Cleaning Up Repository
		addressBookRepository.deleteAllAddressBooks();	

		List<AddressBook> lstAddressBooks = createAddressBooks();

		List<Contact> lstContacts = addContactsToAddressBooks(lstAddressBooks);

		Integer contactIdToBeDeleted = lstContacts.get(0).getId();

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/Contact/deleteContact?contactId="+ contactIdToBeDeleted)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertNotNull(response);

		mockResponse  = objectMapper.readValue(response.getContentAsString(), Response.class);

		assertNotNull(mockResponse);

		assertNotNull(mockResponse.getContact());

		boolean found = mockResponse.getContact().stream().anyMatch(p -> p.getId().equals(contactIdToBeDeleted));

		assertFalse(found);

		logger.info(" testDeleteContactresponse -> Response : "+ response.getContentAsString());
	}

	/** 
	 * testDeleteContact_PassInvalidId will try to delete a contact record that does not exist in system.
	 * In such a case system will return an error no along with the error message in response payload.
	 * 
	 * Following are the steps performed in test case:
	 * 
	 * 1. Create multiple AddressBooks
	 * 2. Create Contacts and assign address book id to each contact.
	 * 3. Pass Contact no that does not exist in the system
	 * 
	 * Operation name : deleteContact
	 * 
	 * Context : /Contact/deleteContact
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDeleteContact_PassInvalidId() throws Exception 
	{	
		//Cleaning Up Repository
		addressBookRepository.deleteAllAddressBooks();	

		List<AddressBook> lstAddressBooks = createAddressBooks();

		List<Contact> lstContacts = addContactsToAddressBooks(lstAddressBooks);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/Contact/deleteContact?contactId=500")
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertNotNull(response);

		mockResponse  = objectMapper.readValue(response.getContentAsString(), Response.class);

		assertNotNull(mockResponse);

		assertEquals(1, mockResponse.getErrorNo());

		assertNotNull(mockResponse.getErrorMessage());

		logger.info(" testDeleteContact_PassInvalidId -> Response : "+ response.getContentAsString());
	}


	/** 
	 * testGetContactById will return the contact information for the Id passed in the URL
	 * 
	 * Following are the steps performed in test case:
	 * 
	 * 1. Create multiple AddressBooks
	 * 2. Create Contacts and assign address book id to each contact.
	 * 3. Pass Contact no to be fetched
	 * 
	 * Operation name : getContactById
	 * 
	 * Context : /Contact/getContactById
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetContactById() throws Exception 
	{	
		//Cleaning Up Repository
		addressBookRepository.deleteAllAddressBooks();	

		List<AddressBook> lstAddressBooks = createAddressBooks();

		List<Contact> lstContacts = addContactsToAddressBooks(lstAddressBooks);

		Integer contactIdToBeFetched = lstContacts.get(0).getId();

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/Contact/getContactById?contactId="+contactIdToBeFetched)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertNotNull(response);

		mockResponse  = objectMapper.readValue(response.getContentAsString(), Response.class);

		assertNotNull(mockResponse);

		assertNotNull(mockResponse.getContact());

		boolean found = mockResponse.getContact().stream().anyMatch(p -> p.getId().equals(contactIdToBeFetched));

		assertTrue(found);	

		logger.info("testGetContactById -> Response " + response.getContentAsString());
	}

	/** 
	 * testGetContactById will try to fetch the contact information of the contact Id 
	 * that does not exist in the system. In such a case, service will return an error no as 1
	 * along with the error message. 
	 * 
	 * Following are the steps performed in test case:
	 * 
	 * 1. Create multiple AddressBooks
	 * 2. Create Contacts and assign address book id to each contact.
	 * 3. Pass Invalid Contact Id
	 * 
	 * Operation name : getContactById
	 * 
	 * Context : /Contact/getContactById
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetContactById_PassInvalidId() throws Exception 
	{	
		//Cleaning Up Repository
		addressBookRepository.deleteAllAddressBooks();	

		List<AddressBook> lstAddressBooks = createAddressBooks();

		List<Contact> lstContacts = addContactsToAddressBooks(lstAddressBooks);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/Contact/getContactById?contactId=500")
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertNotNull(response);

		mockResponse  = objectMapper.readValue(response.getContentAsString(), Response.class);

		assertNotNull(mockResponse);

		assertEquals(null,mockResponse.getContact());		

		logger.info("testGetContactById_PassInvalidId -> Response " + response.getContentAsString());
	}

	/** 
	 * testGetContactsByAddressBookId will return the contacts belonging to the address book id passed 
	 * in the request
	 * 
	 * Following are the steps performed in test case:
	 * 
	 * 1. Create multiple AddressBooks
	 * 2. Create Contacts and assign address book id to each contact.
	 * 3. Pass Address Book id
	 * 
	 * Operation name : getAddressBookContacts
	 * 
	 * Context : /AddressBook/getAddressBookContacts
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetContactsByAddressBookId() throws Exception 
	{	
		//Cleaning Up Repository
		addressBookRepository.deleteAllAddressBooks();	

		List<AddressBook> lstAddressBooks = createAddressBooks();

		List<Contact> lstContacts = addContactsToAddressBooks(lstAddressBooks);

		Integer addressBookId = lstAddressBooks.get(0).getId();

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/AddressBook/getAddressBookContacts?addressBookId=" + addressBookId)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertNotNull(response);

		mockResponse  = objectMapper.readValue(response.getContentAsString(), Response.class);

		assertNotNull(mockResponse);

		assertNotNull(mockResponse.getAddressBook());

		assertNotNull(mockResponse.getContact());

		//Verifying whether result set contains records from other address books or not.
		//If yes, the it should fail the test case
		boolean found = mockResponse.getContact().stream().anyMatch(p -> !p.getAddressBookId().equals(addressBookId));

		assertFalse(found);

		logger.info("testGetContactsByAddressBookId -> Response " + response.getContentAsString());
	}

	/**
	 * testPrintAddressBook will return all the contacts in an address book
	 * It will also print the returned records on the console  
	 *
	 * Following are the steps performed in test case:
	 * 
	 * 1. Create multiple AddressBooks
	 * 2. Create Contacts and assign address book id to each contact.
	 * 3. Print the address book by passing in address book id 
	 * 
	 * @throws Exception
	 */
	@Test
	public void testPrintAddressBook() throws Exception 
	{	
		//Cleaning Up Repository
		addressBookRepository.deleteAllAddressBooks();	

		List<AddressBook> lstAddressBooks = createAddressBooks();

		List<Contact> lstContacts = addContactsToAddressBooks(lstAddressBooks);

		Integer addressBookId = lstAddressBooks.get(0).getId();

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/AddressBook/printAddressBook?addressBookId=" + addressBookId)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertNotNull(response);

		mockResponse  = objectMapper.readValue(response.getContentAsString(), Response.class);

		assertNotNull(mockResponse);

		assertNotNull(mockResponse.getAddressBook());

		assertNotNull(mockResponse.getContact());

		//Verifying whether result set contains records from other address books or not.
		//If yes, the it should fail the test case
		boolean found = mockResponse.getContact().stream().anyMatch(p -> !p.getAddressBookId().equals(addressBookId));

		assertFalse(found);

		logger.info("testPrintAddressBook -> Response " + response.getContentAsString());
	}

	/**
	 * testPrintUniqueContacts will return all the contacts across the address books
	 * It will also print the returned records on the console  
	 *
	 * Following are the steps performed in test case:
	 * 
	 * 1. Create multiple AddressBooks
	 * 2. Create Contacts and assign address book id to each contact.
	 * 3. Return contacts across the address book 
	 * 
	 * In contact list John Dere and John Halt belongs to both address books, but 
	 * it should be returned once
	 * 
	 * @throws Exception
	 */
	@Test
	public void testPrintUniqueContacts() throws Exception 
	{	

		//Cleaning Up Repository
		addressBookRepository.deleteAllAddressBooks();	

		List<AddressBook> lstAddressBooks = createAddressBooks();

		List<Contact> lstContacts = addContactsToAddressBooks(lstAddressBooks);

		//verifying count of John Dere in contact list, it will return 2
		Stream<Contact> streamContactWithDuplicateRecords = mockResponse.getContact().stream().filter(p -> p.getContactName().equalsIgnoreCase("John Dere"));

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/Contact/printUniqueContacts")
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertNotNull(response);

		mockResponse  = objectMapper.readValue(response.getContentAsString(), Response.class);

		assertNotNull(mockResponse);

		assertNotNull(mockResponse.getContact());

		Stream<Contact> streamWithUnqiueContact = mockResponse.getContact().stream().filter(p -> p.getContactName().equalsIgnoreCase("John Dere"));

		//verifying count of John Dere in contact list, it will return 1

		assertTrue(streamContactWithDuplicateRecords.count() > 1);

		assertTrue(streamWithUnqiueContact.count() == 1);
		
		logger.info("testPrintUniqueContacts -> Response " + response.getContentAsString());
	}

	/**
	 * Method to create entries in the address book , so it can be used to test 
	 * other test cases such as getAllAdddressBooks etc.
	 * 
	 * @return List<AddressBook>
	 * 
	 * @throws Exception
	 */
	private List<AddressBook> createAddressBooks() throws Exception
	{

		//Creating two Address Books 
		String jsonAddressBook ="[ {\n" + 
				"            \"addressBookName\": \"CustomerContactBooks\",\n" + 
				"            \"addressBookDesc\": \"Holds contact information of Customers\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"	            \"addressBookName\": \"EmployeeContactBooks\",\n" + 
				"	            \"addressBookDesc\": \"Holds contact information of Employees\"\n" + 
				"        }\n" + 
				"        ]";

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/AddressBook/updateAddressBooks")
				.accept(MediaType.APPLICATION_JSON).content(jsonAddressBook)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		mockHttpServletResponse = result.getResponse();

		assertNotNull(mockHttpServletResponse);

		mockResponse  = objectMapper.readValue(mockHttpServletResponse.getContentAsString(), Response.class);

		assertNotNull(mockResponse);

		assertNotNull(mockResponse.getAddressBook());

		assertTrue(mockResponse.getAddressBook().size() == 2);

		logger.info("createAddressBooks -> Create Address Books -  Response : " +mockHttpServletResponse.getContentAsString());

		return mockResponse.getAddressBook();
	}

	/**
	 * Method to create entries of the contacts, so it can be used to test 
	 * other test cases such as getAllContacts etc.
	 * 
	 * @return List<AddressBook>
	 * 
	 * @throws Exception
	 */
	private List<Contact> addContactsToAddressBooks(List<AddressBook> lstAddressBooks) throws Exception
	{

		String jsonContact ="[\n" + 
				"        {\n" + 
				"            \"contactName\": \"John Dere\",\n" + 
				"            \"phone\": {\n" + 
				"           \n" + 
				"                \"phoneNumber\": [\n" + 
				"                    \"0424681722\",\n" + 
				"                    \"0423681722\"\n" + 
				"                ]\n" + 
				"         },\n" + 
				"            \"addressBookId\": "+lstAddressBooks.get(0).getId()+"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"contactName\": \"Michael Francis\",\n" + 
				"            \"phone\": {\n" + 
				"           \n" + 
				"                \"phoneNumber\": [\n" + 
				"                    \"0424683232\"\n" + 
				"                ]\n" + 
				"             },\n" + 
				"            \"addressBookId\": "+lstAddressBooks.get(0).getId()+"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"contactName\": \"John Dere\",\n" + 
				"            \"phone\": {\n" + 
				"           \n" + 
				"                \"phoneNumber\": [\n" + 
				"                    \"0424681722\",\n" + 
				"                    \"0423681722\"\n" + 
				"                ]\n" + 
				"         },\n" + 
				"            \"addressBookId\": "+lstAddressBooks.get(1).getId()+"\n" + 
				"        },\n" + 
				"        {\n" + 
				"           \n" + 
				"            \"contactName\": \"Tom Alter\",\n" + 
				"            \"phone\": {\n" + 
				"           \n" + 
				"                \"phoneNumber\": [\n" + 
				"                    \"0424231702\"\n" + 
				"                ]\n" + 
				"            },\n" + 
				"            \"addressBookId\": "+lstAddressBooks.get(1).getId()+"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"contactName\": \"John Halt\",\n" + 
				"            \"phone\": {\n" + 
				"                 \"phoneNumber\": [\n" + 
				"                    \"0424231701\"\n" + 
				"                ]\n" + 
				"            },\n" + 
				"            \"addressBookId\": "+lstAddressBooks.get(1).getId()+"\n" + 
				"        },\n" + 
				" 		 {\n" + 
				"            \"contactName\": \"John Halt\",\n" + 
				"            \"phone\": {\n" + 
				"                 \"phoneNumber\": [\n" + 
				"                    \"0424231701\"\n" + 
				"                ]\n" + 
				"            },\n" + 
				"            \"addressBookId\": "+lstAddressBooks.get(0).getId()+"\n" + 
				"        }\n" + 
				"    ]\n" + 
				"";

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/Contact/updateContacts")
				.accept(MediaType.APPLICATION_JSON).content(jsonContact)
				.contentType(MediaType.APPLICATION_JSON);


		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertNotNull(response);

		mockResponse  = objectMapper.readValue(response.getContentAsString(), Response.class);

		assertNotNull(mockResponse);

		assertNotNull(mockResponse.getContact());

		assertTrue(mockResponse.getContact().size() > 0);

		logger.info("addContactsToAddressBooks -> Response : " + response.getContentAsString());

		return mockResponse.getContact();
	}
}