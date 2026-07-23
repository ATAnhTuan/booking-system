package com.bookingSystem.exception;

public class ApiResponse <T>{
    private String code;
    private int status;
    private String message;
    private T data;

    public ApiResponse(String code, int status, String message, T data) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(String code, int status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>("SUCCESS", 200, message, data);
    }

    public static <T> ApiResponse<T> success( String message) {
        return new ApiResponse<>("SUCCESS", 200, message);
    }

    public static <T> ApiResponse<T> created(T data, String message) {
        return new ApiResponse<>("SUCCESS", 201, message, data);
    }

    // Helper for Errors
    public static <T> ApiResponse<T> error(int code, String message) {
        return new ApiResponse<>("ERROR", code, message, null);
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
