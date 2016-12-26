package com.tianyalan.product.client.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tianyalan.product.client.domain.Order;
import com.tianyalan.product.client.domain.OrderStatus;
import com.tianyalan.product.client.service.OrderService;
import com.tianyalan.product.client.service.local.OrderLocalService;

public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderLocalService orderLocalService;
	
	@Override
	public void addOrder(Order order) {
		orderLocalService.addOrder(order);
	}

	@Override
	public void confirmOrder(Order order) {
		order.setStatus(OrderStatus.CONFIRMED);
		orderLocalService.updateOrderStatus(order);
	}

	@Override
	public void cancelOrder(Order order) {
		order.setStatus(OrderStatus.CANCELLED);
		orderLocalService.updateOrderStatus(order);
	}

	@Override
	public void shipOrder(Order order) {
		order.setStatus(OrderStatus.SHIPPED);
		orderLocalService.updateOrderStatus(order);
	}

	@Override
	public void finishOrder(Order order) {
		order.setStatus(OrderStatus.FINISHED);
		orderLocalService.updateOrderStatus(order);
	}

	@Override
	public List<Order> getOrdersByUserId(Long userId) {
		return orderLocalService.getOrdersByUserId(userId);
	}

	@Override
	public List<Order> getOrdersByStatus(OrderStatus status) {
		return orderLocalService.getOrdersByStatus(status);
	}

	@Override
	public Order getOrderById(Long id) {
		return orderLocalService.getOrderById(id);
	}

}
