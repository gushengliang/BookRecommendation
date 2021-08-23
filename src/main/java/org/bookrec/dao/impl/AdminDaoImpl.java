package org.bookrec.dao.impl;

import org.bookrec.dao.AdminDao;
import org.bookrec.entity.Admin;
import org.bookrec.utils.JdbcUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * AdminDaoImpl
 *
 * @author a1311
 */
public class AdminDaoImpl extends JdbcUtil implements AdminDao {
    @Override
    public boolean insert(Admin obj) throws Exception {
        return false;
    }

    @Override
    public boolean update(Admin obj) throws Exception {
        StringBuilder sql = new StringBuilder("UPDATE `admin` SET id=id");
        List<Object> params = new ArrayList<>();
        if (obj.getUsername() != null && !"".equals(obj.getUsername())) {
            sql.append(",username=?");
            params.add(obj.getUsername());
        }
        if (obj.getName() != null && !"".equals(obj.getName())) {
            sql.append(",name=?");
            params.add(obj.getName());
        }
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
    public Admin selectById(String id) throws Exception {
        return null;
    }

    @Override
    public Admin selectById(Integer id) throws Exception {
        return null;
    }

    @Override
    public Admin selectById(Long id) throws Exception {
        String sql = "SELECT id,username,name FROM `admin` WHERE username=?";
        Object[] params = {id};
        return getOneObject(Admin.class,sql, params);
    }

    @Override
    public List<Admin> selectBySelective(Admin obj) throws Exception {
        StringBuilder sql = new StringBuilder("SELECT id,username,name FROM `admin` WHERE 1=1");
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
        return getList(Admin.class, sql.toString(), params.toArray());
    }
}
