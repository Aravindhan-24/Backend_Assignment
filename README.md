# Backend_Assignment
Spring Framework, Java, REST API (preferably with JAX-RS), MySql, Hibernate.

Run as spring boot application create a database jersery.

Four main functionalities implemented in this assignment via REST API calls using JAX-RS from Java Application
  
  1. Add user:
     Enter details of the user and the request will be sent as JSON as POST request. 
     Validations have been applied on email and mobile.
     If Firstname, Mobile, email are not sent in payload API will throw bad request. If the data is persisted the API return the created user entry as a response to the client as JSON.
  
  2. Search Query:
     Enter the search query and limit in the console and the response will be returned as JSON having a List to store user entries.
  
  3. Update:
     Enter the update query and in the back end the version will be updated based on number of time the tuple is updated and the time and date last modification done on that      tuple.
  4. Exit
