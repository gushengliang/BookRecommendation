package org.bookrec.dao.impl;

import org.bookrec.dao.UserDao;
import org.bookrec.entity.User;
import org.bookrec.utils.JdbcUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * UserDao
 *
 * @author a1311
 * @version 1.0, 2021-06-19
 */
public class UserDaoImpl extends JdbcUtil implements UserDao {
    @Override
    public boolean isExist(String username) throws Exception {
        //创建SQL语句及参数数组
        String sql = "SELECT COUNT(*) count FROM `user` WHERE username = ?";
        Object[] params = {username};
        //执行SQL语句返回结果
        return getCount(sql, params) > 0;
    }

    @Override
    public boolean insert(User obj) throws Exception {
        //创建SQL语句及参数数组
        String sql = "INSERT INTO `user`(username,type) VALUES(?,?)";
        Object[] params = {obj.getUsername(), obj.getType()};
        //执行SQL语句返回结果
        return getDml(sql, params);
    }

    @Override
    public boolean update(User obj) throws Exception {
        StringBuilder sql = new StringBuilder("UPDATE `user` SET id=id");
        List<Object> params = new ArrayList<>();
        if (obj.getUsername() != null && !"".equals(obj.getUsername())) {
            sql.append(",username=?");
            params.add(obj.getUsername());
        }
        if (obj.getPassword() != null && !"".equals(obj.getPassword())) {
            sql.append(",password=?");
            params.add(obj.getPassword());
        }
        if (obj.getIsActive() != null) {
            sql.append(",is_active=?");
            params.add(obj.getIsActive());
        }
        sql.append(" WHERE id=?");
        params.add(obj.getId());
        //执行SQL语句返回结果
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
    public User selectById(String id) throws Exception {
        return null;
    }

    @Override
    public User selectById(Integer id) throws Exception {
        return null;
    }

    @Override
    public User selectById(Long id) throws Exception {
        return null;
    }

    @Override
    public List<User> selectBySelective(User obj) throws Exception {
        //创建SQL语句及参数数组
        StringBuilder sql = new StringBuilder("SELECT id,username,password,type,is_active FROM `user` WHERE 1=1");
        List<Object> params = new ArrayList<>();
        if (obj.getId() != null) {
            sql.append(" AND id=?");
            params.add(obj.getId());
        }
        if (obj.getUsername() != null && !"".equals(obj.getUsername())) {
            sql.append(" AND username=?");
            params.add(obj.getUsername());
        }
        if (obj.getPassword() != null && !"".equals(obj.getPassword())) {
            sql.append(" AND password=?");
            params.add(obj.getPassword());
        }
        if (obj.getType() != null) {
            sql.append(" AND type=?");
            params.add(obj.getType());
        }
        if (obj.getIsActive() != null) {
            sql.append(" AND is_active=?");
            params.add(obj.getIsActive());
        }
        //执行SQL语句返回结果
        return getList(User.class, sql.toString(), params.toArray());
    }
}
