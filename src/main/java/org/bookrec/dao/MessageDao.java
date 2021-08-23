package org.bookrec.dao;

import org.bookrec.entity.Message;

import java.util.List;

/**
 * MessageDao
 * @author a1311
 */
public interface MessageDao extends BaseDao<Message>{
    /**
     * 添加课程
     * @param studentId 学生id
     * @param messageId 课程id
     * @throws Exception 异常
     * @return true or false
     */
    @Deprecated
    boolean insert(Long studentId, Long messageId) throws Exception;

    /**
     * 根据学生id查找消息id
     * @param id 学生id
     * @return bookList
     * @throws Exception 异常
     */
    @Deprecated
    List<Long> selectMessageIdListByStudentId(Long id) throws Exception;
}
