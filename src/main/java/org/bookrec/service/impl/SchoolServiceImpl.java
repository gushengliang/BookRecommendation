package org.bookrec.service.impl;

import org.apache.log4j.Logger;
import org.bookrec.common.enums.impl.ErrorCode;
import org.bookrec.dao.SchoolDao;
import org.bookrec.dao.impl.SchoolDaoImpl;
import org.bookrec.entity.Major;
import org.bookrec.entity.School;
import org.bookrec.entity.Student;
import org.bookrec.entity.dto.StudentDto;
import org.bookrec.entity.vo.ResultVo;
import org.bookrec.service.MajorService;
import org.bookrec.service.SchoolService;
import org.bookrec.utils.JdbcUtil;
import org.bookrec.utils.ResultVoUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SchoolServiceImpl implements SchoolService {
    /**
     * 日志管理
     */
    private static final Logger LOG = Logger.getLogger(JdbcUtil.class);
    /**
     * 注入Dao
     */
    private final SchoolDao schoolDao=new SchoolDaoImpl();

    /**
     *注入Service
     */
    MajorService majorService=new MajorServiceImpl();

    @Override
    public ResultVo<?> getAllSchool() throws Exception {
        try {
            JdbcUtil.getConnection();
            List<School> schools=schoolDao.selectBySelective(new School());
            int i=0;
            while(i<schools.size()){
                schools.get(i).setMajorList(majorService.selectBySchoolId(schools.get(i).getId()));
                i++;
            }
            return ResultVoUtil.success(schools);
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            JdbcUtil.rollbackTransaction();
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }

    @Override
    public ResultVo<?> updataSchool(School school) throws Exception {
        try {
            JdbcUtil.getConnection();
            boolean res=schoolDao.update(school);
            return ResultVoUtil.success(res);
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            JdbcUtil.rollbackTransaction();
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }

    @Override
    public ResultVo<?> addSchool(School school) throws Exception {
        try {
            JdbcUtil.getConnection();
            boolean res=schoolDao.insert(school);
            return ResultVoUtil.success(res);
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            JdbcUtil.rollbackTransaction();
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }

    @Override
    public ResultVo<?> addmajor(String schoolid, String majorid) throws Exception {
        try {
            JdbcUtil.getConnection();
            long Schoolid = Long.parseLong(schoolid);
            long Majorid = Long.parseLong(majorid);
            boolean res=schoolDao.insert(Schoolid,Majorid);
            return ResultVoUtil.success(res);
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            JdbcUtil.rollbackTransaction();
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }

    @Override
    public ResultVo<?> deletemajor(String schoolid, String majorid) throws Exception {
        try {
            JdbcUtil.getConnection();
            long Schoolid = Long.parseLong(schoolid);
            long Majorid = Long.parseLong(majorid);
            boolean res=schoolDao.delete(Schoolid,Majorid);
            return ResultVoUtil.success(res);
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            JdbcUtil.rollbackTransaction();
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }
}
