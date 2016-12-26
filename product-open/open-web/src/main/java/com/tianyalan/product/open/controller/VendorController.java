package com.tianyalan.product.open.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tianyalan.product.core.domain.Vendor;
import com.tianyalan.product.core.domain.VendorSystem;
import com.tianyalan.product.core.service.VendorService;
import com.tianyalan.product.foundation.util.BaseController;
import com.tianyalan.product.foundation.util.ResultMessageBuilder;

@Controller
@RequestMapping(value = "/open/vendor")
public class VendorController extends BaseController {

	@Autowired
	private VendorService vendorService;
	
	@RequestMapping(value = "/{vendorName}", method = RequestMethod.POST)
	public void getVendorByVendorName(@PathVariable String vendorName, HttpServletResponse response){
		Vendor vendor;
		try{
			vendor = vendorService.getVendorByVendorName(vendorName);
		} catch(Exception e) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, e), response);
			return;
		}
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, "success!", vendor), response);
	}
	
	@RequestMapping(value = "/vendor_system/{vendorName}", method = RequestMethod.POST)
	public void getVendorSystemByVendorName(@PathVariable String vendorName, HttpServletResponse response){
		VendorSystem vendorSystem;
		try{
			vendorSystem = vendorService.getVendorSystemByVendorName(vendorName);
		} catch(Exception e) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, e), response);
			return;
		}
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, "success!", vendorSystem), response);
	}
}
