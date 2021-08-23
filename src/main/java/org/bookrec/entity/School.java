package org.bookrec.entity;

import java.util.List;

/**
 * 学校类
 *
 * @author a1311
 */
public class School {
    /**
     * id
     */
    private Long id;
    /**
     * 学校名称
     */
    private String name;
    /**
     * 专业列表
     */
    private List<Major> majorList;

    /**
     * constructors
     */

    public School() {

    }

    public School(String name) {
        this.name = name;
    }

    /**
     * getters and setters
     */

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

    public List<Major> getMajorList() {
        return majorList;
    }

    public void setMajorList(List<Major> majorList) {
        this.majorList = majorList;
    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", majorList=" + majorList +
                '}';
    }
}
