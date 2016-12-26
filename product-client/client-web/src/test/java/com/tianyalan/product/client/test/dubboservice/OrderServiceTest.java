package com.tianyalan.product.client.test.dubboservice;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tianyalan.product.client.domain.Order;
import com.tianyalan.product.client.service.OrderService;
import com.tianyalan.product.client.test.TestBase;

public class OrderServiceTest extends TestBase {
	
	@Autowired
	private OrderService dubboOrderService;
	
	@Test
	public void testGetOrderById(){
		Order order = dubboOrderService.getOrderById(1L);
		
		Assert.assertTrue(order != null);
		Assert.assertTrue(order.getOrderItems() != null);
	}

}
