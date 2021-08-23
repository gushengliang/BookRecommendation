package org.bookrec.service.impl;

import org.apache.log4j.Logger;
import org.bookrec.common.enums.impl.ErrorCode;
import org.bookrec.dao.ProhibitedWordDao;
import org.bookrec.dao.impl.ProhibitedWordDaoImpl;
import org.bookrec.entity.ProhibitedWord;
import org.bookrec.entity.vo.ResultVo;
import org.bookrec.service.ProhibitedWordService;
import org.bookrec.utils.JdbcUtil;
import org.bookrec.utils.ResultVoUtil;

import java.sql.SQLException;
import java.util.List;

/**
 * 违禁词Service
 * @author jzt
 */
public class ProhibitedWordServiceImpl implements ProhibitedWordService {
    /**
     * 日志管理
     */
    private static final Logger LOG = Logger.getLogger(JdbcUtil.class);
    /**
     * 注入Dao
     */
    private final ProhibitedWordDao prohibitedWordDao=new ProhibitedWordDaoImpl();

    @Override
    public ResultVo<?> addWord(String word) throws Exception {
        try {
            JdbcUtil.getConnection();
            ProhibitedWord prohibitedWord=new ProhibitedWord();
            prohibitedWord.setContent(word);
            boolean res=prohibitedWordDao.insert(prohibitedWord);
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
    public ResultVo<?> deleteWord(String id) throws Exception {
        try {
            JdbcUtil.getConnection();
            boolean res=prohibitedWordDao.delete(id);
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
    public ResultVo<?> getAllWord() throws Exception {
        try {
            JdbcUtil.getConnection();
            List<ProhibitedWord> prohibitedWords=prohibitedWordDao.selectBySelective(new ProhibitedWord());
            return ResultVoUtil.success(prohibitedWords);
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            JdbcUtil.rollbackTransaction();
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }
}
