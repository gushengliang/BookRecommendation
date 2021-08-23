package org.bookrec.controller;

import org.bookrec.entity.vo.ResultVo;
import org.bookrec.service.UnexaminedBookService;
import org.bookrec.service.impl.UnexaminedBookServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jzt
 */
@WebServlet(value = {"/unexaminedBook"})
public class UnexaminedBookController extends BaseController {
    /**
     * 添加service
     */
    private final UnexaminedBookService unexaminedBookService = new UnexaminedBookServiceImpl();

    /**
     * 获取全部未审核书籍
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void getAllUnexaminedBooks(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ResultVo<?> resultVo = unexaminedBookService.getAllUnexaminedBooks();
        responseAsJson(resultVo, resp);
    }

}
