package org.bookrec.dao.impl;

import org.bookrec.dao.StudentDao;
import org.bookrec.entity.Student;
import org.bookrec.utils.JdbcUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * StudentDaoImpl
 *
 * @author a1311
 */
public class StudentDaoImpl extends JdbcUtil implements StudentDao {


    @Override
    public boolean insert(Student obj) throws Exception {
        String sql = "INSERT INTO student(username,name,school_id,major_id) VALUES(?,?,?,?)";
        Object[] params = {obj.getUsername(), obj.getName(), obj.getSchoolId(), obj.getMajorId()};
        return getDml(sql, params);
    }

    @Override
    public boolean update(Student obj) throws Exception {
        StringBuilder sql = new StringBuilder("UPDATE student SET ");
        List<Object> params = new ArrayList<>();
        if (obj.getUsername() != null && !"".equals(obj.getUsername())) {
            sql.append("username=?");
            params.add(obj.getUsername());
        }
        if (obj.getName() != null && !"".equals(obj.getName())) {
            sql.append(",name=?");
            params.add(obj.getName());
        }
        if (obj.getSchoolId() != null) {
            sql.append(",school_id=?");
            params.add(obj.getSchoolId());
        }
        if (obj.getMajorId() != null) {
            sql.append(",major_id=?");
            params.add(obj.getMajorId());
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
    public Student selectById(String id) throws Exception {
        return null;
    }

    @Override
    public Student selectById(Integer id) throws Exception {
        return null;
    }

    @Override
    public Student selectById(Long id) throws Exception {
        String sql = "SELECT id, username, name, school_id, major_id FROM student WHERE id=?";
        Object[] params = {id};
        return getOneObject(Student.class, sql, params);
    }

    @Override
    public List<Student> selectBySelective(Student obj) throws Exception {
        StringBuilder sql = new StringBuilder("SELECT id,username,name,school_id,major_id FROM student WHERE 1=1");
        List<Object> params = new ArrayList<>();
        if (obj.getId() != null) {
            sql.append(" AND id=?");
            params.add(obj.getId());
        }
        if (obj.getUsername() != null && !"".equals(obj.getUsername())) {
            sql.append(" AND username LIKE CONCAT('%',?,'%')");
            params.add(obj.getUsername());
        }
        if (obj.getName() != null && !"".equals(obj.getName())) {
            sql.append(" AND name LIKE CONCAT('%',?,'%')");
            params.add(obj.getName());
        }
        if (obj.getSchoolId() != null) {
            sql.append(" AND school_id=?");
            params.add(obj.getSchoolId());
        }
        if (obj.getMajorId() != null) {
            sql.append(" AND major_id=?");
            params.add(obj.getMajorId());
        }
        return getList(Student.class, sql.toString(), params.toArray());
    }

    @Override
    public Student selectStudentByUsername(String username) throws Exception {
        String sql = "SELECT id,username,name,school_id,major_id FROM student WHERE username=?";
        Object[] params = {username};
        return getOneObject(Student.class, sql, params);
    }
}
