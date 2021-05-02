package com.promineotech.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;


import com.promineotech.controller.support.FetchJeepTestSupport;
import com.promineotech.jeep.JeepSales;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;



@SpringBootTest(classes = {JeepSales.class}, webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles ("test")
@Sql 
class FetchJeepTest extends FetchJeepTestSupport{

	@Test
	void testThatJeepsAreReturnedWhenAValidModelAndTrimAreSupplied() {
		//Given: a valid model, trim and URI
		JeepModel model = JeepModel.WRANGLER;
		String trim = "Sport";
		String uri = String.format("%s?model=%s&trim=%s", getBaseUri(), model, trim);
//		System.out.println(uri);
	
		//When: a connection is made to the URI
		ResponseEntity<List<Jeep>> response = 
	    getRestTemplate().exchange(uri, HttpMethod.GET, null,
		 new ParameterizedTypeReference<List<Jeep>>() {});		
		
		
		//Then: if the test case passes then a success (OK - 200( status code is returned awesome what ever cool
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		
		//And: the actual list returned is the same as the expected list
		List<Jeep> actual = response.getBody();
		List<Jeep> expected = buildExpected();

		
		assertThat(actual).isEqualTo(expected);
//		System.out.println(getBaseUri());
//		fail("Not yet implemented");
	}

	
	
	
	@Test
	void testThatAnErrorMessageIseturnedWhenAValidModelAndTrimAreSupplied() {
		//Given: a valid model, trim and URI
		JeepModel model = JeepModel.WRANGLER;
		String trim = "Invalid Value";
		String uri = String.format("%s?model=%s&trim=%s", getBaseUri(), model, trim);
//		System.out.println(uri);
	
		//When: a connection is made to the URI
		ResponseEntity<Map<String, Object>> response =   getRestTemplate().exchange(uri,
				HttpMethod.GET, null, new ParameterizedTypeReference<>() {});		
		
		
		//Then: a not found (404) status code is returns
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		
		//And: an error message is returned
//		List<Jeep> actual = response.getBody();
//		List<Jeep> expected = buildExpected();
//
//		
//		assertThat(actual).isEqualTo(expected);
////		System.out.println(getBaseUri());
////		fail("Not yet implemented");
		
		Map<String, Object> error = response.getBody();
		
		assertThat(error)
				.containsKey("message")
				.containsEntry("status code", HttpStatus.NOT_FOUND.value())
				.containsEntry("uri", "/jeeps")
				.containsKey("timestamp")
				.containsEntry("reason", HttpStatus.NOT_FOUND.getReasonPhrase());
	}

}
