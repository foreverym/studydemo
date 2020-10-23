package club.banyuan.service;

import club.banyuan.entity.Dept;
import club.banyuan.exception.BadReqException;
import club.banyuan.request.ListDeptRequest;
import club.banyuan.util.PageBean;
import club.banyuan.util.PropUtil;
import club.banyuan.util.Validate;
import com.alibaba.fastjson.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * @author wangyibo
 */
public class DeptService {

    private static int idCount = 1;
    // 修改集合变为线程安全的集合
    private static List<Dept> deptList = new CopyOnWriteArrayList<>();
    Validate validate = new Validate();

    static {
        load();
    }

    public int getIdCount() {
        return idCount;
    }

    public void setIdCount(int idCount) {
        DeptService.idCount = idCount;
    }

    public List<Dept> getDeptList() {
        return deptList;
    }

    public void setDeptList(List<Dept> deptList) {
        DeptService.deptList = deptList;
    }


    private static void load() {
        String filePath = PropUtil.getProp("dept.store.path");
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            byte[] bytes = fileInputStream.readAllBytes();
            String jsonStr = new String(bytes);
            DeptService deptService = null;
            if (jsonStr == null || jsonStr.length() == 0) {
                deptService = new DeptService();
            } else {
                deptService = JSONObject.parseObject(jsonStr, DeptService.class);
            }
            idCount = deptService.getIdCount();
            deptList = deptService.getDeptList();
        } catch (IOException e) {
            // 读取用户信息文件失败，就创建一个空集合
            deptList = new ArrayList<>();
        }
    }

    private void save() {
        String s = JSONObject.toJSONString(this);
        System.out.println(s);
        try (FileOutputStream fileOutputStream = new FileOutputStream(
                PropUtil.getProp("dept.store.path"))) {
            fileOutputStream.write(s.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取部门列表
     *
     * @param listDeptRequest description%
     * @return
     * @author wangyibo
     */
    public PageBean<Dept> getListDept(ListDeptRequest listDeptRequest) {

        int totalDept = deptList.size();
        String name = listDeptRequest.getName();
        int page = listDeptRequest.getPage();
        int rows = listDeptRequest.getRows();
        PageBean<Dept> pageBean = new PageBean();
        pageBean.setTotal(totalDept);
        if (name == null || name.length() == 0) {
            pageBean.setList(rows, page, deptList);
        } else {
            List<Dept> searchDeptList = deptList.stream().
                    filter(dept -> dept.getName().equals(name)).collect(Collectors.toList());
            int totalSelectSize = searchDeptList.size();
            pageBean.setTotal(totalSelectSize);
            pageBean.setList(rows, page, searchDeptList);
        }
        return pageBean;
    }

    public void addDept(Dept dept) {
        validate.validateDeptAdd(deptList, dept.getName());
        dept.setId(idCount++);
        deptList.add(dept);
        save();
    }

    public void updateDept(Dept dept) {
        validate.validateDeptUpdate(deptList, dept.getName(), dept.getId());
        for (Dept one : deptList) {
            if (one.getId() == dept.getId()) {
                one.setName(dept.getName());
                one.setDescription(dept.getDescription());
                save();
            }
        }
    }

    public void deleteDept(List<Integer> deptIdList) {
        boolean flag = false;
        for (int i = 0; i < deptIdList.size(); i++) {
            for (int j = 0; j <deptList.size(); j++) {
                if (deptIdList.get(i) == deptList.get(j).getId()) {
                    deptList.remove(j);
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

    public static void main(String[] args) {
        DeptService deptService = new DeptService();
        for (int i = 0; i < 30; i++) {
            Dept dept = new Dept();
            int j = i + 1;
            dept.setDescription("部门描述" + j);
            dept.setName("部门" + j);
            deptService.addDept(dept);
        }
        System.out.println(deptList);
        deptService.save();
    }

}
