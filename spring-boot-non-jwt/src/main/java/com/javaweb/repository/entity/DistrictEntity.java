package com.javaweb.repository.entity;

public class DistrictEntity {
	private Long id;
	private String code, name;
	
	public Long getDistrictid() {
		return id;
	}
	public void setDistrictid(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}