package hx.com.example.rule;

import org.apache.ibatis.executor.result.DefaultResultContext;
import org.apache.ibatis.executor.result.DefaultResultHandler;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author mingliang
 * @Date 2018-04-28 11:18
 */
public class MybatisTest {

    public static void main(String[] args) throws IOException {
        String resource = "mybatis-test-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession session = sqlSessionFactory.openSession(false);
        ResultHandler resultHandler = new DefaultResultHandler();
        ResultContext context = new DefaultResultContext();
        resultHandler.handleResult(context);
        try {
//        User user = (User) session.selectOne("com.yw.test01.UserMapper.selectUser", 1);
//        System.out.println(user);
            session.select("SELECT COUNT(c.overdue_days),c.* FROM t_collection_overdue c WHERE c.external_reminder = FALSE AND c.owner_id = 'cgm' GROUP BY c.overdue_days", resultHandler);
            Object result = context.getResultObject();


//            User u=new User();
//            u.setName("lisi2");
//            u.setAge(20);
//            session.insert("com.yw.test04.UserMapper.insertUser",u);
            System.out.println(result.toString());
            session.commit();

        } finally {
            session.close();
        }

    }
}
