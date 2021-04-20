package com.promineotech.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplateExtensionsKt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;


import com.promineotech.controller.support.FetchJeepTestpSupport;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles ("test")
@Sql 
class FetchJeepTest extends FetchJeepTestpSupport{

	@Test
	void testThatJeepsAreReturnedWhenAValidModelAndTrimAreSupplied() {
		//Given: a valid model, trim and URI
		JeepModel model = JeepModel.WRANGLER;
		String trim = "Sport";
		String uri = 
				String.format("%s?model1=%s&trim=%s", getBaseUri(), model, trim);
		
	
		//When: a connection is made to the URI
		ResponseEntity<Jeep> response =
				getRestTemplate().getForEntity(uri, Jeep.class);
		
		
		
		
		//Then: a succes (OK - 200( status code is returned
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		
		
		
//		System.out.println(getBaseUri());
//		fail("Not yet implemented");
	}

}
