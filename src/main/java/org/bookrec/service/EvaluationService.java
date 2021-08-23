package org.bookrec.service;

import org.bookrec.entity.Evaluation;
import org.bookrec.entity.vo.ResultVo;

/**
 * 评论service接口
 * EvaluationService
 *
 * @author a1311
 */
public interface EvaluationService {
    /**
     * 寻找所有未审核的评论
     *
     * @return 评论列表
     * @throws Exception
     */
    ResultVo<?> getUnexaminedEvaluation() throws Exception;

    /**
     * 设置评论合格
     *
     * @param id
     * @return 执行结果
     * @throws Exception
     */
    ResultVo<?> passEvaluation(String id) throws Exception;

    /**
     * 设置评论不合格
     *
     * @param id
     * @return 执行结果
     * @throws Exception
     */
    ResultVo<?> unpassEvaluation(String id) throws Exception;

    /**
     * 上传评论
     *
     * @param evaluation 评论
     * @return resultVo
     * @throws Exception 异常
     */
    ResultVo<?> uploadEvaluation(Evaluation evaluation) throws Exception;

    /**
     * 根据参考书id查找所有评论
     *
     * @param bookId 参考书id
     * @param studentId 学生id
     * @return 评论列表
     * @throws Exception 异常
     */
    ResultVo<?> selectEvaluationByBookId(Long bookId, Long studentId) throws Exception;
}
