package org.bookrec.entity;

import java.util.List;

/**
 * 学生类
 *
 * @author a1311
 */
public class Student {
    /**
     * id
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 姓名
     */
    private String name;
    /**
     * 学校id
     */
    private Long schoolId;
    /**
     * 专业id
     */
    private Long majorId;
    /**
     * 收藏列表
     */
    private List<Book> collection;
    /**
     * 关注列表
     */
    private List<Course> attention;

    public Student() {

    }

    public Student(String username, String name, Long schoolId, Long majorId) {
        this.username = username;
        this.name = name;
        this.schoolId = schoolId;
        this.majorId = majorId;
    }

    public Student(String username, String name, Long schoolId, Long majorId, List<Book> collection, List<Course> attention) {
        this.username = username;
        this.name = name;
        this.schoolId = schoolId;
        this.majorId = majorId;
        this.collection = collection;
        this.attention = attention;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public Long getMajorId() {
        return majorId;
    }

    public void setMajorId(Long majorId) {
        this.majorId = majorId;
    }

    public List<Book> getCollection() {
        return collection;
    }

    public void setCollection(List<Book> collection) {
        this.collection = collection;
    }

    public List<Course> getAttention() {
        return attention;
    }

    public void setAttention(List<Course> attention) {
        this.attention = attention;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", schoolId=" + schoolId +
                ", majorId=" + majorId +
                ", collection=" + collection +
                ", attention=" + attention +
                '}';
    }
}
