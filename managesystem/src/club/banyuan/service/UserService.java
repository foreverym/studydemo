package club.banyuan.service;

import club.banyuan.util.PropUtil;
import club.banyuan.entity.User;
import club.banyuan.http.BadReqException;
import com.alibaba.fastjson.JSONObject;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserService {

  private static int idCount = 1;
  private static List<User> userList = new ArrayList<>();

  // 对象创建的时候，从硬盘读取用户列表数据，反序列化为 User List
  static {
    load();
  }

  private static void load() {
    String filePath = PropUtil.getProp("user.store.path");

    try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
      byte[] bytes = fileInputStream.readAllBytes();
      String jsonStr = new String(bytes);
      UserService userService = null;
      if (jsonStr == null || jsonStr.length() == 0) {
        userService = new UserService();
      } else {
        userService = JSONObject.parseObject(jsonStr, UserService.class);
      }
      idCount = userService.getIdCount();
      userList = userService.getUserList();
    } catch (IOException e) {
      // 读取用户信息文件失败，就创建一个空集合
      userList = new ArrayList<>();
    }
  }

  public int getIdCount() {
    return idCount;
  }

  public void setIdCount(int idCount) {
    UserService.idCount = idCount;
  }

  public void setUserList(List<User> userList) {
    this.userList = userList;
  }

  /**
   * 用户信息保存到文件中
   */
  private void save() {
    String s = JSONObject.toJSONString(this);
    try (FileOutputStream fileOutputStream = new FileOutputStream(
        PropUtil.getProp("user.store.path"))) {
      fileOutputStream.write(s.getBytes());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  /**
   * 比较用户名和密码，在集合中查找和参数对象的用户名和密码一致的对象返回
   * 如果用户不存在则返回null
   *
   * @param user
   * @return
   */
  public User login(User user) {
    for (User one : userList) {
      if (one.getName().equals(user.getName()) && one.getPwd().equals(user.getPwd())) {
        return one;
      }
    }

    return null;
  }

  /**
   * 添加用户的时候，
   * 需要对用户进行校验，用户名不能冲突， 用户的密码和确认密码一致
   * <p>
   * 需要给用户分配一个id， 自增的数字
   *
   * @param user
   */
  public void addUser(User user) {
    validateUserAdd(user);
    user.setId(idCount++);
    userList.add(user);
    // 添加完成后写入硬盘
    save();
  }


  private void validateUserAdd(User user) {
    if (!user.getPwd().equals(user.getPwdConfirm())) {
      // TODO 异常
    }

    for (User one : userList) {
      if (one.getName().equals(user.getName())) {
        // TODO 异常
      }
    }
  }

  public List<User> getUserList() {
    return userList;
  }


  public static void main(String[] args) {
    UserService userService = new UserService();

    System.out.println(userService.userList);
    User u1 = new User();
    u1.setName("admin");
    u1.setPwd("123456");
    u1.setPwdConfirm("123456");
    u1.setUserType(1);

    User u2 = new User();
    u2.setName("u2");
    u2.setPwd("123456");
    u2.setPwdConfirm("123456");
    u2.setUserType(0);

    userService.addUser(u1);
    userService.addUser(u2);

    userService.save();

  }

  /**
   * 根据User对象中的 name 对userList内容进行过滤，如果name为空则查询全部
   * 否则只保留UserList中 包含name字符串的User对象
   *
   * @param user
   * @return
   */
  public List<User> getUserList(User user) {
    String queryName = user.getName();

    // 查询名字如果是null或去除首位空格后长度是0，表示查询条件无效，查询全部
    if (queryName == null || queryName.trim().length() == 0) {
      return getUserList();
    }

    List<User> rlt = new ArrayList<>();
    for (User one : userList) {
      if (one.getName().contains(queryName)) {
        rlt.add(one);
      }
    }

    return rlt;
  }

  public User getUserById(User user) {
    for (User one : userList) {
      if (one.getId() == user.getId()) {
        return one;
      }
    }

    // TODO
    return null;
  }

  public void updateUser(User user) {

    validateUserUpdate(user);

    // TODO 如果更新的用户id不存在，则抛出异常
    User userById = getUserById(user);
    userById.setUserType(user.getUserType());
    userById.setPwdConfirm(user.getPwdConfirm());
    userById.setName(user.getName());
    userById.setPwd(user.getPwd());
    save();
  }

  /**
   * 校验用户名密码和确认密码一致
   * <p>
   * 校验用户名不能和除了当前用户id以外的其他用户名一致
   *
   * @param user
   */
  private void validateUserUpdate(User user) {
    validatePwdConfirm(user);

    for (User one : userList) {
      if (one.getId() != user.getId() && one.getName().equals(user.getName())) {
        throw new BadReqException("用户名已存在!");
      }
    }

  }


  private void validatePwdConfirm(User user) {
    if (!user.getPwd().equals(user.getPwdConfirm())) {
      throw new BadReqException("两次输入的密码不一致！");
    }
  }


  public void deleteUser(User user) {
    boolean removed = userList.removeIf(one -> one.getId() == user.getId());
    if (removed) {
      save();
    } else {
      throw new BadReqException("用户不存在,id:" + user.getId());
    }
  }

}
