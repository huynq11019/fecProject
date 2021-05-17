package com.fec.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fec.demo.entity.UserRole;

public interface IuserRole extends JpaRepository<UserRole, Long> {

}
