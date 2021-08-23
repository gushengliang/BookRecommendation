package service;

import org.bookrec.service.BookService;
import org.bookrec.service.impl.BookServiceImpl;
import org.junit.Test;

public class Test02 {
    private final BookService bookService = new BookServiceImpl();

    @Test
    public void test01() throws Exception {
        bookService.orderByAvgLevel();
        System.out.println("-----------------------------------------");
        bookService.orderByPageView();
        System.out.println("-----------------------------------------");
        bookService.orderByAttention();
    }
}
