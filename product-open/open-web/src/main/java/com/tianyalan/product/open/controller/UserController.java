package com.tianyalan.product.open.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tianyalan.product.core.domain.User;
import com.tianyalan.product.core.service.UserService;
import com.tianyalan.product.foundation.util.BaseController;
import com.tianyalan.product.foundation.util.ResultMessageBuilder;

@Controller
@RequestMapping(value = "/open/user")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;
		
	@RequestMapping(value = "/{userName}", method = RequestMethod.POST)
	public void getUserByUserName(@PathVariable String userName, HttpServletResponse response){
		User user = null;
		try{
			user = userService.getUserByUserName(userName);
		} catch(Exception e) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, e), response);
			return;
		}
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, "success!", user), response);
	}
}
