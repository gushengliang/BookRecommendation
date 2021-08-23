package org.bookrec.entity;

import java.util.Objects;

/**
 * 参考书类
 *
 * @author a1311
 */
public class Book {
    /**
     * id
     */
    private Long id;
    /**
     * 书名
     */
    private String name;
    /**
     * 书本图片（链接）
     */
    private String picture;
    /**
     * 书本简介
     */
    private String introduction;
    /**
     * 浏览量
     */
    private Long pageView;
    /**
     * 关注数
     */
    private Long attention;
    /**
     * 平均评分
     */
    private Double avgLevel;

    /**
     * constructors
     */

    public Book() {

    }

    public Book(String name, String picture, String introduction) {
        this.name = name;
        this.picture = picture;
        this.introduction = introduction;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Long getPageView() {
        return pageView;
    }

    public void setPageView(Long pageView) {
        this.pageView = pageView;
    }

    public Long getAttention() {
        return attention;
    }

    public void setAttention(Long attention) {
        this.attention = attention;
    }

    public Double getAvgLevel() {
        return avgLevel;
    }

    public void setAvgLevel(Double avgLevel) {
        this.avgLevel = avgLevel;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", picture='" + picture + '\'' +
                ", introduction='" + introduction + '\'' +
                ", pageView=" + pageView +
                ", attention=" + attention +
                ", avgLevel=" + avgLevel +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Book book = (Book) o;
        return id.equals(book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
