package com.tianyalan.product.client.dto;

import com.google.common.collect.Lists;
import com.tianyalan.product.client.domain.Order;
import com.tianyalan.product.client.domain.OrderItem;
import com.tianyalan.product.client.domain.OrderStatus;

public class OrderDTO implements BaseDTO {
	
	private Long id;
	private Long userId;
	private Long vendorId;
	private OrderItem[] orderItems;
	private OrderStatus status;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getVendorId() {
		return vendorId;
	}
	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}
	public OrderItem[] getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(OrderItem[] orderItems) {
		this.orderItems = orderItems;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	@Override
	public void convertEntityToDTO(Object entity) {
		if(entity == null)
			return;
		
		Order order = (Order)entity;		
		this.id = order.getId();
		this.orderItems = order.getOrderItems().toArray(orderItems);
		this.userId = order.getUserId();
		this.vendorId = order.getVendorId();
		this.status = order.getStatus();
	}
	
	public Order convertToOrder() {
		Order order = new Order();
		order.setUserId(userId);
		order.setVendorId(vendorId);
		order.setOrderItems(Lists.newArrayList(this.orderItems));
		order.setStatus(status);
		
		return order;
	}
}
