package com.toptal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.toptal.entities.Apartment;

public interface IApartmentRepository extends JpaRepository<Apartment, Long>, JpaSpecificationExecutor<Apartment>{
	
	List<Apartment> findByEnabled(Boolean enabled);

	List<Apartment> findByEnabledAndState(boolean b, boolean c);
	
}
