package com.tianyalan.product.client.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianyalan.product.client.dao.OrderItemDao;
import com.tianyalan.product.client.domain.OrderItem;
import com.tianyalan.product.mybatis.dao.MyBatisDAO;

@SuppressWarnings("unchecked")
@Repository("orderItemDao")
public class OrderItemDaoImpl implements OrderItemDao {

	@Autowired
	private MyBatisDAO myBatisDAO;
	
	@Override
	public void addOrderItems(List<OrderItem> orderItems) {
		myBatisDAO.insert("addOrderItems", orderItems);
	}

	@Override
	public List<OrderItem> getOrderItemsByOrderId(Long orderId) {
		return myBatisDAO.findForList("getOrderItemsByOrderId", orderId);
	}

}
