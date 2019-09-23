package com.toptal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.toptal.entities.Authority;

@Repository
public interface IAuthorityRepository extends JpaRepository<Authority, Long>{

}
