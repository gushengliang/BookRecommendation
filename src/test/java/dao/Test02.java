package dao;

import org.bookrec.dao.EvaluationDao;
import org.bookrec.dao.impl.EvaluationDaoImpl;
import org.bookrec.utils.JdbcUtil;
import org.junit.Test;

public class Test02 {
    private final EvaluationDao evaluationDao = new EvaluationDaoImpl();

    @Test
    public void test() throws Exception {
        JdbcUtil.getConnection();
        Long bookId = 1L;
        Double avg = evaluationDao.getAvgLevelByBookId(bookId);
        System.out.println(avg);
        JdbcUtil.close();
    }
}
