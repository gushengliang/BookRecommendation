package org.bookrec.service.impl;

import org.apache.log4j.Logger;
import org.bookrec.common.enums.impl.ErrorCode;
import org.bookrec.dao.CourseDao;
import org.bookrec.dao.MajorDao;
import org.bookrec.dao.SchoolDao;
import org.bookrec.dao.impl.CourseDaoImpl;
import org.bookrec.dao.impl.MajorDaoImpl;
import org.bookrec.dao.impl.SchoolDaoImpl;
import org.bookrec.entity.Major;
import org.bookrec.entity.School;
import org.bookrec.entity.vo.ResultVo;
import org.bookrec.service.CourseService;
import org.bookrec.service.MajorService;
import org.bookrec.utils.JdbcUtil;
import org.bookrec.utils.ResultVoUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MajorServiceImpl implements MajorService {
    /**
     * 日志管理
     */
    private static final Logger LOG = Logger.getLogger(JdbcUtil.class);
    /**
     * 注入Dao
     */
    private final MajorDao majorDao=new MajorDaoImpl();
    private final CourseService courseService=new CourseServiceImpl();

    @Override
    public synchronized ResultVo<?> getAllMajor() throws Exception {
        try {
            JdbcUtil.getConnection();
            List<Major> majors=majorDao.selectBySelective(new Major());
            int i=0;
            while(i<majors.size()){
                majors.get(i).setCourseList(courseService.selectByMajorId(majors.get(i).getId()));
                i++;
            }
            return ResultVoUtil.success(majors);
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }

    @Override
    public List<Major> selectBySchoolId(Long SchoolId) throws Exception {
        JdbcUtil.getConnection();
        List<Long> majorIds=majorDao.selectMajorIdListBySchoolId(SchoolId);
        List<Major> majors = new ArrayList<>();
        int i=0;
        while(i<majorIds.size()){
            majors.add(majorDao.selectById(majorIds.get(i)));
            i++;
        }
        JdbcUtil.close();
        return majors;

    }


    @Override
    public ResultVo<?> updataMajor(Major major) throws Exception {
        try {
            JdbcUtil.getConnection();
            boolean res=majorDao.update(major);
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
    public ResultVo<?> addMajor(Major major) throws Exception {
        try {
            JdbcUtil.getConnection();
            boolean res=majorDao.insert(major);
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
    public ResultVo<?> addcourse(String majorid, String courseid) throws Exception {
        try {
            JdbcUtil.getConnection();
            long Courseid = Long.parseLong(courseid);
            long Majorid = Long.parseLong(majorid);
            boolean res=majorDao.insert(Majorid,Courseid);
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
    public ResultVo<?> deletecourse(String majorid, String courseid) throws Exception {
        try {
            JdbcUtil.getConnection();
            long Courseid = Long.parseLong(courseid);
            long Majorid = Long.parseLong(majorid);
            boolean res=majorDao.delete(Majorid,Courseid);
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
    public synchronized ResultVo<?> getMajors() throws Exception {
        try {
            JdbcUtil.getConnection();
            List<Major> majors=majorDao.selectBySelective(new Major());
            return ResultVoUtil.success(majors);
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }
}
