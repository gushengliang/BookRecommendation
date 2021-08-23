package org.bookrec.controller;

import org.bookrec.entity.Student;
import org.bookrec.entity.vo.ResultVo;
import org.bookrec.service.StudentService;
import org.bookrec.service.impl.StudentServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * StudentController
 *
 * @author jzt
 */

@WebServlet(value = {"/student"})
public class StudentController extends BaseController {
    /**
     * 注入Service
     */
    private final StudentService studentService = new StudentServiceImpl();

    /**
     * 获取全部学生信息
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void getAllStudents(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //调用service方法
        ResultVo<?> resultVo = studentService.getAllStudent();
        //响应结果
        responseAsJson(resultVo, resp);

    }

    /**
     * 创建学生信息
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void creatStudent(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Student student = new Student();
        student.setName(req.getParameter("name"));
        student.setSchoolId(Long.parseLong(req.getParameter("school")));
        student.setMajorId(Long.parseLong(req.getParameter("major")));
        student.setUsername(req.getParameter("username"));
        //调用service方法
        ResultVo<?> resultVo = studentService.addStudent(student);
        //响应结果
        responseAsJson(resultVo, resp);

    }

    /**
     * 更新学生信息
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void updateStudent(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Student student = new Student();
        student.setId(Long.parseLong(req.getParameter("id")));
        student.setName(req.getParameter("name"));
        student.setSchoolId(Long.parseLong(req.getParameter("school")));
        student.setMajorId(Long.parseLong(req.getParameter("major")));
        student.setUsername(req.getParameter("username"));
        //调用service方法
        ResultVo<?> resultVo = studentService.updateStudent(student);
        //响应结果
        responseAsJson(resultVo, resp);

    }
}
