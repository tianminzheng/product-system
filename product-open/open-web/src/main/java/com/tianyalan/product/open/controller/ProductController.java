package com.tianyalan.product.open.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tianyalan.product.core.domain.Product;
import com.tianyalan.product.core.service.ProductService;
import com.tianyalan.product.foundation.util.BaseController;
import com.tianyalan.product.foundation.util.ResultMessageBuilder;
import com.tianyalan.product.mybatis.page.Page;

@Controller
@RequestMapping(value = "/open/product")
public class ProductController extends BaseController {

	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/{pageNum}/{pageSize}/{wildcard}", method = RequestMethod.POST)
	public void getProductByWildcard(@PathVariable int pageNum, @PathVariable int pageSize,
			@PathVariable String wildcard, HttpServletResponse response){
		Page<Product> productPage;
		
		try{
			productPage = productService.getProductByWildcard(pageNum, pageSize, wildcard);
		} catch(Exception e) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, e), response);
			return;
		}
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, "success!", productPage), response);
	}
	
	@RequestMapping(value = "/{productCode}", method = RequestMethod.POST)
	public void getProductByCode(@PathVariable String productCode, HttpServletResponse response){
		Product product;
		try{
			product = productService.getProductByCode(productCode);
		} catch(Exception e) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, e), response);
			return;
		}
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, "success!", product), response);
	}
}
