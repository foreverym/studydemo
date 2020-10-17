package club.banyuan.http;

import club.banyuan.constant.Constant;
import club.banyuan.entity.Admin;
import club.banyuan.entity.Request;
import club.banyuan.entity.User;
import club.banyuan.service.AdminService;

public class DispatchRequest {

    AdminService adminService = new AdminService();

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
                    request.getSession().put(Constant.USER_INFO, loginAdmin);
                }
            }
            break;
            case "/admin/add": {
                Admin admin = request.parseJsonObject(Admin.class);
                adminService.addAdmin(admin);
            }

            case "/server/user/add": {

            }
            break;
            case "/server/user/get": {

            }
            default:
                // TODO 异常
                break;

        }

        // 如果请求处理数据没有特殊的返回对象，统一返回一个操作成功的字符串
        return "操作成功";
    }

    public Object dispatchAdminRequest()

}
