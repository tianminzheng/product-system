package com.tianyalan.product.integration.listener;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.tianyalan.product.integration.config.RequestCode;
import com.tianyalan.product.integration.request.ProductSyncRequest;
import com.tianyalan.product.integration.route.message.AsyncRouteService;

@Component("productSyncRequestListener")
public class ProductSyncRequestListener implements MessageListener {

	protected static final Logger logger = LoggerFactory.getLogger("PRODUCT_SYNC_LOGGER");
	
	@Autowired
	private AsyncRouteService asyncRouteService;
	
	@Override
	public void onMessage(Message message) {
		TextMessage textMessage = (TextMessage) message;		
		try {
			String jsonStr = textMessage.getText();
			System.out.println("[ProductSyncRequestListener.onMessage]:receive message is,"+ jsonStr);
			
			if (jsonStr != null) {				
				ProductSyncRequest productSyncRequest = JSON.parseObject(jsonStr, ProductSyncRequest.class);
				
				asyncRouteService.call(RequestCode.PRODUCT_SYNC, productSyncRequest);
			}
		} catch (JMSException e) {
			logger.error("[ProductSyncRequestListener.onMessage]:receive message occured an exception",e);
		}
	}
}
