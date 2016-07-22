package com.np.user.service.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.np.crm.common.logger.XMsgBaseSimlpe;
import com.np.crm.common.logger.Xlogger;

/**
 * ����Controller
 * @author zhiya.chai
 * 2016��7��22�� ����5:01:04
 */
@Controller
public class BaseController {

	private ThreadLocal<HttpServletRequest> currentRequest = new ThreadLocal<HttpServletRequest>();
	private ThreadLocal<HttpServletResponse> currentResponse = new ThreadLocal<HttpServletResponse>();
	
	
	@ModelAttribute
	public void setHttpServletRequestAndResponse(HttpServletRequest request,HttpServletResponse response){
		currentRequest.set(request);
		currentResponse.set(response);
	}
	
	/**
	 * ��ȡrequest
	 * @return
	 * @return HttpServletRequest
	 * @author zhiya.chai
	 * 2016��7��6�� ����2:41:53
	 */
	public HttpServletRequest request() {
	    return currentRequest.get();
	}
	/**
	 * ��ȡresponse
	 * @return
	 * @return HttpServletResponse
	 * @author zhiya.chai
	 * 2016��7��6�� ����2:42:10
	 */
	public HttpServletResponse response(){
		return currentResponse.get();
	}
	/**
	 * ������־
	 * @param excuteClass
	 * @param excuteMethod
	 * @param sequence
	 * @return void
	 * @author zhiya.chai
	 * 2016��5��16�� ����3:43:19
	 */
	public void traceLog(String excuteClass,String excuteMethod,String sequence){
		StringBuffer log = new StringBuffer();
		log.append("ǰ�˵��ã�")
			.append("Ψһ�ţ�").append(sequence)
			.append("��ǰʱ�䣺" + new Date());
		Xlogger.log(XMsgBaseSimlpe.build("biz", excuteClass, excuteMethod, log.toString()));
	}
}
