package com.tianyalan.product.client.test.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tianyalan.product.client.dao.OrderDao;
import com.tianyalan.product.client.dao.OrderItemDao;
import com.tianyalan.product.client.domain.Order;
import com.tianyalan.product.client.domain.OrderItem;
import com.tianyalan.product.client.domain.OrderStatus;
import com.tianyalan.product.client.test.TestBase;

public class OrderDaoTest extends TestBase {

	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private OrderItemDao orderItemDao;
	
	@Test
	public void testAddOrders() {
		Order order = new Order();
		order.setUserId(1L);
		order.setVendorId(1L);
		order.setStatus(OrderStatus.INITIALIZED);
		
		orderDao.addOrder(order);
		
		Assert.assertTrue(order.getId() > 0);
		
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		OrderItem item1 = new OrderItem();
		item1.setOrderId(order.getId());
		item1.setItemCount(1);
		item1.setProductCode("productCode1");
		item1.setProductName("productName1");
		item1.setProductPrice(100F);
		orderItems.add(item1);
		
		orderItemDao.addOrderItems(orderItems);
	}

	@Test
	public void testUpdateOrderStatus() {
		Order order = new Order();
		order.setId(1L);
		order.setStatus(OrderStatus.CONFIRMED);
		
		orderDao.updateOrderStatus(order);
		
		Order updatedOrder = orderDao.getOrderById(1L);
		
		Assert.assertTrue(updatedOrder.getStatus().equals(OrderStatus.CONFIRMED));
	}
	
	@Test
	public void testGetOrdersById() {
		Order order = orderDao.getOrderById(1L);
		
		Assert.assertTrue(order != null);
		Assert.assertTrue(order.getOrderItems() == null);
	}
}
