package org.bookrec.dao;

import org.bookrec.entity.Book;
import org.bookrec.entity.Course;
import org.bookrec.entity.Student;

import java.util.List;

/**
 * StudentDao
 *
 * @author a1311
 */
public interface StudentDao extends BaseDao<Student> {

    /**
     * 根据用户名查找学生
     *
     * @param username 用户名
     * @return 学生
     * @throws Exception 异常
     */
    Student selectStudentByUsername(String username) throws Exception;
}
