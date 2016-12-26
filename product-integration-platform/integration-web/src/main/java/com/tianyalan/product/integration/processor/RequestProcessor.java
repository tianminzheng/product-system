package com.tianyalan.product.integration.processor;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianyalan.product.foundation.util.ResultMessageBuilder.ResultMessage;
import com.tianyalan.product.integration.request.RequestBase;
import com.tianyalan.product.integration.response.ResponseBase;
import com.tianyalan.product.integration.util.UniqueSequenceGenerator;

public abstract class RequestProcessor {

	public static final Logger logger = LoggerFactory.getLogger("PROCESSOR_LOGGER");	
	
	public ResponseBase process(RequestBase pRequestBase) {
		ResponseBase response = null;
		
		String requestNO = pRequestBase.getRequestNO();
		if(StringUtils.isBlank(requestNO)){
			requestNO = UniqueSequenceGenerator.getInstance().getUniqueSequence();
			logger.debug("generated request no:{}",requestNO);
		}
		
		response = invokeRESTfulService(pRequestBase);
		
		response.setRequestNO(requestNO);
		
		//转换对象
		response = transformResponse(pRequestBase, response);
		return response;
	}
	
	private ResponseBase invokeRESTfulService(RequestBase pRequestBase) {
		// 使用RestTemplate调用远程RESTful服务
		
		StringBuilder sb = new StringBuilder();
		sb.append("http://");
		sb.append(pRequestBase.getVendorSystemAddress());
		sb.append("/vendor-web/");
		
		//设置目标地址子路径
		sb.append(getTargetRequestPath());
		
		String url = sb.toString();
		ResultMessage result = RestTemplateUtil.postForObject(url, pRequestBase);
		
		ResponseBase response = new ResponseBase();
		response.setErrorCode("");
		response.setErrorMsg(result.getErrMsg());
		response.setRequestNO(pRequestBase.getRequestNO());
		response.setJsonResponse(result.getData().toString());
		
		return response;
	}
	
	public abstract String getTargetRequestPath();
	
	public abstract ResponseBase transformResponse(RequestBase pRequest, ResponseBase pResponse);
	
}
