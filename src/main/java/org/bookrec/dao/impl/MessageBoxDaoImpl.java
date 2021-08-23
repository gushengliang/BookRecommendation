package org.bookrec.dao.impl;

import org.bookrec.dao.MessageBoxDao;
import org.bookrec.entity.MessageBox;
import org.bookrec.utils.JdbcUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * MessageBoxDaoImpl
 *
 * @author a1311
 */
public class MessageBoxDaoImpl extends JdbcUtil implements MessageBoxDao {
    @Override
    public boolean insert(MessageBox obj) throws Exception {
        String sql = "INSERT INTO student_message(student_id,message_id) VALUES(?,?)";
        Object[] params = {obj.getStudentId(), obj.getMessageId()};
        return getDml(sql, params);
    }

    @Override
    public boolean update(MessageBox obj) throws Exception {
        StringBuilder sql = new StringBuilder("UPDATE student_message SET id=id");
        List<Object> params = new ArrayList<>();
        if (obj.getStudentId() != null && obj.getStudentId() != 0) {
            sql.append(",student_id=?");
            params.add(obj.getStudentId());
        }
        if (obj.getMessageId() != null && obj.getMessageId() != 0) {
            sql.append(",message_id=?");
            params.add(obj.getMessageId());
        }
        if (obj.getIsRead() != null && obj.getIsRead() != 0) {
            sql.append(",is_read=?");
            params.add(obj.getIsRead());
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
    public MessageBox selectById(String id) throws Exception {
        return null;
    }

    @Override
    public MessageBox selectById(Integer id) throws Exception {
        return null;
    }

    @Override
    public MessageBox selectById(Long id) throws Exception {
        return null;
    }

    @Override
    public List<MessageBox> selectBySelective(MessageBox obj) throws Exception {
        StringBuilder sql = new StringBuilder("SELECT id,student_id,message_id,is_read FROM student_message WHERE 1=1");
        List<Object> params = new ArrayList<>();
        if (obj.getId() != null && obj.getId() != 0) {
            sql.append(" AND id=?");
            params.add(obj.getId());
        }
        if (obj.getStudentId() != null && obj.getStudentId() != 0) {
            sql.append(" AND student_id=?");
            params.add(obj.getStudentId());
        }
        if (obj.getMessageId() != null && obj.getMessageId() != 0) {
            sql.append(" AND message_id=?");
            params.add(obj.getMessageId());
        }
        if (obj.getIsRead() != null) {
            sql.append(" AND is_read=?");
            params.add(obj.getIsRead());
        }
        return getList(MessageBox.class, sql.toString(), params.toArray());
    }
}
