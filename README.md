**Introduction**

A branch manager would like to maintain address books along with contacts and phone numbers. So they can track the contacts of customers. So there is a need for an application to support the above-said requirement.

The branch manager should be able to perform the following:

- Create a new address book.
- Updates address book.
- Delete an address book.
- Fetch information about all or any address book
- Create contacts and add them to the address book.
- Update contacts in the address book. 
- Delete contacts from the address book. 
- Print address book.
- Print unique contacts across the address books.

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
   
**Operations Exposed**

Below is the list of operations exposed to maintain an Address Book. Each operation information also provides insight regarding sample requests and responses.


**updateAddressBook**

The operation will accept Address Book information as request. It is used for both add/update operations on an Address Book. It will perform an update if an Address Book Id is part of the request; otherwise, carry out insert. 

Context is /AddressBook/updateAddressBook

Request Sample

 {
            "addressBookName": "CustomerContactBooks",
            "addressBookDesc": "Holds contact information of Customers"
 }

Response Sample

{
   "errorNo":0,
   "errorMessage":"",
   "successMessage":"",
   "addressBook":[
      {
         "id":1,
         "addressBookName":"CustomerContactBooks",
         "addressBookDesc":"Holds contact information of Customers"
      }
   ],
   "contact":null
}

**updateAddressBooks** 

This operation is similar to updateAddressBook but can accept multiple Address Books in a request. Like its sibling, it will update if an Address Book Id is part of the request; otherwise, carry out insert.

Context is /AddressBook/updateAddressBook

As per the below sample, it will updates the first two and insert the third one.

Request Sample

[
      {
         "id":145,
         "addressBookName":"CustContBook",
         "addressBookDesc":"Holds contact information of Customers"
      },
      {
         "id":146,
         "addressBookName":"EmpContBook",
         "addressBookDesc":"Holds contact information of Employees"
      },
      {
         "addressBookName":"DepartmentContactBooks",
         "addressBookDesc":"Holds contact information of departments"
      }
   ]
   
Response Sample

