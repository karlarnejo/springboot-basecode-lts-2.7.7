package com.spring.lts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.lts.dao.CrudRepositoryUserr;
import com.spring.lts.model.LoginRequest;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

	@Autowired
	CrudRepositoryUserr crudRepositoryUser;
	
	@Override
	public LoginRequest findByUsername(String username) {
		// TODO Auto-generated method stub
		LoginRequest userModel = new LoginRequest(crudRepositoryUser.findByUsername(username));
				
		return userModel;
	}
}
