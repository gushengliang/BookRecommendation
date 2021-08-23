package org.bookrec.dao.impl;

import org.bookrec.dao.SchoolDao;
import org.bookrec.entity.School;
import org.bookrec.utils.JdbcUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * SchoolDaoImpl
 *
 * @author a1311
 */
public class SchoolDaoImpl extends JdbcUtil implements SchoolDao {
    @Override
    public boolean insert(Long schoolId, Long majorId) throws Exception {
        String sql = "INSERT INTO school_major(school_id, major_id) VALUES(?,?)";
        Object[] params = {schoolId, majorId};
        return getDml(sql, params);
    }

    @Override
    public boolean delete(Long schoolId, Long majorId) throws Exception {
        String sql = "DELETE FROM school_major WHERE school_id=? AND major_id=?";
        Object[] params = {schoolId, majorId};
        return getDml(sql, params);
    }

    @Override
    public boolean insert(School obj) throws Exception {
        String sql = "INSERT INTO school(name) VALUES (?)";
        Object[] params = {obj.getName()};
        return getDml(sql, params);
    }

    @Override
    public boolean update(School obj) throws Exception {
        StringBuilder sql = new StringBuilder("UPDATE school SET id=id");
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
    public School selectById(String id) throws Exception {
        return null;
    }

    @Override
    public School selectById(Integer id) throws Exception {
        return null;
    }

    @Override
    public School selectById(Long id) throws Exception {
        String sql = "SELECT id,name FROM school WHERE id=?";
        Object[] params = {id};
        return getOneObject(School.class, sql, params);
    }

    @Override
    public List<School> selectBySelective(School obj) throws Exception {
        StringBuilder sql = new StringBuilder("SELECT id,name FROM school WHERE 1=1");
        List<Object> params = new ArrayList<>();
        if (obj.getId() != null) {
            sql.append(" AND id=?");
            params.add(obj.getId());
        }
        if (obj.getName() != null && !"".equals(obj.getName())) {
            sql.append(" AND name LIKE CONCAT('%',?,'%')");
            params.add(obj.getName());
        }
        return getList(School.class, sql.toString(), params.toArray());
    }
}
