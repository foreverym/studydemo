package club.banyuan.json;

import club.banyuan.serial.Student;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Demo1 {

    public static void main(String[] args) {
        Student student = new Student();
        student.setId(1);
        student.setName("sss");
        JSONObject jsonObject = new JSONObject();
        String s = jsonObject.toJSONString(student);
        System.out.println(s);

        String jsonStr = "{\"id\":1,\"name\":\"sss\"}";
        Student student1 = JSON.parseObject(jsonStr, Student.class);
        System.out.println(student1);
    }
}
