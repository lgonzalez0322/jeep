package com.promineotech.controller.support;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;

public class FetchJeepTestSupport extends BaseTest {
protected List<Jeep> buildExpected() {
List<Jeep> list = new LinkedList<>();
	

//@formatter;off

	list.add(Jeep.builder()
			.modelId(JeepModel.WRANGLER)
			.trimLevel("sport")
			.numDoors(2)
			.wheelSize(17)
			.basePrice(new BigDecimal("28475.00")).
			build());
	
	
	list.add(Jeep.builder()
			.modelId(JeepModel.WRANGLER)
			.trimLevel("sport")
			.numDoors(4)
			.wheelSize(17)
			.basePrice(new BigDecimal("32475.00")).
			build());
	
	// @formatter:on
	
	Collections.sort(list);
	return list;
	
}


/*
 * 
 * @param error
 * @param status
 */


	protected void asserterrormessagevalid(Map<String, Object> error,
			HttpStatus status) {
		// @formatter:off
		assertThat(error)
				.containsKey("message")
				.containsEntry("status code", HttpStatus.NOT_FOUND.value())
				.containsEntry("uri", "/jeeps")
				.containsKey("timestamp")
				.containsEntry("reason", HttpStatus.NOT_FOUND.getReasonPhrase());
		// @formatter:on
	}
	

}