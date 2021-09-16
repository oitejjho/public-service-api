package com.demo.publicserviceapi.config;

import com.demo.publicserviceapi.exceptions.InvalidRequestException;
import com.demo.publicserviceapi.model.Response;
import com.demo.publicserviceapi.model.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.NoSuchElementException;

import static com.demo.publicserviceapi.constants.StatusConstants.HttpConstants.EXTERNAL_SERVICE_ERROR;
import static com.demo.publicserviceapi.constants.StatusConstants.HttpConstants.NOT_FOUND;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@ResponseBody
public class ControllerErrorHandlingConfig extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LogManager.getLogger(ControllerErrorHandlingConfig.class);

    @ExceptionHandler(InvalidRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleInvalidRequestException(InvalidRequestException ex) {
        return new Response<>(new Status(ex.getStatus()), null);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response handleNoSuchElementException(NoSuchElementException ex) {
        return new Response<>(new Status(NOT_FOUND), null);
    }

    @ExceptionHandler(RestClientException.class)
    public Response handleRestClientException(RestClientException ex, HttpServletResponse response) {
        if (ex instanceof HttpClientErrorException httpClientErrorException) {
            LOG.error("rest client exception happened status code : {}, response :{}", httpClientErrorException.getStatusCode(), httpClientErrorException.getResponseBodyAsString());
            HttpStatus httpStatus = httpClientErrorException.getStatusCode();
            response.setStatus(httpStatus.value());
        }
        else if(ex instanceof HttpServerErrorException httpServerErrorException) {
            LOG.error("rest server exception happened status code : {}, response :{}", httpServerErrorException.getStatusCode(), httpServerErrorException.getResponseBodyAsString());
            HttpStatus httpStatus = httpServerErrorException.getStatusCode();
            response.setStatus(httpStatus.value());
        }
        return new Response<>(new Status(EXTERNAL_SERVICE_ERROR), null);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }
        return new ResponseEntity<>(body, headers, status);
    }


}
