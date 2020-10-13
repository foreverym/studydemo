package club.banyuan.service;

import club.banyuan.PropUtil;
import com.alibaba.fastjson.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserService1 {

    private static List<User1> userList = new ArrayList<>();

    static {
        load();
    }

    public UserService1() {

    }

    private static void save() {
        String filePath = PropUtil.getProp("user.store.path");
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            String s = JSONObject.toJSONString(userList);
            fileOutputStream.write(s.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载json文件
     */
    private static void load() {
        String filePath = PropUtil.getProp("user.store.path");
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            byte[] bytes = fileInputStream.readAllBytes();
            String jsonStr = new String(bytes);
            if (jsonStr == null || jsonStr.length() == 0) {
                userList = new ArrayList<>();
            } else {
                userList = JSONObject.parseArray(jsonStr, User1.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    public User1 login(User1 user) {
        return null;
    }

    /**
     * 添加用户
     * @param user
     */
    public void addUser(User1 user) {
        userList.add(user);
    }



    /**
     * 获取用户列表数据
     *
     * @return
     */
    public List<User1> getUserList() {
        return userList;
    }


    public static void main(String[] args) {
        //UserService userService = new UserService();
        User1 user1 = new User1(1, "", "ss", "ss", 1);
        User1 user2 = new User1(2, "", "ss", "ss", 1);
        User1 user3 = new User1(3, "", "ss", "ss", 1);
        User1 user4 = new User1(4, "", "ss", "ss", 1);
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        save();

    }


}
