package com.spring.lts.exception;

import java.io.IOException;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.spring.lts.constants.ERR_CONSTANTS;

public class PrivilegeException implements AccessDeniedHandler {

	final private UUID error_UUID = UUID.randomUUID();
	private static final Logger logger = LoggerFactory.getLogger(PrivilegeException.class);

	/* Keeping this for future modification on returning ApiResultError
	 * instead of the void handle()
	
	@ExceptionHandler(value = Exception.class)
	public ApiResultError exception(Exception exception) {
		logger.error(ERR_CONSTANTS.UNHANDLED_EXCEPTION + " Stacktrace cause: {}. Transaction ID: {}.", exception.getMessage(), error_UUID);
		return ApiResultError.createResponse(ERR_CONSTANTS.UNHANDLED_EXCEPTION, "CUSTOM_ERROR_STATUS", error_UUID.toString());
	}
	*
	*/

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		// TODO Try to find a way to return ApiResultError.
		
        writeCustomResponse(response);
	}
	
	private void writeCustomResponse(HttpServletResponse response) {
        if (!response.isCommitted()) {
            try {
        		logger.error(ERR_CONSTANTS.NOT_ENOUGH_AUTHORITY + " Stacktrace cause: {} Transaction ID: {}.", ERR_CONSTANTS.NO_STACKTRACE, error_UUID);

                response.setStatus(HttpStatus.FORBIDDEN.value());
                response.setContentType("application/json");
                response.getWriter().write(
            		"{ \"status\": " + HttpStatus.FORBIDDEN.value() 
	                + ", \"message\": \"" + ERR_CONSTANTS.NOT_ENOUGH_AUTHORITY + "\", "
	                + "\"transactionId\": \"" + error_UUID.toString() + "\"}"
                );
            } catch (IOException e) {
        		logger.error(ERR_CONSTANTS.RUNTIME_EXCEPTION + " Stacktrace cause: {} Transaction ID: {}.", ERR_CONSTANTS.NO_STACKTRACE, error_UUID);
                throw new RuntimeException(e);
            }
        }
    }
}
