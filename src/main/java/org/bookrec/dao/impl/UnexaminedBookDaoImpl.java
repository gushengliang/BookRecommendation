package org.bookrec.dao.impl;

import org.bookrec.dao.UnexaminedBookDao;
import org.bookrec.entity.UnexaminedBook;
import org.bookrec.utils.JdbcUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * UnexaminedBookDaoImpl
 *
 * @author a1311
 */
public class UnexaminedBookDaoImpl extends JdbcUtil implements UnexaminedBookDao {
    @Override
    public boolean insert(UnexaminedBook obj) throws Exception {
        String sql = "INSERT INTO unexamined_book(name,picture,introduction) VALUES(?,?,?,?)";
        Object[] params = { obj.getName(), obj.getPicture(), obj.getIntroduction()};
        return getDml(sql, params);
    }

    @Override
    public boolean update(UnexaminedBook obj) throws Exception {
        StringBuilder sql = new StringBuilder("UPDATE unexamined_book SET ");
        List<Object> params = new ArrayList<>();
        if (obj.getName() != null && !"".equals(obj.getName())) {
            sql.append("name=?");
            params.add(obj.getName());
        }
        if (obj.getPicture() != null) {
            sql.append(",picture=?");
            params.add(obj.getPicture());
        }
        if (obj.getIntroduction() != null) {
            sql.append(",introduction=?");
            params.add(obj.getIntroduction());
        }
        if (obj.getIsExamined() != null){
            sql.append(",is_examined=?");
            params.add(obj.getIsExamined());
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
    public UnexaminedBook selectById(String id) throws Exception {
        return null;
    }

    @Override
    public UnexaminedBook selectById(Integer id) throws Exception {
        return null;
    }

    @Override
    public UnexaminedBook selectById(Long id) throws Exception {
        String sql = "SELECT id,name,picture,introduction,is_examined FROM unexamined_book WHERE id=?";
        Object[] params = {id};
        return getOneObject(UnexaminedBook.class, sql, params);
    }

    @Override
    public List<UnexaminedBook> selectBySelective(UnexaminedBook obj) throws Exception {
        StringBuilder sql = new StringBuilder("SELECT id,name,picture,introduction,is_examined FROM unexamined_book WHERE 1=1");
        List<Object> params = new ArrayList<>();
        if(obj.getId() != null) {
            sql.append(" AND id=?");
            params.add(obj.getId());
        }

        if(obj.getName() != null && !"".equals(obj.getName())) {
            sql.append(" AND name LIKE CONCAT('%',?,'%')");
            params.add(obj.getName());
        }
        if(obj.getPicture() != null && !"".equals(obj.getPicture())) {
            sql.append(" AND picture LIKE CONCAT('%',?,'%')");
            params.add(obj.getPicture());
        }
        if(obj.getIntroduction() != null && !"".equals(obj.getIntroduction())) {
            sql.append(" AND introduction LIKE CONCAT('%',?,'%')");
            params.add(obj.getIntroduction());
        }
        if(obj.getIsExamined() != null) {
            sql.append(" AND is_examined=?");
            params.add(obj.getIsExamined());
        }
        return getList(UnexaminedBook.class, sql.toString(), params.toArray());
    }
}
