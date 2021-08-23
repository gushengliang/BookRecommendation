package org.bookrec.dao.impl;

import org.bookrec.dao.EvaluationDao;
import org.bookrec.entity.Evaluation;
import org.bookrec.utils.JdbcUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * EvaluationDaoImpl
 *
 * @author a1311
 */
public class EvaluationDaoImpl extends JdbcUtil implements EvaluationDao {
    @Override
    public boolean insert(Evaluation obj) throws Exception {
        String sql = "INSERT INTO evaluation(book_id,student_id,level,comment) VALUES(?,?,?,?)";
        Object[] params = {obj.getBookId(), obj.getStudentId(), obj.getLevel(), obj.getComment()};
        return getDml(sql, params);
    }

    @Override
    public boolean update(Evaluation obj) throws Exception {
        StringBuilder sql = new StringBuilder("UPDATE evaluation SET id=id");
        List<Object> params = new ArrayList<>();
        if (obj.getBookId() != null) {
            sql.append(",book_id=?");
            params.add(obj.getBookId());
        }
        if (obj.getStudentId() != null) {
            sql.append(",student_id=?");
            params.add(obj.getStudentId());
        }
        if (obj.getLevel() != null) {
            sql.append(",level=?");
            params.add(obj.getLevel());
        }
        if (obj.getComment() != null && !"".equals(obj.getComment())) {
            sql.append(",comment=?");
            params.add(obj.getComment());
        }
        if (obj.getStatus() != null) {
            sql.append(",status=?");
            params.add(obj.getStatus());
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
    public Evaluation selectById(String id) throws Exception {
        return null;
    }

    @Override
    public Evaluation selectById(Integer id) throws Exception {
        return null;
    }

    @Override
    public Evaluation selectById(Long id) throws Exception {
        String sql = "SELECT id,book_id,student_id,level,comment,status FROM evaluation WHERE id=?";
        Object[] params = {id};
        return getOneObject(Evaluation.class, sql, params);
    }

    @Override
    public List<Evaluation> selectBySelective(Evaluation obj) throws Exception {
        StringBuilder sql = new StringBuilder("SELECT id,book_id,student_id,level,comment,status FROM evaluation WHERE 1=1");
        List<Object> params = new ArrayList<>();
        if (obj.getBookId() != null) {
            sql.append(" AND book_id=?");
            params.add(obj.getBookId());
        }
        if (obj.getStudentId() != null) {
            sql.append(" AND student_id=?");
            params.add(obj.getStudentId());
        }
        if (obj.getLevel() != null) {
            sql.append(" AND level=?");
            params.add(obj.getLevel());
        }
        if (obj.getComment() != null && !"".equals(obj.getComment())) {
            sql.append(" AND comment LIKE CONCAT('%',?,'%')");
            params.add(obj.getComment());
        }
        if (obj.getStatus() != null) {
            sql.append(" AND status=?");
            params.add(obj.getStatus());
        }
        return getList(Evaluation.class, sql.toString(), params.toArray());
    }

    @Override
    public Double getAvgLevelByBookId(Long bookId) throws Exception {
        String sql = "SELECT ROUND(AVG(`level`),1) avg FROM evaluation WHERE book_id=? AND status=1";
        Object[] params = {bookId};
        ResultSet rs = query(sql, params);
        if(rs.next()) {
            return rs.getDouble("avg");
        }
        return null;
    }
}
