**Installation and Usage**

Get Docker Image from docker hub using the following command "**docker pull sarabjeetsingh1712/image:addressbookapp1.0**"

or 

Execute run.sh, to create and deploy the project

Once the application is deployed and stared, API information is accessible through **http://localhost:8080/swagger-ui.html#/**. This URL will also provide information about requests and responses for each exposed operation. Users can try any of the processes using the said URL of the swagger.

Following are the steps to create data through Postman or any other tool. It is a prerequisite for testing other operations:

1. Create Address booking using **updateAddressBook** or **updateAddressBooks**

   updateAddressBook context: /AddressBook/updateAddressBook
   
   updateAddressBooks ontext: /AddressBook/updateAddressBooks

2. Create Contacts under the address book using **updateContact** or **updateContacts**

   updateContact context: /Contact/updateContact
   
   updateContacts ontext: /Contact/updateContacts
   
Note : Information about request/respose is available at the following location : https://github.com/sarabjeetsingh1712/AddressBook/wiki
   
Or 

It can be tested by running the @SpringBootTest class **com.reece.addressbook.AddressBookApplicationTests**. This class will create data before running the test cases.

Below is th information about application and contoroller classes:

**AddressBookApplication** : Main Application class, that will is used to run a {@link SpringApplication} from the specified source using default settings.

**AddressBookController** : This API exposes operations to manage an Address Book. It enables the service consumer to perform CRUD on an address book.

Context : /AddressBook

**ContactController** : This API exposes operations to manage contacts within an Address Book. It enables the service consumer to perform CRUD on Contact.

Contact : /Contact


   
