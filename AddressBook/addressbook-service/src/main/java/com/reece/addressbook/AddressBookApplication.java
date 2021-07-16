package com.reece.addressbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Application class, that will is used to run a
 * {@link SpringApplication} from the
 * specified source using default settings.
 * 
 * @author Sarabjeet
 *
 */
@SpringBootApplication
public class AddressBookApplication 
{
	public static void main(String[] args) 
	{
		SpringApplication.run(AddressBookApplication.class, args);
	}
}
