package org.bookrec.entity;

/**
 * 评价类
 *
 * @author a1311
 */
public class Evaluation {
    /**
     * id
     */
    private Long id;
    /**
     * 参考书id
     */
    private Long bookId;
    /**
     * 学生id
     */
    private Long studentId;
    /**
     * 评分
     */
    private Integer level;
    /**
     * 评价
     */
    private String comment;
    /**
     * 状态
     */
    private Integer status;

    public Evaluation() {

    }

    public Evaluation(Long bookId, Long studentId, Integer level, String comment) {
        this.bookId = bookId;
        this.studentId = studentId;
        this.level = level;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Evaluation{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", studentId=" + studentId +
                ", level=" + level +
                ", comment='" + comment + '\'' +
                ", status=" + status +
                '}';
    }
}
