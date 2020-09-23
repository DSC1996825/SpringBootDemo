package com.dsc.springboot.models;

public class Manager {
	private Integer id;
	private String loginName;
	private String username;
	private String password;
	private Integer roleId;
	
	public Manager() {
		super();
	}
	
	public Manager(Integer id, String loginName, String username, String password, Integer roleId) {
		super();
		this.id = id;
		this.loginName = loginName;
		this.username = username;
		this.password = password;
		this.roleId = roleId;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "Manager [id=" + id + ", loginName=" + loginName + ", username=" + username + ", password=" + password
				+ ", roleId=" + roleId + "]";
	}
	
}
