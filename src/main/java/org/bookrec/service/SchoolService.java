package org.bookrec.service;

import org.bookrec.entity.School;
import org.bookrec.entity.vo.ResultVo;

/**
 * SchoolService
 *
 * @author a1311
 */
public interface SchoolService {
    /**
     * 获取全部学校
     *
     * @return resultVo
     * @throws Exception 异常
     */
    ResultVo<?> getAllSchool() throws Exception;

    /**
     * 修改学校信息
     *
     * @param school
     * @return resultVo
     * @throws Exception
     */
    ResultVo<?> updataSchool (School school) throws Exception;

    /**
     * 增加学校信息
     *
     * @param school
     * @returnResultVo
     * @throws Exception
     */
    ResultVo<?> addSchool (School school) throws Exception;

    /**
     * 添加专业
     * @param schoolid
     * @param majorid
     * @return
     * @throws Exception
     */
    ResultVo<?> addmajor (String schoolid,String majorid) throws Exception;

    /**
     * 删除专业
     * @param schoolid
     * @param majorid
     * @return
     * @throws Exception
     */
    ResultVo<?> deletemajor (String schoolid,String majorid) throws Exception;
}
