package com.ferrlo.weixin.mvc;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.unitils.spring.annotation.SpringBeanByType;

import com.ferrlo.test.FerrloTest;

public class WeixinControllerTest extends FerrloTest {
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	@SpringBeanByType
	private WeixinController wc;

	@Before
	public void setup() {
		request = new MockHttpServletRequest();
		request.setCharacterEncoding("UTF-8");
		response = new MockHttpServletResponse();
		request.addParameter("signature", "e547f21bb25e5d0c07ff8cdd0450b3b4c252053");
		request.addParameter("timestamp", "327493127490");
		request.addParameter("token", "hskjwechat");
		request.addParameter("nonce", "324132ewrwe");
		request.addParameter("echostr", "e547f21bb25e5d0c07ff8cdd0450b3b4c252053");
	}
	@Test
	public void wcTest(){
		String str=wc.initWechatURL(request);
		String signature="e547f21bb25e5d0c07ff8cdd0450b3b4c252053";
		//assertNull(str);
		assertEquals(signature, str);
	}
	

}
