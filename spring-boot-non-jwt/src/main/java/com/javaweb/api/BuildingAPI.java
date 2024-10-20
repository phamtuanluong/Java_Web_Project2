package com.javaweb.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.model.BuildingDTO;
import com.javaweb.service.BuildingService;

// Tầng Presentation Layer
@RestController
@PropertySource("classpath:application.properties")
public class BuildingAPI {
	
	@Autowired
	private BuildingService buildingService;
	
	@Value("${dev.pham}")
	private String data;
	
	@GetMapping("/api/building")
	public List<BuildingDTO> getBuilding(@RequestParam Map<String, Object> params,
										@RequestParam(name = "typeCode", required = false) List<String> typeCode) {
		List<BuildingDTO> result = buildingService.findAll(params, typeCode);
		return result;
	}
	
	@DeleteMapping("/api/building/{id}")
	public void deleteBuilding(@PathVariable Long id) {
		System.out.print(data); 
	}
	
}