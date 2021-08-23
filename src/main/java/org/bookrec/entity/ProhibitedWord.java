package org.bookrec.entity;

/**
 * 违禁词类
 *
 * @author a1311
 */
public class ProhibitedWord {
    /**
     * id
     */
    private Long id;
    /**
     * 内容
     */
    private String content;

    public ProhibitedWord() {

    }

    public ProhibitedWord(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ProhibitedWord{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
