package com.tianyalan.product.client.service.local.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianyalan.product.client.dao.OrderDao;
import com.tianyalan.product.client.dao.OrderItemDao;
import com.tianyalan.product.client.domain.Order;
import com.tianyalan.product.client.domain.OrderItem;
import com.tianyalan.product.client.domain.OrderStatus;
import com.tianyalan.product.client.service.local.OrderLocalService;
import com.tianyalan.product.core.domain.Product;
import com.tianyalan.product.core.domain.User;
import com.tianyalan.product.core.domain.Vendor;
import com.tianyalan.product.core.service.ProductService;
import com.tianyalan.product.core.service.UserService;
import com.tianyalan.product.core.service.VendorService;
import com.tianyalan.product.foundation.exception.ServiceException;

@Service("orderLocalService")
public class OrderLocalServiceImpl implements OrderLocalService {
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private OrderItemDao orderItemDao;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private VendorService vendorService;
	
	@Autowired
	private ProductService productService;	

	@Override
	public void addOrder(Order order) throws ServiceException {
		User user = userService.getUserById(order.getUserId());
		if(user == null)
			throw new ServiceException("订单对应的用户信息不存在！");
		
		Vendor vendor = vendorService.getVendorById(order.getVendorId());
		if(vendor == null)
			throw new ServiceException("订单对应的供应商不存在！");		
		
		orderDao.addOrder(order);
		
		for(OrderItem orderItem : order.getOrderItems()){
			orderItem.setOrderId(order.getId());
			
			Product product = productService.getProductByCode(orderItem.getProductCode());
			if(product == null)
				throw new ServiceException("订单对应的产品信息不存在！");
		}
		
		orderItemDao.addOrderItems(order.getOrderItems());		
	}

	@Override
	public void updateOrderStatus(Order order) throws ServiceException {
		orderDao.updateOrderStatus(order);
	}

	@Override
	public List<Order> getOrdersByUserId(Long userId) throws ServiceException {
		List<Order> orders = orderDao.getOrdersByUserId(userId);
		buildOrderWithOrderItems(orders);
				
		return orders;
	}

	@Override
	public List<Order> getOrdersByStatus(OrderStatus status) throws ServiceException {
		List<Order> orders = orderDao.getOrdersByStatus(status);
		buildOrderWithOrderItems(orders);
				
		return orders;
	}
	
	private void buildOrderWithOrderItems(List<Order> orders) throws ServiceException {
		for(Order order : orders) {
			List<OrderItem> orderItems = orderItemDao.getOrderItemsByOrderId(order.getId());
			order.setOrderItems(orderItems);
		}
	}

	@Override
	public Order getOrderById(Long id) throws ServiceException{
		Order order = orderDao.getOrderById(id);
		if(order == null)
			return null;
		
		List<OrderItem> orderItems = orderItemDao.getOrderItemsByOrderId(order.getId());
		order.setOrderItems(orderItems);
		
		return order;
	}
}
