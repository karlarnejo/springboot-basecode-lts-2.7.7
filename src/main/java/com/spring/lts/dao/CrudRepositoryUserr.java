package com.spring.lts.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.lts.entity.Userr;

@Repository
public interface CrudRepositoryUserr extends JpaRepository<Userr, Long> {
	public Userr findByUsername(String username);
}