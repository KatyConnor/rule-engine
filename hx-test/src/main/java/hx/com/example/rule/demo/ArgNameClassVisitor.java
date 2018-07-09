package hx.com.example.rule.demo;

import hx.com.example.rule.common.proxy.ObjectCglibProxyBean;
import org.objectweb.asm.*;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.LocalVariableNode;
import org.objectweb.asm.tree.MethodNode;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @Author mingliang
 * @Date 2018-06-11 14:52
 */
public class ArgNameClassVisitor extends ClassVisitor {
    private Map<String, List<String>> nameArgMap = new HashMap<String, List<String>>();
    public ArgNameClassVisitor() {
        super(Opcodes.ASM5);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc,String signature, String[] exceptions) {
        Type methodType = Type.getMethodType(desc);
        int len = methodType.getArgumentTypes().length;
        List<String> argumentNames = new ArrayList<>();
        nameArgMap.put(name, argumentNames);
        MethodVisitor visitor = new ArgNameMethodVisitor(Opcodes.ASM5,argumentNames,len);
        return visitor;
    }

    public Map<String, List<String>> getNameArgMap(){
        return nameArgMap;
    }
    public static void main(String[] args) throws IOException, NoSuchMethodException {
        Method[] methods = TestObjectParam.class.getDeclaredMethods();
        for (Method method : methods) {
            final String n = method.getDeclaringClass().getName();
            ClassReader cr=new ClassReader(n);
            ArgNameClassVisitor cl=new ArgNameClassVisitor();
            cr.accept(cl, 0);
            for(Map.Entry<String, List<String>> entry:cl.getNameArgMap().entrySet()){
                System.out.println(entry.getKey());
                for(String str:entry.getValue()){
                    System.out.println("value:"+str);
                }
            }
        }

    }
}
