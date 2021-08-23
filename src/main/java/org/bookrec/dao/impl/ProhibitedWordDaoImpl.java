package org.bookrec.dao.impl;

import org.bookrec.dao.ProhibitedWordDao;
import org.bookrec.entity.ProhibitedWord;
import org.bookrec.utils.JdbcUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * ProhibitedWordDaoImpl
 *
 * @author a1311
 */
public class ProhibitedWordDaoImpl extends JdbcUtil implements ProhibitedWordDao {
    @Override
    public boolean insert(ProhibitedWord obj) throws Exception {
        String sql = "INSERT INTO prohibited_word(content) VALUES(?)";
        Object[] params = {obj.getContent()};
        return getDml(sql, params);
    }

    @Override
    public boolean update(ProhibitedWord obj) throws Exception {
        StringBuilder sql = new StringBuilder("UPDATE prohibited_word SET id=id");
        List<Object> params = new ArrayList<>();
        if (obj.getContent() != null && !"".equals(obj.getContent())) {
            sql.append(",content=?");
            params.add(obj.getContent());
        }
        sql.append(" WHERE id=?");
        params.add(obj.getId());
        return getDml(sql.toString(), params.toArray());
    }

    @Override
    public boolean delete(String id) throws Exception {
        StringBuilder sql = new StringBuilder("DELETE from prohibited_word where id=?");
        Object[] params = {id};
        return getDml( sql.toString(), params);
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
    public ProhibitedWord selectById(String id) throws Exception {
        return null;
    }

    @Override
    public ProhibitedWord selectById(Integer id) throws Exception {
        return null;
    }

    @Override
    public ProhibitedWord selectById(Long id) throws Exception {
        String sql = "SELECT id,content FROM prohibited_word WHERE id=?";
        Object[] params = {id};
        return getOneObject(ProhibitedWord.class, sql, params);
    }

    @Override
    public List<ProhibitedWord> selectBySelective(ProhibitedWord obj) throws Exception {
        StringBuilder sql = new StringBuilder("SELECT id,content FROM prohibited_word WHERE 1=1");
        List<Object> params = new ArrayList<>();
        if (obj.getId() != null) {
            sql.append(" AND id=?");
            params.add(obj.getId());
        }
        if (obj.getContent() != null && !"".equals(obj.getContent())) {
            sql.append(" AND content LIKE CONCAT('%',?,'%')");
            params.add(obj.getContent());
        }
        return getList(ProhibitedWord.class, sql.toString(), params.toArray());
    }
}
