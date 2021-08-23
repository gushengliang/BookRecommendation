package org.bookrec.controller;

import org.bookrec.entity.vo.ResultVo;
import org.bookrec.service.AdminService;
import org.bookrec.service.impl.AdminServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * AdminController
 *
 * @author a1311
 */


@WebServlet(value = {"/admin"})
public class AdminController extends BaseController {

    private final AdminService adminService = new AdminServiceImpl();

    /**
     * 获取管理员信息
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void getadmin(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取参数
        String adminId = req.getParameter("adminId");
        //调用service方法
        ResultVo<?> resultVo = adminService.getAdmin(adminId);
        //响应结果
        responseAsJson(resultVo, resp);

    }

}
