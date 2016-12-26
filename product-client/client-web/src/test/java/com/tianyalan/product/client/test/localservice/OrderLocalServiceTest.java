package com.tianyalan.product.client.test.localservice;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tianyalan.product.client.domain.Order;
import com.tianyalan.product.client.domain.OrderItem;
import com.tianyalan.product.client.domain.OrderStatus;
import com.tianyalan.product.client.service.local.OrderLocalService;
import com.tianyalan.product.client.test.TestBase;
import com.tianyalan.product.foundation.exception.ServiceException;

public class OrderLocalServiceTest extends TestBase {

	@Autowired
	private OrderLocalService orderLocalService;
	
	@Test
	public void testGetOrderById() {		
		Order order = orderLocalService.getOrderById(1L);
		
		Assert.assertTrue(order != null);
		Assert.assertTrue(order.getOrderItems().size() > 0);
	}
	
	@Test//(expected = ServiceException.class)
	public void testAddOrder() {
		Order order = new Order();
		order.setUserId(1L);
		order.setVendorId(1L);
		order.setStatus(OrderStatus.INITIALIZED);
		
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		OrderItem item1 = new OrderItem();
		item1.setOrderId(order.getId());
		item1.setItemCount(1);
		item1.setProductCode("product1");
		item1.setProductName("productName1");
		item1.setProductPrice(100F);
		orderItems.add(item1);
		order.setOrderItems(orderItems);
		
		orderLocalService.addOrder(order);
	}
	
	@Test
	public void testGetOrdersByUserId() {
		Long userId = 1L;
		
		List<Order> orders = orderLocalService.getOrdersByUserId(userId);
		
		Assert.assertTrue(orders != null);
		Assert.assertTrue(orders.size() > 0);
	}
}
