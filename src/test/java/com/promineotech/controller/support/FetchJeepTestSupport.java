package com.promineotech.controller.support;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;

public class FetchJeepTestSupport extends BaseTest {
protected List<Jeep> buildExpected() {
List<Jeep> list = new LinkedList<>();
	list.add(Jeep.builder()
			.modelId(JeepModel.WRANGLER)
			.trimLevel("sport")
			.numDoors(2)
			.wheelSize(17)
			.basePrice(new BigDecimal("28475.00")).
			build());
	return list;
	
}
}