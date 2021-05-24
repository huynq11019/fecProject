package com.fec.demo.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString.Exclude;
import lombok.EqualsAndHashCode;
@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name")
	private String name;
	@JsonIgnore
//mappedBy trỏ tới tên biến roleuser ở trong User.
	@ManyToMany(mappedBy = "roleuser")
//LAZY để tránh việc truy xuất dữ liệu không cần thiết. Lúc nào cần thì mới query
	@EqualsAndHashCode.Exclude
	@Exclude
	private Collection<User> UserRole;

}
