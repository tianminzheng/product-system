package com.tianyalan.product.client.domain;

import java.util.List;

import com.tianyalan.product.foundation.entity.BaseEntity;

public class Order extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Long userId;
	
	private Long vendorId;
	
	private List<OrderItem> orderItems;
	
	private OrderStatus status;
	
	public Order() { }
	
	public Order(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
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

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
	public Float getTotalPrice() {
		Float totalPrice = 0.0F;
		
		if(orderItems == null || orderItems.size() == 0)
			return totalPrice;
		
		for(OrderItem orderItem : orderItems) {
			totalPrice += orderItem.getProductPrice() * orderItem.getItemCount();
		}
		
		return totalPrice;
	}
}
