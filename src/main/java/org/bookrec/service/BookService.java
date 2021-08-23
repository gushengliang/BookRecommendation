package org.bookrec.service;

import org.bookrec.entity.Book;
import org.bookrec.entity.vo.ResultVo;

import java.util.List;

/**
 * BookService
 *
 * @author a1311
 */
public interface BookService {

    /**
     * 根据参考书id获取参考书参数
     *
     * @param id 参考书id
     * @return resultVo
     * @throws Exception 异常
     */
    ResultVo<?> selectBookById(Long id) throws Exception;

    /**
     * 新增参考书
     *
     * @param book 参考书
     * @return resultVo
     * @throws Exception 异常
     */
    ResultVo<?> addBook(Book book) throws Exception;

    /**
     * 编辑参考书
     *
     * @param book 参考书
     * @return resultVo
     * @throws Exception 异常
     */
    ResultVo<?> modifyBook(Book book) throws Exception;

    /**
     * 删除参考书
     *
     * @param bookId 参考书id
     * @return resultVo
     * @throws Exception 异常
     */
    ResultVo<?> deleteBook(Long bookId) throws Exception;

    /**
     * 添加收藏
     *
     * @param studentId 学生id
     * @param bookId    书本id
     * @return resultVo
     * @throws Exception 异常
     */
    ResultVo<?> addCollection(Long studentId, Long bookId) throws Exception;

    /**
     * 取消收藏
     *
     * @param studentId 学生id
     * @param bookId    书本id
     * @return resultVo
     * @throws Exception 异常
     */
    ResultVo<?> removeCollection(Long studentId, Long bookId) throws Exception;

    /**
     * 判断该参考书是否被该学生收藏
     *
     * @param studentId 学生id
     * @param bookId    书本id
     * @return resultVo
     * @throws Exception 异常
     */
    ResultVo<?> isCollected(Long studentId, Long bookId) throws Exception;

    /**
     * 根据课程id查询参考书列表
     *
     * @param id 课程id
     * @return resultVo
     * @throws Exception 异常
     */
    ResultVo<?> searchBooksByCourseId(Long id) throws Exception;

    /**
     * 根据学生id查询参考书列表（查询收藏列表）
     *
     * @param id 学生id
     * @return resultVo
     * @throws Exception 异常
     */
    ResultVo<?> viewCollections(Long id) throws Exception;

    /**
     * 查询所有书籍列表
     *
     * @return 书籍列表
     * @throws Exception 异常
     */
    ResultVo<?> selectAllBooks() throws Exception;

    /**
     * 查询未审核书籍列表
     *
     * @return 未审核书籍列表
     * @throws Exception 异常
     */
    ResultVo<?> selectUnexaminedBooks() throws Exception;

    /**
     * 通过待审核书籍
     *
     * @param id id
     * @return 执行结果
     * @throws Exception 异常
     */
    ResultVo<?> passBook(String id) throws Exception;

    /**
     * 驳回待审核书籍
     *
     * @param id id
     * @return 执行结果
     * @throws Exception 异常
     */
    ResultVo<?> unPassBook(String id) throws Exception;

    /**
     * 更新参考书浏览量
     *
     * @param id 参考书id
     * @return resultVo
     * @throws Exception 异常
     */
    @Deprecated
    ResultVo<?> updatePageView(Long id) throws Exception;

    /**
     * 浏览量排名
     * @return resultVo
     * @throws Exception 异常
     */
    ResultVo<?> orderByPageView() throws Exception;

    /**
     * 关注数排名
     * @return resultVo
     * @throws Exception 异常
     */
    ResultVo<?> orderByAttention() throws Exception;

    /**
     * 平均分排名
     * @return resultVo
     * @throws Exception 异常
     */
    ResultVo<?> orderByAvgLevel() throws Exception;

    /**
     * 推荐参考书
     * @param majorId 专业id
     * @return 参考书列表
     * @throws Exception 异常
     */
    ResultVo<?> recommendBook(Long majorId) throws Exception;

    /**
     * 根据课程id列表和关键词查找参考书
     *
     * @param majors 专业id列表
     * @param courses 课程id列表
     * @param keyword 关键词
     * @return ResultVo
     * @throws Exception 异常
     */
    ResultVo<?> selectByMajorAndCourseAndKeyword(String[] majors, String[] courses, String keyword) throws Exception;
}
