package com.tianyalan.product.open.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tianyalan.product.foundation.util.BaseController;
import com.tianyalan.product.foundation.util.ResultMessageBuilder;
import com.tianyalan.product.open.entity.ThirdSupport;
import com.tianyalan.product.open.service.local.ThirdSupportService;
import com.tianyalan.product.open.util.AESCodeUtil;

@Controller
@RequestMapping(value = "/access")
public class AccessController extends BaseController {

	@Autowired
	private ThirdSupportService thirdSupportService;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public void accessPlatform(@RequestBody ThirdSupport param, HttpServletResponse response) {
		if (null != validAccess(param)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, validAccess(param)), response);

			return;
		}
		// 生成16位长度的加密key
		String accessKey = AESCodeUtil.getRandomKey(16);
		// 生成接入token
		String accessToken = "";
		try {
			accessToken = AESCodeUtil.Encrypt(param.getAccessSecretKey().trim(), accessKey);
		} catch (Exception e) {
			e.printStackTrace();
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, "接入失败，请检查密钥是否正确"), response);
			return;
		}

		ThirdSupport thirdSupport = new ThirdSupport();
		thirdSupport.setOrganization(param.getOrganization());
		thirdSupport.setAccessName(param.getAccessName());
		thirdSupport.setAccessKey(accessKey);
		thirdSupport.setAccessSecretKey(param.getAccessSecretKey().trim());
		thirdSupport.setAccessIp(param.getAccessIp());

		thirdSupportService.addThirdSupport(thirdSupport);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, "success!", thirdSupport), response);
	}
	
	private String validAccess(ThirdSupport param) {
		if (null == param.getAccessName() || "".equals(param.getAccessName())) {
			return "请输入接入方名称";
		}
		if (null == param.getAccessSecretKey() || "".equals(param.getAccessSecretKey())) {
			return "请输入接入秘钥";
		}
		if (null == param.getAccessIp() || "".equals(param.getAccessIp())) {
			return "请输入接入方可信任IP";
		}

		return null;
	}
}