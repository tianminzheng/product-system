package com.tianyalan.product.open.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianyalan.product.foundation.enums.ValidFlag;
import com.tianyalan.product.foundation.util.BaseController;
import com.tianyalan.product.foundation.util.ResultMessageBuilder;
import com.tianyalan.product.open.entity.ThirdSupport;
import com.tianyalan.product.open.service.local.ThirdSupportService;
import com.tianyalan.product.open.util.AESCodeUtil;
import com.tianyalan.product.spring.adaptor.BeanFactory;

public class AuthenticationFilter extends BaseController implements Filter {

	protected final Logger info_logger = LoggerFactory.getLogger(this.getClass());

	protected final Logger error_logger = LoggerFactory.getLogger("ERROR_LOGGER");

	ThirdSupportService thirdSupportService = (ThirdSupportService) BeanFactory.getInstance().getBean(
			"thirdSupportService");

	public void init(FilterConfig arg0) throws ServletException {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest sReq, ServletResponse sRes, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest request = (HttpServletRequest) sReq;
		HttpServletResponse response = (HttpServletResponse) sRes;

		ThirdSupport thirdSupport = null;
		long thirdSupportId = 0;

		// 接入第三放编号
		String openId = request.getParameter("openId");
		// 接入第三方token
		String accessToken = request.getParameter("access_token");
		// 请求URL
		String uri = request.getRequestURI();

		info_logger.info("[AuthenticationFilter.doFilter]:begin valid access info,openId:" + openId + ",access_token:"
				+ accessToken + ",uri:" + uri);

		// 验证接入编号是否输入
		if ("".equals(openId) || null == openId) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, "openId is invalid"), response);

			return;
		} else {
			thirdSupportId = Long.parseLong(openId);
		}
		// 验证接入编号是否为0
		if (0 == thirdSupportId) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, "thirdSupportId is invalid"), response);

			return;
		} else {
			thirdSupport = thirdSupportService.getThirdSupportById(thirdSupportId);
		}
		// 验证接入token是否输入
		if ("".equals(accessToken) || null == accessToken) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, "access_token is null"), response);

			return;
		}
		// 验证接入IP信息
//		if (!validAccessIp(thirdSupport, IpUtil.getIpAddr(request))) {
//			writeAjaxJSONResponse(ResultMessageBuilder.build(false, "Ip is invalid"), response);
//
//			return;
//		}
		// 校验是否和该第三方有合作以及合作是否正常
		if (!validPartner(thirdSupport)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, "ThirdSupport is invalid"), response);

			return;
		}
		// 校验接入第三放传入的口令信息是否正确
		if (!validToken(thirdSupport.getAccessKey(), accessToken, thirdSupport.getAccessSecretKey())) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, "access_token is invalid"), response);

			return;
		}
		info_logger.info("[AuthenticationFilter.doFilter]:end valid access info");
		
		chain.doFilter(request, response);
	}

	private boolean validAccessIp(ThirdSupport thirdSupport, String targetAccessIp) {
		// 默认接入IP有效合法
		boolean flag = true;
		if (null == thirdSupport || "".equals(thirdSupport.getAccessIp()) || null == thirdSupport.getAccessIp()) {
			flag = false;
			error_logger.error("[AuthenticationFilter.validAccessIp]:valid access ip failed,access info or access ip is null.");
		} else {
			if (!thirdSupport.getAccessIp().contains(targetAccessIp)) {
				flag = false;
				error_logger.error("[AuthenticationFilter.validAccessIp]:valid access ip failed,access ip:"
						+ targetAccessIp + " does not exist.");
			}
		}

		return flag;
	}
	
	private boolean validPartner(ThirdSupport thirdSupport) {
		// 默认合作正常
		boolean flag = true;
		if (null == thirdSupport) {
			flag = false;
			error_logger
					.error("[AuthenticationFilter.validPartner]:valid partner failed:third support info does not exist.");
		} else if (ValidFlag.DISABLE.equals(thirdSupport.getValidFlag())) {
			flag = false;
			error_logger
					.error("[AuthenticationFilter.validPartner]:valid partner failed:third support info is disabled.");
		}

		return flag;
	}

	private boolean validToken(String key, String accessToken, String srcSource) {
		// 默认token有效
		boolean flag = true;
		String targetSource = "";
		try {
			targetSource = AESCodeUtil.Decrypt(accessToken.trim(), key.trim());
		} catch (Exception e) {
			flag = false;
			error_logger.error("[AuthenticationFilter.validToken]:valid access token failed,key:" + key
					+ ",access_token:" + accessToken + ",srcSource:" + srcSource, e);
			e.printStackTrace();
		}
		if (!srcSource.trim().equals(targetSource.trim())) {
			flag = false;
			error_logger.error("[AuthenticationFilter.validToken]:valid access token failed,key:" + key
					+ ",access_token:" + accessToken + ",srcSource:" + srcSource + ",targetSource:" + targetSource);
		}

		return flag;
	}
}