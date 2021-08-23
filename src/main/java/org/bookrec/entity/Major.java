package org.bookrec.entity;

import java.util.List;

/**
 * 专业类
 *
 * @author a1311
 */
public class Major {
    /**
     * id
     */
    private Long id;
    /**
     * 专业名称
     */
    private String name;
    /**
     * 课程列表
     */
    private List<Course> courseList;

    public Major() {

    }

    public Major(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    @Override
    public String toString() {
        return "Major{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", courseList=" + courseList +
                '}';
    }
}
