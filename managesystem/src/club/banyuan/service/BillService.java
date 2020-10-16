package club.banyuan.service;

import club.banyuan.util.PropUtil;
import club.banyuan.entity.Bill;
import club.banyuan.entity.Provider;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class BillService {

    private static int countId = 1;
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
     * @param bill
     */
    public void updateBill(Bill bill) {
        for (Bill one : billList) {
            if (bill.getId() == one.getId()) {
                one.setProviderId(bill.getProviderId());
                one.setMoney(bill.getMoney());
                one.setProduct(bill.getProduct());
                one.setIsPay(bill.getIsPay());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = simpleDateFormat.format(new Date());
                one.setUpdateTime(date);
                save();
            }
        }
    }

    /**
     * 获取账单列表
     */
    public List<Bill> getListBill(Bill bill) {
        ProviderService providerService = new ProviderService();
        List<Provider> providerList = providerService.getProviderList();
//        for (Bill bill1 : billList) {
//            int providerid = bill.getProviderId();
//            if ()
//        }



        for (int i = 0; i < billList.size(); i++) {
            for (int j = 0; j < providerList.size(); j++) {
                if (billList.get(i).getProviderId() == providerList.get(j).getId()) {
                    billList.get(i).setProviderName(providerList.get(j).getName());
                }
            }
        }
        List<Bill> result = new ArrayList<>();
        String product = bill.getProduct();
        int isPay = bill.getIsPay();
        if (product == null || product.length() == 0) {
            result = billList.stream().filter(one -> one.getIsPay() == isPay).collect(Collectors.toList());
        }
        else {
            result = billList.stream().filter(
                    one -> one.getIsPay() == isPay && one.getProduct().contains(product.trim())).
                    collect(Collectors.toList());
        }

        return billList;
    }

    /**
     * 查询用户信息
     */
    public Bill getBillInfo(Bill bill) {
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
