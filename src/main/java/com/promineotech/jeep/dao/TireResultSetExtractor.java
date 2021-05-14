package com.promineotech.jeep.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;

import com.promineotech.jeep.entity.Tire;

public class TireResultSetExtractor implements ResultSetExtractor<Tire> {

	@Override
	public Tire extractData(ResultSet rs) throws SQLException, DataAccessException {
		
		
		return null;
	}



}
