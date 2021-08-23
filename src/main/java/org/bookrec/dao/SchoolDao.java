package org.bookrec.dao;

import org.bookrec.entity.Major;
import org.bookrec.entity.School;

import java.util.List;

/**
 * SchoolDao
 *
 * @author a1311
 */
public interface SchoolDao extends BaseDao<School> {
    /**
     * 增加学校专业关系
     * @param schoolId
     * @param majorId
     * @return
     * @throws Exception
     */
    boolean insert(Long schoolId, Long majorId) throws Exception ;

    /**
     * 删除学校专业关系
     * @param schoolId
     * @param majorId
     * @return
     * @throws Exception
     */
    boolean delete(Long schoolId, Long majorId) throws Exception ;
}
