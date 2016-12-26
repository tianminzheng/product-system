package com.tianyalan.product.client.service.local;

import java.util.List;

import com.tianyalan.product.client.domain.Order;
import com.tianyalan.product.client.domain.OrderStatus;
import com.tianyalan.product.foundation.exception.ServiceException;

public interface OrderLocalService {

	void addOrder(Order order) throws ServiceException;
	
	void updateOrderStatus(Order order) throws ServiceException;
	
	Order getOrderById(Long id) throws ServiceException;
	
	List<Order> getOrdersByUserId(Long userId) throws ServiceException;
	
	List<Order> getOrdersByStatus(OrderStatus status) throws ServiceException;
}
