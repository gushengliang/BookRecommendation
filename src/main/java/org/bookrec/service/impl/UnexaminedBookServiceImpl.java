package org.bookrec.service.impl;

import org.apache.log4j.Logger;
import org.bookrec.common.enums.impl.ErrorCode;
import org.bookrec.dao.UnexaminedBookDao;
import org.bookrec.dao.impl.UnexaminedBookDaoImpl;
import org.bookrec.entity.Book;
import org.bookrec.entity.UnexaminedBook;
import org.bookrec.entity.vo.ResultVo;
import org.bookrec.service.UnexaminedBookService;
import org.bookrec.utils.JdbcUtil;
import org.bookrec.utils.ResultVoUtil;

import java.sql.SQLException;
import java.util.List;

/**
 * @author jzt
 */
public class UnexaminedBookServiceImpl implements UnexaminedBookService {
    /**
     * 日志管理
     */
    private static final Logger LOG = Logger.getLogger(JdbcUtil.class);
    /**
     * 注入Dao
     */
    private final UnexaminedBookDao unexaminedBookDao=new UnexaminedBookDaoImpl();
    @Override
    public ResultVo<?> getAllUnexaminedBooks() throws Exception {
        try {
            JdbcUtil.getConnection();
            UnexaminedBook unexaminedBook=new UnexaminedBook();
            unexaminedBook.setIsExamined(0);
            List<UnexaminedBook> unexaminedBooks =unexaminedBookDao.selectBySelective(unexaminedBook);
            return ResultVoUtil.success(unexaminedBooks);
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            JdbcUtil.rollbackTransaction();
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }
}
