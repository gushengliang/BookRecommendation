package org.bookrec.service;

import org.bookrec.entity.Message;
import org.bookrec.entity.MessageBox;
import org.bookrec.entity.vo.ResultVo;

/**
 * 发送通知
 * MessageService
 *
 * @author a1311
 */
public interface MessageService {
    /**
     * 发送广播消息
     *
     * @param message
     * @return ResultVo
     * @throws Exception
     */
    ResultVo<?> sendinform(Message message) throws Exception;

    /**
     * 按照是否已读查看消息
     *
     * @param isRead 是否已读
     * @return ResultVo
     * @throws Exception 异常
     */
    ResultVo<?> viewMessage(Long studentId, Integer isRead) throws Exception;

    /**
     * 将消息设置为已读
     *
     * @param studentId 学生id
     * @param messageId 消息id
     * @return ResultVo
     * @throws Exception 异常
     */
    ResultVo<?> getRead(Long studentId, Long messageId) throws Exception;

    /**
     * 设置阅读提醒
     *
     * @param bookName  书名
     * @param studentId 学生id
     * @return ResultVo
     * @throws Exception 异常
     */
    ResultVo<?> setNotice(String bookName, Long studentId) throws Exception;
}
