package service;

import org.bookrec.entity.User;
import org.bookrec.entity.vo.ResultVo;
import org.bookrec.service.BookService;
import org.bookrec.service.UserService;
import org.bookrec.service.impl.BookServiceImpl;
import org.bookrec.service.impl.UserServiceImpl;
import org.junit.Test;

public class Test01 {
    private final UserService userService = new UserServiceImpl();
    private final BookService bookService = new BookServiceImpl();

    @Test
    public void testSignIn() throws Exception {
        User user = new User();
        user.setUsername("heiheihei");
        user.setPassword("666666");
        ResultVo<?> res = userService.signIn(user);
        System.out.println(res);
    }

    @Test
    public void testCollections() throws Exception {
        bookService.removeCollection(1L, 2L);
        //bookService.addCollection(1L, 2L);
        System.out.println(bookService.viewCollections(1L));
    }

    @Test
    public void testRecommendBook() throws Exception {
        bookService.recommendBook(1L);
    }
}
