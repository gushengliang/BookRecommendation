package org.bookrec.dao;

import org.bookrec.entity.User;

/**
 * @author a1311
 */
public interface UserDao extends BaseDao<User> {
    /**
     * 判定该用户名是否存在
     * @param username 用户名
     * @return true or false
     */
    boolean isExist(String username) throws Exception;
}
