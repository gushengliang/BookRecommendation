package org.bookrec.utils;

import org.bookrec.common.enums.IErrorCode;
import org.bookrec.common.enums.impl.ErrorCode;
import org.bookrec.entity.vo.ResultVo;

/**
 * ResultVoUtil
 *
 * @author a1311
 */
public class ResultVoUtil {

    /**
     * 成功的返回对象
     *
     * @param data data
     * @return resultVo
     */
    public static ResultVo<?> success(Object data) {
        return new ResultVo<>(ErrorCode.SUCCESS, data);
    }

    /**
     * 失败的返回对象
     *
     * @param errorCode errorCode
     * @return resultVo
     */
    public static ResultVo<?> fail(IErrorCode errorCode) {
        return new ResultVo<>().setCodeMessage(errorCode);
    }


    /**
     * 描述: 通过errorCode和数据对象参数，构建一个新的对象
     *
     * @param errorCode errorCode
     * @param data      data
     * @return resultVo
     */
    public static ResultVo<?> result(IErrorCode errorCode, Object data) {
        return new ResultVo<>(errorCode, data);
    }
}
