package com.javaweb.converter;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.utils.MapUtil;

@Component
public class BuildingSearchBuilderConverter {
	public BuildingSearchBuilder toBuildingSearchBuilder(Map<String, Object> params, List<String> typeCode ) {
		BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
															        .setName(MapUtil.getObject(params, "name", String.class))
															        .setFloorArea(MapUtil.getObject(params, "floorarea", Long.class))
															        .setWard(MapUtil.getObject(params, "ward", String.class))
															        .setStreet(MapUtil.getObject(params, "street", String.class))
															        .setNumberofBasement(MapUtil.getObject(params, "numberofBasement", Integer.class))
															        .setTypeCode(typeCode)
															        .setManagerName(MapUtil.getObject(params, "managerName", String.class))
															        .setManagerPhoneNumber(MapUtil.getObject(params, "managerPhoneNumber", String.class))
															        .setRentPriceFrom(MapUtil.getObject(params, "rentPricefrom", Long.class))
															        .setRentPriceTo(MapUtil.getObject(params, "rentPriceto", Long.class))
															        .setAreaFrom(MapUtil.getObject(params, "areaFrom", String.class))
															        .setAreaTo(MapUtil.getObject(params, "areaTo", String.class))
															        .setStaffId(MapUtil.getObject(params, "staffid", Long.class))
															        .build();

				return buildingSearchBuilder;
	}
}