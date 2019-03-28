package com.hawk.web.ams.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.hawk.utils.exception.BusinessException;
import com.hawk.utils.response.api.ReturnData;

@ControllerAdvice
public class ControllerExceptionHandler {

	// 这个注解是指当controller中抛出这个指定的异常类的时候，都会转到这个方法中来处理异常
	@ExceptionHandler(BusinessException.class)
	@ResponseBody
	// 返回的状态码
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR) // 服务内部错误
	public ReturnData handlerUserNotExistException(BusinessException ex) {
		return ReturnData.error(ex.getCode(), ex.getMessage());
	}
}