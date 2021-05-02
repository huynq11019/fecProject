package com.fec.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fec.demo.entity.User;

public interface IuserRepository extends JpaRepository<User, Long> {
public User findOneById(Long id);
}
