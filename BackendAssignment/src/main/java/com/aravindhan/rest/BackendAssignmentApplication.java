package com.aravindhan.rest;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.aravindhan.rest.response.UserResponse;

@SpringBootApplication
public class BackendAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendAssignmentApplication.class, args);
		while(true) {
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter choice");
			System.out.println("1. Add User");
			System.out.println("2. SearchQuery and Limit");
			System.out.println("3. Update");
			System.out.println("4. Exit");
			int choice = sc.nextInt();
			switch(choice) {
			
			case 1:
				UserResponse.addUser();
			break;
			
			case 2:
				System.out.println("Enter Search query without semicolon");
				sc.nextLine();
				String searchQuery = sc.nextLine();
				while(searchQuery.length()<1) {
					System.out.println("Sql Query can't be empty");
					searchQuery = sc.nextLine();
					}
				System.out.println("Enter Limit ");
				int limit = sc.nextInt();
				UserResponse.searchQuery(searchQuery, limit);
				break;
			case 3:
				System.out.println("Enter Query to update without semicolon in the end");
				sc.nextLine();
				String Query = sc.nextLine();
				while(Query.length()<1) {
				System.out.println("Sql Query can't be empty");
				Query = sc.nextLine();
				}
				UserResponse.update(Query);
				break;
			case 4:
				System.out.println("Application Exited");
				sc.close();
				return;
			default:
				System.out.println("Enter valid Integer 1-4");
				break;
			}
			
		}
			
	}

}
