package com.promineotech.jeep.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;


//import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

import com.promineotech.jeep.JeepSales;
import com.promineotech.jeep.entity.Tire;

@TestConfiguration
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = {JeepSales.class})
@ActiveProfiles ("test")
@Sql(
	scripts = {"classpath:flyway/migrations/V1.0__Jeep_Schema.sql", 
			"classpath:flyway/migrations/V1.1__Jeep_Data.sql"},
config = @SqlConfig(encoding = "utf-8"))


class DefaultJeepOrderDaoTest {

	
	@Autowired
	private DefaultJeepOrderDao dao;
	
	@Test
	void test() {
		fail("Not yet implemented");
		
		
	}
//	
//	@Test
//	public static void testFetchTire() {
//		Optional<Tire> result = dao.fetchTire();
//	}
	

}
