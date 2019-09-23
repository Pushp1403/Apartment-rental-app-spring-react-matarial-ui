package com.toptal.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.toptal.entities.Authority;
import com.toptal.repositories.IAuthorityRepository;

public class AuthorityServceImpl implements IAuthorityService {
	
	@Autowired
	private IAuthorityRepository repository;

	@Override
	public Authority saveOrUpdateAuthority(Authority auth) {
		return repository.save(auth);
	}

}
