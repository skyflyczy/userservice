package com.np.user.service.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.np.crm.common.logger.XMsgBaseSimlpe;
import com.np.crm.common.logger.Xlogger;

/**
 * 基础Controller
 * @author zhiya.chai
 * 2016年7月22日 下午5:01:04
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
	 * 获取request
	 * @return
	 * @return HttpServletRequest
	 * @author zhiya.chai
	 * 2016年7月6日 下午2:41:53
	 */
	public HttpServletRequest request() {
	    return currentRequest.get();
	}
	/**
	 * 获取response
	 * @return
	 * @return HttpServletResponse
	 * @author zhiya.chai
	 * 2016年7月6日 下午2:42:10
	 */
	public HttpServletResponse response(){
		return currentResponse.get();
	}
	/**
	 * 跟踪日志
	 * @param excuteClass
	 * @param excuteMethod
	 * @param sequence
	 * @return void
	 * @author zhiya.chai
	 * 2016年5月16日 下午3:43:19
	 */
	public void traceLog(String excuteClass,String excuteMethod,String sequence){
		StringBuffer log = new StringBuffer();
		log.append("前端调用，")
			.append("唯一号：").append(sequence)
			.append("当前时间：" + new Date());
		Xlogger.log(XMsgBaseSimlpe.build("biz", excuteClass, excuteMethod, log.toString()));
	}
}
