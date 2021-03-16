package com.fystock.bigdata.cloud.response;

/**
 * 枚举常用Response操作码
 *
 * @author He.Yong
 * @since 2021-03-11 18:33:25
 */
public enum ResultCode implements IErrorCode {
    SUCCESS(200, "操作成功!"),

    FAILED(500, "操作失败!"),
    AUTHENTICATION_FAILED(501, "参数username或password错误!"),

    UNAUTHORIZED(401, "Token已经过期,请刷新Token或重新获取!"),
    VALIDATE_FAILED(402, "Token参数校验失败,请检查Token是否正确!"),
    FORBIDDEN(403, "无权限请求,请检测当前用户身份信息!");

    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
