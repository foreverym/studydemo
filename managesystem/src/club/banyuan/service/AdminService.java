package club.banyuan.service;

import club.banyuan.entity.Admin;
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

public class AdminService {

  private static int idCount = 1;
  private static List<Admin> adminList = new ArrayList<>();

  // 对象创建的时候，从硬盘读取用户列表数据，反序列化为 User List
  static {
    load();
  }


  public int getIdCount() {
    return idCount;
  }

  public void setIdCount(int idCount) {
    AdminService.idCount = idCount;
  }

  public void setUserList(List<Admin> userList) {
    this.adminList = adminList;
  }

  public List<Admin> getAdminList() {
    return adminList;
  }


  private static void load() {
    String filePath = PropUtil.getProp("admin.store.path");
    try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
      byte[] bytes = fileInputStream.readAllBytes();
      String jsonStr = new String(bytes);
      AdminService adminService = null;
      if (jsonStr == null || jsonStr.length() == 0) {
        adminService = new AdminService();
      } else {
        adminService = JSONObject.parseObject(jsonStr, AdminService.class);
      }
      idCount = adminService.getIdCount();
      adminList = adminService.getAdminList();
    } catch (IOException e) {
      // 读取用户信息文件失败，就创建一个空集合
      adminList = new ArrayList<>();
    }
  }

  /**
   * 用户信息保存到文件中
   */
  private void save() {
    String s = JSONObject.toJSONString(this);
    try (FileOutputStream fileOutputStream = new FileOutputStream(
        PropUtil.getProp("admin.store.path"))) {
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
   * @param admin
   * @return
   */
  public Admin login(Admin admin) {
    for (Admin one : adminList) {
      if (one.getUsername().equals(admin.getUsername()) && one.getPassword().equals(admin.getPassword())) {
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
   * @param admin
   */
  public void addAdmin(Admin admin) {
    //validateUserAdd(admin);
    admin.setId(idCount++);
    adminList.add(admin);
    // 添加完成后写入硬盘
    save();
  }



  /**
   * 根据User对象中的 name 对userList内容进行过滤，如果name为空则查询全部
   * 否则只保留UserList中 包含name字符串的User对象
   *
   * @param admin
   * @return
   */
  public List<Admin> getListAdmin(Admin admin) {
    String queryName = admin.getUsername();

    // 查询名字如果是null或去除首位空格后长度是0，表示查询条件无效，查询全部
    if (queryName == null || queryName.trim().length() == 0) {
      return adminList;
    }

    List<Admin> rlt = new ArrayList<>();
    for (Admin one : adminList) {
      if (one.getUsername().contains(queryName)) {
        rlt.add(one);
      }
    }

    return rlt;
  }


//  public void updateUser(User user) {
//
//    validateUserUpdate(user);
//
//    // TODO 如果更新的用户id不存在，则抛出异常
//    User userById = getUserById(user);
//    userById.setUserType(user.getUserType());
//    userById.setPwdConfirm(user.getPwdConfirm());
//    userById.setName(user.getName());
//    userById.setPwd(user.getPwd());
//    save();
//  }

  /**
   * 校验用户名密码和确认密码一致
   * <p>
   * 校验用户名不能和除了当前用户id以外的其他用户名一致
   *
   * @param admin
   */
  private void validateUserUpdate(Admin admin) {
    //validatePwdConfirm(admin);

    for (Admin one : adminList) {
      if (one.getId() != admin.getId() && one.getUsername().equals(admin.getUsername())) {
        throw new BadReqException("用户名已存在!");
      }
    }

  }


  private void validatePwdConfirm(User user) {
    if (!user.getPwd().equals(user.getPwdConfirm())) {
      throw new BadReqException("两次输入的密码不一致！");
    }
  }


  public void deleteUser(Admin admin) {
    boolean removed = adminList.removeIf(one -> one.getId() == admin.getId());
    if (removed) {
      save();
    } else {
      throw new BadReqException("用户不存在,id:" + admin.getId());
    }
  }

}
