package hx.com.example.rule.demo;

import org.objectweb.asm.*;

import java.io.IOException;

/**
 * @Author mingliang
 * @Date 2018-06-11 14:17
 */
public class TestVisitor extends ClassVisitor {

    public TestVisitor(int i) {
        super(i);
    }

    @Override
    public FieldVisitor visitField(int i, String s, String s1, String s2, Object o) {
        //如果字段加 final ,则可以有默认值value,否则为null
//        System.out.println(i+"\t"+s+"\t"+s1+"\t"+s2+"\t"+o);
        return super.visitField(i, s, s1, s2, o);
    }

    @Override
    public MethodVisitor visitMethod(int i, String s, String s1, String s2, String[] strings) {
        MethodVisitor methodVisitor = super.visitMethod(i, s, s1, s2, strings);
        return methodVisitor;
    }

    public static void main(String[] args) throws IOException {
//		String p = t.getClass().getName();
//		ClassReader creader = new ClassReader(p);
        ClassReader creader = new ClassReader(
                ClassLoader.getSystemResourceAsStream(
                        TestObjectParam.class.getName().replace(".", "/")+".class"));
        TestVisitor visitor = new TestVisitor(Opcodes.ASM5);
        creader.accept(visitor, 0);
    }
}
