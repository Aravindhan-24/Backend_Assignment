package com.aravindhan.rest.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import com.aravindhan.rest.resource.UserResource;

@Component
@EnableJpaRepositories(basePackages = "com.aravindhan.rest")
public class RestConfig extends ResourceConfig{

	public RestConfig(Class<?>... classes) {
		register(UserResource.class);
	}
	
	
	

}
