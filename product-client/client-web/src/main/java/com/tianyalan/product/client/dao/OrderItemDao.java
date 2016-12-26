package com.tianyalan.product.client.dao;

import java.util.List;

import com.tianyalan.product.client.domain.OrderItem;

public interface OrderItemDao {

	void addOrderItems(List<OrderItem> orderItems);
	
	List<OrderItem> getOrderItemsByOrderId(Long orderId);
}
