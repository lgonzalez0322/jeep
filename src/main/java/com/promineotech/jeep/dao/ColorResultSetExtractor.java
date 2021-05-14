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
		
		return null;
	}

	
	

}
