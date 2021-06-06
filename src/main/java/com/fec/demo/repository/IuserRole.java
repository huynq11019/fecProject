package com.fec.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fec.demo.entity.UserRole;

public interface IuserRole extends JpaRepository<UserRole, Integer> {
	@Query("SELECT o FROM UserRole o WHERE o.users.id = ?1")
 	public List<UserRole> findByUserid( Integer id);
}
