package com.fec.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fec.demo.entity.Role;

public interface Irolerepo extends JpaRepository<Role, Long> {

}
