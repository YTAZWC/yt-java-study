package top.ytazwc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.ytazwc.entity.User;
import top.ytazwc.mapper.UserMapper;

import java.util.List;

@SpringBootTest
class MpStudyDemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void testUser() {
        System.out.println("Test User Select All...");
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

}
