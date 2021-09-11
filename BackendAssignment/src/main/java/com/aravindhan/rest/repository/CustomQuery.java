package com.aravindhan.rest.repository;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aravindhan.rest.model.User;
import com.aravindhan.rest.resource.UserResource;

@Repository
public class CustomQuery {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<User> search(String searchQuery){
		
		Query query = entityManager.createNativeQuery(searchQuery, User.class);
		return query.getResultList();
		
	}

	@Transactional
	public void update(String query) {
		
		String[] str = query.split(" ");
		Query query_update = entityManager.createNativeQuery(query,User.class);
		query_update.executeUpdate();
		
		Query query_update_version = entityManager.createNativeQuery("update users set version = version+1, last_modified_on = '"+UserResource.getDateTime()+"' where email = "+str[str.length-1]);
		query_update_version.executeUpdate();
	}
	
	

}
