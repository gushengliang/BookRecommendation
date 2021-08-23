package org.bookrec.controller;


import org.bookrec.entity.School;
import org.bookrec.entity.Student;
import org.bookrec.entity.vo.ResultVo;
import org.bookrec.service.SchoolService;
import org.bookrec.service.impl.SchoolServiceImpl;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jzt
 */
@WebServlet(value = {"/school"})
public class SchoolController extends BaseController {
    /**
     * 注入Service
     */
    private final SchoolService schoolService = new SchoolServiceImpl();

    /**
     * 获取全部的学校信息
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void getAllSchools(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //调用service
        ResultVo<?> resultVo = schoolService.getAllSchool();
        //响应结果
        responseAsJson(resultVo, resp);

    }

    /**
     * 创建学校信息
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void creatSchool(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        School school = new School();
        school.setName(req.getParameter("name"));
        //调用service方法
        ResultVo<?> resultVo = schoolService.addSchool(school);
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
    public void updateSchool(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        School school = new School();
        school.setName(req.getParameter("name"));
        school.setId(Long.parseLong(req.getParameter("id")));

        //调用service方法
        ResultVo<?> resultVo = schoolService.updataSchool(school);
        //响应结果
        responseAsJson(resultVo, resp);

    }

    /**
     * 增加学校内专业
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void addMajor(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String schoolId = req.getParameter("school");
        String majorId = req.getParameter("major");
        System.out.print(schoolId + "剑斩天" + majorId);
        //调用service方法
        ResultVo<?> resultVo = schoolService.addmajor(schoolId, majorId);
        //响应结果
        responseAsJson(resultVo, resp);

    }

    /**
     * 删除学校内专业
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void deleteMajor(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String schoolId = req.getParameter("school");
        String majorId = req.getParameter("major");
        //调用service方法
        ResultVo<?> resultVo = schoolService.deletemajor(schoolId, majorId);
        //响应结果
        responseAsJson(resultVo, resp);

    }
}
