package com.fec.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "userrole")
public class UserRole {
@Id
@GeneratedValue(strategy =GenerationType.IDENTITY)
private Long id;
@ManyToOne
@JoinColumn(name="userID")
private User user;
@ManyToOne
@JoinColumn(name="roleID")
private Role role;
}
