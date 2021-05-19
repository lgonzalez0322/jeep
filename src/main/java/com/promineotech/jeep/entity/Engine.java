package com.promineotech.jeep.entity;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class Engine {

	
	private Long enginePK;
	private String engineID;
	private Float sizeInLiteers;
	private String name;
	private FuelType fuelType;
	private Float mpgCity;
	private Float mpgHwy;
	private Boolean hasStartStop;
	private String description;
	private BigDecimal price;
}
