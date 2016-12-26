
package com.tianyalan.product.spring.adaptor;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

public class SpringContextLoaderListener extends ContextLoaderListener {

	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		
		ServletContextHolder.setContext(event.getServletContext());
	}
}
