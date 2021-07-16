package com.reece.addressbook.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AddressBook 
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "address_book_id")
	private Integer id;

	@Column(name ="ADDRESS_BOOK_NAME")
	private String addressBookName;

	@Column(name ="ADDRESS_BOOK_Description")
	private String addressBookDesc;

	public AddressBook(String addressBookName, String addressBookDesc)
	{
		this.addressBookName = addressBookName;

		this.addressBookDesc = addressBookDesc;

	}
	public AddressBook()
	{

	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the addressBookDesc
	 */
	public String getAddressBookDesc() {
		return addressBookDesc;
	}

	/**
	 * @param addressBookDesc the addressBookDesc to set
	 */
	public void setAddressBookDesc(String addressBookDesc) {
		this.addressBookDesc = addressBookDesc;
	}

	/**
	 * @return the addressBookName
	 */
	public String getAddressBookName() {
		return addressBookName;
	}

	/**
	 * @param addressBookName the addressBookName to set
	 */
	public void setAddressBookName(String addressBookName) {
		this.addressBookName = addressBookName;
	}


	@Override
	public String toString() 
	{
		return "AddressBook [id=" + id + ", addressBookName=" + addressBookName + ", addressBookDesc=" + addressBookDesc
				+ "]";
	}



}
