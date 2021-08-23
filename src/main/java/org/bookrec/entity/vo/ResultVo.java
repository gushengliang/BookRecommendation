package org.bookrec.entity.vo;

import org.bookrec.common.enums.IErrorCode;

/**
 * ResultVO
 *
 * @author a1311
 */
public class ResultVo<T> {
    /**
     * 错误代码
     */
    private Integer code;
    /**
     * 消息
     */
    private String msg;
    /**
     * 返回对应数据
     */
    private T data;

    public ResultVo() {

    }

    public ResultVo(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultVo(int code, String mesage) {
        setCode(code);
        setMsg(mesage);
    }

    public ResultVo(IErrorCode errorCode, T data) {
        setCodeMessage(errorCode);
        setData(data);
    }

    public ResultVo<?> setCodeMessage(IErrorCode codeMessage) {
        setCode(codeMessage.getCode());
        setMsg(codeMessage.getMsg());
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}