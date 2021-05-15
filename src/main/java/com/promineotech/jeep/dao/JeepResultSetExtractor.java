package com.promineotech.jeep.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;

import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;

public class JeepResultSetExtractor implements ResultSetExtractor<Jeep> {


	

	@Override
	public Jeep extractData(ResultSet rs) throws SQLException, DataAccessException {
		
		  rs.next();
	      // @formatter:off
	      return Jeep.builder()
	          .modelPK(rs.getLong("model_pk"))
	          .modelId(JeepModel.valueOf(rs.getString("model_id")))
	          .trimLevel(rs.getString("trim_level"))
	          .numDoors(rs.getInt("num_doors"))
	          .wheelSize(rs.getInt("wheel_size"))
	          .basePrice(rs.getBigDecimal("base_price"))         
	          .build();
	      // @formatter:on
		
	}

}
