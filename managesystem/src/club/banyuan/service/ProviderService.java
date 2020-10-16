package club.banyuan.service;

import club.banyuan.util.PropUtil;
import club.banyuan.entity.Bill;
import club.banyuan.entity.Provider;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class ProviderService {

    private static int countId = 1;
    private static List<Provider> providerList = new ArrayList<>();

    public int getCountId() {
        return countId;
    }

    public void setCountId(int countId) {
        ProviderService.countId = countId;
    }

    public List<Provider> getProviderList() {
        return providerList;
    }

    public void setProviderList(List<Provider> providerList) {
        ProviderService.providerList = providerList;
    }

    static {
        load();
    }


    /**
     * 加载json文件
     */
    public static void load() {
        Properties properties = new Properties();
        InputStream resourceAsStream = ProviderService.class.getResourceAsStream("/app.properties");
        try {
            properties.load(resourceAsStream);
            String path = (String) properties.get("provider.store.path");
            InputStream inputStream = new FileInputStream(path);
            byte[] bytes = inputStream.readAllBytes();
            String providerJsonStr = new String(bytes);
            ProviderService providerService = JSONObject.parseObject(providerJsonStr, ProviderService.class);
            if (providerService == null) {
                providerService = new ProviderService();
            } else {
                providerList = providerService.getProviderList();
                countId = providerService.getCountId();
            }

        } catch (IOException e) {
            List<ProviderService> providerServiceList = new ArrayList<>();
            e.printStackTrace();
        }
    }

    /**
     * 保存供应商列表
     */
    public void save() {
        String jsonString = JSONObject.toJSONString(this);
        String path = PropUtil.getProp("provider.store.path");
        try (OutputStream outputStream = new FileOutputStream(path)) {
            outputStream.write(jsonString.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加一个供应商
     *
     * @param provider
     */
    public void addProvider(Provider provider) {
        validateAddProvider(provider);
        provider.setId(countId++);
        providerList.add(provider);
        save();
    }

    /**
     * 更新供应商信息
     *
     * @param provider
     */
    public void updateProvider(Provider provider) {
        for (Provider one : providerList) {
            if (provider.getId() == one.getId()) {
                one.setName(provider.getName());
                one.setDesc(provider.getDesc());
                one.setContactPerson(provider.getContactPerson());
                one.setPhone(provider.getPhone());
                save();
            }
        }
    }

    /**
     * 获取用户列表
     */
    public List<Provider> getListProvider(Provider provider) {

        if (provider == null) {
            return providerList;
        }
        String desc = provider.getDesc();
        String name = provider.getName();
        /**
         * 判断desc为空的条件
         */
        if (desc == null || desc.length() == 0) {
            if (desc == null || name.length() == 0) {
                return providerList;
            } else {
                List<Provider> result = new ArrayList<>();
                for (Provider one : providerList) {
                    if (one.getName().contains(provider.getName().trim())) {
                        result.add(one);
                    }
                }
                return result;
            }
        }
        /**
         * 判断name为空的条件
         */
        if (name == null || name.length() == 0) {
            if (desc == null || desc.length() == 0) {
                return providerList;
            } else {
                List<Provider> result = new ArrayList<>();
                result = providerList.stream().filter(one -> one.getDesc().contains(provider.getDesc().trim())).collect(Collectors.toList());
                return result;
            }
        } else {
            List<Provider> result = providerList.stream().filter(one -> one.getDesc().contains(provider.getDesc().trim())
                    && one.getName().contains(provider.getName().trim())).collect(Collectors.toList());
            return result;
        }

    }

    /**
     * 查询用户信息
     */
    public Provider getProviderInfo(Provider provider) {
        for (Provider one : providerList) {
            if (one.getId() == provider.getId()) {
                return one;
            }
        }
        return null;
    }

    /**
     * 删除供应商信息
     */
    public void deleteProvider(Provider provider) {

        //先获取账单列表，看是否包含该供应商id
        BillService billService = new BillService();
        List<Bill> billList = billService.getBillList();
        int id = provider.getId();
        boolean flag = false;
        for (Bill bill : billList) {
            if (bill.getProviderId() == provider.getId()) {
                flag = true;
            }
        }

        if (flag == false) {
            Iterator<Provider> iterator = providerList.iterator();
            while (iterator.hasNext()) {
                Provider next = iterator.next();
                if (next.getId() == id) {
                    iterator.remove();
                }
            }
        }

    }

    /**
     * 校验添加的数据是否合法
     */
    public void validateAddProvider(Provider provider) {

    }




    public static void main(String[] args) {
        Provider provider = new Provider(1, "ssss", "ss", "", "");

        ProviderService userService = new ProviderService();
        userService.providerList.add(provider);
        String s = JSONObject.toJSONString(userService);
        System.out.println(s);
    }



}
