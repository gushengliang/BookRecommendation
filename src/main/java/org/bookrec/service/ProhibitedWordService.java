package org.bookrec.service;

import org.bookrec.entity.vo.ResultVo;

/**
 * ProhibitedWordService
 * 违禁词service接口
 * @author a1311
 */
public interface ProhibitedWordService {
    /**
     * 添加违禁词
     * @return 执行结果
     * @param word
     * @throws Exception
     */
    ResultVo<?> addWord(String word) throws Exception;

    /**
     * 删除违禁词
     * @return 执行结果
     * @param id
     * @throws Exception
     */
    ResultVo<?> deleteWord(String id) throws Exception;

    /**
     * 获取全部违禁词
     * @return
     * @throws Exception
     */
    ResultVo<?> getAllWord()throws Exception;
}
