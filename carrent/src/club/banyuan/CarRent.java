package club.banyuan;

import java.util.*;

public class CarRent {


    static List<Car> cars = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("欢迎使用半圆租车系统");

        showChoice();

    }

    public static void addCar() {
        Car car = new Car("", "", "", false, 4);
        System.out.println("请输入车辆年份(4位数年份)：");
        Scanner scannerYear = new Scanner(System.in);
        String year = scannerYear.nextLine();
        car.setYear(year);
        System.out.println("请输入车辆厂家：");
        Scanner scannerFactory = new Scanner(System.in);
        String factory = scannerFactory.nextLine();
        car.setFactory(factory);
        System.out.println("请输入要添加的车辆类型 1.客车 2.货车");
        Scanner scannerCarType = new Scanner(System.in);
        String carType = scannerCarType.nextLine();
        if ("1".equals(carType)) {
            car.setCarType("客车");
        }
        if ("2".equals(carType)) {
            car.setCarType("货车");
        }
        System.out.println("请输入客车载客量：");
        Scanner scannerSit = new Scanner(System.in);
        String sitString  = scannerSit.nextLine();
        int sit = Integer.valueOf(sitString);
        car.setSit(sit);
        car.setIdRent(false);
        cars.add(car);
        System.out.println("添加成功!");
        System.out.println(car);
        System.out.println();

        showChoice();
        //return car;

    }

    public static void findCar() {
        System.out.println("查找成功");

        for (int i = 0; i < cars.size(); i++) {
            System.out.println(i + 1 + "." + cars.get(i));
        }
        showChoice();
    }

    public static void rentCar() {
        Map<Integer, Integer> map = new HashMap<>();
        System.out.println("请选择租用的车辆编号(0返回上一级):");
        int index = 0;
        for (int i = 0; i < cars.size(); i++) {
            if (!cars.get(i).getIdRent()) {
                index++;
                System.out.println(index + "." + cars.get(i));
                map.put(index, i);
                //cars.get(i).setIdRent(true);
            }
        }
        Scanner scanner = new Scanner(System.in);
        String num = scanner.nextLine();
        Integer integer = Integer.valueOf(num);

        Set<Integer> set = map.keySet();
        Iterator<Integer> integerIterator = set.iterator();
        while (integerIterator.hasNext()) {
            Integer integerNnm = map.get(integerIterator.next());
            if (integer.equals(integerNnm)) {
                cars.get(integerNnm).setIdRent(true);
            }
        }
        System.out.println("操作成功！");
        showChoice();

    }

    public static void returnCar() {
        System.out.println("请选择租用的车辆编号(0返回上一级):");
        for (int i = 0; i < cars.size(); i++) {
            if (!cars.get(i).getIdRent()) {
                System.out.println(i + 1 + "." + cars.get(i));
                //cars.get(i).setIdRent(true);
            }
        }
        Scanner scanner = new Scanner(System.in);
        String numString = scanner.nextLine();
        Integer num = Integer.valueOf(numString);
        cars.get(num - 1).setIdRent(false);
        System.out.println("还车成功！");
        showChoice();
    }

    public static void showChoice() {
        MainMenu.showMenu();
        Scanner scanner = new Scanner(System.in);
        String num = scanner.nextLine();
        int numToint = Integer.valueOf(num);
        switch (numToint) {
            case 1: addCar();break;
            case 2: findCar();break;
            case 3: rentCar();break;
            case 4: returnCar();break;
            case 0: showChoice();

        }
    }
}
