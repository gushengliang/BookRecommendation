package dao;

import org.bookrec.dao.BookDao;
import org.bookrec.dao.StudentDao;
import org.bookrec.dao.impl.BookDaoImpl;
import org.bookrec.dao.impl.StudentDaoImpl;
import org.bookrec.entity.Book;
import org.bookrec.utils.JdbcUtil;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentTest {
    private final StudentDao studentDao = new StudentDaoImpl();
    private final BookDao bookDao = new BookDaoImpl();

    @Test
    public void test01() throws Exception {
        try {
            //Long num = Long.class.newInstance();
            JdbcUtil.getConnection();
            List<Long> bookIds = bookDao.selectBookIdListByStudentId((long) 1);
            System.out.println(bookIds);
            List<Book> books = new ArrayList<>();
            for(Long bookId : bookIds) {
                books.add(bookDao.selectById(bookId));
            }
            System.out.println(books);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close();
        }
    }
}
