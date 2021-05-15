package com.promineotech.jeep.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.jeep.entity.Order;
import com.promineotech.jeep.entity.OrderRequest;
import com.promineotech.jeep.services.JeepOrderService;

import lombok.extern.slf4j.Slf4j;
@RestController
@Slf4j
public class BasicJeepOrderController implements JeepOrderController {
 
	
	@Autowired
	private JeepOrderService jeepOrderService;
	
	
	@Override
	public Order createOrder(OrderRequest orderRequest) {
	log.debug("order={}",orderRequest);
		return jeepOrderService.createOrder(orderRequest);
	}

}
