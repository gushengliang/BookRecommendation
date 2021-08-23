package org.bookrec.service.impl;

import org.apache.log4j.Logger;
import org.bookrec.common.enums.impl.ErrorCode;
import org.bookrec.dao.AdminDao;
import org.bookrec.dao.impl.AdminDaoImpl;
import org.bookrec.entity.Admin;
import org.bookrec.entity.Book;
import org.bookrec.entity.vo.ResultVo;
import org.bookrec.service.AdminService;
import org.bookrec.utils.JdbcUtil;
import org.bookrec.utils.ResultVoUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * AdminServiceImpl
 *
 * @author a1311
 */
public class AdminServiceImpl implements AdminService {
    /**
     * 日志管理
     */
    private static final Logger LOG = Logger.getLogger(JdbcUtil.class);
    /**
     * 注入Dao
     */
    private final AdminDao adminDao=new AdminDaoImpl();

    /**
     * 查询管理员
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public ResultVo<?> getAdmin(String id) throws Exception {
        try {
            JdbcUtil.getConnection();
            long adminId = Long.parseLong(id);
            Admin admin=adminDao.selectById(adminId);
            return ResultVoUtil.success(admin);
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }
}
