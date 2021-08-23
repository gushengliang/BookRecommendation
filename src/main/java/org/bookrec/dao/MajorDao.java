package org.bookrec.dao;

import org.bookrec.entity.Course;
import org.bookrec.entity.Major;

import java.util.List;

/**
 * MajorDao
 *
 * @author a1311
 */
public interface MajorDao extends BaseDao<Major> {
    /**
     * 根据学校id查询其专业列表
     *
     * @param id 学校id
     * @return 专业id列表
     * @throws Exception 异常
     */
    List<Long> selectMajorIdListBySchoolId(Long id) throws Exception;

    /**
     * 为专业添加课程
     * @param majorId
     * @param courseId
     * @return 执行结果
     * @throws Exception
     */
    boolean insert(Long majorId, Long courseId) throws Exception;

    /**
     *  从专业中删除课程
     * @param majorId
     * @param courseId
     * @return 执行结果
     * @throws Exception
     */
    boolean delete(Long majorId, Long courseId) throws Exception;
}