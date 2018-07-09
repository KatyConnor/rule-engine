package hx.com.example.rule.demo;

import com.alibaba.fastjson.JSONObject;
import javassist.*;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;

/**
 * @Author mingliang
 * @Date 2018-06-11 11:20
 */
public class MapperTest {

    public static void main(String[] args) throws NoSuchMethodException {
        Class<?> classzz = TestObjectParam.class;
        Method[] methods = classzz.getMethods();
        for (int j = 0; j < methods.length; j++) {
            System.out.println("返回类型 returnType = "+methods[j].getReturnType());
            Class<?>[] params = methods[j].getParameterTypes();
            System.out.println("入参个数 ： ");
//            for (int i = 0; i < params.length ; i++) {
//                System.out.println("class = "+params[i]+" name = "+params[i].getSimpleName());
//            }
            Parameter[] parameter = methods[j].getParameters();
            for (int i = 0; i < parameter.length ; i++) {
                if (parameter[i].isNamePresent()) {
                    System.out.print(parameter[i].getName() + ' ');
                }
//                    System.out.println("class = "+parameter[i]+" name = "+parameter[i].getName()+" param = "+JSONObject.toJSONString(parameter[i]+
//                "  paramClass = "+parameter[i].getType()) +" annotation = "+parameter[i].getAnnotation(Param.class));
            }


//            Class<?> clazz = TestObjectParam.class;
//            ClassPool pool = ClassPool.getDefault();
//            try {
//                CtClass ctClass = pool.get(clazz.getName());
//                CtMethod ctMethod = ctClass.getDeclaredMethod("findByCondition");
//
//                // 使用javassist的反射方法的参数名
//                MethodInfo methodInfo = ctMethod.getMethodInfo();
////                CodeAttribute codeAttribute =
//                List attr =  methodInfo.getAttributes();
//                if (attr != null) {
//                    int len = ctMethod.getParameterTypes().length;
//                    // 非静态的成员函数的第一个参数是this
//                    int pos = Modifier.isStatic(ctMethod.getModifiers()) ? 0 : 1;
//                    System.out.print("test : ");
//                    for (int i = 0; i < len; i++) {
//                        LocalVariableAttribute attrs = (LocalVariableAttribute) attr.get(0);
//                        System.out.print(attrs.variableName(i + pos) + ' ');
//                    }
//                }
//            } catch (NotFoundException e) {
//                e.printStackTrace();
//            }

            try {
                String[] paramNames = getAllParamaterName(methods[j]);
                for (int i = 0; i < paramNames.length ; i++) {
                    System.out.println(paramNames[i]);
                }
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static String[] getAllParamaterName(Method method) throws NotFoundException {
        Class<?> clazz = method.getDeclaringClass();
        ClassPool pool = ClassPool.getDefault();
        CtClass clz = pool.get(clazz.getName());
        CtClass[] params = new CtClass[method.getParameterTypes().length];
        for (int i = 0; i < method.getParameterTypes().length; i++) {
            params[i] = pool.getCtClass(method.getParameterTypes()[i].getName());
        }
        CtMethod cm = clz.getDeclaredMethod(method.getName(), params);
        MethodInfo methodInfo = cm.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
        int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
        String[] paramNames = new String[cm.getParameterTypes().length];
        for (int i = 0; i < paramNames.length; i++) {
            paramNames[i] = attr.variableName(i + pos);
        }
        return paramNames;
    }
}
