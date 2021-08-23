package org.bookrec.service;

import org.bookrec.entity.vo.ResultVo;

/**
 * UnexaminedBookService
 *
 * @author a1311
 */
public interface UnexaminedBookService {
    /**
     * 返回所有未审核的书籍
     * @return 书籍列表
     * @throws Exception
     */
    ResultVo<?> getAllUnexaminedBooks()throws Exception;
}
