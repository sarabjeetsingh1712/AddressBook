package com.reece.addressbook.entities;

import java.util.List;

import org.springframework.stereotype.Component;

/**
 * Response entity for all the operations will wrap AddressBook and Contact and ship it over to service consumer.
 * 
 * @author Sarabjeet
 *
 */
@Component
public class Response 
{
	private Integer errorNo;

	private String errorMessage;

	private String successMessage;

	private List<AddressBook> addressBook;

	private List<Contact> contact;

	/**
	 * @return the errorNo
	 */
	public Integer getErrorNo() {
		return errorNo;
	}

	/**
	 * @param errorNo the errorNo to set
	 */
	public void setErrorNo(Integer errorNo) {
		this.errorNo = errorNo;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the successMessage
	 */
	public String getSuccessMessage() {
		return successMessage;
	}

	/**
	 * @param successMessage the successMessage to set
	 */
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	/**
	 * @return the addressBook
	 */
	public List<AddressBook> getAddressBook() {
		return addressBook;
	}

	/**
	 * @param addressBook the addressBook to set
	 */
	public void setAddressBook(List<AddressBook> addressBook) {
		this.addressBook = addressBook;
	}

	/**
	 * @return the contact
	 */
	public List<Contact> getContact() {
		return contact;
	}

	/**
	 * @param contact the contact to set
	 */
	public void setContact(List<Contact> contact) {
		this.contact = contact;
	}



}
