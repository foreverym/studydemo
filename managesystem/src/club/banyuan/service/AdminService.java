package club.banyuan.service;

import club.banyuan.constant.Constant;
import club.banyuan.entity.Admin;
import club.banyuan.entity.Dept;
import club.banyuan.entity.Request;
import club.banyuan.exception.BadReqException;
import club.banyuan.request.ListAdminRequest;
import club.banyuan.util.PageBean;
import club.banyuan.util.PropUtil;
import club.banyuan.util.ServerSession;
import club.banyuan.util.SessionUtil;
import com.alibaba.fastjson.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
     * @param listAdminRequest
     * @return
     */
    public PageBean<Admin> getListAdmin(ListAdminRequest listAdminRequest) {
        int totalAdmin = adminList.size();
        String name = listAdminRequest.getUsername();
        int page = listAdminRequest.getPage();
        int rows = listAdminRequest.getRows();
        PageBean<Admin> pageBean = new PageBean();
        pageBean.setTotal(totalAdmin);
        if (name == null || name.length() == 0) {
            pageBean.setList(rows, page, adminList);
        } else {
            List<Admin> searchDeptList = adminList.stream().
                    filter(admin -> admin.getUsername().equals(name)).collect(Collectors.toList());
            int totalSelectSize = searchDeptList.size();
            pageBean.setTotal(totalSelectSize);
            pageBean.setList(rows, page, searchDeptList);
        }
        return pageBean;
    }



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


    public void deleteUser(List<Integer> adminIdList) {
        boolean flag = false;
        for (int i = 0; i < adminIdList.size(); i++) {
            for (int j = 0; j <adminList.size(); j++) {
                if (adminIdList.get(i) == adminList.get(j).getId()) {
                    adminList.remove(j);
                }
            }
            flag = true;
        }
        if (flag) {
            save();
        } else {
            throw new BadReqException("删除失败");
        }
    }

    public String getAdminInfo(Request request) {
        Admin admin = (Admin) request.getSession().get(Constant.Admin_INFO);
        return admin.getUsername();
    }

    public static void main(String[] args) {
        AdminService adminService = new AdminService();
        for (int i = 0; i < 30; i++) {
            Admin admin = new Admin();
            admin.setPassword("admin");
            admin.setUsername("admin" + i);
            adminService.addAdmin(admin);
        }
        adminService.save();
    }


}
