package org.bookrec.controller;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.bookrec.common.enums.impl.ErrorCode;
import org.bookrec.entity.vo.ResultVo;
import org.bookrec.utils.ResultVoUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * BaseController
 *
 * @author a1311
 */
public class BaseController extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(BaseController.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        //获取方法的名字
        String action = req.getParameter("action");
        //通过反射调用方法
        try {
            //把方法名转换成方法
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            //给私有方法授权
            method.setAccessible(true);
            //调用方法
            method.invoke(this, req, resp);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            ResultVo<?> resultVo = ResultVoUtil.fail(ErrorCode.SYSTEM_BUSY);
            responseAsJson(resultVo, resp);
        }

    }

    protected void responseAsJson(ResultVo<?> resultVo, HttpServletResponse resp) throws IOException {
        String json = JSON.toJSONString(resultVo);
        resp.setContentType("application/json");
        PrintWriter pw = resp.getWriter();
        pw.write(json);
        pw.flush();
        pw.close();
    }
}
