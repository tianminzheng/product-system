package com.tianyalan.product.open.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/api")
public class ApiController {

	@RequestMapping(value = "/api", method = RequestMethod.GET)
	public String api(Locale locale, Model model) {

		return "api/api";
	}
}
