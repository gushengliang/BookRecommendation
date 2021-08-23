package org.bookrec.dao;

import org.bookrec.entity.Book;

import java.util.List;

/**
 * BookDao
 *
 * @author a1311
 */
public interface BookDao extends BaseDao<Book> {
    /**
     * 查找收藏参考书
     *
     * @param studentId 学生id
     * @param bookId    参考书id
     * @return 返回查询到的记录数
     * @throws Exception 异常
     */
    Integer selectCount(Long studentId, Long bookId) throws Exception;

    /**
     * 收藏参考书
     *
     * @param studentId 学生id
     * @param bookId    参考书id
     * @return true or false
     * @throws Exception 异常
     */
    boolean insert(Long studentId, Long bookId) throws Exception;

    /**
     * 取消收藏参考书
     *
     * @param studentId 学生id
     * @param bookId    参考书id
     * @return true or false
     * @throws Exception 异常
     */
    boolean delete(Long studentId, Long bookId) throws Exception;

    /**
     * 如果删除了该参考书，会同时将该书从学生的收藏列表中移除
     *
     * @param bookId 参考书id
     * @return true or false
     * @throws Exception 异常
     */
    boolean deleteByBookId(Long bookId) throws Exception;

    /**
     * 根据学生id查找收藏的参考书id
     *
     * @param id 学生id
     * @return bookList
     * @throws Exception 异常
     */
    List<Long> selectBookIdListByStudentId(Long id) throws Exception;

    /**
     * 根据课程id查询其参考书id列表
     *
     * @param id 课程id
     * @return 参考书id列表
     * @throws Exception 异常
     */
    List<Long> selectBookIdListByCourseId(Long id) throws Exception;

    /**
     * 更新浏览量（浏览量+1操作）
     *
     * @param id 参考书id
     * @return 更新结果
     * @throws Exception 异常
     */
    boolean updatePageView(Long id) throws Exception;

    /**
     * 增加关注数（关注数+1操作）
     *
     * @param id 参考书id
     * @return 更新结果
     * @throws Exception 异常
     */
    boolean increaseAttention(Long id) throws Exception;

    /**
     * 减少关注数（关注数-1操作）
     *
     * @param id 参考书id
     * @return 更新结果
     * @throws Exception 异常
     */
    boolean decreaseAttention(Long id) throws Exception;

    /**
     * 按照收藏数排序
     * @return bookList
     * @throws Exception 异常
     */
    List<Book> orderByPageView() throws Exception;

    /**
     * 按照关注数排序
     * @return bookList
     * @throws Exception 异常
     */
    List<Book> orderByAttention() throws Exception;

    /**
     * 按照评分排序
     * @return bookList
     * @throws Exception 异常
     */
    List<Book> orderByAvgLevel() throws Exception;


}
