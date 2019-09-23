package com.toptal.utils;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.toptal.entities.Authority;
import com.toptal.entities.User;
import com.toptal.entities.UserDetail;
import com.toptal.models.JwtTokenRequest;
import com.toptal.models.JwtUserDetails;

@Component
public class UserDetailConverterUtil {

	public User jwtUserDetailsToUserEntityConverter(JwtUserDetails user) {
		List<Authority> authorities = new ArrayList<>();
		for (com.toptal.models.Authority authority : user.getAuthorities()) {
			Authority auth = new Authority();
			auth.setUsername(user.getUsername());
			auth.setAuthority(authority.getAuthority());
			authorities.add(auth);
		}
		User details = new User();
		details.setUsername(user.getUsername());
		details.setPassword(this.encoded(user.getPassword()));
		details.setEnabled(true);
		UserDetail userDetail = convertJwtUserToUserDetails(user);
		details.setUserDetails(userDetail);
		details.setRoles(authorities);
		return details;
	}

	public UserDetail convertJwtUserToUserDetails(JwtUserDetails user) {
		UserDetail userDetail = new UserDetail();
		userDetail.setFirstName(user.getFirstName());
		userDetail.setLastName(user.getLastName());
		userDetail.setUsername(user.getUsername());
		return userDetail;
	}

	public JwtUserDetails userEntityToJwtUserDetails(User detail) {
		List<com.toptal.models.Authority> authorities = new ArrayList<>();
		for (Authority auth : detail.getRoles()) {
			com.toptal.models.Authority authority = new com.toptal.models.Authority();
			authority.setRole(auth.getAuthority());
			authority.setUsername(auth.getUsername());
			authority.setId(auth.getId());
			authorities.add(authority);
		}
		JwtUserDetails user = new JwtUserDetails(detail.getUsername(), detail.getPassword(),
				detail.getUserDetails().getFirstName(), detail.getUserDetails().getLastName(), authorities);
		return user;
	}

	public String validateUserRegistrationDetails(JwtUserDetails user) {
		StringBuilder sb = new StringBuilder(Constants.BLANK_STRING);
		if (user.getFirstName() == null || user.getFirstName().trim().equals(Constants.BLANK_STRING)
				|| user.getLastName() == null || user.getLastName().trim().equals(Constants.BLANK_STRING)
				|| user.getPassword() == null || user.getPassword().trim().equals(Constants.BLANK_STRING)
				|| user.getSecretKey() == null || user.getSecretKey().trim().equals(Constants.BLANK_STRING)
				|| user.getUsername() == null || user.getUsername().trim().equals(Constants.BLANK_STRING)) {
			sb.append(Constants.MISSING_INFORMATION);
			if (user.getFirstName() == null || user.getFirstName().trim().equals(Constants.BLANK_STRING))
				sb.append(Constants.FIRST_NAME+Constants.COMMA);
			if (user.getLastName() == null || user.getLastName().trim().equals(Constants.BLANK_STRING))
				sb.append(Constants.LAST_NAME+Constants.COMMA);
			if (user.getPassword() == null || user.getPassword().trim().equals(Constants.BLANK_STRING))
				sb.append(Constants.PASSWORD+Constants.COMMA);
			if (user.getUsername() == null || user.getUsername().trim().equals(Constants.BLANK_STRING))
				sb.append(Constants.USER_NAME+Constants.COMMA);
		}
		return sb.toString();
	}
	
	public String validateUpdates(JwtUserDetails user) {
		StringBuilder sb = new StringBuilder(Constants.BLANK_STRING);
		if (user.getFirstName() == null || user.getFirstName().trim().equals(Constants.BLANK_STRING)
				|| user.getLastName() == null || user.getLastName().trim().equals(Constants.BLANK_STRING)
				|| user.getUsername() == null || user.getUsername().trim().equals(Constants.BLANK_STRING)) {
			sb.append(Constants.MISSING_INFORMATION);
			if (user.getFirstName() == null || user.getFirstName().trim().equals(Constants.BLANK_STRING))
				sb.append(Constants.FIRST_NAME+Constants.COMMA);
			if (user.getLastName() == null || user.getLastName().trim().equals(Constants.BLANK_STRING))
				sb.append(Constants.LAST_NAME+Constants.COMMA);
			if (user.getUsername() == null || user.getUsername().trim().equals(Constants.BLANK_STRING))
				sb.append(Constants.USER_NAME+Constants.COMMA);
		}
		return sb.toString();
	}

	public String validateAuthenticationDetails(JwtTokenRequest req) {
		StringBuilder sb = new StringBuilder(Constants.BLANK_STRING);
		if (req.getPassword() == null || req.getUsername() == null
				|| req.getPassword().trim().equals(Constants.BLANK_STRING)
				|| req.getUsername().trim().equals(Constants.BLANK_STRING)) {
			sb.append(Constants.MISSING_INFORMATION+Constants.COMMA);
			if (req.getPassword() == null || req.getPassword().trim().equals(Constants.BLANK_STRING))
				sb.append(Constants.PASSWORD+Constants.COMMA);
			if (req.getUsername() == null || req.getUsername().trim().equals(Constants.BLANK_STRING))
				sb.append(Constants.USER_NAME+Constants.COMMA);
		}
		return sb.toString();
	}

	public JwtUserDetails userDetailEntityToJwtUserDetails(UserDetail details) {
		JwtUserDetails user = new JwtUserDetails();
		user.setFirstName(details.getFirstName());
		user.setLastName(details.getLastName());
		return user;
	}

	private String encoded(String password) {
		return new BCryptPasswordEncoder().encode(password);
	}

	public Authority createAuthorityEntity(com.toptal.models.Authority auth, JwtUserDetails authUser) {
		Authority at = new Authority();
		at.setId(authUser.getAuthorities().get(0).getId());
		at.setUsername(auth.getUsername());
		at.setAuthority(auth.getAuthority());
		return at;
	}
}
