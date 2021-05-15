package com.promineotech.jeep.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;

import com.promineotech.jeep.entity.Engine;
import com.promineotech.jeep.entity.FuelType;

public class EngineResultSetExtractor implements ResultSetExtractor<Engine> {

	

	@Override
	public Engine extractData(ResultSet rs) throws SQLException, DataAccessException {
		// TODO Auto-generated method stub
		 rs.next();
	      // @formatter:off
	      return Engine.builder()
	          .enginePK(rs.getLong("engine_pk"))
	          .engineID(rs.getString("engine_id"))
	          .sizeInLiteers(rs.getFloat("size_in_liters"))
	          .name(rs.getString("name"))
	          .fuelType(FuelType.valueOf(rs.getString("fuel_type")))
	          .mpgCity(rs.getFloat("mpg_city"))
	          .mpgHwy(rs.getFloat("mpg_hwy"))
	          .hasStartStop(rs.getBoolean("has_start_stop"))
	          .description(rs.getString("description"))
	          .price(rs.getBigDecimal("price"))
	          .build();
	      // @formatter:on
	}

}