{
   "errorNo":0,
   "errorMessage":"",
   "successMessage":"",
   "addressBook":[
      {
         "id":145,
         "addressBookName":"CustContBook",
         "addressBookDesc":"Holds contact information of Customers"
      },
      {
         "id":146,
         "addressBookName":"EmpContBook",
         "addressBookDesc":"Holds contact information of Employees"
      },
      {
         "id":147,
         "addressBookName":"DepartmentContactBooks",
         "addressBookDesc":"Holds contact information of departments"
      }
   ]
   
**getAllAddressBooks**

The operation will return information about the Address Books, such as Address Book Id, name, and description.

Context is /AddressBook/getAllAddressBooks

Response Sample - When data is there in the system

{
   "errorNo":0,
   "errorMessage":"",
   "successMessage":"",
   "addressBook":[
      {
         "id":61,
         "addressBookName":"CustomerContactBooks",
         "addressBookDesc":"Holds contact information of Customers"
      },
      {
         "id":62,
         "addressBookName":"EmployeeContactBooks",
         "addressBookDesc":"Holds contact information of Employees"
      }
   ],
   "contact":null
}

Response Sample - When data is not there in the system

{
   "errorNo":1,
   "errorMessage":"Address Book does not exist",
   "successMessage":"",
   "addressBook":null,
   "contact":null
}

**deleteAddressBook**

The operation deletes the address book for the address book id passed in the request as a URL parameter, i.e., addressBookId. It will also delete all the contacts that belong to the deleted address book.

Context : /AddressBook/deleteAddressBook

@RequestParam addressBookId

Response Sample - When data is there in the system

{
   "errorNo":0,
   "errorMessage":"",
   "successMessage":"CustomerContactBooks is Deleted",
   "addressBook":[
      {
         "id":96,
         "addressBookName":"EmployeeContactBooks",
         "addressBookDesc":"Holds contact information of Employees"
      }
   ],
   "contact":null
}

Response Sample - When user tried to delete address book which does not exist

{
   "errorNo":1,
   "errorMessage":"Address Book with 200 does no exist",
   "successMessage":"",
   "addressBook":null,
   "contact":null
}

**getAddressBookContacts**

The operation returns the contacts in an Address Book for the addressBookId passed in the request as a URL parameter. If the address book does not exist, the response will comprise an error message.

Context : /AddressBook/getAddressBookContacts

@RequestParam addressBookId

Response Sample 

{
   "errorNo":0,
   "errorMessage":"",
   "successMessage":"",
   "addressBook":[
      {
         "id":117,
         "addressBookName":"CustomerContactBooks",
         "addressBookDesc":"Holds contact information of Customers"
      }
   ],
   "contact":[
      {
         "id":119,
         "contactName":"John Dere",
         "phone":{
            "id":120,
            "phoneNumber":[
               "0424681722",
               "0423681722"
            ],
            "contact":null
         },
         "addressBookId":117
      },
      {
         "id":121,
         "contactName":"Michael Francis",
         "phone":{
            "id":122,
            "phoneNumber":[
               "0424683232"
            ],
            "contact":null
         },
         "addressBookId":117
      },
      {
         "id":129,
         "contactName":"John Halt",
         "phone":{
            "id":130,
            "phoneNumber":[
               "0424231701"
            ],
            "contact":null
         },
         "addressBookId":117
      }
   ]
}

**getAddressBookById**

The operation returns Address Book Information such as "Address Book Name" and "Address Book Description" for addressBookId passed in the request. If addressBookId is invalid, the response will comprise an error message.

Context : /AddressBook/getAddressBookById

@RequestParam addressBookId

Response Sample - When Address Book exists

{
   "errorNo":0,
   "errorMessage":"",
   "successMessage":"",
   "addressBook":[
      {
         "id":64,
         "addressBookName":"EmployeeContactBooks",
         "addressBookDesc":"Holds contact information of Employees"
      }
   ],
   "contact":null
}

Response Sample - When Address Book does not exists

{
   "errorNo":1,
   "errorMessage":"Address Book with id 100 does no exist",
   "successMessage":"",
   "addressBook":null,
   "contact":null
}
 
**printAddressBook**

The operation returns all the contacts in the address book for the addressBookId passed in the request as a URL parameter.

Context : /AddressBook/printAddressBook

@RequestParam addressBookId

Response Sample 

{
   "errorNo":0,
   "errorMessage":"",
   "successMessage":"Address Book Printed",
   "addressBook":[
      {
         "id":81,
         "addressBookName":"CustomerContactBooks",
         "addressBookDesc":"Holds contact information of Customers"
      }
   ],
   "contact":[
      {
         "id":83,
         "contactName":"John Dere",
         "phone":{
            "id":84,
            "phoneNumber":[
               "0424681722",
               "0423681722"
            ],
            "contact":null
         },
         "addressBookId":81
      },
      {
         "id":85,
         "contactName":"Michael Francis",
         "phone":{
            "id":86,
            "phoneNumber":[
               "0424683232"
            ],
            "contact":null
         },
         "addressBookId":81
      },
      {
         "id":93,
         "contactName":"John Halt",
         "phone":{
            "id":94,
            "phoneNumber":[
               "0424231701"
            ],
            "contact":null
         },
         "addressBookId":81
      }
   ]
}


**updateContact**

The operation will accept Contact information as request. It is used for both add/update operations on Contact. It will perform an update if Contact Id is part of the request; otherwise, carry out insert.

Context : /Contact/updateContact

Request Sample

{
    "customerName": "Peter Martin",
    "phone": {
    "phoneNumber":[
       "0234392093"
    ],
   },
    "addressBookId": 113

}

Response Sample

{
   "errorNo":0,
   "errorMessage":"",
   "successMessage":"",
   "addressBook":null,
   "contact":[
      {
         "id":115,
         "contactName":"Peter Martin",
         "phone":{
            "id":116,
            "phoneNumber":[
               "0234392093"
            ],
            "contact":null
         },
         "addressBookId":113
      }
   ]
}

**updateContacts** 

This operation is similar to updateAddressBook but can accept multiple Address Books in a request. Like its sibling, it will update if an Address Book Id is part of the request; otherwise, carry out insert.

Context : /Contact/updateContacts

As per the below sample, it will updates the first two and insert the third one.

Request Sample

 [
      {
         "contactName":"John Dere",
         "phone":{
            "id":70,
            "phoneNumber":[
               "0424681722",
               "0423681722"
            ],
            "contact":null
         },
         "addressBookId":67
      },
      {
       "contactName":"Michael Francis",
         "phone":{
            "id":72,
            "phoneNumber":[
               "0424683232"
            ],
            "contact":null
         },
         "addressBookId":67
      },
      {
         "contactName":"John Dere",
         "phone":{
            "id":74,
            "phoneNumber":[
               "0424681722",
               "0423681722"
            ],
            "contact":null
         },
         "addressBookId":68
      },
      {
          "contactName":"Tom Alter",
         "phone":{
            "id":76,
            "phoneNumber":[
               "0424231702"
            ],
            "contact":null
         },
         "addressBookId":68
      },
      {
         "contactName":"John Halt",
         "phone":{
            "id":78,
            "phoneNumber":[
               "0424231701"
            ],
            "contact":null
         },
         "addressBookId":68
      },
      {
        "contactName":"John Halt",
         "phone":{
            "id":80,
            "phoneNumber":[
               "0424231701"
            ],
            "contact":null
         },
         "addressBookId":67
      }
   ]
   
Request Sample

{
   "errorNo":0,
   "errorMessage":"",
   "successMessage":"",
   "addressBook":null,
   "contact":[
      {
         "id":69,
         "contactName":"John Dere",
         "phone":{
            "id":70,
            "phoneNumber":[
               "0424681722",
               "0423681722"
            ],
            "contact":null
         },
         "addressBookId":67
      },
      {
         "id":71,
         "contactName":"Michael Francis",
         "phone":{
            "id":72,
            "phoneNumber":[
               "0424683232"
            ],
            "contact":null
         },
         "addressBookId":67
      },
      {
         "id":73,
         "contactName":"John Dere",
         "phone":{
            "id":74,
            "phoneNumber":[
               "0424681722",
               "0423681722"
            ],
            "contact":null
         },
         "addressBookId":68
      },
      {
         "id":75,
         "contactName":"Tom Alter",
         "phone":{
            "id":76,
            "phoneNumber":[
               "0424231702"
            ],
            "contact":null
         },
         "addressBookId":68
      },
      {
         "id":77,
         "contactName":"John Halt",
         "phone":{
            "id":78,
            "phoneNumber":[
               "0424231701"
            ],
            "contact":null
         },
         "addressBookId":68
      },
      {
         "id":79,
         "contactName":"John Halt",
         "phone":{
            "id":80,
            "phoneNumber":[
               "0424231701"
            ],
            "contact":null
         },
         "addressBookId":67
      }
   ]
}

**getAllContacts**

The operation will return information about the contacts from all the Address Books, such as Address Book Id, Contact Name, and Phone Numbers..

Context : /Contact/getAllContacts

Response Sample - When data is there in the system

{
   "errorNo":0,
   "errorMessage":"",
   "successMessage":"",
   "addressBook":null,
   "contact":[
      {
         "id":133,
         "contactName":"John Dere",
         "phone":{
            "id":134,
            "phoneNumber":[
               "0424681722",
               "0423681722"
            ],
            "contact":null
         },
         "addressBookId":131
      },
      {
         "id":135,
         "contactName":"Michael Francis",
         "phone":{
            "id":136,
            "phoneNumber":[
               "0424683232"
            ],
            "contact":null
         },
         "addressBookId":131
      },
      {
         "id":137,
         "contactName":"John Dere",
         "phone":{
            "id":138,
            "phoneNumber":[
               "0424681722",
               "0423681722"
            ],
            "contact":null
         },
         "addressBookId":132
      },
      {
         "id":139,
         "contactName":"Tom Alter",
         "phone":{
            "id":140,
            "phoneNumber":[
               "0424231702"
            ],
            "contact":null
         },
         "addressBookId":132
      },
      {
         "id":141,
         "contactName":"John Halt",
         "phone":{
            "id":142,
            "phoneNumber":[
               "0424231701"
            ],
            "contact":null
         },
         "addressBookId":132
      },
      {
         "id":143,
         "contactName":"John Halt",
         "phone":{
            "id":144,
            "phoneNumber":[
               "0424231701"
            ],
            "contact":null
         },
         "addressBookId":131
      }
   ]
}

Response Sample - When data is not there in the system

{
   "errorNo":1,
   "errorMessage":"There is no contacts in any of the address book",
   "successMessage":"",
   "addressBook":null,
   "contact":null
}

**deleteContact**

The operation deletes the contact from the address book, as per the contact Id passed in the request as a URL parameter, i.e., contactId

Context : /Contact/deleteContact

@RequestParam contactId

Response Sample - When data is there in the system

{
   "errorNo":0,
   "errorMessage":"",
   "successMessage":"CustomerContactBooks is Deleted",
   "addressBook":[
      {
         "id":96,
         "addressBookName":"EmployeeContactBooks",
         "addressBookDesc":"Holds contact information of Employees"
      }
   ],
   "contact":null
}

Response Sample - When the user tried to delete address book which does not exist

{
   "errorNo":1,
   "errorMessage":"Address Book with 200 does no exist",
   "successMessage":"",
   "addressBook":null,
   "contact":null
}


**getContactById**

The operation returns Contact Information such as "Contact Name" and "Phone Number" for contactId passed in the request. If contactId is invalid, the response will comprise an error message.

Context : /Contact/deleteContact

@RequestParam contactId


Response Sample - When Contact Id exists

{
   "errorNo":0,
   "errorMessage":"",
   "successMessage":"",
   "addressBook":null,
   "contact":[
      {
         "id":17,
         "contactName":"John Dere",
         "phone":{
            "id":18,
            "phoneNumber":[
               "0424681722",
               "0423681722"
            ],
            "contact":null
         },
         "addressBookId":15
      }
   ]
}

Response Sample - When Contact Id does not exists

{
   "errorNo":1,
   "errorMessage":"Contact with id 500 does no exist",
   "successMessage":"",
   "addressBook":null,
   "contact":null
}
 

**printUniqueContacts**

The operation returns the unique contacts across the Address Books; e.g., If John Dere is part of multiple address books with duplicate phone numbers, it will be returned once in the payload. Address Book Id should be ignored if it is used for any processing when getting unique contacts.

Response Sample 

{
   "errorNo":0,
   "errorMessage":"",
   "successMessage":"Address Book Printed",
   "addressBook":[
      {
         "id":81,
         "addressBookName":"CustomerContactBooks",
         "addressBookDesc":"Holds contact information of Customers"
      }
   ],
   "contact":[
      {
         "id":83,
         "contactName":"John Dere",
         "phone":{
            "id":84,
            "phoneNumber":[
               "0424681722",
               "0423681722"
            ],
            "contact":null
         },
         "addressBookId":81
      },
      {
         "id":85,
         "contactName":"Michael Francis",
         "phone":{
            "id":86,
            "phoneNumber":[
               "0424683232"
            ],
            "contact":null
         },
         "addressBookId":81
      },
      {
         "id":93,
         "contactName":"John Halt",
         "phone":{
            "id":94,
            "phoneNumber":[
               "0424231701"
            ],
            "contact":null
         },
         "addressBookId":81
      }
   ]
}


**getAddressBookContacts**

The operation returns the contacts in an Address Book for the addressBookId passed in the request as a URL parameter. If the address book does not exist, the response will comprise an error message.

Response Sample 

{
   "errorNo":0,
   "errorMessage":"",
   "successMessage":"",
   "addressBook":[
      {
         "id":117,
         "addressBookName":"CustomerContactBooks",
         "addressBookDesc":"Holds contact information of Customers"
      }
   ],
   "contact":[
      {
         "id":119,
         "contactName":"John Dere",
         "phone":{
            "id":120,
            "phoneNumber":[
               "0424681722",
               "0423681722"
            ],
            "contact":null
         },
         "addressBookId":117
      },
      {
         "id":121,
         "contactName":"Michael Francis",
         "phone":{
            "id":122,
            "phoneNumber":[
               "0424683232"
            ],
            "contact":null
         },
         "addressBookId":117
      },
      {
         "id":129,
         "contactName":"John Halt",
         "phone":{
            "id":130,
            "phoneNumber":[
               "0424231701"
            ],
            "contact":null
         },
         "addressBookId":117
      }
   ]
}

**Steps to execute the project**

Execute run.sh, to create and deploy the project
