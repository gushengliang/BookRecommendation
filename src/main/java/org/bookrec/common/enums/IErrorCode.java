package org.bookrec.common.enums;

/**
 * IErrorCode
 * @author a1311
 */
public interface IErrorCode {
    /**
     * 描述:得到错误码
     * @return Integer
     **/
    Integer getCode();

    /**
     * 得到错误信息
     * @return String
     */
    String getMsg();
}
