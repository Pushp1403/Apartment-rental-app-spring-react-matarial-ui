package com.toptal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toptal.entities.UserDetail;

public interface IUserDetailsRepository extends JpaRepository<UserDetail, String>{

}
