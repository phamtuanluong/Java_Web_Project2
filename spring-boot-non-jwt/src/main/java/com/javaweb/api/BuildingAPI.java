package com.javaweb.api;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.BuildingService;

// Tầng Presentation Layer
@RestController
@PropertySource("classpath:application.properties")
@Transactional
public class BuildingAPI {
	
	@Autowired
	private BuildingRepository buildingRepository;
	
	@Autowired
	private BuildingService buildingService;
	
	@GetMapping(value = "/api/building")
	public List<BuildingDTO> getBuilding(@RequestParam Map<String, Object> params,
										@RequestParam(name = "typeCode", required = false) List<String> typeCode) {
		List<BuildingDTO> result = buildingService.findAll(params, typeCode);
		return result;
	}
	
	@PostMapping(value = "/api/building/{id}")
	public BuildingDTO getBuildingById(@PathVariable Long id) {
		BuildingDTO result = new BuildingDTO();
		BuildingEntity buildings = buildingRepository.findById(id).get(); 
		return result;
	}
	
	@PutMapping(value = "/api/building/{name}")
	public BuildingDTO getBuildingById(@PathVariable String name) {
		BuildingDTO result = new BuildingDTO();
		List<BuildingEntity> buildings = buildingRepository.findByNameContaining(name); 
		return result;
	}
	
	@DeleteMapping(value = "/api/building/{ids}")
	public void deleteBuilding(@PathVariable Long[] ids) {
		buildingRepository.deleteByIdIn(ids);
	}
	
}