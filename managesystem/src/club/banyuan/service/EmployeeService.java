package club.banyuan.service;

import club.banyuan.entity.Dept;
import club.banyuan.entity.Employee;
import club.banyuan.exception.BadReqException;
import club.banyuan.request.ListDeptRequest;
import club.banyuan.request.ListEmployeeRequest;
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
public class EmployeeService {

    private static int idCount = 1;
    // 修改集合变为线程安全的集合
    private static List<Employee> employeeList = new CopyOnWriteArrayList<>();
    Validate validate = new Validate();

    static {
        load();
    }

    public int getIdCount() {
        return idCount;
    }

    public void setIdCount(int idCount) {
        EmployeeService.idCount = idCount;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        EmployeeService.employeeList = employeeList;
    }


    private static void load() {
        String filePath = PropUtil.getProp("employee.store.path");
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            byte[] bytes = fileInputStream.readAllBytes();
            String jsonStr = new String(bytes);
            EmployeeService employeeService = null;
            if (jsonStr == null || jsonStr.length() == 0) {
                employeeService = new EmployeeService();
            } else {
                employeeService = JSONObject.parseObject(jsonStr, EmployeeService.class);
            }
            idCount = employeeService.getIdCount();
            employeeList = employeeService.getEmployeeList();
        } catch (IOException e) {
            // 读取用户信息文件失败，就创建一个空集合
            employeeList = new ArrayList<>();
        }
    }

    private void save() {
        String s = JSONObject.toJSONString(this);
        System.out.println(s);
        try (FileOutputStream fileOutputStream = new FileOutputStream(
                PropUtil.getProp("employee.store.path"))) {
            fileOutputStream.write(s.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取员工列表
     *
     * @param listEmployeeRequest description%
     * @return
     * @author wangyibo
     */
    public PageBean<Employee> getListEmployee(ListEmployeeRequest listEmployeeRequest) {

        int totalDept = employeeList.size();
        Integer id = listEmployeeRequest.getId();
        String name = listEmployeeRequest.getName();
        String departmentName = listEmployeeRequest.getDepartmentName();
        String position = listEmployeeRequest.getPosition();
        String sex = listEmployeeRequest.getSex();
        int page = listEmployeeRequest.getPage();
        int rows = listEmployeeRequest.getRows();
        PageBean<Employee> pageBean = new PageBean();
        pageBean.setTotal(totalDept);
        if (id == null) {
            if (name == null || name.length() == 0) {
                //departmentName为空
                if (departmentName == null || departmentName.length() == 0) {
                    //position为空
                    if (position == null || position.length() == 0) {
                        if (sex == null || sex.length() == 0) {
                            //全为空的状态
                            pageBean.setList(rows, page, employeeList);
                        } else {
                            //只有sex不为空的条件
                            List<Employee> searchEmployeeList = employeeList.stream().
                                    filter(employee -> employee.getSex().equals(sex)).collect(Collectors.toList());
                            int totalSelectSize = searchEmployeeList.size();
                            pageBean.setTotal(totalSelectSize);
                            pageBean.setList(rows, page, searchEmployeeList);
                        }
                        //position不为空
                    } else {
                        //position不为空    sex为空
                        if (sex == null || sex.length() == 0) {
                            List<Employee> searchEmployeeList = employeeList.stream().
                                    filter(employee -> employee.getPositionName().equals(position)).collect(Collectors.toList());
                            int totalSelectSize = searchEmployeeList.size();
                            pageBean.setTotal(totalSelectSize);
                            pageBean.setList(rows, page, searchEmployeeList);
                        } else {
                            //position不为空   sex不为空
                            List<Employee> searchEmployeeList = employeeList.stream().
                                    filter(employee -> employee.getPositionName().equals(position) && employee.getSex().equals(sex)).collect(Collectors.toList());
                            int totalSelectSize = searchEmployeeList.size();
                            pageBean.setTotal(totalSelectSize);
                            pageBean.setList(rows, page, searchEmployeeList);
                        }
                    }
                    //departmentName不为空
                } else {
                    if (position == null || position.length() == 0) {
                        if (sex == null || sex.length() == 0) {
                            //departmentName不为空  position为空  sex为空
                            List<Employee> searchEmployeeList = employeeList.stream().
                                    filter(employee -> employee.getDepartmentName().equals(departmentName)).collect(Collectors.toList());
                            int totalSelectSize = searchEmployeeList.size();
                            pageBean.setTotal(totalSelectSize);
                            pageBean.setList(rows, page, searchEmployeeList);
                        } else {
                            //departmentName不为空  position为空  sex不为空
                            List<Employee> searchEmployeeList = employeeList.stream().
                                    filter(employee -> employee.getDepartmentName().equals(departmentName) && employee.getSex().equals(sex)).collect(Collectors.toList());
                            int totalSelectSize = searchEmployeeList.size();
                            pageBean.setTotal(totalSelectSize);
                            pageBean.setList(rows, page, searchEmployeeList);
                        }
                    } else {
                        if (sex == null || sex.length() == 0) {
                            //departmentName不为空  position不为空  sex为空
                            List<Employee> searchEmployeeList = employeeList.stream().
                                    filter(employee -> employee.getDepartmentName().equals(departmentName) && employee.getPositionName().equals(position)).collect(Collectors.toList());
                            int totalSelectSize = searchEmployeeList.size();
                            pageBean.setTotal(totalSelectSize);
                            pageBean.setList(rows, page, searchEmployeeList);
                        } else {
                            //departmentName不为空  position不为空  sex不为空
                            List<Employee> searchEmployeeList = employeeList.stream().
                                    filter(employee -> employee.getDepartmentName().equals(departmentName) && employee.getPositionName().equals(position) && employee.getSex().equals(sex)).collect(Collectors.toList());
                            int totalSelectSize = searchEmployeeList.size();
                            pageBean.setTotal(totalSelectSize);
                            pageBean.setList(rows, page, searchEmployeeList);
                        }
                    }
                }
                //name不为空
            } else {
                if (departmentName == null || departmentName.length() == 0) {
                    if (position == null || position.length() == 0) {
                        if (sex == null || sex.length() == 0) {
                            //name不为空 departmentName为空  position为空  sex为空
                            List<Employee> searchEmployeeList = employeeList.stream().
                                    filter(employee -> employee.getName().equals(name)).collect(Collectors.toList());
                            int totalSelectSize = searchEmployeeList.size();
                            pageBean.setTotal(totalSelectSize);
                            pageBean.setList(rows, page, searchEmployeeList);
                        } else {
                            //name不为空 departmentName为空  position为空  sex不为空
                            List<Employee> searchEmployeeList = employeeList.stream().
                                    filter(employee -> employee.getName().equals(name) && employee.getSex().equals(sex)).collect(Collectors.toList());
                            int totalSelectSize = searchEmployeeList.size();
                            pageBean.setTotal(totalSelectSize);
                            pageBean.setList(rows, page, searchEmployeeList);
                        }
                    } else {
                        if (sex == null || sex.length() == 0) {
                            //name不为空 departmentName为空  position不为空  sex为空
                            List<Employee> searchEmployeeList = employeeList.stream().
                                    filter(employee -> employee.getName().equals(name) && employee.getPositionName().equals(position)).collect(Collectors.toList());
                            int totalSelectSize = searchEmployeeList.size();
                            pageBean.setTotal(totalSelectSize);
                            pageBean.setList(rows, page, searchEmployeeList);
                        } else {
                            //name不为空 departmentName为空  position不为空  sex不为空
                            List<Employee> searchEmployeeList = employeeList.stream().
                                    filter(employee -> employee.getName().equals(name) && employee.getPositionName().equals(position) && employee.getSex().equals(sex)).collect(Collectors.toList());
                            int totalSelectSize = searchEmployeeList.size();
                            pageBean.setTotal(totalSelectSize);
                            pageBean.setList(rows, page, searchEmployeeList);
                        }

                    }
                } else {
                    if (position == null || position.length() == 0) {
                        if (sex == null || sex.length() == 0) {
                            //departmentName不为空  position为空  sex为空
                            List<Employee> searchEmployeeList = employeeList.stream().
                                    filter(employee -> employee.getName().equals(name) && employee.getDepartmentName().equals(departmentName)).collect(Collectors.toList());
                            int totalSelectSize = searchEmployeeList.size();
                            pageBean.setTotal(totalSelectSize);
                            pageBean.setList(rows, page, searchEmployeeList);
                        } else {
                            //departmentName不为空  position为空  sex不为空
                            List<Employee> searchEmployeeList = employeeList.stream().
                                    filter(employee -> employee.getName().equals(name) && employee.getDepartmentName().equals(departmentName) && employee.getSex().equals(sex)).collect(Collectors.toList());
                            int totalSelectSize = searchEmployeeList.size();
                            pageBean.setTotal(totalSelectSize);
                            pageBean.setList(rows, page, searchEmployeeList);
                        }
                    } else {
                        if (sex == null || sex.length() == 0) {
                            //departmentName不为空  position不为空  sex为空
                            List<Employee> searchEmployeeList = employeeList.stream().
                                    filter(employee -> employee.getName().equals(name) && employee.getDepartmentName().equals(departmentName) && employee.getPositionName().equals(position)).collect(Collectors.toList());
                            int totalSelectSize = searchEmployeeList.size();
                            pageBean.setTotal(totalSelectSize);
                            pageBean.setList(rows, page, searchEmployeeList);
                        } else {
                            //departmentName不为空  position不为空  sex不为空
                            List<Employee> searchEmployeeList = employeeList.stream().
                                    filter(employee -> employee.getName().equals(name) && employee.getDepartmentName().equals(departmentName) && employee.getPositionName().equals(position) && employee.getSex().equals(sex)).collect(Collectors.toList());
                            int totalSelectSize = searchEmployeeList.size();
                            pageBean.setTotal(totalSelectSize);
                            pageBean.setList(rows, page, searchEmployeeList);
                        }
                    }

                }
            }
            //id不为空
        } else {
            if (name == null || name.length() == 0) {
                if (departmentName == null || departmentName.length() == 0) {
                    if (position == null || position.length() == 0) {
                        if (sex == null || sex.length() == 0) {
                            List<Employee> searchEmployeeList = employeeList.stream().
                                    filter(employee -> employee.getId() == id).collect(Collectors.toList());
                            int totalSelectSize = searchEmployeeList.size();
                            pageBean.setTotal(totalSelectSize);
                            pageBean.setList(rows, page, searchEmployeeList);

                        } else {
                            //sex不为空的条件
                            List<Employee> searchEmployeeList = employeeList.stream().
                                    filter(employee -> employee.getId() == id && employee.getSex().equals(sex)).collect(Collectors.toList());
                            int totalSelectSize = searchEmployeeList.size();
                            pageBean.setTotal(totalSelectSize);
                            pageBean.setList(rows, page, searchEmployeeList);
                        }
                    } else {
                        //position不为空    sex为空
                        if (sex == null || sex.length() == 0) {
                            List<Employee> searchEmployeeList = employeeList.stream().
                                    filter(employee -> employee.getId() == id && employee.getPositionName().equals(position)).collect(Collectors.toList());
                            int totalSelectSize = searchEmployeeList.size();
                            pageBean.setTotal(totalSelectSize);
                            pageBean.setList(rows, page, searchEmployeeList);
                        } else {
                            //position不为空   sex不为空
                            List<Employee> searchEmployeeList = employeeList.stream().
                                    filter(employee -> employee.getId() == id && employee.getPositionName().equals(position) && employee.getSex().equals(sex)).collect(Collectors.toList());
                            int totalSelectSize = searchEmployeeList.size();
                            pageBean.setTotal(totalSelectSize);
                            pageBean.setList(rows, page, searchEmployeeList);
                        }
                    }
                } else {
                    if (position == null || position.length() == 0) {
                        if (sex == null || sex.length() == 0) {
                            //departmentName不为空  position为空  sex为空
                            List<Employee> searchEmployeeList = employeeList.stream().
                                    filter(employee -> employee.getId() == id && employee.getDepartmentName().equals(departmentName)).collect(Collectors.toList());
                            int totalSelectSize = searchEmployeeList.size();
                            pageBean.setTotal(totalSelectSize);
                            pageBean.setList(rows, page, searchEmployeeList);
                        } else {
                            //departmentName不为空  position为空  sex不为空
                            List<Employee> searchEmployeeList = employeeList.stream().
                                    filter(employee -> employee.getId() == id && employee.getDepartmentName().equals(departmentName) && employee.getSex().equals(sex)).collect(Collectors.toList());
                            int totalSelectSize = searchEmployeeList.size();
                            pageBean.setTotal(totalSelectSize);
                            pageBean.setList(rows, page, searchEmployeeList);
                        }
                    } else {
                        if (sex == null || sex.length() == 0) {
                            //departmentName不为空  position不为空  sex为空
                            List<Employee> searchEmployeeList = employeeList.stream().
                                    filter(employee -> employee.getId() == id && employee.getDepartmentName().equals(departmentName) && employee.getPositionName().equals(position)).collect(Collectors.toList());
                            int totalSelectSize = searchEmployeeList.size();
                            pageBean.setTotal(totalSelectSize);
                            pageBean.setList(rows, page, searchEmployeeList);
                        } else {
                            //departmentName不为空  position不为空  sex不为空
                            List<Employee> searchEmployeeList = employeeList.stream().
                                    filter(employee -> employee.getId() == id && employee.getDepartmentName().equals(departmentName) && employee.getPositionName().equals(position) && employee.getSex().equals(sex)).collect(Collectors.toList());
                            int totalSelectSize = searchEmployeeList.size();
                            pageBean.setTotal(totalSelectSize);
                            pageBean.setList(rows, page, searchEmployeeList);
                        }
                    }
                }
            } else {
                if (departmentName == null || departmentName.length() == 0) {
                    if (position == null || position.length() == 0) {
                        if (sex == null || sex.length() == 0) {
                            //name不为空 departmentName为空  position为空  sex为空
                            List<Employee> searchEmployeeList = employeeList.stream().
                                    filter(employee -> employee.getId() == id && employee.getName().equals(name)).collect(Collectors.toList());
                            int totalSelectSize = searchEmployeeList.size();
                            pageBean.setTotal(totalSelectSize);
                            pageBean.setList(rows, page, searchEmployeeList);
                        } else {
                            //name不为空 departmentName为空  position为空  sex不为空
                            List<Employee> searchEmployeeList = employeeList.stream().
                                    filter(employee -> employee.getId() == id && employee.getName().equals(name) && employee.getSex().equals(sex)).collect(Collectors.toList());
                            int totalSelectSize = searchEmployeeList.size();
                            pageBean.setTotal(totalSelectSize);
                            pageBean.setList(rows, page, searchEmployeeList);
                        }
                    } else {
                        if (sex == null || sex.length() == 0) {
                            //name不为空 departmentName为空  position不为空  sex为空
                            List<Employee> searchEmployeeList = employeeList.stream().
                                    filter(employee -> employee.getId() == id && employee.getName().equals(name) && employee.getPositionName().equals(position)).collect(Collectors.toList());
                            int totalSelectSize = searchEmployeeList.size();
                            pageBean.setTotal(totalSelectSize);
                            pageBean.setList(rows, page, searchEmployeeList);
                        } else {
                            //name不为空 departmentName为空  position不为空  sex不为空
                            List<Employee> searchEmployeeList = employeeList.stream().
                                    filter(employee -> employee.getId() == id && employee.getName().equals(name) && employee.getPositionName().equals(position) && employee.getSex().equals(sex)).collect(Collectors.toList());
                            int totalSelectSize = searchEmployeeList.size();
                            pageBean.setTotal(totalSelectSize);
                            pageBean.setList(rows, page, searchEmployeeList);
                        }

                    }
                } else {
                    if (position == null || position.length() == 0) {
                        if (sex == null || sex.length() == 0) {
                            //departmentName不为空  position为空  sex为空
                            List<Employee> searchEmployeeList = employeeList.stream().
                                    filter(employee -> employee.getId() == id && employee.getName().equals(name) && employee.getDepartmentName().equals(departmentName)).collect(Collectors.toList());
                            int totalSelectSize = searchEmployeeList.size();
                            pageBean.setTotal(totalSelectSize);
                            pageBean.setList(rows, page, searchEmployeeList);
                        } else {
                            //departmentName不为空  position为空  sex不为空
                            List<Employee> searchEmployeeList = employeeList.stream().
                                    filter(employee -> employee.getId() == id && employee.getName().equals(name) && employee.getDepartmentName().equals(departmentName) && employee.getSex().equals(sex)).collect(Collectors.toList());
                            int totalSelectSize = searchEmployeeList.size();
                            pageBean.setTotal(totalSelectSize);
                            pageBean.setList(rows, page, searchEmployeeList);
                        }
                    } else {
                        if (sex == null || sex.length() == 0) {
                            //departmentName不为空  position不为空  sex为空
                            List<Employee> searchEmployeeList = employeeList.stream().
                                    filter(employee -> employee.getId() == id && employee.getName().equals(name) && employee.getDepartmentName().equals(departmentName) && employee.getPositionName().equals(position)).collect(Collectors.toList());
                            int totalSelectSize = searchEmployeeList.size();
                            pageBean.setTotal(totalSelectSize);
                            pageBean.setList(rows, page, searchEmployeeList);
                        } else {
                            //departmentName不为空  position不为空  sex不为空
                            List<Employee> searchEmployeeList = employeeList.stream().
                                    filter(employee -> employee.getId() == id && employee.getName().equals(name) && employee.getDepartmentName().equals(departmentName) && employee.getPositionName().equals(position) && employee.getSex().equals(sex)).collect(Collectors.toList());
                            int totalSelectSize = searchEmployeeList.size();
                            pageBean.setTotal(totalSelectSize);
                            pageBean.setList(rows, page, searchEmployeeList);
                        }
                    }

                }
            }
        }
        return pageBean;
    }

    public void addEmployee(Employee employee) {
        employee.setId(idCount++);
        employeeList.add(employee);
        save();
    }

    public void updateEmployee(Employee employee) {
        //validate.validateEmployeeUpdate(deptList, dept.getName(), dept.getId());
        for (Employee one : employeeList) {
            if (one.getId() == employee.getId()) {
                one.setName(employee.getName());
                //one.setDescription(dept.getDescription());
                save();
            }
        }
    }

    public void deleteEmployee(List<Integer> employeeIdList) {
        boolean flag = false;
        for (int i = 0; i < employeeIdList.size(); i++) {
            for (int j = 0; j <employeeList.size(); j++) {
                if (employeeIdList.get(i) == employeeList.get(j).getId()) {
                    employeeList.remove(j);
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
        EmployeeService employeeService = new EmployeeService();
        for (int i = 0; i < 30; i++) {
            Employee employee = new Employee();
            int j = i + 1;
            employee.setAddress("南京栖霞区");
            employee.setName("员工"+j);
            employee.setEducation("本科");
            employee.setPositionId(1);
            employee.setPositionName("员工");
            employee.setDepartmentId(36);
            employee.setDepartmentName("人事部");

            employeeService.addEmployee(employee);
        }
        //System.out.println(deptList);
        employeeService.save();
    }


}
