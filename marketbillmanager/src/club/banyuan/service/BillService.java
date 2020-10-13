package club.banyuan.service;

import club.banyuan.util.PropUtil;
import club.banyuan.entity.Bill;
import club.banyuan.entity.Provider;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class BillService {

    private static int countId = 0;
    private static List<Bill> billList = new ArrayList<>();

    public  int getCountId() {
        return countId;
    }

    public  void setCountId(int countId) {
        BillService.countId = countId;
    }

    public  List<Bill> getBillList() {
        return billList;
    }

    public  void setBillList(List<Bill> providerList) {
        BillService.billList = providerList;
    }

    static {
        load();
    }


    /**
     * 加载json文件
     */
    public static void load() {
        Properties properties = new Properties();
        InputStream resourceAsStream = BillService.class.getResourceAsStream("/app.properties");
        try {
            properties.load(resourceAsStream);
            String path = (String) properties.get("bill.store.path");
            InputStream inputStream = new FileInputStream(path);
            byte[] bytes = inputStream.readAllBytes();
            String billJsonStr = new String(bytes);
            BillService billService = JSONObject.parseObject(billJsonStr, BillService.class);
            if (billService == null) {
                billService = new BillService();
            } else {
                billList = billService.getBillList();
                countId = billService.getCountId();
            }

        } catch (IOException e) {
            List<BillService> providerServiceList = new ArrayList<>();
            e.printStackTrace();
        }
    }

    /**
     * 保存供应商列表
     */
    public void save() {
        String jsonString = JSONObject.toJSONString(this);
        String path = PropUtil.getProp("bill.store.path");
        try(OutputStream outputStream = new FileOutputStream(path)) {
            outputStream.write(jsonString.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * 添加一个供应商
     * @param bill
     *
     */
    public void addBill(Bill bill) {
        bill.setId(countId++);
        billList.add(bill);
        save();
    }

    /**
     * 更新供应商信息
     * @param provider
     */
    public void updateProvider(Provider provider) {
        for (Bill one : billList) {
            if (provider.getId() == one.getId()) {
//                one.setName(provider.getName());
//                one.setDesc(provider.getDesc());
//                one.setContactPerson(provider.getContactPerson());
//                one.setPhone(provider.getPhone());
                save();
            }
        }
    }

    /**
     * 获取用户列表
     */
    public List<Bill> getListProvider(Provider provider) {
        String desc = provider.getDesc();
        String name = provider.getName();
        if (desc == null || desc.length() == 0) {
            if (desc == null || name.length() == 0) {
                return billList;
            } else {
                List<Bill> result = new ArrayList<>();
//                for (Bill one : billList) {
//                    if (one.getName().contains(provider.getName().trim())) {
//                        result.add(one);
//                    }
//                }
                return result;
            }
        }
        if (name == null || name.length() == 0) {
            if (desc == null || desc.length() == 0) {
                return billList;
            } else {
                List<Bill> result = new ArrayList<>();
                result = billList.stream().filter(one -> one.getProduct().contains(provider.getDesc().trim())).collect(Collectors.toList());
//                for (Provider one : providerList) {
//                    if (one.getDesc().contains(provider.getDesc().trim())) {
//                        result.add(one);
//                    }
//                }
                return result;
            }
        }

        return billList;
    }

    /**
     * 查询用户信息
     */
    public Bill getProviderInfo(Bill bill) {
        for (Bill one : billList) {
            if (one.getId() == bill.getId()) {
                return one;
            }
        }
        return null;
    }

    /**
     *校验添加的数据是否合法
     */
    public void validateAddProvider(Provider provider) {

    }





}
