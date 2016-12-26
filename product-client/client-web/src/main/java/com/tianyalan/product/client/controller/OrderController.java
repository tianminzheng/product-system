package com.tianyalan.product.client.controller;

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
import com.tianyalan.product.client.dto.OrderDTO;
import com.tianyalan.product.client.service.local.OrderLocalService;
import com.tianyalan.product.foundation.exception.ServiceException;
import com.tianyalan.product.foundation.util.BaseController;
import com.tianyalan.product.foundation.util.ResultMessageBuilder;

@Controller
@RequestMapping(value = "/order")
public class OrderController extends BaseController {

	@Autowired
	private OrderLocalService orderLocalService;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public void addOrder(@RequestBody OrderDTO orderDTO, HttpServletResponse response){
		try{
			Order order = orderDTO.convertToOrder();
			orderLocalService.addOrder(order);
		} catch(ServiceException e) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, e.getMessage()), response);
			return;
		}
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, "success!"), response);
	}
	
	@RequestMapping(value = "/confirm/{orderId}", method = RequestMethod.POST)
	public void confirmOrder(@PathVariable Long orderId, HttpServletResponse response){
		try{
			Order order = new Order();
			order.setId(orderId);
			order.setStatus(OrderStatus.CONFIRMED);
			orderLocalService.updateOrderStatus(order);
		} catch(ServiceException e) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, e.getMessage()), response);
			return;
		}
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, "success!"), response);
	}
	
	@RequestMapping(value = "/cancel/{orderId}", method = RequestMethod.POST)
	public void cancelOrder(@PathVariable Long orderId, HttpServletResponse response){
		try{
			Order order = new Order();
			order.setId(orderId);
			order.setStatus(OrderStatus.CANCELLED);
			orderLocalService.updateOrderStatus(order);
		} catch(ServiceException e) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, e.getMessage()), response);
			return;
		}
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, "success!"), response);
	}
	
	@RequestMapping(value = "/ship/{orderId}", method = RequestMethod.POST)
	public void shipOrder(@PathVariable Long orderId, HttpServletResponse response){
		try{
			Order order = new Order();
			order.setId(orderId);
			order.setStatus(OrderStatus.SHIPPED);
			orderLocalService.updateOrderStatus(order);
		} catch(ServiceException e) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, e.getMessage()), response);
			return;
		}
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, "success!"), response);
	}
	
	@RequestMapping(value = "/finish/{orderId}", method = RequestMethod.POST)
	public void finishOrder(@PathVariable Long orderId, HttpServletResponse response){
		try{
			Order order = new Order();
			order.setId(orderId);
			order.setStatus(OrderStatus.FINISHED);
			orderLocalService.updateOrderStatus(order);
		} catch(ServiceException e) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, e.getMessage()), response);
			return;
		}
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, "success!"), response);
	}
	
	@RequestMapping(value = "/{orderId}", method = RequestMethod.GET)
	public void getOrderById(@PathVariable Long orderId, HttpServletResponse response){		
		Order order = null;
		try{			
			order = orderLocalService.getOrderById(orderId);
		} catch(ServiceException e) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, e.getMessage()), response);
			return;
		}
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, "success!", order), response);
	}
	
	@RequestMapping(value = "/by_user/{userId}", method = RequestMethod.GET)
	public void getOrdersByUserId(@PathVariable Long userId, HttpServletResponse response){		
		List<Order> orders = null;
		try{			
			orders = orderLocalService.getOrdersByUserId(userId);
		} catch(ServiceException e) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, e.getMessage()), response);
			return;
		}
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, "success!", orders), response);
	}
	
	@RequestMapping(value = "/by_status/{status}", method = RequestMethod.GET)
	public void getOrdersByStatus(@PathVariable OrderStatus status, HttpServletResponse response){		
		List<Order> orders = null;
		try{			
			orders = orderLocalService.getOrdersByStatus(status);
		} catch(ServiceException e) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, e.getMessage()), response);
			return;
		}
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, "success!", orders), response);
	}
}
