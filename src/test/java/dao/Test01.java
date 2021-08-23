package dao;

import org.bookrec.dao.UserDao;
import org.bookrec.dao.impl.UserDaoImpl;
import org.bookrec.entity.User;
import org.junit.Test;

import java.util.List;

public class Test01 {

    private UserDao userDao = new UserDaoImpl();

    @Test
    public void testIsExist() throws Exception {
        boolean isExist = userDao.isExist("abc");
        System.out.println(isExist);
    }

    @Test
    public void testInsert() throws Exception {
        User user = new User("geigeigei", "123456", 1);
        boolean res = userDao.insert(user);
        System.out.println(res);
    }

    @Test
    public void testInsertNewUser() throws Exception {
        User user = new User("heiheihei", "123456", 1);
        if(userDao.isExist(user.getUsername())) {
            System.out.println("用户已存在");
        } else {
            boolean res = userDao.insert(user);
            System.out.println("添加成功");
        }
    }

    @Test
    public void testSelectBySelective() throws Exception {
        User user = new User();
        List<User> users = userDao.selectBySelective(user);
        System.out.println(users);
    }

    @Test
    public void testUpdate() throws Exception {
        User user = new User();
        user.setId((long) 3);
        user.setPassword("666666");
        boolean res = userDao.update(user);
        System.out.println(res);
    }
}
