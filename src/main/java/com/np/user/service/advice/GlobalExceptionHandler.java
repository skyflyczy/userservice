package com.np.user.service.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.np.crm.common.enums.ErrorCode;
import com.np.crm.common.exception.ErrorCodeException;
import com.np.crm.common.response.Response;

/**
 * ȫ���쳣����
 * @author zhiya.chai
 * 2016��7��20�� ����2:51:38
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	
private Logger logger = LoggerFactory.getLogger(getClass());

	
	/**
	 * ��������ErrorCode�쳣
	 * @param exception
	 * @return
	 * @return Response
	 * @author zhiya.chai
	 * 2016��7��20�� ����3:24:28
	 */
	@ExceptionHandler(ErrorCodeException.class)
	@ResponseBody
	public Response handleErrorCodeException(ErrorCodeException exception) {
		return Response.build()
		.setRetcode(exception.getErrorCode().getCode())
		.setMsg(exception.getErrorCode().getMessage());
	}
	
	/**
	 * ��ȥErrorCode�쳣�������쳣
	 * @param exception
	 * @return
	 * @return Response
	 * @author zhiya.chai
	 * 2016��7��20�� ����3:22:34
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Response handleException(Exception exception) {
		logger.error("Handle request fail!", exception);
		if(exception instanceof HttpRequestMethodNotSupportedException){
			return Response.build()
					.setRetcode(ErrorCode.SYSTEM_HTTPMETHOD_ERROR.getCode())
					.setMsg(ErrorCode.SYSTEM_HTTPMETHOD_ERROR.getMessage());
		}
		return Response.build()
				.setRetcode(ErrorCode.SYSTEM_ERROR.getCode())
				.setMsg(ErrorCode.SYSTEM_ERROR.getMessage());
	}

}
