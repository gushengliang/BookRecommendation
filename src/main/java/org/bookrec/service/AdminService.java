package org.bookrec.service;

import org.bookrec.entity.vo.ResultVo;

/**
 * AdminService
 *
 * @author a1311
 */
public interface AdminService {
    /**
     * 获取管理员
     *
     * @param id
     * @return ResultVo
     * @throws Exception 异常
     */
    ResultVo<?> getAdmin(String id) throws Exception;
}
