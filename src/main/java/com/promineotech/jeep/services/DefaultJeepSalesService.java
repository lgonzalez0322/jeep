package com.promineotech.jeep.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.promineotech.jeep.entity.Jeep;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class DefaultJeepSalesService implements JeepSalesService {
	



		
		@Override
		public List<Jeep> fetchJeeps(String model, String trim) {
			log.info("the fetchJeeps method was called with model={} and trim={}",
					model, trim);
		
		return null;
	}

}