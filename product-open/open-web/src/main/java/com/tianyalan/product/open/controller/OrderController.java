package com.tianyalan.product.open.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tianyalan.product.client.domain.Order;
import com.tianyalan.product.client.domain.OrderStatus;
import com.tianyalan.product.client.service.OrderService;
import com.tianyalan.product.foundation.util.BaseController;
import com.tianyalan.product.foundation.util.ResultMessageBuilder;

@Controller
@RequestMapping(value = "/open/order")
public class OrderController extends BaseController {

	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value = "/{orderId}", method = RequestMethod.POST)
	public void getOrderById(@PathVariable Long orderId, HttpServletResponse response){		
		Order order = null;
		try{			
			order = orderService.getOrderById(orderId);
		} catch(Exception e) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, e), response);
			return;
		}
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, "success!", order), response);
	}
	
	@RequestMapping(value = "/by_user/{userId}", method = RequestMethod.POST)
	public void getOrdersByUserId(@PathVariable Long userId, HttpServletResponse response){		
		List<Order> orders = null;
		try{			
			orders = orderService.getOrdersByUserId(userId);
		} catch(Exception e) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, e), response);
			return;
		}
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, "success!", orders), response);
	}
	
	@RequestMapping(value = "/by_status/{status}", method = RequestMethod.POST)
	public void getOrdersByStatus(@PathVariable OrderStatus status, HttpServletResponse response){		
		List<Order> orders = null;
		try{			
			orders = orderService.getOrdersByStatus(status);
		} catch(Exception e) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, e), response);
			return;
		}
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, "success!", orders), response);
	}
}
