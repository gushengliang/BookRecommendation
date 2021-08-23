package org.bookrec.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.bookrec.common.enums.impl.ErrorCode;
import org.bookrec.dao.StudentDao;
import org.bookrec.dao.UserDao;
import org.bookrec.dao.impl.StudentDaoImpl;
import org.bookrec.dao.impl.UserDaoImpl;
import org.bookrec.entity.Student;
import org.bookrec.entity.User;
import org.bookrec.entity.vo.ResultVo;
import org.bookrec.service.UserService;
import org.bookrec.utils.JdbcUtil;
import org.bookrec.utils.ResultVoUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * UserServiceImpl
 *
 * @author a1311
 */
public class UserServiceImpl implements UserService {
    /**
     * 日志管理
     */
    private static final Logger LOG = Logger.getLogger(JdbcUtil.class);
    /**
     * 注入dao
     */
    private final UserDao userDao = new UserDaoImpl();
    private final StudentDao studentDao = new StudentDaoImpl();

    @Override
    public ResultVo<?> signIn(User user) throws Exception {
        try {
            JdbcUtil.getConnection();
            user.setPassword(DigestUtils.md5Hex(user.getPassword()));
            if (userDao.isExist(user.getUsername())) {
                List<User> users = userDao.selectBySelective(user);
                if (users.size() == 1) {
                    User userInfo = users.get(0);
                    if (userInfo.getIsActive() == 1) {
                        System.out.println("登录成功");
                        userInfo.setPassword(null);
                        return ResultVoUtil.success(userInfo);
                    } else {
                        System.out.println("用户已注销");
                        return ResultVoUtil.fail(ErrorCode.INVALID_USER_OR_TOKEN);
                    }
                } else {
                    System.out.println("密码错误");
                    return ResultVoUtil.fail(ErrorCode.INVALID_USER_OR_TOKEN);
                }
            } else {
                System.out.println("该用户不存在");
                return ResultVoUtil.fail(ErrorCode.INVALID_USER_OR_TOKEN);
            }
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            JdbcUtil.rollbackTransaction();
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }

    @Override
    public ResultVo<?> signUp() {
        return null;
    }

    @Override
    public ResultVo<?> quit() {
        return null;
    }

    @Override
    public ResultVo<?> logOff() {
        return null;
    }

    @Override
    public ResultVo<?> conditionSelect(User user) throws Exception {
        try {
            JdbcUtil.getConnection();
            List<User> list = userDao.selectBySelective(user);
            return ResultVoUtil.success(list);
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }

    @Override
    public ResultVo<?> getUserInfo(User user) throws Exception {
        try {
            JdbcUtil.getConnection();
            //获取用户名
            String username = user.getUsername();
            List<Student> list;
            //如果用户类型为1（也就是用户为学生）
            if (user.getType() == 1 && user.getIsActive() == 1) {
                Student student = new Student();
                student.setUsername(username);
                list = studentDao.selectBySelective(student);
                if(list.size() == 1) {
                    return ResultVoUtil.success(list.get(0));
                } else {
                    return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
                }
            } else {
                return ResultVoUtil.fail(ErrorCode.TOKEN_ERROR);
            }
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }
}
