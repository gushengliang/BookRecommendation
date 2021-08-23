package org.bookrec.controller;

import org.bookrec.common.enums.impl.ErrorCode;
import org.bookrec.entity.Student;
import org.bookrec.entity.User;
import org.bookrec.entity.vo.ResultVo;
import org.bookrec.service.StudentService;
import org.bookrec.service.UserService;
import org.bookrec.service.impl.StudentServiceImpl;
import org.bookrec.service.impl.UserServiceImpl;
import org.bookrec.utils.ResultVoUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * IndexController
 *
 * @author a1311
 */
@WebServlet("/index")
public class IndexController extends BaseController {

    private final UserService userService = new UserServiceImpl();
    private final StudentService studentService = new StudentServiceImpl();

    /**
     * 登录
     *
     * @param req  request
     * @param resp response
     * @throws Exception 异常
     */
    public void login(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //封装对象
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        //调用service方法
        ResultVo<?> resultVo = userService.signIn(user);
        if (resultVo.getData() != null) {
            ResultVo<?> userInfo = studentService.selectStudentByUsername(username);
            //获取session
            HttpSession session = req.getSession();
            //设置session
            session.setAttribute("loginInfo", user);
            session.setAttribute("userInfo", userInfo.getData());
            //设置session有效时长
            session.setMaxInactiveInterval(30 * 60);
        }
        //响应结果
        responseAsJson(resultVo, resp);
    }

    /**
     * 获取登录信息
     *
     * @param req  request
     * @param resp response
     * @throws Exception 异常
     */
    public void getUserInfo(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        ResultVo<?> resultVo;
        try {
            Student student = (Student) session.getAttribute("userInfo");
            if (student != null) {
                resultVo = ResultVoUtil.success(student);
            } else {
                resultVo = ResultVoUtil.fail(ErrorCode.TOKEN_ERROR);
            }
        } catch (Exception e) {
            resultVo = ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        }
        //响应结果
        responseAsJson(resultVo, resp);
    }

    /**
     * 退出
     *
     * @param req  request
     * @param resp response
     * @throws Exception 异常
     */
    public void logout(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ResultVo<?> resultVo;
        HttpSession session = req.getSession();
        try {
            session.removeAttribute("userInfo");
            resultVo = ResultVoUtil.success(null);
        } catch (Exception e) {
            resultVo = ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        }
        responseAsJson(resultVo, resp);
    }
}
