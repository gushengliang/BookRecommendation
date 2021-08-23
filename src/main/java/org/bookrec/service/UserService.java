package org.bookrec.service;

import org.bookrec.entity.User;
import org.bookrec.entity.vo.ResultVo;

import java.util.List;

/**
 * UserService
 *
 * @author a1311
 */
public interface UserService {
    /**
     * 登录
     *
     * @param user 用户
     * @return resultVo
     * @throws Exception 异常
     */
    ResultVo<?> signIn(User user) throws Exception;

    /**
     * 注册
     *
     * @return resultVo
     */
    ResultVo<?> signUp();

    /**
     * 退出
     *
     * @return resultVo
     */
    ResultVo<?> quit();

    /**
     * 注销用户
     *
     * @return resultVo
     */
    ResultVo<?> logOff();

    /**
     * 条件查询
     *
     * @param user 用户
     * @return resultVo
     * @throws Exception 异常
     */
    ResultVo<?> conditionSelect(User user) throws Exception;

    /**
     * 获取登录用户的信息
     *
     * @param user 用户
     * @return 用户信息
     * @throws Exception 异常
     */
    ResultVo<?> getUserInfo(User user) throws Exception;
}
