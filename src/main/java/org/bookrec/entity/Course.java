package org.bookrec.entity;

import java.util.List;

/**
 * 课程类
 *
 * @author a1311
 */
public class Course {
    /**
     * id
     */
    private Long id;
    /**
     * 课程名称
     */
    private String name;
    /**
     * 参考书列表
     */
    private List<Book> bookList;

    public Course() {

    }

    public Course(String name) {
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

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bookList=" + bookList +
                '}';
    }
}
