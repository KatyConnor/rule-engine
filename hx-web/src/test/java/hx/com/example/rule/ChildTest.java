package hx.com.example.rule;

/**
 * @Author mingliang
 * @Date 2018-05-17 21:16
 */
public class ChildTest {

    public static void main(String[] args) {
        Class<?>[] cs = (Class<?>[]) TestMapper.class.getDeclaredClasses();
        for (Class cddddd : cs){
            System.out.println(cddddd);
        }
    }
}
