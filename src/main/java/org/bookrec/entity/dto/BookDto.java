package org.bookrec.entity.dto;

import org.bookrec.entity.Book;

/**
 * BookDto
 *
 * @author a1311
 */
public class BookDto extends Book {
    /**
     * 是否被收藏
     */
    private Integer isCollected;

    public BookDto() {

    }

    public Integer getIsCollected() {
        return isCollected;
    }

    public void setIsCollected(Integer isCollected) {
        this.isCollected = isCollected;
    }
}
