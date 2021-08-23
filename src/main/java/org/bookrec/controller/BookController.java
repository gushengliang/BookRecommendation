package org.bookrec.controller;

import org.bookrec.entity.Book;
import org.bookrec.entity.vo.ResultVo;
import org.bookrec.service.BookService;
import org.bookrec.service.impl.BookServiceImpl;
import org.bookrec.utils.CastUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * BookController
 *
 * @author a1311
 */
@WebServlet("/book")
public class BookController extends BaseController {
    /**
     * 注入service
     */
    private final BookService bookService = new BookServiceImpl();

    /**
     * 更新书籍信息
     *
     * @param req  request
     * @param resp response
     * @throws Exception 异常
     */
    public void updateBook(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Book book = new Book();
        book.setName(req.getParameter("name"));
        book.setId(Long.parseLong(req.getParameter("id")));
        book.setIntroduction(req.getParameter("introduction"));

        ResultVo<?> resultVo = bookService.modifyBook(book);
        responseAsJson(resultVo, resp);
    }

    /**
     * 审核成功书籍
     *
     * @param req  request
     * @param resp response
     * @throws Exception 异常
     */
    public void approvedBooks(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ResultVo<?> resultVo = bookService.passBook(req.getParameter("id"));
        responseAsJson(resultVo, resp);
    }

    /**
     * 驳回书籍
     *
     * @param req  request
     * @param resp response
     * @throws Exception 异常
     */
    public void rejectBooks(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ResultVo<?> resultVo = bookService.unPassBook(req.getParameter("id"));
        responseAsJson(resultVo, resp);
    }

    /**
     * 获取全部参考书
     *
     * @param req  request
     * @param resp response
     * @throws Exception 异常
     */
    public void selectAllBooks(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ResultVo<?> resultVo = bookService.selectAllBooks();
        responseAsJson(resultVo, resp);
    }

    /**
     * 根据浏览量排序
     *
     * @param req  request
     * @param resp response
     * @throws Exception 异常
     */
    public void orderByPageView(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ResultVo<?> resultVo = bookService.orderByPageView();
        responseAsJson(resultVo, resp);
    }

    /**
     * 根据关注数排序
     *
     * @param req  request
     * @param resp response
     * @throws Exception 异常
     */
    public void orderByAttention(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ResultVo<?> resultVo = bookService.orderByAttention();
        responseAsJson(resultVo, resp);
    }

    /**
     * 根据平均评分排序
     *
     * @param req  request
     * @param resp response
     * @throws Exception 异常
     */
    public void orderByAvgLevel(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ResultVo<?> resultVo = bookService.orderByAvgLevel();
        responseAsJson(resultVo, resp);
    }

    /**
     * 获取个性推荐列表
     *
     * @param req  request
     * @param resp response
     * @throws Exception 异常
     */
    public void getRecommendBookList(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Long majorId = CastUtil.castLong(req.getParameter("majorId"));
        ResultVo<?> resultVo = bookService.recommendBook(majorId);
        responseAsJson(resultVo, resp);
    }

    /**
     * 条件查询（课程+关键字）
     *
     * @param req  request
     * @param resp response
     * @throws Exception 异常
     */
    public void selectByMajorAndCourseAndKeyword(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String[] majors = req.getParameterValues("majors");
        String[] courses = req.getParameterValues("courses");
        String keyword = req.getParameter("keyword");
        ResultVo<?> resultVo = bookService.selectByMajorAndCourseAndKeyword(majors, courses, keyword);
        responseAsJson(resultVo, resp);
    }

    /**
     * 根据参考书id获取参考书详情
     *
     * @param req  request
     * @param resp response
     * @throws Exception 异常
     */
    public void getView(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Long bookId = CastUtil.castLong(req.getParameter("bookId"));
        ResultVo<?> resultVo = bookService.selectBookById(bookId);
        responseAsJson(resultVo, resp);
    }

    /**
     * 查看收藏
     *
     * @param req  request
     * @param resp response
     * @throws Exception 异常
     */
    public void viewCollection(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Long studentId = CastUtil.castLong(req.getParameter("studentId"));
        ResultVo<?> resultVo = bookService.viewCollections(studentId);
        responseAsJson(resultVo, resp);
    }

    /**
     * 添加收藏
     *
     * @param req  request
     * @param resp response
     * @throws Exception 异常
     */
    public void addCollection(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Long studentId = CastUtil.castLong(req.getParameter("studentId"));
        Long bookId = CastUtil.castLong(req.getParameter("bookId"));
        ResultVo<?> resultVo = bookService.addCollection(studentId, bookId);
        responseAsJson(resultVo, resp);
    }

    /**
     * 取消收藏
     *
     * @param req  request
     * @param resp response
     * @throws Exception 异常
     */
    public void removeCollection(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Long studentId = CastUtil.castLong(req.getParameter("studentId"));
        Long bookId = CastUtil.castLong(req.getParameter("bookId"));
        ResultVo<?> resultVo = bookService.removeCollection(studentId, bookId);
        responseAsJson(resultVo, resp);
    }

    /**
     * 根据参考书id和学生id判断该参考书是否已被该学生收藏
     *
     * @param req  request
     * @param resp response
     * @throws Exception 异常
     */
    public void isCollected(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Long studentId = CastUtil.castLong(req.getParameter("studentId"));
        Long bookId = CastUtil.castLong(req.getParameter("bookId"));
        ResultVo<?> resultVo = bookService.isCollected(studentId, bookId);
        responseAsJson(resultVo, resp);
    }

    /**
     * 浏览量+1
     *
     * @param req  request
     * @param resp response
     * @throws Exception 异常
     */
    @Deprecated
    public void pageViewPlusOne(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Long bookId = CastUtil.castLong(req.getParameter("bookId"));
        ResultVo<?> resultVo = bookService.updatePageView(bookId);
        responseAsJson(resultVo, resp);
    }

    /**
     * 获取全部参考书（之前已经有同样的方法）
     *
     * @param req  request
     * @param resp response
     * @throws Exception 异常
     */
    @Deprecated
    public void getAllBooks(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ResultVo<?> resultVo = bookService.selectAllBooks();
        responseAsJson(resultVo, resp);
    }
}
