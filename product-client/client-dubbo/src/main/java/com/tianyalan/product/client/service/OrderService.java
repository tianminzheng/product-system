package com.tianyalan.product.client.service;

import java.util.List;

import com.tianyalan.product.client.domain.Order;
import com.tianyalan.product.client.domain.OrderStatus;

public interface OrderService {

	void addOrder(Order order);
	
	void confirmOrder(Order order);
	
	void cancelOrder(Order order);
	
	void shipOrder(Order order);
	
	void finishOrder(Order order);
	
	Order getOrderById(Long id);
	
	List<Order> getOrdersByUserId(Long userId);
	
	List<Order> getOrdersByStatus(OrderStatus status);
	
}
