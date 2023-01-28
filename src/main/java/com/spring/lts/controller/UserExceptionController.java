package com.spring.lts.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.spring.lts.common.ApiResultError;
import com.spring.lts.constants.ERR_CONSTANTS;
import com.spring.lts.exception.UserAccountDisabledException;
import com.spring.lts.exception.UserAccountExpiredException;
import com.spring.lts.exception.UserAccountLockedException;
import com.spring.lts.exception.UserInvalidPasswordException;
import com.spring.lts.exception.UserInvalidUsernameException;
import com.spring.lts.exception.UserPasswordExpiredException;

@RestControllerAdvice
public class UserExceptionController {

	private static final Logger logger = LoggerFactory.getLogger(UserExceptionController.class);

	@ExceptionHandler(value = UserAccountDisabledException.class)
	public ApiResultError exception(UserAccountDisabledException exception) {
		logger.error(ERR_CONSTANTS.ACCOUNT_DISABLED + ": {}. Stacktrace cause: {}. Transaction ID: {}.", exception.getOptionalData(), exception.getErrorData().getMessage(), exception.getTransactionId());
		return ApiResultError.createResponse(ERR_CONSTANTS.ACCOUNT_DISABLED, "CUSTOM_ERROR_STATUS", exception.getTransactionId().toString());
	}
	
	@ExceptionHandler(value = UserAccountLockedException.class)
	public ApiResultError exception(UserAccountLockedException exception) {
		logger.error(ERR_CONSTANTS.ACCOUNT_LOCKED + ": {}. Stacktrace cause: {}. Transaction ID: {}.", exception.getOptionalData(), exception.getErrorData().getMessage(), exception.getTransactionId());
		return ApiResultError.createResponse(ERR_CONSTANTS.ACCOUNT_LOCKED, "CUSTOM_ERROR_STATUS", exception.getTransactionId().toString());
	}
	
	@ExceptionHandler(value = UserAccountExpiredException.class)
	public ApiResultError exception(UserAccountExpiredException exception) {
		logger.error(ERR_CONSTANTS.ACCOUNT_EXPIRED + ": {}. Stacktrace cause: {}. Transaction ID: {}.", exception.getOptionalData(), exception.getErrorData().getMessage(), exception.getTransactionId());
		return ApiResultError.createResponse(ERR_CONSTANTS.ACCOUNT_EXPIRED, "CUSTOM_ERROR_STATUS", exception.getTransactionId().toString());
	}
	
	@ExceptionHandler(value = UserPasswordExpiredException.class)
	public ApiResultError exception(UserPasswordExpiredException exception) {
		logger.error(ERR_CONSTANTS.ACCOUNT_PASSWORD_EXPIRED + ": {}. Stacktrace cause: {}. Transaction ID: {}.", exception.getOptionalData(), exception.getErrorData().getMessage(), exception.getTransactionId());
		return ApiResultError.createResponse(ERR_CONSTANTS.ACCOUNT_PASSWORD_EXPIRED, "CUSTOM_ERROR_STATUS", exception.getTransactionId().toString());
	}
	
	@ExceptionHandler(value = UserInvalidUsernameException.class)
	public ApiResultError exception(UserInvalidUsernameException exception) {
		logger.error(ERR_CONSTANTS.USERNAME_INVALID + ": {}. Stacktrace cause: {}. Transaction ID: {}.", exception.getOptionalData(), exception.getErrorData().getMessage(), exception.getTransactionId());
		return ApiResultError.createResponse(ERR_CONSTANTS.USERNAME_INVALID, "CUSTOM_ERROR_STATUS", exception.getTransactionId().toString());
	}
	
	@ExceptionHandler(value = UserInvalidPasswordException.class)
	public ApiResultError exception(UserInvalidPasswordException exception) {
		logger.error(ERR_CONSTANTS.PASSWORD_INVALID + ": {}. Stacktrace cause: {}. Transaction ID: {}.", exception.getOptionalData(), exception.getErrorData().getMessage(), exception.getTransactionId());
		return ApiResultError.createResponse(ERR_CONSTANTS.PASSWORD_INVALID, "CUSTOM_ERROR_STATUS", exception.getTransactionId().toString());
	}
}
