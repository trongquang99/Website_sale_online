package com.website.online.sale.base;

import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class ResponseBuilder {

    public static final String SUCCESS_MESSAGE = "Thành công!";

    public static <T> BaseResponse<T> error(String message) {
        return error(500, message);
    }

    public static <T> BaseResponse<T> error(int code, String message) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setCode(code);
        response.setSuccess(false);
        response.setMessage(message);
        return response;
    }


    public static <T> BaseResponse<T> success(T data) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setSuccess(true);
        response.setCode(200);
        response.setMessage(SUCCESS_MESSAGE);
        response.setData(data);
        return response;
    }

    public static <T> BaseListResponse<T> successList(List<T> data) {
        BaseListResponse<T> response = new BaseListResponse<>();
        response.setSuccess(true);
        response.setCode(200);
        response.setMessage(SUCCESS_MESSAGE);
        response.setData(data);
        return response;
    }

    public static <T> void successList(List<T> data, BaseListResponse<T> response) {
        response.setSuccess(true);
        response.setCode(200);
        response.setMessage(SUCCESS_MESSAGE);
        response.setData(data);
    }

    public static <U extends BaseListResponse<T>,T> U successListWithResponse(List<T> data, U response) {
        response.setSuccess(true);
        response.setCode(200);
        response.setMessage(SUCCESS_MESSAGE);
        response.setData(data);
        return response;
    }

    public static <T> BaseListResponse<T> successList(List<T> data, String before, String after) {
        BaseListResponse<T> response = new BaseListResponse<>();
        response.setSuccess(true);
        response.setCode(200);
        response.setMessage(SUCCESS_MESSAGE);
        response.setData(data);
        response.setBefore(before);
        response.setAfter(after);
        return response;
    }

}