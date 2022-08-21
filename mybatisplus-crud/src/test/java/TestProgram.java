import com.cgy.demo.Program;
import com.cgy.demo.entity.User;
import com.cgy.demo.mapper.User2Mapper;
import com.cgy.demo.mapper.UserMapper;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;


@SpringBootTest(classes = Program.class)
public class TestProgram {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private User2Mapper user2Mapper;

    @Test
    public void testInsert() {
        User user = new User();
        user = user.setName("cgy").setAge(22).setEmail("guan1004@qq.com").setId(100L);
        mapper.insert(user);

        Assertions.assertThat(user.getId()).isNotNull();
    }
}
