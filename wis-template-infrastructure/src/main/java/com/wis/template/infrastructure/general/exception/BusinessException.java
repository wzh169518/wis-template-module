package com.wis.template.infrastructure.general.exception;

/**
 * 自定义顶级异常 BusinessException
 *
 * @author wzengheng
 * @date @since 2021/05/10
 */
public class BusinessException extends Exception {
    private int code;

    private Object data;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(int code, String message, Object data) {
        super(message);
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
