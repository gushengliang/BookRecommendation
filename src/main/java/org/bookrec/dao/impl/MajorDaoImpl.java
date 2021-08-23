package org.bookrec.dao.impl;

import org.bookrec.dao.MajorDao;
import org.bookrec.entity.Major;
import org.bookrec.utils.JdbcUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * MajorDaoImpl
 *
 * @author a1311
 */
public class MajorDaoImpl extends JdbcUtil implements MajorDao {
    @Override
    public boolean insert(Long majorId, Long courseId) throws Exception {
        String sql = "INSERT INTO major_course(major_id, course_id) VALUES(?,?)";
        Object[] params = {majorId, courseId};
        return getDml(sql, params);
    }

    @Override
    public boolean delete(Long majorId, Long courseId) throws Exception {
        String sql = "DELETE FROM major_course WHERE major_id=? AND course_id=?";
        Object[] params = {majorId, courseId};
        return getDml(sql, params);
    }

    @Override
    public List<Long> selectMajorIdListBySchoolId(Long id) throws Exception {
        String sql = "SELECT major_id FROM school_major WHERE school_id=?";
        Object[] params = {id};
        ResultSet rs = query(sql, params);
        List<Long> list = new ArrayList<>();
        while (rs.next()) {
            list.add(rs.getLong("major_id"));
        }
        return list;
    }

    @Override
    public boolean insert(Major obj) throws Exception {
        String sql = "INSERT INTO major(name) VALUES(?)";
        Object[] params = {obj.getName()};
        return getDml(sql, params);
    }

    @Override
    public boolean update(Major obj) throws Exception {
        StringBuilder sql = new StringBuilder("UPDATE major SET id=id");
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
    public Major selectById(String id) throws Exception {
        return null;
    }

    @Override
    public Major selectById(Integer id) throws Exception {

        return null;
    }

    @Override
    public Major selectById(Long id) throws Exception {
        String sql = "SELECT id,name FROM major WHERE id=?";
        Object[] params = {id};
        return getOneObject(Major.class, sql, params);
    }

    @Override
    public List<Major> selectBySelective(Major obj) throws Exception {
        StringBuilder sql = new StringBuilder("SELECT id,name FROM major WHERE 1=1");
        List<Object> params = new ArrayList<>();
        if (obj.getId() != null && obj.getId() != 0) {
            sql.append(" AND id=?");
            params.add(obj.getId());
        }
        if (obj.getName() != null && !"".equals(obj.getName())) {
            sql.append(" AND name LIKE CONCAT('%',?,'%')");
            params.add(obj.getName());
        }
        return getList(Major.class, sql.toString(), params.toArray());
    }
}
