package org.bookrec.dao;

import org.bookrec.entity.Evaluation;

import java.util.List;

/**
 * EvaluationDao
 *
 * @author a1311
 */
public interface EvaluationDao extends BaseDao<Evaluation> {
    /**
     * 根据参考书id查询平均分
     * @param bookId 参考书id
     * @return 平均分
     * @throws Exception 异常
     */
    Double getAvgLevelByBookId(Long bookId) throws Exception;
}
