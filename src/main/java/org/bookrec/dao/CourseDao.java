package org.bookrec.dao;

import org.bookrec.entity.Book;
import org.bookrec.entity.Course;

import java.util.List;

/**
 * CourseDao
 *
 * @author a1311
 */
public interface CourseDao extends BaseDao<Course> {
    /**
     * 学生收藏课程
     *
     * @param studentId 学生id
     * @param courseId  课程id
     * @return true or false
     * @throws Exception 异常
     */
    boolean insert(Long studentId, Long courseId) throws Exception;

    /**
     * 学生取消收藏课程
     *
     * @param studentId 学生id
     * @param courseId  课程id
     * @return true or false
     * @throws Exception 异常
     */
    boolean delete(Long studentId, Long courseId) throws Exception;

    /**
     * 根据学生id查找关注的课程id
     *
     * @param id 学生id
     * @return bookList
     * @throws Exception 异常
     */
    List<Long> selectCourseIdListByStudentId(Long id) throws Exception;

    /**
     * 根据专业id查询其课程列表
     *
     * @param id 专业id
     * @return 课程id列表
     * @throws Exception 异常
     */
    List<Long> selectCourseIdListByMajorId(Long id) throws Exception;

    /**
     * 根据参考书id查询其参课程列表
     *
     * @param id 参考书id
     * @return 课程id列表
     * @throws Exception 异常
     */
    List<Long> selectCourseIdListByBookId(Long id) throws Exception;
}
