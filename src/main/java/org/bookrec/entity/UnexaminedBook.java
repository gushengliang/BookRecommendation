package org.bookrec.entity;

/**
 * 待审核参考书类
 *
 * @author a1311
 */
public class UnexaminedBook {
    /**
     * id
     */
    private Long id;
    /**
     * 参考书唯一标识符
     */
    /**private String identifier;**/
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
     * 是否已过审
     */
    private Integer isExamined;

    /**
     * constructors
     */

    public UnexaminedBook() {

    }

    public UnexaminedBook(String identifier, String name, String picture, String introduction, Integer isExamined) {

        this.name = name;
        this.picture = picture;
        this.introduction = introduction;
        this.isExamined = isExamined;
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

    public Integer getIsExamined() {
        return isExamined;
    }

    public void setIsExamined(Integer isExamined) {
        this.isExamined = isExamined;
    }

    @Override
    public String toString() {
        return "UnexaminedBook{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", picture='" + picture + '\'' +
                ", introduction='" + introduction + '\'' +
                ", isExamined=" + isExamined +
                '}';
    }
}
