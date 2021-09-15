package com.demo.publicserviceapi.controller;


import com.demo.publicserviceapi.model.Response;
import com.demo.publicserviceapi.model.Status;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;

import static com.demo.publicserviceapi.constants.StatusConstants.HttpConstants;

public interface BaseController {

    default <T> Response<T> success() {
        return new Response<>(new Status(HttpConstants.SUCCESS), null);
    }

    default <T> Response<T> success(T data) {
        return new Response<>(new Status(HttpConstants.SUCCESS), data);
    }

    default <T> Response<T> created(HttpServletResponse response) {
        response.setStatus(HttpStatus.CREATED.value());
        return new Response<>(new Status(HttpConstants.SUCCESS), null);
    }

    default <T> Response<T> created(T data, HttpServletResponse response) {
        response.setStatus(HttpStatus.CREATED.value());
        return new Response<>(new Status(HttpConstants.SUCCESS), data);
    }


    default <T> Response<T> badRequest(HttpConstants httpConstants) {
        return new Response<T>(new Status(httpConstants), null);
    }

    default <T> Response<T> badRequest(HttpConstants httpConstants, HttpServletResponse response) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return new Response<T>(new Status(httpConstants), null);
    }

    default <T> Response<T> badRequest(HttpConstants httpConstants, String message, HttpServletResponse response) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return new Response<T>(new Status(httpConstants.getCode(), message), null);
    }

    default <T> Response<T> serverError(HttpConstants httpConstants, HttpServletResponse response) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new Response<T>(new Status(httpConstants), null);
    }

    default <T> Response<T> serverError(HttpConstants httpConstants) {
        return new Response<T>(new Status(httpConstants), null);
    }

    default <T> Response<T> notFound(HttpConstants httpConstants, HttpServletResponse response) {
        response.setStatus(HttpStatus.NOT_FOUND.value());
        return new Response<T>(new Status(httpConstants), null);
    }

}
