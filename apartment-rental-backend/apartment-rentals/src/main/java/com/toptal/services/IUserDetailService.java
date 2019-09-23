package com.toptal.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.toptal.models.JwtUserDetails;

@Service
public interface IUserDetailService extends UserDetailsService {

	JwtUserDetails registerNewUser(JwtUserDetails user);

	List<JwtUserDetails> retrieveAllUsers();
	
	JwtUserDetails updateUserDetails(JwtUserDetails user, JwtUserDetails authUser);

}
