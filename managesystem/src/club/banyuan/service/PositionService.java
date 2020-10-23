package club.banyuan.service;

import club.banyuan.entity.Dept;
import club.banyuan.entity.Position;
import club.banyuan.exception.BadReqException;
import club.banyuan.request.ListDeptRequest;
import club.banyuan.request.ListPositionRequest;
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
import java.util.stream.Collectors;

/**
 * @author wangyibo
 */
public class PositionService {

    private static int idCount = 1;
    // 修改集合变为线程安全的集合
    private static List<Position> positionList = new ArrayList<>();

    Validate validate = new Validate();


    static {
        load();
    }

    public int getIdCount() {
        return idCount;
    }

    public void setIdCount(int idCount) {
        PositionService.idCount = idCount;
    }

    public List<Position> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<Position> positionList) {
        PositionService.positionList = positionList;
    }

    private static void load() {
        String filePath = PropUtil.getProp("position.store.path");
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            byte[] bytes = fileInputStream.readAllBytes();
            String jsonStr = new String(bytes);
            PositionService positionService = null;
            if (jsonStr == null || jsonStr.length() == 0) {
                positionService = new PositionService();
            } else {
                positionService = JSONObject.parseObject(jsonStr, PositionService.class);
            }
            idCount = positionService.getIdCount();
            positionList = positionService.getPositionList();
        } catch (IOException e) {
            // 读取用户信息文件失败，就创建一个空集合
            positionList = new ArrayList<>();
        }
    }

    private void save() {
        String s = JSONObject.toJSONString(this);
        System.out.println(s);
        try (FileOutputStream fileOutputStream = new FileOutputStream(
                PropUtil.getProp("position.store.path"))) {
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
     * @param listPositionRequest description%
     * @return
     * @author wangyibo
     */
    public PageBean<Position> getListPosition(ListPositionRequest listPositionRequest) {

        int totalDept = positionList.size();
        String name = listPositionRequest.getName();
        int page = listPositionRequest.getPage();
        int rows = listPositionRequest.getRows();
        PageBean<Position> pageBean = new PageBean();
        pageBean.setTotal(totalDept);
        if (name == null || name.length() == 0) {
            pageBean.setList(rows, page, positionList);
        } else {
            List<Position> searchPositiontList = positionList.stream().
                    filter(position -> position.getName().equals(name)).collect(Collectors.toList());
            int totalSelectSize = searchPositiontList.size();
            pageBean.setTotal(totalSelectSize);
            pageBean.setList(rows, page, searchPositiontList);
        }
        return pageBean;
    }

    public void addPosition(Position position) {
        position.setId(idCount++);
        positionList.add(position);
        save();
    }


    public void updatePosition(Position position) {
        validate.validatePositionAdd(positionList, position.getName());
        for (Position one : positionList) {
            if (one.getId() == position.getId()) {
                one.setName(position.getName());
                one.setDescription(position.getDescription());
                save();
            }
        }
    }


    public void deletePosition(List<Integer> positionIdList) {
        boolean flag = false;
        for (int i = 0; i < positionIdList.size(); i++) {
            for (int j = 0; j <positionList.size(); j++) {
                if (positionIdList.get(i) == positionList.get(j).getId()) {
                    positionList.remove(j);
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
        PositionService positionService = new PositionService();
        for (int i = 0; i < 30; i++) {
            Position position = new Position();
            int j = i + 1;
            position.setDescription("职位描述" + j);
            position.setName("职位" + j);
            positionService.addPosition(position);
        }
        positionService.save();
    }

}
