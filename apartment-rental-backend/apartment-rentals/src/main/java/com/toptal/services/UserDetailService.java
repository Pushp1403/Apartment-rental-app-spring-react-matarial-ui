package com.toptal.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.toptal.entities.Authority;
import com.toptal.entities.User;
import com.toptal.entities.UserDetail;
import com.toptal.models.JwtUserDetails;
import com.toptal.repositories.IAuthorityRepository;
import com.toptal.repositories.IUserDetailsRepository;
import com.toptal.repositories.IUserRepository;
import com.toptal.utils.UserDetailConverterUtil;

@Service
@Transactional
public class UserDetailService implements IUserDetailService {

	@Autowired
	private UserDetailConverterUtil converterUtil;

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IUserDetailsRepository userDetailRepository;
	
	@Autowired
	private IAuthorityRepository authRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> details = userRepository.findById(username);
		User detail = details.isEmpty() ? null : details.get();
		JwtUserDetails user = null;
		if (null != detail) {
			user = converterUtil.userEntityToJwtUserDetails(detail);
		}
		return user;
	}

	public JwtUserDetails registerNewUser(JwtUserDetails user) {
		User details = converterUtil.jwtUserDetailsToUserEntityConverter(user);
		details = userRepository.save(details);
		return converterUtil.userEntityToJwtUserDetails(details);
	}

	public List<JwtUserDetails> retrieveAllUsers() {
		List<User> userDetails = userRepository.findByEnabled(true);
		List<JwtUserDetails> details = userDetails.stream().map(user -> {
			return converterUtil.userEntityToJwtUserDetails(user);
		}).collect(Collectors.toList());
		return details;
	}

	public JwtUserDetails updateUserDetails(JwtUserDetails user, JwtUserDetails authUser) {
		UserDetail details = converterUtil.convertJwtUserToUserDetails(user);
		List<Authority> authority = user.getAuthorities().stream().map(auth -> {
			return converterUtil.createAuthorityEntity(auth, authUser);
		}).collect(Collectors.toList());
		details = userDetailRepository.save(details);
		authRepository.saveAll(authority);
		return converterUtil.userDetailEntityToJwtUserDetails(details);
	}

}
