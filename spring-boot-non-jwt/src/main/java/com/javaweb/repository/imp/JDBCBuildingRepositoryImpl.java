package com.javaweb.repository.imp;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.StringUtil;

// Tầng Data Access Layer
@Repository
@PropertySource("classpath:application.properties")
public class JDBCBuildingRepositoryImpl implements BuildingRepository{
	
	@Value("${spring.datasource.url}")
	private String DB_URL;
	
	@Value("${spring.datasource.username}")
	private String USER;
	
	@Value("${spring.datasource.password}")
	private String PASS;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public static void joinTable(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql) {
		Long staffId = buildingSearchBuilder.getStaffId();
		if(staffId != null) {
			sql.append(" INNER JOIN assignmentbuilding ON b.id = assignmentbuilding.buildingid ");
		}
		
		List<String> typeCode = buildingSearchBuilder.getTypeCode();
		if(typeCode != null && typeCode.size() != 0) {
			sql.append(" INNER JOIN buildingtype ON b.id = buildingtype.buildingid ");
			sql.append(" INNER JOIN renttype ON renttype.id = buildingtype.buildingid ");
		}
		
		String rentAreaFrom = buildingSearchBuilder.getAreaFrom();
		String rentAreaTo = buildingSearchBuilder.getAreaTo();
		if(StringUtil.checkString(rentAreaFrom) == true || StringUtil.checkString(rentAreaTo) == true) {
			sql.append(" INNER JOIN rentarea ON b.id = rentarea.buildingid ");
		}
	}
	
	public static void queryNomal(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
		try {
			Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
			for(Field item : fields) {
				item.setAccessible(true);
				String fieldName = item.getName();
				if(!fieldName.equals("staffId") && !fieldName.equals("typeCode") && 
						!fieldName.startsWith("area") && !fieldName.startsWith("rentPrice")) {
					Object value = item.get(buildingSearchBuilder);
					if(value != null) {
					 	if(item.getType().getName().equals("java.lang.Long") || item.getType().getName().equals("java.lang.Integer")) {
						 	where.append(" AND b." + fieldName + " = " + value);
					 	}
					 	else if(item.getType().getName().equals("java.lang.String")){
						 	where.append(" AND b." + fieldName + " LIKE '%" + value + "%' ");
					 	}
				 	}
				}
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void querySpecial(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
		Long staffId = buildingSearchBuilder.getStaffId();
		if(staffId != null) {
			where.append(" AND assignmentbuilding.staffid = " + staffId);
		}
		String rentAreaFrom = buildingSearchBuilder.getAreaFrom();
		String rentAreaTo = buildingSearchBuilder.getAreaTo();
		if(StringUtil.checkString(rentAreaFrom) == true || StringUtil.checkString(rentAreaTo) == true) {
			if(StringUtil.checkString(rentAreaFrom)) {
				where.append(" AND rentarea.value >=" + rentAreaFrom);
			}
			if(StringUtil.checkString(rentAreaTo)) {
				where.append(" AND rentarea.value <=" + rentAreaTo);
			}
		}
		
		Long rentPriceFrom = buildingSearchBuilder.getRentPriceFrom();
		Long rentPriceTo = buildingSearchBuilder.getRentPriceTo();
		if(rentPriceFrom != null || rentPriceTo != null) {
			if(rentPriceFrom != null) {
				where.append(" AND b.rentprice >=" + rentPriceFrom);
			}
			if(rentPriceTo != null) {
				where.append(" AND b.rentprice <=" + rentPriceTo);
			}
		}
		
		List<String> typeCode = buildingSearchBuilder.getTypeCode();
		if(typeCode != null && typeCode.size() != 0) {
			where.append("AND (");
			String sql = typeCode.stream().map(it -> "renttype.code like " + "'%" + it + "%' ").collect(Collectors.joining(" OR "));
			where.append(sql);			
			where.append(" ) ");
		}
	}
	
	@Override
	public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
		StringBuilder sql = new StringBuilder("SELECT b.* FROM building b ");
		joinTable(buildingSearchBuilder, sql);
		StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
		queryNomal(buildingSearchBuilder, where);
		querySpecial(buildingSearchBuilder, where);
		where.append(" GROUP BY b.id;");
		sql.append(where);
		Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
		return query.getResultList(); 
	}
	
}