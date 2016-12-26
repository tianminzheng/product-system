package com.tianyalan.product.client.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianyalan.product.client.dao.OrderDao;
import com.tianyalan.product.client.domain.Order;
import com.tianyalan.product.client.domain.OrderStatus;
import com.tianyalan.product.mybatis.dao.MyBatisDAO;

@SuppressWarnings("unchecked")
@Repository("orderDao")
public class OrderDaoImpl implements OrderDao {

	@Autowired
	private MyBatisDAO myBatisDAO;
	
	@Override
	public void addOrder(Order order) {
		myBatisDAO.insert("addOrder", order);
	}

	@Override
	public void updateOrderStatus(Order order) {
		myBatisDAO.update("updateOrderStatus", order);
	}

	@Override
	public List<Order> getOrdersByUserId(Long userId) {
		return myBatisDAO.findForList("getOrdersByUserId", userId);
	}

	@Override
	public List<Order> getOrdersByStatus(OrderStatus status) {
		return myBatisDAO.findForList("getOrdersByStatus", status);
	}

	@Override
	public Order getOrderById(Long id) {
		return (Order)myBatisDAO.findForObject("getOrderById", id);
	}

}
