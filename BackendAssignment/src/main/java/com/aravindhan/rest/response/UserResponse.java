package com.aravindhan.rest.response;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.type.TypeReference;

import com.aravindhan.rest.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserResponse {
	
	public static void addUser() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String firstName, lastName,email,address, state,city,mobile,pincode;
		try {
		String urlString = "http://localhost:8080/users/postUser";
		URL url = new URL(urlString);
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Accept", "application/json");
		connection.setDoOutput(true);
		
		System.out.println("Enter user details one by one");
		System.out.println("Enter firstName");
		firstName=sc.nextLine();
		System.out.println("Enter lastName");
		lastName = sc.nextLine();
		System.out.println("Enter email");
		email = sc.nextLine();
		while(! (Pattern.matches("^(.+)@(.+)$", email)) ) {
			System.out.println("Email id must is this format sample@gmail.com");
			email=sc.nextLine();
		}
		System.out.println("Enter mobile");
		mobile = sc.nextLine();
		while(mobile.length()!=10) {
			System.out.println("Mobile Number must have 10 digits");
			mobile = sc.nextLine();
		}
		System.out.println("Enter address");
		address = sc.nextLine();
		System.out.println("Enter city");
		city = sc.nextLine();
		System.out.println("Enter state");
		state = sc.nextLine();
		System.out.println("Enter pincode");
		pincode = sc.nextLine();
		while(pincode.length()!=6) {
			System.out.println("Enter pincode must contain 6 digits");
			pincode = sc.nextLine();
		}
		
		Map<String, String> jsonInputString= new HashMap<>();
		jsonInputString.put(" \"firstName\" "," \""+firstName+"\" ");
		jsonInputString.put(" \"lastName\" "," \" "+ lastName+" \" ");
		jsonInputString.put(" \"email\" ", " \" "+ email+" \" ");
		jsonInputString.put(" \"mobile\" ", mobile);
		jsonInputString.put(" \"address\" "," \" "+ address+" \" ");
		jsonInputString.put(" \"city\" "," \" "+city+" \" ");
		jsonInputString.put(" \"state\" ", " \" "+state+" \" ");
		jsonInputString.put(" \"pincode\" ", pincode);
		String s =  jsonInputString.toString().replace('=', ':');
		
		//System.out.println(s);
		
		try(OutputStream os = connection.getOutputStream()) {
		    byte[] input = s.getBytes("utf-8");
		    os.write(input, 0, input.length);			
		}
		
		try(BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
			StringBuilder response = new StringBuilder();
			String responseLine = null;
			while ((responseLine = br.readLine()) != null) {
				  response.append(responseLine.trim());
			}
			System.out.println(response.toString());
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("First Name, email, mobile might be missing");
		}
	}
	public static void searchQuery(String Query, int limit) {
		try {
		String searchQuery = Query;
		int query_limit = limit;
		String readLine = null;
		String urlString = "http://localhost:8080/users/getUser?query="+URLEncoder.encode(searchQuery,"UTF-8")+"&limit="+query_limit;

		URL url = new URL(urlString);
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setDoOutput(true);
		
		int responseCode = connection.getResponseCode();
		
		StringBuffer response=null;
	    if (responseCode == HttpURLConnection.HTTP_OK) {
	        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	        response = new StringBuffer();
	        while ((readLine = in .readLine()) != null) {
	            response.append(readLine);
	        } in .close();
	     }
	     ObjectMapper obj_mapper = new ObjectMapper();
	     List<User> userJsonList = obj_mapper.readValue(String.valueOf(response),new TypeReference<List<User>>(){});
	     for(int i=0;i<userJsonList.size();i++,System.out.println(", ")) {
	    	 User user = userJsonList.get(i);
	    	 System.out.println("{ ");
	    	 System.out.println("fistName : \""+user.getFirstName()+"\" ");
	    	 System.out.println("lastName : \""+user.getLastName()+"\" ");
	    	 System.out.println("email : \""+user.getEmail()+"\" ");
	    	 System.out.println("mobile : \""+user.getMobile()+"\" ");
	    	 System.out.println("address : \""+user.getAddress()+"\" ");
	    	 System.out.println("city : \""+user.getCity()+"\" ");
	    	 System.out.println("state : \""+user.getState()+"\" ");
	    	 System.out.println("pincode : \""+user.getPincode()+"\" ");
	    	 System.out.println("created_on : \""+user.getCreated_on()+"\" ");
	    	 System.out.println("last_modified_on : \""+user.getLast_modified_on()+"\" ");
	    	 System.out.println("version : \""+user.getVersion()+"\" ");
	    	 System.out.println("} ");
	     }
		}
		catch(Exception e) {
			System.out.println("Check for proper table name or syntax");
	
		}
	}
	public static void update(String Query) {
		try {
			String sql = Query;
		
			String urlString = "http://localhost:8080/users/updateUser?query="+URLEncoder.encode(sql,"UTF-8");

			URL url = new URL(urlString);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("PUT");
			
			connection.getResponseCode();
			
		}
		catch(Exception e) {
			System.out.println("Check for proper table name or syntax");
		}
	}
}
