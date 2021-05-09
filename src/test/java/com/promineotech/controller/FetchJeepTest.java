package com.promineotech.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.doThrow;

import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import com.promineotech.controller.support.FetchJeepTestSupport;
import com.promineotech.jeep.Constants;
import com.promineotech.jeep.JeepSales;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;
import com.promineotech.jeep.services.JeepSalesService;




class FetchJeepTest extends FetchJeepTestSupport{
	
	@Nested
	@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
	@ActiveProfiles ("test")
	@Sql (
		scripts = {"classpath:flyway/migrations/V1.0__Jeep_Schema.sql", 
				"classpath:flyway/migrations/V1.1__Jeep_Data.sql"},
	config = @SqlConfig(encoding = "utf-8"))
	class TestThatDoNotPolliuteTheApplicationConetxt extends FetchJeepTestSupport {

		@Test
		void testThatAnErrorMessageIseturnedWhenAnUnkownTrimAreSupplied() {
			//Given: a valid model, trim and URI
			JeepModel model = JeepModel.WRANGLER;
			String trim = "Unknown Value";
			String uri = String.format("%s?model=%s&trim=%s", getBaseUri(), model, trim);
//			System.out.println(uri);
		
			//When: a connection is made to the URI
			ResponseEntity<List<Jeep>> response =   getRestTemplate().exchange(uri,
					HttpMethod.GET, null, new ParameterizedTypeReference<List<Jeep>>() {});		
			
			
			//Then: a not found (404) status code is returned
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
			
			//And: an error message is returned

			
			@SuppressWarnings("unchecked")
			Map<String, Object> error = (Map<String, Object>) response.getBody();
			
			asserterrormessagevalid(error, HttpStatus.NOT_FOUND);
		}

		
		
		@ParameterizedTest
		@MethodSource("com.promineotech.controller.FetchJeepTest#parametersForINvalidInput")
		void testThatAnErrorMessageIseturnedWhenAnInvalidValueSupplied(String model, String trim, String reason) {
			//Given: a valid model, trim and URI
			
			String uri = String.format("%s?model=%s&trim=%s", getBaseUri(), model, trim);
			
//			System.out.println(uri);
		
			//When: a connection is made to the URI
			ResponseEntity<List<Jeep>> response =   getRestTemplate().exchange(uri,
					HttpMethod.GET, null, new ParameterizedTypeReference<List<Jeep>>() {});		
			
			
			//Then: a not found (404) status code is returned
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
			
			//And: an error message is returned

			
			@SuppressWarnings("unchecked")
			Map<String, Object> error = (Map<String, Object>) response.getBody();
			
			asserterrormessagevalid(error, HttpStatus.BAD_REQUEST);
		}
	
	}
	
	
	
	
	/*
	 * 
	 * @param error
	 * @param status
	 */


	static Stream<Arguments> parametersForINvalidInput1() {
		//@formatter:off
		return Stream.of(
				arguments("Wrangles", "@^#*&#%", "Trim contains non-alpha-numeric charachters"),
				arguments("WRANGLER", "reiohroh8434hfodhfddkjfdh489383fhuiejkdsnc298hfuijcn208fhowjckn028fhuwi"
						, "Trim length too long"),
				arguments("INVALID", "Suport", "Model is not enum value")
				//@formatter:on
				
				);
			}
	

	@Nested
	@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
	@ActiveProfiles ("test")
	@Sql (
		scripts = {"classpath:flyway/migrations/V1.0__Jeep_Schema.sql", 
				"classpath:flyway/migrations/V1.1__Jeep_Data.sql"},
	config = @SqlConfig(encoding = "utf-8"))
	class TestThatPolliuteTheApplicationConetxt extends FetchJeepTestSupport {
	@MockBean	
	private JeepSalesService jeepSalesService;
	
	@Test
	void testThatAnUnplannedErrorResultsInA500Status() {
		//Given: a valid model, trim and URI
		JeepModel model = JeepModel.WRANGLER;
		String trim = "Invalid";
		String uri = String.format("%s?model=%s&trim=%s", getBaseUri(), model, trim);
//		System.out.println(uri);
	
		
		doThrow(new RuntimeException("Ouch!")).when(jeepSalesService).fetchJeeps(model, trim);
		
		//When: a connection is made to the URI
		ResponseEntity<List<Jeep>> response =   getRestTemplate().exchange(uri,
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Jeep>>() {});		
		
		
		//Then: a not
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
		
		//And: an error message is returned

		
		@SuppressWarnings("unchecked")
		Map<String, Object> error = (Map<String, Object>) response.getBody();
		
		asserterrormessagevalid(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}


   }
		
}
