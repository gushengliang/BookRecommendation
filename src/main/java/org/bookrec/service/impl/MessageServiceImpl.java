package org.bookrec.service.impl;

import org.apache.log4j.Logger;
import org.bookrec.common.enums.impl.ErrorCode;
import org.bookrec.dao.MessageBoxDao;
import org.bookrec.dao.MessageDao;
import org.bookrec.dao.StudentDao;
import org.bookrec.dao.impl.MessageBoxDaoImpl;
import org.bookrec.dao.impl.MessageDaoImpl;
import org.bookrec.dao.impl.StudentDaoImpl;
import org.bookrec.entity.*;
import org.bookrec.entity.dto.StudentDto;
import org.bookrec.entity.vo.ResultVo;
import org.bookrec.service.MessageService;
import org.bookrec.utils.JdbcUtil;
import org.bookrec.utils.ResultVoUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageServiceImpl implements MessageService {
    /**
     * 日志管理
     */
    private static final Logger LOG = Logger.getLogger(JdbcUtil.class);
    /**
     * 注入Dao
     */
    private final MessageDao messageDao = new MessageDaoImpl();
    private final MessageBoxDao messageBoxDao = new MessageBoxDaoImpl();
    private final StudentDao studentDao = new StudentDaoImpl();

    @Override
    public ResultVo<?> sendinform(Message message) throws Exception {

        try {
            JdbcUtil.getConnection();
            boolean res = messageDao.insert(message);
            List<Message> messages = messageDao.selectBySelective(message);
            message = messages.get(0);
            List<Student> students = studentDao.selectBySelective(new Student());
            MessageBox messageBox = new MessageBox();
            int i = 0;
            while (i < students.size() && res) {
                messageBox.setMessageId(message.getId());
                messageBox.setStudentId(students.get(i).getId());
                res = messageBoxDao.insert(messageBox);
                i++;
            }

            return ResultVoUtil.success(res);
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            JdbcUtil.rollbackTransaction();
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }

    @Override
    public ResultVo<?> viewMessage(Long studentId, Integer isRead) throws Exception {
        try {
            JdbcUtil.getConnection();
            //初始化消息列表
            List<Message> messages = new ArrayList<>();
            MessageBox messageBox = new MessageBox();
            messageBox.setStudentId(studentId);
            if (isRead != 2) {
                messageBox.setIsRead(isRead);
            }
            List<MessageBox> messageBoxes = messageBoxDao.selectBySelective(messageBox);
            for (MessageBox mb : messageBoxes) {
                System.out.println(mb);
                messages.add(messageDao.selectById(mb.getMessageId()));
            }
            return ResultVoUtil.success(messages);
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }

    @Override
    public ResultVo<?> getRead(Long studentId, Long messageId) throws Exception {
        try {
            JdbcUtil.beginTransaction();
            boolean flag = true;
            MessageBox messageBox = new MessageBox();
            messageBox.setStudentId(studentId);
            if (messageId != 0) {
                messageBox.setMessageId(messageId);
            }
            List<MessageBox> messageBoxes = messageBoxDao.selectBySelective(messageBox);
            for (MessageBox mb : messageBoxes) {
                mb.setIsRead(1);
                if (!messageBoxDao.update(mb)) {
                    flag = false;
                }
            }
            JdbcUtil.commitTransaction();
            return ResultVoUtil.success(flag);
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            JdbcUtil.rollbackTransaction();
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }

    @Override
    public ResultVo<?> setNotice(String bookName, Long studentId) throws Exception {
        try {
            JdbcUtil.beginTransaction();
            boolean flag = true;
            //创建消息对象
            String title = "《" + bookName + "》阅读提醒";
            String content = "你已经订阅了《" + bookName + "》的阅读提醒，记得多多阅读哦~";
            Message message = new Message(title, content, 0);
            //先从数据库中查询
            List<Message> messages = messageDao.selectBySelective(message);
            //如果查询不到就将该消息插入数据库中
            if (messages == null || messages.size() == 0) {
                flag = messageDao.insert(message);
                messages = messageDao.selectBySelective(message);
            }
            //创建消息盒子对象
            MessageBox messageBox = new MessageBox(studentId, messages.get(0).getId());
            //插入数据库
            flag = flag && messageBoxDao.insert(messageBox);
            JdbcUtil.commitTransaction();
            return ResultVoUtil.success(flag);
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            JdbcUtil.rollbackTransaction();
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }
}
