package com.javaweb.repository.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "building")
public class BuildingEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "street")
	private String street;
	
	@Column(name = "ward")
	private String ward;
	
//	@Column(name = "districtId")
//	private Long districtId;

	@Column(name = "floorarea")
	private Long floorarea;
	
	@Column(name = "rentArea")
	private String rentArea;
	
	@Column(name = "rentprice")
	private Long rentprice;
	
	@Column(name = "emptyArea")
	private String emptyArea;
	
	@Column(name = "managername")
	private String managername;
	
	@Column(name = "managerphonenumber")
	private String managerphonenumber;
	
	@ManyToOne
	@JoinColumn(name = "districtid")
	private DistrictEntity district;
	
	@OneToMany(mappedBy = "building")
	private List<RentAreaEntity> rentAreas = new ArrayList<>();
	
	public DistrictEntity getDistrict() {
		return district;
	}
	public void setDistrict(DistrictEntity district) {
		this.district = district;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public Long getFloorarea() {
		return floorarea;
	}
	public void setFloorarea(Long floorarea) {
		this.floorarea = floorarea;
	}
	public Long getRentprice() {
		return rentprice;
	}
	public void setRentprice(Long rentprice) {
		this.rentprice = rentprice;
	}
	public String getManagername() {
		return managername;
	}
	public void setManagername(String managername) {
		this.managername = managername;
	}
	public String getManagerphonenumber() {
		return managerphonenumber;
	}
	public void setManagerphonenumber(String managerphonenumber) {
		this.managerphonenumber = managerphonenumber;
	}
	public String getRentArea() {
		return rentArea;
	}
	public void setRentArea(String rentArea) {
		this.rentArea = rentArea;
	}
	public String getEmptyArea() {
		return emptyArea;
	}
	public void setEmptyArea(String emptyArea) {
		this.emptyArea = emptyArea;
	}
	public List<RentAreaEntity> getRentAreas() {
		return rentAreas;
	}
	public void setRentAreas(List<RentAreaEntity> rentAreas) {
		this.rentAreas = rentAreas;
	}
	
}