package club.banyuan;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JsonUtil {

    /**
     * {id:""}
     *
     * @param o
     * @return
     */
    public static String toJsonString(Object o) throws InvocationTargetException, IllegalAccessException, IOException {
        Class<?> aClass = o.getClass();
        Method[] declaredMethods = aClass.getDeclaredMethods();
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");

        for (Method declareMethod : declaredMethods) {
            String methodName = declareMethod.getName();
            int parameterCount = declareMethod.getParameterCount();

            //序列化json的时候调用其get方法获取值,get方法必须是无参的
            //
            if (methodName.startsWith("get") && parameterCount == 0) {
                Object value = declareMethod.invoke(o);
                String key = (methodName.charAt(3)+"").toLowerCase() + methodName.substring(3, methodName.length());
                //String key = methodName.substring(3);
                //如果value是string类型用""包裹，
                //如果是int类型，不需要包裹
                if (value.getClass() == int.class || value.getClass() == Integer.class) {
                    stringBuilder.append("\""+ key + "\"");
                }
            }
        }
        stringBuilder.append("}");

        String jsonStr = stringBuilder.toString();

        DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(""));
        dataOutputStream.writeBytes(jsonStr);

        return jsonStr;
    }


    static <T> T parseObject(String str, Class<T> clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Constructor<T> constructor = clazz.getConstructor();
        T t = constructor.newInstance();
        if (str.length()>2) {

        }

        return t;
        //return new Object();
    }


    public static void main(String[] args) {
        System.out.println("\"");
        String s = "abcdefg";
        System.out.println(s.substring(1,4));

        System.out.println(s.lastIndexOf("a"));
    }

}
