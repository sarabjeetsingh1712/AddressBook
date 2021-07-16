package com.reece.addressbook.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Contact  
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name="contact_name", nullable=false)
	private String contactName;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "phone_id", referencedColumnName = "id")
	private Phone phone;

	@Column(name = "address_book_id")
	private Integer addressBookId;

	public Contact() {}

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
	 * @return the contactName
	 */
	public String getContactName() {
		return contactName;
	}

	/**
	 * @param contactName the contactName to set
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	/**
	 * @return the phone
	 */
	public Phone getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	/**
	 * @return the addressBookId
	 */
	public Integer getAddressBookId() {
		return addressBookId;
	}

	/**
	 * @param addressBookId the addressBookId to set
	 */
	public void setAddressBookId(Integer addressBookId) {
		this.addressBookId = addressBookId;
	}

	@Override
	public String toString() 
	{
		return "Contact [id=" + id + ", contactName=" + contactName + ","
				+ " phone=" + phone + ", addressBookId="
				+ addressBookId + "]";
	}

	@Override
	public int hashCode() 
	{
		return contactName.length();
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (getClass() != obj.getClass())
			return false;

		Contact other = (Contact) obj;

		if (contactName == null) 
		{
			if (other.contactName != null)
				return false;
		} 
		else if (!contactName.equals(other.contactName))
			return false;

		if (phone == null) 
		{
			if (other.phone != null)
				return false;
		} 
		else if (!phone.equals(other.phone))
			return false;

		return true;
	}




}
