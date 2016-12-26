package com.tianyalan.product.client.test.restfulservice;

import com.tianyalan.product.client.domain.OrderItem;
import com.tianyalan.product.client.domain.OrderStatus;
import com.tianyalan.product.client.dto.OrderDTO;
import com.tianyalan.product.client.test.RestTemplateUtil;
import com.tianyalan.product.foundation.util.ResultMessageBuilder.ResultMessage;

public class OrderControllerTest {

	public static void main(String[] args) {
//		testAddOrder();
//		tesConfirmOrder();
		testGetOrderById();
	}

	private static void testAddOrder() {

		OrderDTO order = new OrderDTO();
		order.setUserId(1L);
		order.setVendorId(1L);
		order.setStatus(OrderStatus.INITIALIZED);

		OrderItem[] orderItems = new OrderItem[1];
		OrderItem item1 = new OrderItem();
		item1.setOrderId(order.getId());
		item1.setItemCount(1);
		item1.setProductCode("productCode1");
		item1.setProductName("productName1");
		item1.setProductPrice(100F);
		orderItems[0] = item1;
		order.setOrderItems(orderItems);

		ResultMessage result = RestTemplateUtil.postForObject(
				"http://127.0.0.1:8081/client-web/order", order);

		System.out.println(result.getErrMsg());
	}

	private static void tesConfirmOrder() {

		ResultMessage result = RestTemplateUtil.postForObjectWithPathVariable(
				"http://127.0.0.1:8081/client-web/order/confirm/{orderId}",
				null, 2L);

		System.out.println(result.getErrMsg());
	}

	private static void testGetOrderById() {

		ResultMessage result = RestTemplateUtil.getForObject(
				"http://127.0.0.1:8081/client-web/order/1", null);

		System.out.println(result.getErrMsg());
	}
}
