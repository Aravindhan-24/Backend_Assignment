package com.aravindhan.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aravindhan.rest.model.User;

public interface UserRepository extends JpaRepository<User, String>{}
