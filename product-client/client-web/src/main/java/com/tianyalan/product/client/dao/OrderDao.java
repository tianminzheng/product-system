package com.tianyalan.product.client.dao;

import java.util.List;

import com.tianyalan.product.client.domain.Order;
import com.tianyalan.product.client.domain.OrderStatus;

public interface OrderDao {

	void addOrder(Order order);
	
	void updateOrderStatus(Order order);
	
	Order getOrderById(Long id);
	
	List<Order> getOrdersByUserId(Long userId);
	
	List<Order> getOrdersByStatus(OrderStatus status);
}
