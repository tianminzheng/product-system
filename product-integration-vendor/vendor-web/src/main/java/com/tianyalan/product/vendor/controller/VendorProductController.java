package com.tianyalan.product.vendor.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.tianyalan.product.foundation.exception.ServiceException;
import com.tianyalan.product.foundation.util.BaseController;
import com.tianyalan.product.foundation.util.ResultMessageBuilder;
import com.tianyalan.product.integration.request.ProductSyncRequest;
import com.tianyalan.product.integration.response.ProductItem;
import com.tianyalan.product.integration.response.ResponseBase;
import com.tianyalan.product.vendor.domain.VendorProduct;
import com.tianyalan.product.vendor.localservice.VendorProductLocalService;

@Controller
@RequestMapping(value = "/product")
public class VendorProductController extends BaseController {

	@Autowired
	private VendorProductLocalService vendorProductLocalService;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public void productSync(@RequestBody ProductSyncRequest request,
			HttpServletResponse httpResponse) {

		List<VendorProduct> vendorProducts = null;
		try {
			vendorProducts = vendorProductLocalService.getVendorProductByTimeRange(
					request.getStartTime(), request.getEndTime());
		} catch (ServiceException e) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, e.getMessage()), httpResponse);
			return;
		}

		List<ProductItem> productItems = convertProductItems(vendorProducts);

		ResponseBase response = new ResponseBase();
		response.setErrorCode("");
		response.setErrorMsg("success！");
		response.setRequestNO(request.getRequestNO());
		response.setJsonResponse(JSON.toJSONString(productItems));

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, "success!",JSON.toJSONString(productItems)), httpResponse);
	}

	private List<ProductItem> convertProductItems(List<VendorProduct> vendorProducts) {
		if(vendorProducts == null || vendorProducts.size() == 0)
			return null;
		
		List<ProductItem> productItems = new ArrayList<ProductItem>();
		for(VendorProduct vendorProduct : vendorProducts) {
			ProductItem productItem = new ProductItem();
			productItem.setProductCode(vendorProduct.getCode());
			productItem.setProductName(vendorProduct.getName());;
			productItem.setDescription(vendorProduct.getDescription());
			productItem.setPrice(vendorProduct.getPrice());
			
			productItems.add(productItem);
		}
		
		return productItems;
	}

}
