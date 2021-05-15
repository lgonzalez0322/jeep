package com.promineotech.jeep.entity;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class Tire {

	private Long tirepk;
	private String tireId;
	private String tireSize;
	private String manufacturer;
	private BigDecimal price;
	private int warrantyMiles;
}
