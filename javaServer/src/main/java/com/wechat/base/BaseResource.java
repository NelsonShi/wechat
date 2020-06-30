package com.wechat.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class BaseResource {
	
	private static final Logger logger = LoggerFactory.getLogger(BaseResource.class);

	@ExceptionHandler()
	@ResponseStatus(reason = "系统错误", value = HttpStatus.INTERNAL_SERVER_ERROR)
	public String exceptionHandler(Exception e) {
		logger.error("系统错误", e);
		return e.getMessage();
	}
	
}
