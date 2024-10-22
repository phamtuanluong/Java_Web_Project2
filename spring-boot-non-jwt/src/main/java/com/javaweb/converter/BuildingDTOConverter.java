package com.javaweb.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.RentAreaEntity;

@Service
public class BuildingDTOConverter {
	@Autowired
	private ModelMapper modelMapper;
	
	public BuildingDTO toBuiildingDTO(BuildingEntity item) {
		BuildingDTO building = modelMapper.map(item, BuildingDTO.class);
		building.setAddress(item.getStreet() + ", " + item.getWard() + ", " + item.getDistrict().getName());
		List<RentAreaEntity> rentAreas = item.getRentAreas();
		String areaResult = rentAreas.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(","));
		building.setRentArea(areaResult);
		return building;
	}
}
