package com.promineotech.jeep.controller;

import java.util.List;
import com.promineotech.jeep.entity.Jeep;

import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;


import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class BasicJeepSalesController implements JeepSalesController {

	@Override
	public List<Jeep> fetchJeeps(String model, String trim) {
		// TODO Auto-generated method stub
		return null;
	}
	


}
 