package com.fec.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fec.demo.entity.User;

public interface IuserRepository extends JpaRepository<User, Long> {
public User findOneById(Long id);
//public List<User> findByEmail(String email);
@Query("SELECT u FROM User u WHERE u.active = true")
public Page<User> findAll(Pageable pageable);
public User findByEmail(String email);
public User findByPhonenumber(String phonenumber);
}
