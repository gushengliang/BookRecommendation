package org.bookrec.service.impl;

import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;
import org.apache.log4j.Logger;
import org.bookrec.common.enums.impl.ErrorCode;
import org.bookrec.dao.*;
import org.bookrec.dao.impl.*;
import org.bookrec.entity.Book;
import org.bookrec.entity.Course;
import org.bookrec.entity.Major;
import org.bookrec.entity.UnexaminedBook;
import org.bookrec.entity.vo.ResultVo;
import org.bookrec.service.BookService;
import org.bookrec.utils.CastUtil;
import org.bookrec.utils.JdbcUtil;
import org.bookrec.utils.ResultVoUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * BookServiceImpl
 *
 * @author a1311
 */
public class BookServiceImpl implements BookService {
    /**
     * 日志管理
     */
    private static final Logger LOG = Logger.getLogger(JdbcUtil.class);
    /**
     * 注入dao
     */
    private final BookDao bookDao = new BookDaoImpl();
    private final UnexaminedBookDao unexaminedBookDao = new UnexaminedBookDaoImpl();
    private final EvaluationDao evaluationDao = new EvaluationDaoImpl();
    private final CourseDao courseDao = new CourseDaoImpl();
    private final MajorDao majorDao = new MajorDaoImpl();

    @Override
    public synchronized ResultVo<?> selectBookById(Long id) throws Exception {
        try {
            JdbcUtil.beginTransaction();
            bookDao.updatePageView(id);
            Book book = bookDao.selectById(id);
            JdbcUtil.commitTransaction();
            return ResultVoUtil.success(book);
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            JdbcUtil.rollbackTransaction();
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }

    @Override
    public ResultVo<?> addBook(Book book) throws Exception {
        try {
            JdbcUtil.getConnection();
            boolean res = bookDao.insert(book);
            return ResultVoUtil.success(res);
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }

    @Override
    public ResultVo<?> modifyBook(Book book) throws Exception {
        try {
            JdbcUtil.getConnection();
            boolean res = bookDao.update(book);
            return ResultVoUtil.success(res);
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }

    @Override
    public synchronized ResultVo<?> deleteBook(Long bookId) throws Exception {
        try {
            JdbcUtil.beginTransaction();
            boolean flag = bookDao.delete(bookId);
            flag = flag && bookDao.deleteByBookId(bookId);
            if (!flag) {
                JdbcUtil.rollbackTransaction();
            } else {
                JdbcUtil.commitTransaction();
            }
            return ResultVoUtil.success(flag);
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            JdbcUtil.rollbackTransaction();
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }

    @Override
    public synchronized ResultVo<?> addCollection(Long studentId, Long bookId) throws Exception {
        try {
            JdbcUtil.beginTransaction();
            boolean flag = bookDao.insert(studentId, bookId);
            flag = flag && bookDao.increaseAttention(bookId);
            if (!flag) {
                JdbcUtil.rollbackTransaction();
            } else {
                JdbcUtil.commitTransaction();
            }
            return ResultVoUtil.success(flag);
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            JdbcUtil.rollbackTransaction();
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }

    @Override
    public synchronized ResultVo<?> removeCollection(Long studentId, Long bookId) throws Exception {
        try {
            JdbcUtil.beginTransaction();
            boolean flag = bookDao.delete(studentId, bookId);
            if (bookDao.selectById(bookId).getAttention() > 0) {
                flag = flag && bookDao.decreaseAttention(bookId);
            }
            if (!flag) {
                JdbcUtil.rollbackTransaction();
            } else {
                JdbcUtil.commitTransaction();
            }
            return ResultVoUtil.success(flag);
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            JdbcUtil.rollbackTransaction();
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }

    @Override
    public synchronized ResultVo<?> isCollected(Long studentId, Long bookId) throws Exception {
        try {
            JdbcUtil.getConnection();
            boolean res = bookDao.selectCount(studentId, bookId) > 0;
            System.out.println("studentId=" + studentId + ", bookId=" + bookId + ", res=" + res);
            return ResultVoUtil.success(res);
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }

    @Override
    public synchronized ResultVo<?> viewCollections(Long id) throws Exception {
        try {
            JdbcUtil.getConnection();
            List<Long> longList = bookDao.selectBookIdListByStudentId(id);
            List<Book> bookList = new ArrayList<>();
            for (Long longId : longList) {
                bookList.add(bookDao.selectById(longId));
            }
            return ResultVoUtil.success(bookList);
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }

    @Override
    public synchronized ResultVo<?> selectAllBooks() throws Exception {
        try {
            JdbcUtil.getConnection();
            List<Book> bookList = bookDao.selectBySelective(new Book());
            return ResultVoUtil.success(bookList);
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }

    @Override
    public ResultVo<?> selectUnexaminedBooks() throws Exception {
        try {
            JdbcUtil.getConnection();
            UnexaminedBook unexaminedBook = new UnexaminedBook();
            unexaminedBook.setIsExamined(0);
            List<UnexaminedBook> bookList = unexaminedBookDao.selectBySelective(unexaminedBook);
            return ResultVoUtil.success(bookList);
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }

    @Override
    public ResultVo<?> passBook(String id) throws Exception {
        try {
            JdbcUtil.getConnection();
            Long bookId = Long.parseLong(id);
            UnexaminedBook unexaminedBook = unexaminedBookDao.selectById(bookId);
            unexaminedBook.setIsExamined(1);


            Book book = new Book();
            book.setIntroduction(unexaminedBook.getIntroduction());
            book.setName(unexaminedBook.getName());
            book.setPicture(unexaminedBook.getPicture());

            boolean res = unexaminedBookDao.update(unexaminedBook);
            System.out.print(res);
            if (res) {
                res = bookDao.insert(book);
            }
            return ResultVoUtil.success(res);
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }

    @Override
    public ResultVo<?> unPassBook(String id) throws Exception {
        try {
            JdbcUtil.getConnection();
            Long bookId = Long.parseLong(id);
            UnexaminedBook unexaminedBook = unexaminedBookDao.selectById(bookId);
            unexaminedBook.setIsExamined(1);
            Boolean res = unexaminedBookDao.update(unexaminedBook);
            return ResultVoUtil.success(res);
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }

    @Override
    public ResultVo<?> updatePageView(Long id) throws Exception {
        try {
            JdbcUtil.beginTransaction();
            boolean res = bookDao.updatePageView(id);
            return ResultVoUtil.success(res);
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            JdbcUtil.rollbackTransaction();
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }

    @Override
    public synchronized ResultVo<?> orderByPageView() throws Exception {
        try {
            JdbcUtil.getConnection();
            List<Book> list = bookDao.orderByPageView();
            return ResultVoUtil.success(list);
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }

    @Override
    public synchronized ResultVo<?> orderByAttention() throws Exception {
        try {
            JdbcUtil.getConnection();
            List<Book> list = bookDao.orderByAttention();
            return ResultVoUtil.success(list);
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }

    @Override
    public synchronized ResultVo<?> orderByAvgLevel() throws Exception {
        try {
            JdbcUtil.beginTransaction();
            //获取所有参考书
            List<Book> books = bookDao.selectBySelective(new Book());
            //遍历得到的列表
            for (Book book : books) {
                //根据参考书id获取平均分
                Double avgLevel = evaluationDao.getAvgLevelByBookId(book.getId());
                //将平均分设置为获取到的分数
                book.setAvgLevel(avgLevel);
                //更新参考书数据
                bookDao.update(book);
            }
            //按照评分排序
            List<Book> list = bookDao.orderByAvgLevel();
            JdbcUtil.commitTransaction();
            return ResultVoUtil.success(list);
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            JdbcUtil.rollbackTransaction();
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }

    @Override
    public synchronized ResultVo<?> recommendBook(Long majorId) throws Exception {
        try {
            JdbcUtil.getConnection();
            //初始化参考书列表
            List<Book> bookList = new ArrayList<>();
            //根据专业id获取课程id列表
            List<Long> courseIdList = courseDao.selectCourseIdListByMajorId(majorId);
            //遍历课程id列表
            for (Long courseId : courseIdList) {
                //根据每个课程id获取对应的参考书id列表
                List<Long> bookIdList = bookDao.selectBookIdListByCourseId(courseId);
                //遍历每个参考书id列表
                for (Long bookId : bookIdList) {
                    //根据参考书id获取参考书并将参考书添加到参考书列表中
                    bookList.add(bookDao.selectById(bookId));
                }
            }
            //内部排序（根据参考书的加权评分）
            bookList.sort(new Comparator<Book>() {
                @Override
                public int compare(Book o1, Book o2) {
                    return getScore(o2) - getScore(o1);
                }

                /**
                 * 获得加权评分
                 * @param book 参考书
                 * @return 该书的加权评分
                 */
                public int getScore(Book book) {
                    return (int) Math.round(book.getPageView() * 10 + book.getAttention() * 490 + book.getAvgLevel() * 500);
                }
            });
            return ResultVoUtil.success(bookList);
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }

    @Override
    public synchronized ResultVo<?> selectByMajorAndCourseAndKeyword(String[] majors, String[] courses, String keyword) throws Exception {
        try {
            JdbcUtil.getConnection();
            Major major = new Major(keyword);
            Course course = new Course(keyword);
            List<Major> majorList = majorDao.selectBySelective(major);
            List<Course> courseList = courseDao.selectBySelective(course);
            //按书名模糊查询
            Book book1 = new Book();
            book1.setName(keyword);
            List<Book> bookList1 = bookDao.selectBySelective(book1);
            //按简介模糊查询
            Book book2 = new Book();
            book2.setIntroduction(keyword);
            List<Book> bookList2 = bookDao.selectBySelective(book2);
            //合并查询结果
            bookList1.addAll(bookList2);
            //去除重复元素
            deleteSameElement(bookList1);
            //根据专业课程参数查找的结果
            List<Book> books = new ArrayList<>();
            if (courses == null || courses.length == 0) {
                //如果传进的专业参数不为空
                if (majors == null || majors.length == 0) {
                    //关键字查询
                    books = bookList1;
                } else {
                    //获取专业查询参数列表
                    List<Long> majorIds = new ArrayList<>();
                    for (String majorId : majors) {
                        majorIds.add(CastUtil.castLong(majorId));
                    }
                    //遍历专业列表
                    for (Long majorId : majorIds) {
                        //获取专业所对应的课程id列表
                        List<Long> courseIdList = courseDao.selectCourseIdListByMajorId(majorId);
                        //遍历课程列表
                        for (Long courseId : courseIdList) {
                            //获取课程所对应的参考书id列表
                            List<Long> bookIdList = bookDao.selectBookIdListByCourseId(courseId);
                            //遍历参考书id列表
                            for (Long bookId : bookIdList) {
                                books.add(bookDao.selectById(bookId));
                            }
                        }
                    }
                }
            } else {
                //获取课程查询参数列表
                List<Long> courseIds = new ArrayList<>();
                for (String courseId : courses) {
                    courseIds.add(CastUtil.castLong(courseId));
                }
                //遍历课程列表
                for (Long courseId : courseIds) {
                    //获取课程所对应的参考书id列表
                    List<Long> bookIdList = bookDao.selectBookIdListByCourseId(courseId);
                    //遍历参考书id列表
                    for (Long bookId : bookIdList) {
                        books.add(bookDao.selectById(bookId));
                    }
                }
            }
            //求交集
            books = books.stream()
                    .filter(item -> bookList1.stream().map(Book::getId)
                            .collect(Collectors.toList()).contains(item.getId()))
                    .collect(Collectors.toList());
            //去除重复元素
            deleteSameElement(books);
            List<List<?>> searchList = new ArrayList<>();
            searchList.add(books);
            searchList.add(majorList);
            searchList.add(courseList);
            return ResultVoUtil.success(searchList);
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }

    /**
     * 删除List中的相同元素
     * @param bookList
     */
    private void deleteSameElement(List<Book> bookList) {
        for (int i = 0; i < bookList.size() - 1; i++) {
            for (int j = bookList.size() - 1; j > i; j--) {
                if (bookList.get(j).getId().equals(bookList.get(i).getId())) {
                    bookList.remove(j);
                }
            }
        }
    }

    @Override
    public synchronized ResultVo<?> searchBooksByCourseId(Long id) throws Exception {
        try {
            JdbcUtil.getConnection();
            List<Long> longList = bookDao.selectBookIdListByCourseId(id);
            List<Book> bookList = new ArrayList<>();
            for (Long longId : longList) {
                bookList.add(bookDao.selectById(longId));
            }
            return ResultVoUtil.success(bookList);
        } catch (SQLException | RuntimeException e) {
            LOG.error(e.getMessage(), e);
            return ResultVoUtil.fail(ErrorCode.UNKNOWN_ERROR);
        } finally {
            JdbcUtil.close();
        }
    }
}
