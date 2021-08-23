package org.bookrec.dao;

import java.util.List;

/**
 * 泛型接口
 * @param <E> 泛型
 * @author a1311
 * @version 1.0,2021-06-20
 */
public interface BaseDao<E> {
    /**
     * 插入
     * @param obj
     * @return 影响的行数
     * @throws Exception
     */
    boolean insert(E obj) throws Exception;

    /**
     * 修改
     * @param obj
     * @return 影响的行数
     * @throws Exception
     */
    boolean update(E obj) throws Exception;

    /**
     * 删除（id为String类型）
     * @param id 主键
     * @return 影响的行数
     * @throws Exception
     */
    boolean delete(String id) throws Exception;

    /**
     * 删除（id为Integer类型）
     * @param id 主键
     * @return 影响的行数
     * @throws Exception
     */
    boolean delete(Integer id) throws Exception;

    /**
     * 删除（id为Long类型）
     * @param id 主键
     * @return 影响的行数
     * @throws Exception
     */
    boolean delete(Long id) throws Exception;

    /**
     * 按id选择（id为String类型）
     * @param id 主键
     * @return 返回的结果
     * @throws Exception
     */
    E selectById(String id) throws Exception;

    /**
     * 按id选择（id为Integer类型）
     * @param id 主键
     * @return 返回的结果
     * @throws Exception
     */
    E selectById(Integer id) throws Exception;

    /**
     * 按id选择（id为Long类型）
     * @param id 主键
     * @return 返回的结果
     * @throws Exception
     */
    E selectById(Long id) throws Exception;

    /**
     * 按条件查询
     * @param obj
     * @return 结果集
     * @throws Exception
     */
    List<E> selectBySelective(E obj) throws Exception;
}
