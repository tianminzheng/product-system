package com.tianyalan.product.vendor.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.tianyalan.product.foundation.util.BaseController;
import com.tianyalan.product.foundation.util.ResultMessageBuilder;
import com.tianyalan.product.integration.request.ProductSyncRequest;
import com.tianyalan.product.integration.response.ProductItem;
import com.tianyalan.product.integration.response.ResponseBase;

@Controller
@RequestMapping(value = "/product")
public class MockProductController extends BaseController {

	@RequestMapping(value = "", method = RequestMethod.POST)
	public void productSync(@RequestBody ProductSyncRequest request, HttpServletResponse httpResponse) {
		ResponseBase response = new ResponseBase();
		response.setErrorCode("");
		response.setErrorMsg("success！");
		response.setRequestNO(request.getRequestNO());
		
		List<ProductItem> productItems = new ArrayList<ProductItem>();
		ProductItem product = new ProductItem();
		product.setProductCode("vendor2_code");
		product.setProductName("vendor2_name");
		product.setDescription("vendor2_description");
		product.setPrice(20.0F);
		product.setVendorId(request.getVendorId());
		productItems.add(product);
		
		response.setJsonResponse(JSON.toJSONString(productItems));
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, "success!", JSON.toJSONString(productItems)), httpResponse);
	}
	
}
