package club.banyuan;

public class MainMenu {

    public static final String CAR_ADD_DESCRIPTION = "1. 添加车辆";
    public static final String CAR_FIND_DESCRIPTION = "2. 查询车辆";
    public static final String CAR_RENT_DESCRIPTION = "3. 出租车辆";
    public static final String CAR_RETURN_DESCRIPTION = "4. 归还车辆";
    public static final String CAR_OUT_DESCRIPTION = "0. 退出系统";

    public static void showMenu() {
        System.out.println("================");
        System.out.println(CAR_ADD_DESCRIPTION);
        System.out.println(CAR_FIND_DESCRIPTION);
        System.out.println(CAR_RENT_DESCRIPTION);
        System.out.println(CAR_RETURN_DESCRIPTION);
        System.out.println(CAR_OUT_DESCRIPTION);
    }

}
