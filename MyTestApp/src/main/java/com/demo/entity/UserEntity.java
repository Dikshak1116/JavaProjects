package com.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="USER_DTLS")
public class UserEntity {
	@Id
	@GeneratedValue
	private Integer uid;
	
	//@NotBlank(message = "Name is required")
	private String name;
	/*@NotBlank(message = "Email is required")
	@Email*/
	private String email;
	//@NotBlank(message = "Password is required")
	private String password;
	//@NotBlank(message = "Gender is required")
	private String gender;
	
	private long phno;
	
	
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public long getPhno() {
		return phno;
	}
	public void setPhno(long phno) {
		this.phno = phno;
	}
	@Override
	public String toString() {
		return "UserEntity [uid=" + uid + ", name=" + name + ", email=" + email + ", password=" + password + ", gender="
				+ gender + ", phno=" + phno + "]";
	}
	
	
	
}
