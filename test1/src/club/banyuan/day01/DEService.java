package club.banyuan.day01;

import java.util.function.Function;

public class DEService {

    /**
     * 查找苏州所有的部门
     * @param depts
     * //@param location
     * @return
     */
    public String[] findDeptByShuZhou(Dept[] depts) {
        String[] deptNames = new String[depts.length];
        Function<String, String[]> function = (name) -> {

            int j = 0;
            for (int i = 0; i < depts.length; i++) {
                if (depts[i].getLocation().equals("苏州")) {
                    deptNames[j] = depts[i].getName();
                    j++;
                }
            }
            return deptNames;
        };
        return deptNames;
    }

    /**
     * 查找出入职超过三年的所有员工
     */
    public Employee[] findByTime(Employee[] employees) {
        Employee[] conditionEmployees = new Employee[employees.length];
        Function<Integer, Employee[]> function = (name) -> {

            int j = 0;
            for (int i = 0; i < employees.length; i++) {
                if (employees[i].getTime() > 3) {
                    conditionEmployees[j] = employees[i];
                    j++;
                }
            }
            return conditionEmployees;
        };
        return conditionEmployees;
    }

    /**
     * 找出工资在前三的员工
     */
//    public Employee[] findEmployeeInThree(Employee[] employees) {
//        Employee[] conditionEmployees = new Employee[3];
//        for (int i = 0; i < 2; i++) {
//            for (int j = 0; j < employees.length-i-1; j++) {
//                //if ()
//            }
//        }
//    }
}
