package org.bookrec.entity;

/**
 * 消息盒子类
 *
 * @author a1311
 */
public class MessageBox {
    /**
     * id
     */
    private Long id;
    /**
     * 学生id
     */
    private Long studentId;
    /**
     * 消息id
     */
    private Long messageId;
    /**
     * 是否已读
     */
    private Integer isRead;

    public MessageBox() {

    }

    public MessageBox(Long studentId, Long messageId) {
        this.studentId = studentId;
        this.messageId = messageId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    @Override
    public String toString() {
        return "MessageBox{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", messageId=" + messageId +
                ", isRead=" + isRead +
                '}';
    }
}
