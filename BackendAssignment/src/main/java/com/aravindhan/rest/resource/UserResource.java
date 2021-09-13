package com.aravindhan.rest.resource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.aravindhan.rest.model.User;
import com.aravindhan.rest.repository.CustomQuery;
import com.aravindhan.rest.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Path("/users")
public class UserResource {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private CustomQuery customQuery;
	
	public String finalQuery;
	
	
	
	private static final ObjectMapper mapper = new ObjectMapper();
	ObjectNode json = mapper.createObjectNode();

	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/postUser")
	public Response addUser(User user) {
		
		user.setCreated_on(getDateTime());

		if( (user.getFirstName()==" "||user.getFirstName().length()<1) || 
				 (user.getEmail()==" "||user.getFirstName().length()<1) || 
				  user.getMobile().length()==0) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
		else {
		repository.save(user);
		
		json.put("result","created user entry");
		return Response.status(Response.Status.OK).entity(json).build();
		}
	}
	
	@GET
	@Path("/getUser")
	@Produces("application/json")
	public List<User> search(@QueryParam("query") String query, @QueryParam("limit") String limit){
		finalQuery = query+" LIMIT "+limit; 
		return customQuery.search(finalQuery);
	}
	
	@PUT
	@Path("/updateUser")
	@Produces("application/json")
	public void update(@QueryParam("query") String query) {
		customQuery.update(query);
	}
	
	
	public static String getDateTime() {
		LocalDateTime now = LocalDateTime.now(); 
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); 
		return now.format(format);
		
	}

}

