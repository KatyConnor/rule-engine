package hx.com.example.rule.demo;

import com.sun.xml.internal.ws.org.objectweb.asm.MethodAdapter;
import net.sf.cglib.transform.ClassVisitorTee;
import org.objectweb.asm.*;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.LocalVariableNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.util.CheckClassAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author mingliang
 * @Date 2018-06-11 13:34
 */
public class MethodParamNamesScanner {
    /**
     * 获取方法参数名列表
     *
     * @param clazz
     * @param m
     * @return
     * @throws IOException
     */
    public static List<String> getMethodParamNames(Class<?> clazz, Method m) throws IOException {
        try (InputStream in = clazz.getResourceAsStream("/" + clazz.getName().replace('.', '/') + ".class")) {
            return getMethodParamNames(in,m);
        }

    }
    public static List<String> getMethodParamNames(InputStream in, Method m) throws IOException {
        try (InputStream ins=in) {
            return getParamNames(ins,new EnclosingMetadata(m.getName(),Type.getMethodDescriptor(m), m.getParameterTypes().length));
        }

    }
    /**
     * 获取构造器参数名列表
     *
     * @param clazz
     * @param constructor
     * @return
     */
    public static List<String> getConstructorParamNames(Class<?> clazz, Constructor<?> constructor) {
        try (InputStream in = clazz.getResourceAsStream("/" + clazz.getName().replace('.', '/') + ".class")) {
            return getConstructorParamNames(in, constructor);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ArrayList<String>();
    }

    public static List<String> getConstructorParamNames(InputStream ins, Constructor<?> constructor) {
        try (InputStream in = ins) {
            return getParamNames(in, new EnclosingMetadata(constructor.getName(),Type.getConstructorDescriptor(constructor),
                    constructor.getParameterTypes().length));
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return new ArrayList<String>();
    }
    /**
     * 获取参数名列表辅助方法
     *
     * @param in
     * @param m
     * @return
     * @throws IOException
     */
    private static List<String> getParamNames(InputStream in, EnclosingMetadata m) throws IOException {
        ClassReader cr = new ClassReader(in);
        ClassVisitor visitor = new ClassWriter(Opcodes.ASM5);
        ClassNode cn = new ClassNode();
        cr.accept(cn, ClassReader.EXPAND_FRAMES);// 建议EXPAND_FRAMES
        // ASM树接口形式访问
        List<MethodNode> methods = cn.methods;
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < methods.size(); ++i) {
            List<LocalVariable> varNames = new ArrayList<LocalVariable>();
            MethodNode method = methods.get(i);
            // 验证方法签名
            if (method.desc.equals(m.desc)&&method.name.equals(m.name)) {
                List<LocalVariableNode> local_variables = method.localVariables;
                for (int l = 0; l < local_variables.size(); l++) {
                    String varName = local_variables.get(l).name;
                    // index-记录了正确的方法本地变量索引。(方法本地变量顺序可能会被打乱。而index记录了原始的顺序)
                    int index = local_variables.get(l).index;
                    if (!"this".equals(varName)) // 非静态方法,第一个参数是this
                        varNames.add(new LocalVariable(index, varName));
                }
                LocalVariable[] tmpArr = varNames.toArray(new LocalVariable[varNames.size()]);
                // 根据index来重排序，以确保正确的顺序
                Arrays.sort(tmpArr);
                for (int j = 0; j < m.size; j++) {
                    list.add(tmpArr[j].name);
                }
                break;

            }

        }
        return list;
    }

    /**
     * 方法本地变量索引和参数名封装
     * @author xby Administrator
     */
    static class LocalVariable implements Comparable<LocalVariable> {
        public int index;
        public String name;

        public LocalVariable(int index, String name) {
            this.index = index;
            this.name = name;
        }

        public int compareTo(LocalVariable o) {
            return this.index - o.index;
        }
    }

    /**
     * 封装方法描述和参数个数
     *
     * @author xby Administrator
     */
    static class EnclosingMetadata {
        //method name
        public String name;
        // method description
        public String desc;
        // params size
        public int size;

        public EnclosingMetadata(String name,String desc, int size) {
            this.name=name;
            this.desc = desc;
            this.size = size;
        }
    }

    public static void main(String[] args) throws IOException {
        for (Method m : TestObjectParam.class.getDeclaredMethods()) {
            List<String> list = getMethodParamNames(ParamDTO.class, m);
            System.out.println(m.getName() + ":");
            for (String str : list) {
                System.out.println(str);
            }
            System.out.println("------------------------");
        }

//            String[] list = getMethodParamNames(m);
//            System.out.println(m.getName() + ":");
//            for (String str : list) {
//                System.out.println(str);
//            }
//            System.out.println("------------------------");
        }

//    public static void main(String[] args) throws SecurityException, NoSuchMethodException, IOException {
//        Method method1 = Test.class.getDeclaredMethod("method", String.class, Object.class);
//        System.out.println(Arrays.toString(getMethodParamNames(method)));
//    }
        /** 使用字节码工具ASM来获取方法的参数名 */
//    public static String[] getMethodParamNames(final Method method) throws IOException {
//
//        final String methodName = method.getName();
//        final Class<?>[] methodParameterTypes = method.getParameterTypes();
//        final int methodParameterCount = methodParameterTypes.length;
//        final String className = method.getDeclaringClass().getName();
//        final boolean isStatic = Modifier.isStatic(method.getModifiers());
//        final String[] methodParametersNames = new String[methodParameterCount];
//
//        ClassReader cr = new ClassReader(className);
//        cr.accept(new ClassVisitor(8) {
//            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
//
//                MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
//
//                final Type[] argTypes = Type.getArgumentTypes(desc);
//
//                //参数类型不一致
//                if (!methodName.equals(name) || !matchTypes(argTypes, methodParameterTypes)) {
//                    return mv;
//                }
//                return new MethodVisitor(0) {
//                    public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {
//                        //如果是静态方法，第一个参数就是方法参数，非静态方法，则第一个参数是 this ,然后才是方法的参数
//                        int methodParameterIndex = isStatic ? index : index - 1;
//                        if (0 <= methodParameterIndex && methodParameterIndex < methodParameterCount) {
//                            methodParametersNames[methodParameterIndex] = name;
//                        }
//                        super.visitLocalVariable(name, desc, signature, start, end, index);
//                    }
//                };
//            }
//        }, 0);
//        return methodParametersNames;
////        return null;
//    }
//
//    /**
//     * 比较参数是否一致
//     */
//    private static boolean matchTypes(Type[] types, Class<?>[] parameterTypes) {
//        if (types.length != parameterTypes.length) {
//            return false;
//        }
//        for (int i = 0; i < types.length; i++) {
//            if (!Type.getType(parameterTypes[i]).equals(types[i])) {
//                return false;
//            }
//        }
//        return true;
//    }
}
