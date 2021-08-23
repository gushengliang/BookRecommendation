package org.bookrec.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.bookrec.common.enums.impl.ErrorCode;
import org.bookrec.dao.MajorDao;
import org.bookrec.dao.SchoolDao;
import org.bookrec.dao.StudentDao;
import org.bookrec.dao.UserDao;
import org.bookrec.dao.impl.MajorDaoImpl;
import org.bookrec.dao.impl.SchoolDaoImpl;
import org.bookrec.dao.impl.StudentDaoImpl;
import org.bookrec.dao.impl.UserDaoImpl;
import org.bookrec.entity.Major;
import org.bookrec.entity.School;
import org.bookrec.entity.Student;
import org.bookrec.entity.User;
import org.bookrec.entity.dto.StudentDto;
import org.bookrec.entity.vo.ResultVo;
import org.bookrec.service.StudentService;
import org.bookrec.utils.JdbcUtil;
import org.bookrec.utils.ResultVoUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * StudentServiceImpl
 *
 * @author a1311
 */
public class StudentServiceImpl implements StudentService {
    /**
     * 日志管理
     */
    private static final Logger LOG = Logger.getLogger(JdbcUtil.class);
    /**
     * 注入dao
     */
    private final StudentDao studentDao = new StudentDaoImpl();
    private final SchoolDao schoolDao = new SchoolDaoImpl();
    private final MajorDao majorDao = new MajorDaoImpl();
    private final UserDao userDao = new UserDaoImpl();


    @Override
    public ResultVo<?> getAllStudent() throws Exception {
        try {
            JdbcUtil.getConnection();
            List<Student> list = studentDao.selectBySelective(new Student());
            List<StudentDto> students = new ArrayList<>();
            for (Student stu : list) {
                StudentDto dto = new StudentDto();
                School school = schoolDao.selectById(stu.getSchoolId());
                Major major = majorDao.selectById(stu.getMajorId());
                dto.setId(stu.getId());
                dto.setUsername(stu.getUsername());
                dto.setName(stu.getName());
                dto.setSchool(school);
                dto.setMajor(major);
                students.add(dto);
            }
            return ResultVoUtil.success(students);
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            JdbcUtil.rollbackTransaction();
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }

    @Override
    public ResultVo<?> selectStudentByUsername(String username) throws Exception {
        try {
            JdbcUtil.getConnection();
            Student student = new Student();
            student.setUsername(username);
            student = studentDao.selectStudentByUsername(username);
            if (student.getId() != null) {
                return ResultVoUtil.success(student);
            } else {
                return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
            }
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }

    @Override
    public ResultVo<?> updateStudent(Student student) throws Exception {
        try {
            JdbcUtil.getConnection();
            boolean res = studentDao.update(student);
            return ResultVoUtil.success(res);
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            JdbcUtil.rollbackTransaction();
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }

    @Override
    public ResultVo<?> addStudent(Student student) throws Exception {
        try {
            JdbcUtil.getConnection();
            boolean res = studentDao.insert(student);
            if (res) {
                User user = new User();
                user.setType(1);
                user.setUsername(student.getUsername());
                res = userDao.insert(user);
            }


            return ResultVoUtil.success(res);
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            JdbcUtil.rollbackTransaction();
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }
}
