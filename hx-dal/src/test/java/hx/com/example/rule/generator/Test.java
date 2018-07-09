package hx.com.example.rule.generator;

/**
 * @Author mingliang
 * @Date 2018-03-30 16:34
 */
public class Test {

    public static void main(String[] args) {
        String  str = "rule..testObject";
        String[] strings = str.split("\\.");
        System.out.println(strings[2]);
    }
}
