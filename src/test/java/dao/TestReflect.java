package dao;

import org.bookrec.entity.User;
import org.junit.Test;

import java.util.Arrays;

public class TestReflect {
    @Test
    public void test01() {
        System.out.println(User.class);
        System.out.println(Arrays.toString(User.class.getDeclaredFields()));
    }
}
