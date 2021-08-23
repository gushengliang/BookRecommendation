package org.bookrec.service.impl;

import org.apache.log4j.Logger;
import org.bookrec.common.enums.impl.ErrorCode;
import org.bookrec.dao.BookDao;
import org.bookrec.dao.EvaluationDao;
import org.bookrec.dao.StudentDao;
import org.bookrec.dao.impl.BookDaoImpl;
import org.bookrec.dao.impl.EvaluationDaoImpl;
import org.bookrec.dao.impl.StudentDaoImpl;
import org.bookrec.entity.*;
import org.bookrec.entity.dto.EvaluationDto;
import org.bookrec.entity.dto.StudentDto;
import org.bookrec.entity.vo.ResultVo;
import org.bookrec.service.EvaluationService;
import org.bookrec.utils.JdbcUtil;
import org.bookrec.utils.ResultVoUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author jzt
 */
public class EvaluationServiceImpl implements EvaluationService {
    /**
     * 日志管理
     */
    private static final Logger LOG = Logger.getLogger(JdbcUtil.class);
    /**
     * 注入Dao
     */
    private final EvaluationDao evaluationDao = new EvaluationDaoImpl();
    private final StudentDao studentDao = new StudentDaoImpl();
    private final BookDao bookDao = new BookDaoImpl();

    @Override
    public ResultVo<?> getUnexaminedEvaluation() throws Exception {
        try {
            JdbcUtil.getConnection();
            Evaluation evaluation = new Evaluation();
            evaluation.setStatus(0);
            List<Evaluation> list = evaluationDao.selectBySelective(evaluation);
            List<EvaluationDto> evaluations = new ArrayList<>();
            for (Evaluation eva : list) {
                EvaluationDto dto = new EvaluationDto();
                Student student = studentDao.selectById(eva.getStudentId());
                Book book = bookDao.selectById(eva.getBookId());
                dto.setId(eva.getId());
                dto.setStatus(eva.getStatus());
                dto.setComment(eva.getComment());
                dto.setLevel(eva.getLevel());
                dto.setBook(book);
                dto.setStudent(student);
                evaluations.add(dto);
            }
            return ResultVoUtil.success(evaluations);
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            JdbcUtil.rollbackTransaction();
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }

    @Override
    public ResultVo<?> passEvaluation(String id) throws Exception {

        try {
            JdbcUtil.getConnection();
            Long EvaId = Long.parseLong(id);
            Evaluation evaluation = evaluationDao.selectById(EvaId);
            evaluation.setStatus(1);
            Boolean res = evaluationDao.update(evaluation);
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
    public ResultVo<?> unpassEvaluation(String id) throws Exception {
        try {
            JdbcUtil.getConnection();
            Long EvaId = Long.parseLong(id);
            Evaluation evaluation = evaluationDao.selectById(EvaId);
            evaluation.setStatus(2);
            Boolean res = evaluationDao.update(evaluation);
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
    public ResultVo<?> uploadEvaluation(Evaluation evaluation) throws Exception {
        try {
            JdbcUtil.getConnection();
            if (evaluation.getComment() == null || "".equals(evaluation.getComment())) {
                evaluation.setComment("该用户暂未撰写评论~");
            }
            boolean res = evaluationDao.insert(evaluation);
            return ResultVoUtil.success(res);
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }

    @Override
    public synchronized ResultVo<?> selectEvaluationByBookId(Long bookId, Long studentId) throws Exception {
        try {
            JdbcUtil.getConnection();
            Evaluation evaluation = new Evaluation();
            //按照参考书id查找评论
            evaluation.setBookId(bookId);
            List<Evaluation> evaluations = evaluationDao.selectBySelective(evaluation);
            //按照参考书id和学生id查找该学生的历史评论
            List<Evaluation> evaluationsForSelf = new ArrayList<>();
            if (studentId != null && studentId != 0) {
                evaluation.setStudentId(studentId);
                evaluationsForSelf = evaluationDao.selectBySelective(evaluation);
            }
            List<List<Evaluation>> list = new ArrayList<>();
            list.add(evaluationsForSelf);
            list.add(evaluations);
            return ResultVoUtil.success(list);
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }
}
