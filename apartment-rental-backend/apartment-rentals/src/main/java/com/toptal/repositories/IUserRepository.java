package com.toptal.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.toptal.entities.User;

@Repository
@Transactional
public interface IUserRepository extends JpaRepository<User, String>{
	
	List<User> findByEnabled(boolean enabled);
}
