package org.bookrec.dao.impl;

import org.bookrec.dao.MessageDao;
import org.bookrec.entity.Major;
import org.bookrec.entity.Message;
import org.bookrec.utils.JdbcUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * MessageDaoImpl
 *
 * @author a1311
 */
public class MessageDaoImpl extends JdbcUtil implements MessageDao {
    @Override
    public boolean insert(Long studentId, Long messageId) throws Exception {
        String sql = "INSERT INTO student_message(student_id, message_id) VALUES(?,?)";
        Object[] params = {studentId, messageId};
        return getDml(sql, params);
    }

    @Override
    public List<Long> selectMessageIdListByStudentId(Long id) throws Exception {
        String sql = "SELECT message_id FROM student_message WHERE student_id=?";
        Object[] params = {id};
        ResultSet rs = query(sql, params);
        List<Long> list = new ArrayList<>();
        while (rs.next()) {
            list.add(rs.getLong("message_id"));
        }
        return list;
    }


    @Override
    public boolean insert(Message obj) throws Exception {
        String sql = "INSERT INTO message(title,content,type) VALUES(?,?,?)";
        Object[] params = {obj.getTitle(), obj.getContent(), obj.getType()};
        return getDml(sql, params);
    }

    @Override
    public boolean update(Message obj) throws Exception {
        return false;
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
    public Message selectById(String id) throws Exception {
        return null;
    }

    @Override
    public Message selectById(Integer id) throws Exception {
        return null;
    }

    @Override
    public Message selectById(Long id) throws Exception {
        String sql = "SELECT id,title,content,type FROM message WHERE id=?";
        Object[] params = {id};
        return getOneObject(Message.class, sql, params);
    }

    @Override
    public List<Message> selectBySelective(Message obj) throws Exception {
        StringBuilder sql = new StringBuilder("SELECT id,title,content,type FROM message WHERE 1=1");
        List<Object> params = new ArrayList<>();
        if (obj.getId() != null) {
            sql.append(" AND id=?");
            params.add(obj.getId());
        }
        if (obj.getTitle() != null && !"".equals(obj.getTitle())) {
            sql.append(" AND title LIKE CONCAT('%',?,'%')");
            params.add(obj.getTitle());
        }
        if (obj.getContent() != null && !"".equals(obj.getContent())) {
            sql.append(" AND content LIKE CONCAT('%',?,'%')");
            params.add(obj.getContent());
        }
        if (obj.getType() != null) {
            sql.append(" AND type=?");
            params.add(obj.getType());
        }
        return getList(Message.class, sql.toString(), params.toArray());
    }
}
