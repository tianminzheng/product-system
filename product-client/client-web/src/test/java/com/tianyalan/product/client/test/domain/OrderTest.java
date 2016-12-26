package com.tianyalan.product.client.test.domain;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.tianyalan.product.client.domain.Order;
import com.tianyalan.product.client.domain.OrderItem;
import com.tianyalan.product.client.domain.OrderStatus;

public class OrderTest {

	@Test
	public void testOrderTotalPrice() {
		
		Order order = new Order();
		order.setUserId(1L);
		order.setVendorId(1L);
		order.setStatus(OrderStatus.INITIALIZED);
		
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		OrderItem item1 = new OrderItem();
		item1.setOrderId(order.getId());
		item1.setItemCount(1);
		item1.setProductCode("productCode1");
		item1.setProductName("productName1");
		item1.setProductPrice(100F);
		orderItems.add(item1);
		
		OrderItem item2 = new OrderItem();
		item2.setOrderId(order.getId());
		item2.setItemCount(2);
		item2.setProductCode("productCode2");
		item2.setProductName("productName3");
		item2.setProductPrice(150F);
		orderItems.add(item2);
		
		order.setOrderItems(orderItems);
		
		Assert.assertTrue(order.getTotalPrice() == 400F);
	}
}
