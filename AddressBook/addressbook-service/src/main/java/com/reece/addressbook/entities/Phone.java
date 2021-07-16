package com.reece.addressbook.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Phone 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;	

	@ElementCollection(targetClass = String.class)
	@Column(name="phone_number", nullable=false)
	private Set<String> phoneNumber;

	//@OneToOne(mappedBy = "phone")
	//private Contact contact;

	@ManyToOne
	@JoinColumn(name = "contact_id")
	private Contact contact;

	public Phone() {

	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	//public Contact getContact() {
	//return contact;
	//}
	//public void setContact(Contact contact) {
	//this.contact = contact;
	//}



	/**
	 * @return the contact
	 */
	public Contact getContact() {
		return contact;
	}

	/**
	 * @param contact the contact to set
	 */
	public void setContact(Contact contact) {
		this.contact = contact;
	}

	/**
	 * @return the phoneNumber
	 */
	public Set<String> getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(Set<String> phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Phone other = (Phone) obj;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!(phoneNumber.containsAll(other.phoneNumber)) && !(other.phoneNumber.containsAll(phoneNumber)))
			return false;
		return true;
	}

	@Override
	public int hashCode() {

		return phoneNumber.size();
	}

}
