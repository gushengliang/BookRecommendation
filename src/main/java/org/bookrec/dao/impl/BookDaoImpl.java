package org.bookrec.dao.impl;

import org.bookrec.dao.BookDao;
import org.bookrec.entity.Book;
import org.bookrec.utils.JdbcUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * BookDaoImpl
 *
 * @author a1311
 */
public class BookDaoImpl extends JdbcUtil implements BookDao {
    @Override
    public Integer selectCount(Long studentId, Long bookId) throws Exception {
        String sql = "SELECT COUNT(*) count FROM student_book WHERE student_id=? AND book_id=?";
        Object[] params = {studentId, bookId};
        return getCount(sql, params);
    }

    @Override
    public boolean insert(Long studentId, Long bookId) throws Exception {
        String sql = "INSERT INTO student_book(student_id, book_id) VALUES(?,?)";
        Object[] params = {studentId, bookId};
        return getDml(sql, params);
    }

    @Override
    public boolean delete(Long studentId, Long bookId) throws Exception {
        String sql = "DELETE FROM student_book WHERE student_id=? AND book_id=?";
        Object[] params = {studentId, bookId};
        return getDml(sql, params);
    }

    @Override
    public boolean deleteByBookId(Long bookId) throws Exception {
        String sql = "DELETE FROM student_book WHERE book_id=?";
        Object[] params = {bookId};
        return getDml(sql, params);
    }

    @Override
    public List<Long> selectBookIdListByStudentId(Long id) throws Exception {
        String sql = "SELECT book_id FROM student_book WHERE student_id=?";
        return getLongs(id, sql);
    }

    @Override
    public List<Long> selectBookIdListByCourseId(Long id) throws Exception {
        String sql = "SELECT book_id FROM book_course WHERE course_id=?";
        return getLongs(id, sql);
    }

    @Override
    public boolean updatePageView(Long id) throws Exception {
        String sql = "UPDATE book SET page_view=page_view+1 WHERE id=?";
        Object[] params = {id};
        return getDml(sql, params);
    }

    @Override
    public boolean increaseAttention(Long id) throws Exception {
        String sql = "UPDATE book SET attention=attention+1 WHERE id=?";
        Object[] params = {id};
        return getDml(sql, params);
    }

    @Override
    public boolean decreaseAttention(Long id) throws Exception {
        String sql = "UPDATE book SET attention=attention-1 WHERE id=?";
        Object[] params = {id};
        return getDml(sql, params);
    }

    @Override
    public List<Book> orderByPageView() throws Exception {
        String sql = "SELECT id,name,picture,introduction,page_view,attention,avg_level FROM book ORDER BY page_view DESC";
        Object[] params = {};
        return getList(Book.class, sql, params);
    }

    @Override
    public List<Book> orderByAttention() throws Exception {
        String sql = "SELECT id,name,picture,introduction,page_view,attention,avg_level FROM book ORDER BY attention DESC";
        Object[] params = {};
        return getList(Book.class, sql, params);
    }

    @Override
    public List<Book> orderByAvgLevel() throws Exception {
        String sql = "SELECT id,name,picture,introduction,page_view,attention,avg_level FROM book ORDER BY avg_level DESC";
        Object[] params = {};
        return getList(Book.class, sql, params);
    }

    @Override
    public boolean insert(Book obj) throws Exception {
        String sql = "INSERT INTO book(name,picture,introduction) VALUES(?,?,?)";
        Object[] params = {obj.getName(), obj.getPicture(), obj.getIntroduction()};
        return getDml(sql, params);
    }

    @Override
    public boolean update(Book obj) throws Exception {
        StringBuilder sql = new StringBuilder("UPDATE book SET id=id");
        List<Object> params = new ArrayList<>();
        if (obj.getName() != null && !"".equals(obj.getName())) {
            sql.append(",name=?");
            params.add(obj.getName());
        }
        if (obj.getPicture() != null && !"".equals(obj.getPicture())) {
            sql.append(",picture=?");
            params.add(obj.getPicture());
        }
        if (obj.getIntroduction() != null && !"".equals(obj.getIntroduction())) {
            sql.append(",introduction=?");
            params.add(obj.getIntroduction());
        }
        if (obj.getPageView() != null) {
            sql.append(",page_view=?");
            params.add(obj.getPageView());
        }
        if (obj.getAvgLevel() != null) {
            sql.append(",avg_level=?");
            params.add(obj.getAvgLevel());
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
        String sql = "DELETE FROM book WHERE id=?";
        Object[] params = {id};
        return getDml(sql, params);
    }

    @Override
    public Book selectById(String id) throws Exception {
        return null;
    }

    @Override
    public Book selectById(Integer id) throws Exception {
        return null;
    }

    @Override
    public Book selectById(Long id) throws Exception {
        String sql = "SELECT id,name,picture,introduction,page_view,attention,avg_level FROM book WHERE id=?";
        Object[] params = {id};
        return getOneObject(Book.class, sql, params);
    }

    @Override
    public List<Book> selectBySelective(Book obj) throws Exception {
        StringBuilder sql = new StringBuilder("SELECT id,name,picture,introduction,page_view,attention,avg_level FROM book WHERE 1=1");
        List<Object> params = new ArrayList<>();
        if (obj.getId() != null && obj.getId() != 0) {
            sql.append(" AND id=?");
            params.add(obj.getId());
        }
        if (obj.getName() != null && !"".equals(obj.getName())) {
            sql.append(" AND name LIKE CONCAT('%',?,'%')");
            params.add(obj.getName());
        }
        if (obj.getPicture() != null && !"".equals(obj.getPicture())) {
            sql.append(" AND picture LIKE CONCAT('%',?,'%')");
            params.add(obj.getPicture());
        }
        if (obj.getIntroduction() != null && !"".equals(obj.getIntroduction())) {
            sql.append(" AND introduction LIKE CONCAT('%',?,'%')");
            params.add(obj.getIntroduction());
        }
        if (obj.getPageView() != null) {
            sql.append(" AND page_view=?");
            params.add(obj.getPageView());
        }
        if (obj.getAttention() != null) {
            sql.append(" AND attention=?");
            params.add(obj.getAttention());
        }
        if (obj.getAvgLevel() != null) {
            sql.append(" AND avg_level=?");
            params.add(obj.getAvgLevel());
        }
        return getList(Book.class, sql.toString(), params.toArray());
    }

    private List<Long> getLongs(Long id, String sql) throws Exception {
        Object[] params = {id};
        ResultSet rs = query(sql, params);
        List<Long> list = new ArrayList<>();
        while (rs.next()) {
            list.add(rs.getLong("book_id"));
        }
        return list;
    }
}
