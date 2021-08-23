package org.bookrec.service;

import org.bookrec.entity.Student;
import org.bookrec.entity.vo.ResultVo;

/**
 * StudentService
 *
 * @author a1311
 */
public interface StudentService {
    /**
     * 获取全部学生
     *
     * @return resultVo
     * @throws Exception 异常
     */
    ResultVo<?> getAllStudent() throws Exception;

    /**
     * 条件查询学生
     *
     * @param username 用户名
     * @return resultVo
     * @throws Exception 异常
     */
    ResultVo<?> selectStudentByUsername(String username) throws Exception;


    /**
     * 修改学生信息
     * @param student 学生
     * @return resultVo
     * @throws Exception 异常
     */
    ResultVo<?> updateStudent(Student student) throws Exception;

    /**
     * 增加学生信息
     *
     * @param student
     * @returnResultVo
     * @throws Exception
     */
    ResultVo<?> addStudent (Student student) throws Exception;
}
