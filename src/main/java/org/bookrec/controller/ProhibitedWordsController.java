package org.bookrec.controller;

import org.bookrec.entity.ProhibitedWord;
import org.bookrec.entity.School;
import org.bookrec.entity.vo.ResultVo;
import org.bookrec.service.ProhibitedWordService;
import org.bookrec.service.impl.ProhibitedWordServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jzt
 */
@WebServlet(value = {"/prohibitedWord"})
public class ProhibitedWordsController extends BaseController {
    /**
     * 注入service
     */
    private final ProhibitedWordService prohibitedWordService = new ProhibitedWordServiceImpl();

    /**
     * 获取全部违禁词数据
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void getAllProhibitedWords(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //调用service
        ResultVo<?> resultVo = prohibitedWordService.getAllWord();
        //响应结果
        responseAsJson(resultVo, resp);
    }

    /**
     * 添加违禁词
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void creatProhibitedWord(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        //调用service方法
        ResultVo<?> resultVo = prohibitedWordService.addWord(req.getParameter("word"));
        //响应结果
        responseAsJson(resultVo, resp);

    }

    /**
     * 删除违禁词
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void deleteProhibitedWord(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        //调用service方法
        ResultVo<?> resultVo = prohibitedWordService.deleteWord(req.getParameter("id"));
        //响应结果
        responseAsJson(resultVo, resp);

    }
}
