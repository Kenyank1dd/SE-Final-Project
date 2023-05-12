package com.aurora.exception;


import com.aurora.enums.StatusCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 自定义业务异常类
 */
@Getter
@AllArgsConstructor
public class BizException extends RuntimeException {

    // 异常状态码，默认为失败状态码
    private Integer code = StatusCodeEnum.FAIL.getCode();

    // 异常信息
    private final String message;

    /**
     构造方法，通过传入异常信息创建业务异常对象
     @param message 异常信息
     */
    public BizException(String message) {
        this.message = message;
    }
    /**
     构造方法，通过传入异常状态码枚举对象创建业务异常对象
     @param statusCodeEnum 异常状态码枚举对象
     */
    public BizException(StatusCodeEnum statusCodeEnum) {
        this.code = statusCodeEnum.getCode();
        this.message = statusCodeEnum.getDesc();
    }
}
