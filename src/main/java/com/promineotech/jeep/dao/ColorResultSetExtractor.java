package com.promineotech.jeep.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;

import com.promineotech.jeep.entity.Color;
import com.promineotech.jeep.entity.Jeep;

public class ColorResultSetExtractor implements ResultSetExtractor<Color> {

	@Override
	public Color extractData(ResultSet rs) throws SQLException, DataAccessException {
		
		  rs.next();
	      // @formatter:off
	      return Color.builder()
	          .colorPK(rs.getLong("color_pk"))
	          .colorId(rs.getString("color_id"))
	          .color(rs.getString("color"))
	          .price(rs.getBigDecimal("price"))
	          .isExterior(rs.getBoolean("is_exterior"))
	          .build();
	      // @formatter:on
	}

	
	

}
