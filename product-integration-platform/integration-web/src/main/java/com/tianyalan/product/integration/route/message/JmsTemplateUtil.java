package com.tianyalan.product.integration.route.message;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.alibaba.fastjson.JSON;
import com.tianyalan.product.integration.response.ProductSyncResponse;

public class JmsTemplateUtil {
	private JmsTemplate internalJmsTemplate;
    private Destination destination;

    public void sendProductSyncResponseMessage(ProductSyncResponse response) {
    	final ProductSyncResponse productSyncResponse = response;
        internalJmsTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                Message message = session.createTextMessage(JSON.toJSONString(productSyncResponse));
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