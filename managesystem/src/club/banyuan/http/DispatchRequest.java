package club.banyuan.http;

import club.banyuan.constant.Constant;
import club.banyuan.entity.*;
import club.banyuan.exception.BadReqException;
import club.banyuan.request.*;
import club.banyuan.service.AdminService;
import club.banyuan.service.DeptService;
import club.banyuan.service.EmployeeService;
import club.banyuan.service.PositionService;
import club.banyuan.util.PageBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DispatchRequest {

    private AdminService adminService = new AdminService();
    private DeptService deptService = new DeptService();
    private PositionService positionService = new PositionService();
    private EmployeeService employeeService = new EmployeeService();

    public Object dispatchAdminRequest(Request request) {
        String url = request.getUrl();
        switch (url) {
            case "/admin/login": {
                Admin admin = request.parseJsonObject(Admin.class);
                Admin loginAdmin = adminService.login(admin);
                if (loginAdmin == null) {
                    // 登录失败
                    throw new BadReqException("用户名或密码错误");
                } else {
                    // 登录成功
                    // sendSuccess(outputStream);
                    //写入cookie
                    request.getSession().put(Constant.Admin_INFO, loginAdmin);
                }
            }
            break;
            case "/admin/info": {
                String username = adminService.getAdminInfo(request);
                Map<String, Object> result = new HashMap<>();
                result.put("code", 0);
                result.put("message", "操作成功");
                result.put("username", username);
                return result;
            }
            case "/admin/list": {
                ListAdminRequest listAdminRequest = request.parseJsonObject(ListAdminRequest.class);
                PageBean<Admin> pageBean = adminService.getListAdmin(listAdminRequest);
                Map<String, Object> result = new HashMap<>();
                result.put("code", 0);
                result.put("message", "操作成功");
                result.put("total", pageBean.getTotal());
                result.put("rows", pageBean.getList());
                return result;
            }
            case "/admin/add": {
                Admin admin = request.parseJsonObject(Admin.class);
                adminService.addAdmin(admin);
            }
            break;
            case "/admin/delete": {
                DeleteRequest deleteRequest = request.parseJsonObject(DeleteRequest.class);
                adminService.deleteUser(deleteRequest.getIds());
            }
            break;
            case "/admin/quit": {
                Admin admin = (Admin) request.getSession().get(Constant.Admin_INFO);
                String username = admin.getUsername();
                request.getSession().put(Constant.Admin_INFO, null);
                Map<String, Object> result = new HashMap<>();
                result.put("code", 0);
                result.put("message", "操作成功");
                result.put("username", username);
                return result;
            }
            default:
                // TODO 异常
                break;

        }

        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("message", "操作成功");
        // 如果请求处理数据没有特殊的返回对象，统一返回一个操作成功的字符串
        return result;
    }

    public Object dispatchDeptRequest(Request request) {

        String url = request.getUrl();
        switch (url) {
            case "/dept/list": {
                ListDeptRequest listDeptRequest = request.parseJsonObject(ListDeptRequest.class);
                PageBean<Dept> pageBean = deptService.getListDept(listDeptRequest);
                Map<String, Object> result = new HashMap<>();
                result.put("code", 0);
                result.put("message", "操作成功");
                result.put("total", pageBean.getTotal());
                result.put("rows", pageBean.getList());
                return result;

            }
            case "/dept/add": {
                Dept dept = request.parseJsonObject(Dept.class);
                deptService.addDept(dept);
                Map<String, Object> result = new HashMap<>();
                result.put("code", 0);
                result.put("message", "");
            }
            break;
            case "/dept/update": {
                Dept dept = request.parseJsonObject(Dept.class);
                deptService.updateDept(dept);
            }
            break;
            case "/dept/delete": {
                DeleteRequest deleteRequest = request.parseJsonObject(DeleteRequest.class);
                System.out.println(deleteRequest.getIds());
                deptService.deleteDept(deleteRequest.getIds());
            }
            default:
                // TODO 异常
                break;
        }

        // 如果请求处理数据没有特殊的返回对象，统一返回一个操作成功的字符串
        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("message", "操作成功");
        return result;
    }


    public Object dispatchPositionRequest(Request request) {

        String url = request.getUrl();
        switch (url) {
            case "/position/list": {
                ListPositionRequest listPositionRequest = request.parseJsonObject(ListPositionRequest.class);
                PageBean<Position> pageBean = positionService.getListPosition(listPositionRequest);
                Map<String, Object> result = new HashMap<>();
                result.put("code", 0);
                result.put("message", "操作成功");
                result.put("total", pageBean.getTotal());
                result.put("rows", pageBean.getList());
                return result;
            }
            case "/position/add": {
                Position position = request.parseJsonObject(Position.class);
                positionService.addPosition(position);
            }
            break;
            case "/position/update": {
                Position position = request.parseJsonObject(Position.class);
                positionService.updatePosition(position);
            }
            break;
            case "/position/delete": {
                DeleteRequest deleteRequest = request.parseJsonObject(DeleteRequest.class);
                positionService.deletePosition(deleteRequest.getIds());
            }
            break;
            case "/position/getcombobox": {
                List<Position> positionList = positionService.getPositionList();
                return positionList;
            }
            default:
                // TODO 异常
                break;

        }

        // 如果请求处理数据没有特殊的返回对象，统一返回一个操作成功的字符串
        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("message", "操作成功");
        return result;
    }


    public Object dispatchEmployeeRequest(Request request) {

        String url = request.getUrl();
        switch (url) {
            case "/empl/list": {
                ListEmployeeRequest listEmployeeRequest = request.parseJsonObject(ListEmployeeRequest.class);
                PageBean<Employee> pageBean = employeeService.getListEmployee(listEmployeeRequest);
                Map<String, Object> result = new HashMap<>();
                result.put("code", 0);
                result.put("message", "操作成功");
                result.put("total", pageBean.getTotal());
                result.put("rows", pageBean.getList());
                return result;
            }
            case "/empl/add": {
                Dept dept = request.parseJsonObject(Dept.class);
                deptService.addDept(dept);
            }
            break;
            case "/empl/update": {
                Dept dept = request.parseJsonObject(Dept.class);
                deptService.updateDept(dept);
            }
            break;
            case "/empl/delete": {
                DeleteRequest deleteRequest = request.parseJsonObject(DeleteRequest.class);
                employeeService.deleteEmployee(deleteRequest.getIds());
            }
            default:
                // TODO 异常
                break;

        }

        // 如果请求处理数据没有特殊的返回对象，统一返回一个操作成功的字符串
        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("message", "操作成功");
        return result;
    }


}
