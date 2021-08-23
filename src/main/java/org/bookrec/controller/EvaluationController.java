package org.bookrec.controller;

import org.bookrec.entity.Evaluation;
import org.bookrec.entity.vo.ResultVo;
import org.bookrec.service.EvaluationService;
import org.bookrec.service.impl.EvaluationServiceImpl;
import org.bookrec.utils.CastUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jzt
 */
@WebServlet(value = {"/evaluation"})
public class EvaluationController extends BaseController {
    /**
     * 注入service
     */
    private final EvaluationService evaluationService = new EvaluationServiceImpl();

    /**
     * 获取全部未审核的评论
     *
     * @param req  request
     * @param resp response
     * @throws Exception 异常
     */
    public void getUnCheckedEvaluation(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //调用service方法
        ResultVo<?> resultVo = evaluationService.getUnexaminedEvaluation();
        //响应结果
        responseAsJson(resultVo, resp);
    }

    /**
     * 审核通过评论
     *
     * @param req  request
     * @param resp response
     * @throws Exception 异常
     */
    public void approvedEvaluation(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //调用service方法
        ResultVo<?> resultVo = evaluationService.passEvaluation(req.getParameter("id"));
        //响应结果
        responseAsJson(resultVo, resp);
    }

    /**
     * 标注违规评论
     *
     * @param req  request
     * @param resp response
     * @throws Exception 异常
     */
    public void tagEvaluation(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //调用service方法
        ResultVo<?> resultVo = evaluationService.unpassEvaluation(req.getParameter("id"));
        //响应结果
        responseAsJson(resultVo, resp);
    }

    /**
     * 上传评论
     *
     * @param req  request
     * @param resp response
     * @throws Exception 异常
     */
    public void uploadEvaluation(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取参数
        Long bookId = CastUtil.castLong(req.getParameter("bookId"));
        Long studentId = CastUtil.castLong(req.getParameter("studentId"));
        Integer level = CastUtil.castInt(req.getParameter("level"));
        String comment = req.getParameter("comment");
        //封装对象
        Evaluation evaluation = new Evaluation(bookId, studentId, level, comment);
        //获取ResultVo
        ResultVo<?> resultVo = evaluationService.uploadEvaluation(evaluation);
        //响应结果
        responseAsJson(resultVo, resp);
    }

    /**
     * 根据参考书id查找所有评论
     *
     * @param req  request
     * @param resp response
     * @throws Exception 异常
     */
    public void selectEvaluationByBookIdAndStudentId(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取参数
        Long bookId = CastUtil.castLong(req.getParameter("bookId"));
        Long studentId = CastUtil.castLong(req.getParameter("studentId"));
        //获取ResultVo
        ResultVo<?> resultVo = evaluationService.selectEvaluationByBookId(bookId, studentId);
        //响应结果
        responseAsJson(resultVo, resp);
    }

}
