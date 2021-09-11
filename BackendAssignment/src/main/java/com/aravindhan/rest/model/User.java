package com.aravindhan.rest.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="USERS")
public class User {
	
	private String firstName;
	private String lastName;
	@Id
	private String email;
	private String mobile;
	private String address;
	private String city;
	private String state;
	private int pincode;
	private String created_on; 
	private String last_modified_on; 
	private int	version;
						 
	

}
