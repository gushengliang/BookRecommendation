package org.bookrec.service;

import org.bookrec.entity.Course;
import org.bookrec.entity.vo.ResultVo;

import java.util.List;

/**
 * CourseService
 *
 * @author a1311
 */
public interface CourseService {
    /**
     * 添加关注
     *
     * @param studentId 学生id
     * @param courseId  书本id
     * @return true or false
     * @throws Exception 异常
     */
    ResultVo<?> addAttention(Long studentId, Long courseId) throws Exception;

    /**
     * 取消关注
     *
     * @param studentId 学生id
     * @param courseId  课程id
     * @return true or false
     * @throws Exception 异常
     */
    ResultVo<?> removeAttention(Long studentId, Long courseId) throws Exception;

    /**
     * 根据专业id查询课程列表
     *
     * @param id 专业id
     * @return 课程列表
     * @throws Exception 异常
     */
    ResultVo<?> searchCoursesByMajorId(Long id) throws Exception;

    /**
     * 根据参考书id查询课程列表
     *
     * @param id 参考书id
     * @return 课程列表
     * @throws Exception 异常
     */
    ResultVo<?> searchCoursesByBookId(Long id) throws Exception;

    /**
     * 根据学生id查询参考书列表（查询关注列表）
     *
     * @param studentId 学生id
     * @return 课程列表
     * @throws Exception 异常
     */
    ResultVo<?> viewAttention(Long studentId) throws Exception;

    /***
     * 添加课程
     *
     * @param course
     * @return 执行结果
     * @throws Exception 异常
     */
    ResultVo<?> addCourse(Course course) throws Exception;

    /**
     * 修改课程信息
     *
     * @param course
     * @return 执行结果
     * @throws Exception 异常
     */
    ResultVo<?> updateCourse(Course course) throws Exception;

    /**
     * 根据专业id搜索课程信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    List<Course> selectByMajorId(Long id) throws Exception;

    /**
     * 获取全部课程信息
     *
     * @return 课程列表
     * @throws Exception
     */
    ResultVo<?> getAllCourses() throws Exception;

    /**
     * 根据专业列表查询课程列表
     * @param majors 专业id列表
     * @return 课程列表
     * @throws Exception 异常
     */
    ResultVo<?> getCoursesByMajors(String[] majors) throws Exception;
}
