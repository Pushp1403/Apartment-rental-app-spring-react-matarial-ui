package com.toptal.services;

import org.springframework.stereotype.Service;

import com.toptal.entities.Authority;

@Service
public interface IAuthorityService {

	Authority saveOrUpdateAuthority(Authority auth);
}
