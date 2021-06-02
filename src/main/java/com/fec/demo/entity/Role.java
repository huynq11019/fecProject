package com.fec.demo.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString.Exclude;

@Entity
@Table(name="roles")
@Data

public class Role implements Serializable{
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@Column(name = "name")
private String name;
//@JsonIgnore
////mappedBy trỏ tới tên biến roleuser ở trong User.
//@ManyToMany(mappedBy = "roleuser")
////LAZY để tránh việc truy xuất dữ liệu không cần thiết. Lúc nào cần thì mới query
//@EqualsAndHashCode.Exclude
//@Exclude
@JsonIgnore
@OneToMany(mappedBy = "roles", fetch = FetchType.LAZY)
private List<UserRole> UserRole;
}
