package org.bookrec.controller;

import org.bookrec.entity.Course;
import org.bookrec.entity.School;
import org.bookrec.entity.vo.ResultVo;
import org.bookrec.service.CourseService;
import org.bookrec.service.impl.CourseServiceImpl;
import org.bookrec.utils.CastUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 课程Controller
 *
 * @author jzt
 */
@WebServlet(value = {"/course"})
public class CourseController extends BaseController {
    /**
     * 注入Service
     */
    private final CourseService courseService = new CourseServiceImpl();

    /**
     * 获取全部课程信息
     *
     * @param req  request
     * @param resp response
     * @throws Exception 异常
     */
    public void getAllCourses(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //调用service
        ResultVo<?> resultVo = courseService.getAllCourses();
        //响应结果
        responseAsJson(resultVo, resp);

    }

    /**
     * 查看关注
     *
     * @param req  request
     * @param resp response
     * @throws Exception 异常
     */
    public void viewAttention(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Long studentId = CastUtil.castLong(req.getParameter("studentId"));
        //调用service
        ResultVo<?> resultVo = courseService.viewAttention(studentId);
        //响应结果
        responseAsJson(resultVo, resp);
    }

    /**
     * 添加关注
     *
     * @param req  request
     * @param resp response
     * @throws Exception 异常
     */
    public void addAttention(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Long studentId = CastUtil.castLong(req.getParameter("studentId"));
        Long courseId = CastUtil.castLong(req.getParameter("courseId"));
        //调用service
        ResultVo<?> resultVo = courseService.addAttention(studentId, courseId);
        //响应结果
        responseAsJson(resultVo, resp);
    }

    /**
     * 取消关注
     *
     * @param req  request
     * @param resp response
     * @throws Exception 异常
     */
    public void removeAttention(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Long studentId = CastUtil.castLong(req.getParameter("studentId"));
        Long courseId = CastUtil.castLong(req.getParameter("courseId"));
        //调用service
        ResultVo<?> resultVo = courseService.removeAttention(studentId, courseId);
        //响应结果
        responseAsJson(resultVo, resp);
    }

    /**
     * 添加课程信息
     *
     * @param req request
     * @param resp response
     * @throws Exception 异常
     */
    public void creatCourse(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Course course = new Course();
        course.setName(req.getParameter("name"));
        //调用service方法
        ResultVo<?> resultVo = courseService.addCourse(course);
        //响应结果
        responseAsJson(resultVo, resp);

    }

    /**
     * 更新课程信息
     *
     * @param req request
     * @param resp response
     * @throws Exception 异常
     */
    public void updateCourse(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Course course = new Course();
        course.setName(req.getParameter("name"));
        course.setId(Long.parseLong(req.getParameter("id")));

        //调用service方法
        ResultVo<?> resultVo = courseService.updateCourse(course);
        //响应结果
        responseAsJson(resultVo, resp);
    }
    /**
     * 根据选定的专业信息查找课程信息
     *
     * @param req request
     * @param resp response
     * @throws Exception 异常
     */
    public void getCoursesByMajors(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String[] majors = req.getParameterValues("majors");
        ResultVo<?> resultVo = courseService.getCoursesByMajors(majors);
        responseAsJson(resultVo, resp);
    }

}
