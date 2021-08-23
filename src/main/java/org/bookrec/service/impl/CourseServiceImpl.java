package org.bookrec.service.impl;

import org.apache.log4j.Logger;
import org.bookrec.common.enums.impl.ErrorCode;
import org.bookrec.dao.CourseDao;
import org.bookrec.dao.impl.CourseDaoImpl;
import org.bookrec.entity.Book;
import org.bookrec.entity.Course;
import org.bookrec.entity.Major;
import org.bookrec.entity.School;
import org.bookrec.entity.vo.ResultVo;
import org.bookrec.service.CourseService;
import org.bookrec.utils.CastUtil;
import org.bookrec.utils.JdbcUtil;
import org.bookrec.utils.ResultVoUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * CourseServiceImpl
 *
 * @author a1311
 */
public class CourseServiceImpl implements CourseService {
    /**
     * 日志管理
     */
    private static final Logger LOG = Logger.getLogger(JdbcUtil.class);
    /**
     * 注入dao
     */
    private final CourseDao courseDao = new CourseDaoImpl();

    @Override
    public ResultVo<?> addAttention(Long studentId, Long courseId) throws Exception {
        try {
            JdbcUtil.beginTransaction();
            if(courseDao.selectCourseIdListByStudentId(studentId).contains(courseId)) {
                JdbcUtil.commitTransaction();
                return ResultVoUtil.fail(ErrorCode.SYSTEM_BUSY);
            } else {
                boolean flag = courseDao.insert(studentId, courseId);
                JdbcUtil.commitTransaction();
                return ResultVoUtil.success(flag);
            }

        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            JdbcUtil.rollbackTransaction();
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }

    @Override
    public ResultVo<?> removeAttention(Long studentId, Long courseId) throws Exception {
        try {
            JdbcUtil.beginTransaction();
            if(!courseDao.selectCourseIdListByStudentId(studentId).contains(courseId)) {
                JdbcUtil.commitTransaction();
                return ResultVoUtil.fail(ErrorCode.SYSTEM_BUSY);
            } else {
                boolean flag = courseDao.delete(studentId, courseId);
                JdbcUtil.commitTransaction();
                return ResultVoUtil.success(flag);
            }
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            JdbcUtil.rollbackTransaction();
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }

    @Override
    public ResultVo<?> searchCoursesByMajorId(Long id) throws Exception {
        try {
            JdbcUtil.getConnection();
            List<Long> longList = courseDao.selectCourseIdListByMajorId(id);
            List<Course> courseList = new ArrayList<>();
            for (Long longId : longList) {
                courseList.add(courseDao.selectById(longId));
            }
            return ResultVoUtil.success(courseList);
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }

    @Override
    public ResultVo<?> searchCoursesByBookId(Long id) throws Exception {
        try {
            JdbcUtil.getConnection();
            List<Long> longList = courseDao.selectCourseIdListByBookId(id);
            List<Course> courseList = new ArrayList<>();
            for (Long longId : longList) {
                courseList.add(courseDao.selectById(longId));
            }
            return ResultVoUtil.success(courseList);
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }

    @Override
    public ResultVo<?> viewAttention(Long studentId) throws Exception {
        try {
            JdbcUtil.getConnection();
            List<Long> courseIdList = courseDao.selectCourseIdListByStudentId(studentId);
            List<Course> courseList = new ArrayList<>();
            for (Long courseId : courseIdList) {
                courseList.add(courseDao.selectById(courseId));
            }
            return ResultVoUtil.success(courseList);
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }

    @Override
    public ResultVo<?> addCourse(Course course) throws Exception {
        try {
            JdbcUtil.getConnection();
            boolean res = courseDao.insert(course);
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
    public ResultVo<?> updateCourse(Course course) throws Exception {
        try {
            JdbcUtil.getConnection();
            boolean res = courseDao.update(course);
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
    public List<Course> selectByMajorId(Long majorId) throws Exception {
        JdbcUtil.getConnection();
        List<Long> courseIds = courseDao.selectCourseIdListByMajorId(majorId);
        List<Course> courses = new ArrayList<>();
        int i = 0;
        while (i < courseIds.size()) {
            courses.add(courseDao.selectById(courseIds.get(i)));
            i++;
        }
        JdbcUtil.close();
        return courses;
    }

    @Override
    public ResultVo<?> getAllCourses() throws Exception {
        try {
            JdbcUtil.getConnection();
            List<Course> courses = courseDao.selectBySelective(new Course());
            return ResultVoUtil.success(courses);
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            JdbcUtil.rollbackTransaction();
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }

    @Override
    public synchronized ResultVo<?> getCoursesByMajors(String[] majors) throws Exception {
        try {
            JdbcUtil.getConnection();
            List<Course> courses = new ArrayList<>();
            //如果传进的专业参数不为空
            if (majors == null || majors.length == 0) {
                courses = courseDao.selectBySelective(new Course());
            } else {
                List<Long> majorIds = new ArrayList<>();
                for (String majorId : majors) {
                    majorIds.add(CastUtil.castLong(majorId));
                }
                //遍历专业列表
                for (Long majorId : majorIds) {
                    //获取专业所对应的课程id列表
                    List<Long> courseIdList = courseDao.selectCourseIdListByMajorId(majorId);
                    //遍历课程id列表
                    for (Long courseId : courseIdList) {
                        courses.add(courseDao.selectById(courseId));
                    }
                }
            }
            //删除相同元素
            for (int i = 0; i < courses.size() - 1; i++) {
                for (int j = courses.size() - 1; j > i; j--) {
                    if (courses.get(j).getId().equals(courses.get(i).getId())) {
                        courses.remove(j);
                    }
                }
            }
            //System.out.println(Arrays.toString(courses.toArray()));
            return ResultVoUtil.success(courses);
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            JdbcUtil.rollbackTransaction();
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }
}
