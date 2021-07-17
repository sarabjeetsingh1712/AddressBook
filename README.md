**Installation and Usage**

Execute run.sh, to create and deploy the project

Once the application is deployed and stared, API information is accessible through **http://localhost:8080/swagger-ui.html#/**. This URL will also provide information about requests and responses for each exposed operation. Users can try any of the processes using the said URL of the swagger.

Following are the steps to create data through Postman or any other tool. It is a prerequisite for testing other operations:

1. Create Address booking using **updateAddressBook** or **updateAddressBooks**

   Context: /AddressBook/updateAddressBook
   
   Context: /AddressBook/updateAddressBooks

2. Create Contacts under the address book using **updateContact** or **updateContacts**

   Context: /Contact/updateContact
   
   Context: /Contact/updateContacts
   
Or 

It can be tested by running the @SpringBootTest class **com.reece.addressbook.AddressBookApplicationTests**. This class will create data before running the test cases.
   
