package com.fec.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fec.demo.entity.UserRole;
import com.fec.demo.repository.IuserRole;

public class UserRoleService {
	@Autowired
	IuserRole repo;

	public UserRole saveRoleUser(UserRole uRole) {
		return repo.saveAndFlush(uRole);
	}

	public UserRole dele(UserRole uRole) {
		repo.delete(uRole);
		return null;
	}

	public List<UserRole> getallUserByid(Integer IdUser) {
		return repo.findByUserid(IdUser);

	}
}
