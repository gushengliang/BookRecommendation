package org.bookrec.controller;

import org.bookrec.entity.Major;
import org.bookrec.entity.School;
import org.bookrec.entity.vo.ResultVo;
import org.bookrec.service.MajorService;
import org.bookrec.service.impl.MajorServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 专业信息countroller
 *
 * @author jzt
 */
@WebServlet(value = {"/major"})
public class MajorController extends BaseController {
    /**
     * 注入service
     */
    private final MajorService majorService = new MajorServiceImpl();

    /**
     * 获取全部专业信息
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void getAllMajors(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //调用service
        ResultVo<?> resultVo = majorService.getAllMajor();
        //响应结果
        responseAsJson(resultVo, resp);

    }

    /**
     * 创建专业信息
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void creatMajor(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Major major = new Major();
        major.setName(req.getParameter("name"));
        //调用service方法
        ResultVo<?> resultVo = majorService.addMajor(major);
        //响应结果
        responseAsJson(resultVo, resp);

    }

    /**
     * 更新学校信息
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void updateMajor(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Major major = new Major();
        major.setName(req.getParameter("name"));
        major.setId(Long.parseLong(req.getParameter("id")));

        //调用service方法
        ResultVo<?> resultVo = majorService.updataMajor(major);
        //响应结果
        responseAsJson(resultVo, resp);

    }

    /**
     * 向专业中添加课程
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void addCourse(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String majorId = req.getParameter("major");
        String course = req.getParameter("course");
        //调用service
        ResultVo<?> resultVo = majorService.addcourse(majorId, course);
        //响应结果
        responseAsJson(resultVo, resp);

    }

    /**
     * 从专业中删除课程
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void deleteCourse(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String majorId = req.getParameter("major");
        String course = req.getParameter("course");
        //调用service
        ResultVo<?> resultVo = majorService.deletecourse(majorId, course);
        //响应结果
        responseAsJson(resultVo, resp);

    }

    /**
     * 获取专业列表
     * @param req request
     * @param resp response
     * @throws Exception 异常
     */
    public void getMajors(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ResultVo<?> resultVo = majorService.getMajors();
        responseAsJson(resultVo, resp);
    }
}
