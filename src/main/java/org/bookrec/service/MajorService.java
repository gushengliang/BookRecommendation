package org.bookrec.service;

import org.bookrec.entity.Major;
import org.bookrec.entity.School;
import org.bookrec.entity.vo.ResultVo;

import java.util.List;

/**
 * MajorService
 *
 * @author a1311
 */
public interface MajorService {
    /**
     * 获取全部专业
     *
     * @return resultVo
     * @throws Exception 异常
     */
    ResultVo<?> getAllMajor() throws Exception;


    /**
     * 根据学校Id寻找专业
     *
     * @param SchoolId
     * @return 专业对象列表
     */
    List<Major> selectBySchoolId(Long SchoolId) throws Exception;

    /**
     * 修改专业信息
     *
     * @param major
     * @return resultVo
     * @throws Exception
     */
    ResultVo<?> updataMajor(Major major) throws Exception;

    /**
     * 增加专业信息
     *
     * @param major
     * @throws Exception
     * @returnResultVo
     */
    ResultVo<?> addMajor(Major major) throws Exception;

    /**
     * 添加课程
     *
     * @param courseid
     * @param majorid
     * @return
     * @throws Exception
     */
    ResultVo<?> addcourse(String majorid, String courseid) throws Exception;

    /**
     * 删除课程
     *
     * @param courseid
     * @param majorid
     * @return
     * @throws Exception
     */
    ResultVo<?> deletecourse(String majorid, String courseid) throws Exception;

    /**
     * 获取专业列表
     *
     * @return 专业列表
     * @throws Exception 异常
     */
    ResultVo<?> getMajors() throws Exception;
}
