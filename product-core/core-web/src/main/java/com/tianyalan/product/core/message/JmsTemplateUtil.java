package com.tianyalan.product.core.message;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.alibaba.fastjson.JSON;
import com.tianyalan.product.integration.request.ProductSyncRequest;

public class JmsTemplateUtil {
	private JmsTemplate internalJmsTemplate;
    private Destination destination;

    public void sendProductSyncRequestMessage(ProductSyncRequest request) {
    	final ProductSyncRequest productSyncRequest = request;
        internalJmsTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                Message message = session.createTextMessage(JSON.toJSONString(productSyncRequest));
                return message;
            }
        });
    }

    public void setInternalJmsTemplate(JmsTemplate internalJmsTemplate) {
        this.internalJmsTemplate = internalJmsTemplate;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }
}