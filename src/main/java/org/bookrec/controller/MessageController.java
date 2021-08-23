package org.bookrec.controller;

import org.bookrec.entity.Message;
import org.bookrec.entity.MessageBox;
import org.bookrec.entity.vo.ResultVo;
import org.bookrec.service.MajorService;
import org.bookrec.service.MessageService;
import org.bookrec.service.impl.MajorServiceImpl;
import org.bookrec.service.impl.MessageServiceImpl;
import org.bookrec.utils.CastUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 广播信息controller
 *
 * @author jzt
 */
@WebServlet(value = {"/message"})
public class MessageController extends BaseController {
    /**
     * 注入service
     */
    private final MessageService messageService = new MessageServiceImpl();

    /**
     * 发送广播信息
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void sendRadio(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取参数
        Message message = new Message();
        message.setContent(req.getParameter("message"));
        message.setTitle(req.getParameter("title"));
        message.setType(0);
        //调用service
        ResultVo<?> resultVo = messageService.sendinform(message);
        //响应结果
        responseAsJson(resultVo, resp);

    }

    public void viewMessage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取参数
        Long studentId = CastUtil.castLong(req.getParameter("studentId"));
        Integer isRead = CastUtil.castInt(req.getParameter("isRead"));
        //调用service
        ResultVo<?> resultVo = messageService.viewMessage(studentId, isRead);
        //响应结果
        responseAsJson(resultVo, resp);
    }

    public void getRead(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取参数
        Long studentId = CastUtil.castLong(req.getParameter("studentId"));
        Long messageId = CastUtil.castLong(req.getParameter("messageId"));
        //调用service
        ResultVo<?> resultVo = messageService.getRead(studentId, messageId);
        //响应结果
        responseAsJson(resultVo, resp);
    }

    public void setNotice(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String bookName = req.getParameter("bookName");
        Long studentId = CastUtil.castLong(req.getParameter("studentId"));
        //调用service
        ResultVo<?> resultVo = messageService.setNotice(bookName, studentId);
        //响应结果
        responseAsJson(resultVo, resp);
    }
}
