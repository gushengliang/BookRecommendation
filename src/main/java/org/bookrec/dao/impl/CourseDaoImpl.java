package org.bookrec.dao.impl;

import org.bookrec.dao.CourseDao;
import org.bookrec.entity.Course;
import org.bookrec.utils.JdbcUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * CourseDaoImpl
 *
 * @author a1311
 */
public class CourseDaoImpl extends JdbcUtil implements CourseDao {
    @Override
    public boolean insert(Long studentId, Long courseId) throws Exception {
        String sql = "INSERT INTO student_course(student_id, course_id) VALUES(?,?)";
        Object[] params = {studentId, courseId};
        return getDml(sql, params);
    }

    @Override
    public boolean delete(Long studentId, Long courseId) throws Exception {
        String sql = "DELETE FROM student_course WHERE student_id=? AND course_id=?";
        Object[] params = {studentId, courseId};
        return getDml(sql, params);
    }

    @Override
    public List<Long> selectCourseIdListByStudentId(Long id) throws Exception {
        String sql = "SELECT course_id FROM student_course WHERE student_id=?";
        return getLongs(id, sql);
    }

    @Override
    public List<Long> selectCourseIdListByMajorId(Long id) throws Exception {
        String sql = "SELECT course_id FROM major_course WHERE major_id=?";
        return getLongs(id, sql);
    }

    @Override
    public List<Long> selectCourseIdListByBookId(Long id) throws Exception {
        String sql = "SELECT course_id FROM book_course WHERE book_id=?";
        return getLongs(id, sql);
    }

    @Override
    public boolean insert(Course obj) throws Exception {
        String sql = "INSERT INTO course(name) VALUES (?)";
        Object[] params = {obj.getName()};
        return getDml(sql, params);
    }

    @Override
    public boolean update(Course obj) throws Exception {
        StringBuilder sql = new StringBuilder("UPDATE course SET id=id");
        List<Object> params = new ArrayList<>();
        if (obj.getName() != null && !"".equals(obj.getName())) {
            sql.append(",name=?");
            params.add(obj.getName());
        }
        sql.append(" WHERE id=?");
        params.add(obj.getId());
        return getDml(sql.toString(), params.toArray());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Long id) throws Exception {
        return false;
    }

    @Override
    public Course selectById(String id) throws Exception {
        return null;
    }

    @Override
    public Course selectById(Integer id) throws Exception {
        return null;
    }

    @Override
    public Course selectById(Long id) throws Exception {
        String sql = "SELECT id,name FROM course WHERE id=?";
        Object[] params = {id};
        return getOneObject(Course.class, sql, params);
    }

    @Override
    public List<Course> selectBySelective(Course obj) throws Exception {
        StringBuilder sql = new StringBuilder("SELECT id,name FROM course WHERE 1=1");
        List<Object> params = new ArrayList<>();
        if (obj.getId() != null) {
            sql.append(" AND id=?");
            params.add(obj.getId());
        }
        if (obj.getName() != null && !"".equals(obj.getName())) {
            sql.append(" AND name LIKE CONCAT('%',?,'%')");
            params.add(obj.getName());
        }
        return getList(Course.class, sql.toString(), params.toArray());
    }

    private List<Long> getLongs(Long id, String sql) throws Exception {
        Object[] params = {id};
        ResultSet rs = query(sql, params);
        List<Long> list = new ArrayList<>();
        while (rs.next()) {
            list.add(rs.getLong("course_id"));
        }
        return list;
    }
}
