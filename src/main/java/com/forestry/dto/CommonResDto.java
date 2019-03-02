package com.forestry.dto;

public class CommonResDto {
    private Integer code;
    private String message;
    private Object data;

    private CommonResDto() {
    }

    private CommonResDto(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static CommonResDto build(Integer code, String message, Object data) {
        return new CommonResDto(code, message, data);
    }

    public static CommonResDto ok(String message, Object data) {
        return new CommonResDto(0, message, data);
    }

    public static CommonResDto ok(String message) {
        return new CommonResDto(0, message, null);
    }

    public static CommonResDto error(String message, Object data) {
        return new CommonResDto(-1, message, data);
    }

    public static CommonResDto error(String message) {
        return new CommonResDto(-1, message, null);
    }

    public Integer getCode() {
        return code;
    }

    public CommonResDto setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CommonResDto setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public CommonResDto setData(Object data) {
        this.data = data;
        return this;
    }
}
