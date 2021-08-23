package dao;

import org.bookrec.dao.BookDao;
import org.bookrec.dao.impl.BookDaoImpl;
import org.bookrec.entity.Book;
import org.bookrec.utils.JdbcUtil;
import org.junit.Test;

import java.util.List;

public class Test03 {
    private final BookDao bookDao = new BookDaoImpl();

    @Test
    public void test01() throws Exception {
        JdbcUtil.getConnection();
        List<Book> list = bookDao.orderByAvgLevel();
        System.out.println(list);
        JdbcUtil.close();
    }
}
