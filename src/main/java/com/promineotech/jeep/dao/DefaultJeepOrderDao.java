package com.promineotech.jeep.dao;



import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.promineotech.jeep.entity.Color;
import com.promineotech.jeep.entity.Customer;
import com.promineotech.jeep.entity.Engine;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;
import com.promineotech.jeep.entity.Option;
import com.promineotech.jeep.entity.OptionType;
import com.promineotech.jeep.entity.Order;
import com.promineotech.jeep.entity.Tire;
import com.promineotech.jeep.entity.FuelType;

@Component
public class DefaultJeepOrderDao implements JeepOrderDao {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	

	@Override
	public Order saveOrder(Customer customer, Jeep jeep, Color color, 
			Engine engine, Tire tire, BigDecimal price, List<Option> options) {
		SqlParams params =
				generateInsertSql(customer, jeep, color, engine, tire, price);
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(params.sql, params.source, keyHolder);
		
		Long orderPK = keyHolder.getKey().longValue();
		
		saveOptions(options, orderPK);
		//@formatter :off
		return Order.builder()
				.orderPK(orderPK)
				.customer(customer)
				.model(jeep)
				.color(color)
				.engine(engine)
				.tire(tire)
				.options(options)
				.price(price)
				.build();
				
		//@formatter:on
	}
	
	
	
	
	private void saveOptions(List<Option> options, Long orderPK) {
for(Option option : options) {
		SqlParams params = generateInsertSql(option, orderPK);
			
		jdbcTemplate.update(params.sql, params.source);
 }

	}


	private SqlParams generateInsertSql(Option option, Long orderPK) {
		SqlParams params = new SqlParams();
		//@formatter:off
		params.sql = ""
				+ "INSERT INTO order_options ("
				+"option_fk, order_fk"
				+ ") VALUES ("
				+ ":option_fk, :order_fk"
				+ ")";
		//@formatter:on
		params.source.addValue("option_fk",option.getOptionPK());
		params.source.addValue("order_fk", orderPK);
		
		return params;
	}




	private SqlParams generateInsertSql(Customer customer, Jeep jeep, Color color, Engine engine, Tire tire,
			BigDecimal price) {
		//@formatter :off
		String sql = ""
				+ "INSERT INTO orders ("
				+ "customer_fk, color_fk, engine_fk, tire_fk, model_fk, price"
				+") VALUES ("
				+ ":customer_fk, :color_fk, :engine_fk, :tire_fk, :model_fk, :price"				
				+ ")";
				//@formatter:on
		
		SqlParams params = new SqlParams();
		params.sql = sql;
		params.source.addValue("customer_fk", customer.getCustomerPK());
		params.source.addValue("color_fk", color.getColorPK());
		params.source.addValue("engine_fk", engine.getEnginePK());
		params.source.addValue("tire_fk", tire.getTirepk());
		params.source.addValue("model_fk", jeep.getModelPK());
		params.source.addValue("price", price);
		
				
		return params;
	}




	@Override
	public List<Option> fetchOptions(List<String> optionIds) {
		if(optionIds.isEmpty()) {
			return new LinkedList<Option>();
		}
		
		Map<String, Object> params = new HashMap<>();
		
		//@ formatter:off
		String sql = ""
		+"Select * "
		+ "FROM options "
		+ "WHERE option_id IN(";
	//@formatter :on
		
		
		for (int index = 0; index < optionIds.size(); index++) {
			String key = "option_" + index;
			sql += ":" + key + ", ";
			params.put(key, optionIds.get(index));
		}
		
		
		sql = sql.substring(0, sql.length() - 2);
		sql += ")";
		
	return jdbcTemplate.query(sql, params, new RowMapper<Option>() {

		@Override
		public Option mapRow(ResultSet rs, int rowNum) throws SQLException {
		
			return Option.builder()
					.category(OptionType.valueOf(rs.getString("category")))
					.manufacturer(rs.getString("manufacturer"))
					.name(rs.getString("name"))
					.optionId(rs.getString("option_id"))
					.optionPK(rs.getLong("option_pk"))
					.price(rs.getBigDecimal("price"))
					.build();
			
		}});
		
		
	}
	
	
	
	
	/*
	 * 
	 * 
	 */
	
	@Override
	public Optional <Customer> fetchCustomer(String customerId) {
	String sql = ""
			+ "Select * "
			+ "From customers "
			+ "Where customer_id = :customer_id";
			
	
	Map<String, Object> params = new HashMap<>();
	params.put("customer_id", customerId);
	
	return Optional.ofNullable(
			jdbcTemplate.query(sql, params, new CustomerResultSetExtractor()));
	}

		
	@Override
	public Optional <Jeep> fetchModel(JeepModel model, String trim, int doors) {
		// @formatter :off
		String sql = ""
				+ "SELECT * "
				+ "FROM models "
				+ "WHERE model_id = :model_id "
				+ "AND trim_level = :trim_level "
				+ "AND num_doors = :num_doors";
		// @formatter :on
		Map<String, Object> params = new HashMap<>();
		params.put("model_id", model.toString());
		params.put("trim_level", trim);
		params.put("num_doors", doors);
		
		return Optional.ofNullable(jdbcTemplate.query(sql, params, new JeepResultSetExtractor()));
		
		}
	
	
	@Override
	public Optional <Color> fetchColor(String colorId) {
		//formatter :off
		String sql = ""
		+ "SELECT * "
		+ "FROM colors "
		+ "WHERE color_id = :colorid";
		// @formatter :on
				
		Map<String, Object> params = new HashMap<>();  
		params.put("color_id", colorId);
		
		return Optional.ofNullable(jdbcTemplate.query(sql, params, new ColorResultSetExtractor()));
	}
	
	
	

	@Override
	public Optional <Engine> fetchEngine(String engineId) {
		//formatter :off
		String sql = ""
		+ "SELECT * "
		+ "FROM engines "
		+ "WHERE engine_id = :engine_id";
		// @formatter :on
				
		Map<String, Object> params = new HashMap<>();  
		params.put("engine_id", engineId);
		
		return Optional.ofNullable(jdbcTemplate.query(sql,
				params, new EngineResultSetExtractor()));
	}
	
	
	
	@Override
	public Optional <Tire> fetchTire (String tireId) {
		//formatter :off
				String sql = ""
				+ "SELECT * "
				+ "FROM tires "
				+ "WHERE tire_id = :tire_id";
				// @formatter :on
				
				
			Map<String, Object> params = new HashMap<>();
			params.put("tire_id", tireId);
			
			return Optional.ofNullable(jdbcTemplate.query(sql, params, new TireResultSetExtractor()));
	}
	
	
	


	
		
	class CustomerResultSetExtractor implements ResultSetExtractor<Customer> {
	
		@Override
		public Customer extractData(ResultSet rs) throws SQLException, DataAccessException {
			rs.next();
			
			
			// @formatter:off
			return Customer.builder()
					.customerId(rs.getString("customer_id"))
					.customerPK(rs.getLong("customer_pk"))
					.firstName(rs.getString("first_name"))
					.lastName(rs.getString("last_name"))
					.phone(rs.getString("phone"))
					.build();
		//@formatter: on
		}
		
	}

class SqlParams {
	String sql;
	MapSqlParameterSource source = new MapSqlParameterSource();
}

//@Override
//public Order saveOrder(Customer customer, Jeep jeep, Color color, Engine engine, Tire tire, BigDecimal price) {
//	// TODO Auto-generated method stub
//	return null;
//}



}
