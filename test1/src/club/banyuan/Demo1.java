package club.banyuan;

public class Demo1 {

    /**
     *1.不接受参数，直接返回1
     *     ()->1
     * 2.接受两个int类型的参数，返回这两个参数的和
     *     (int x,int y )-> x+y
     * 3.接受x,y两个参数，JVM根据上下文推断参数的类型，返回两个参数的和
     *     (x,y)->x+y
     * 4.接受一个字符串，打印该字符串，没有返回值
     *     (String name)->System.out.println(name)
     * 5.接受一个参数，JVM根据上下文推断参数的类型，打印该参数，没有返回值,只有一个参数可以省略圆括号
     *    name->System.out.prinln(name)
     * 6.接受两个String类型参数，分别输出，没有返回值
     *     (String name,String sex)->{System.out.println(name);System.out.println(sex)}
     * 7.接受一个参数，返回它本身的2倍
     *     x->2*x
     */
    public static void main(String[] args) {
        //匿名内部类 方式
    /*UserDao  dao =new  UserDao(){
      @Override
      public void add() {
        System.out.println("张三丰");
      }
    };
    dao.add();*/

        //lambda表达式
        UserDao   dao1=(String  string)-> {
            System.out.println("张大炮"+string);
            return  true;  //有返回值时候
        }; // 根据上下文进行判断   只有一行代码的时候可以把大括号删除

        // 当参数只有一个的时候 可以省略括号
        dao1=str -> {System.out.println("张大炮"+str);return  true;};
        dao1.add("少刻");


        Calculator calculator=( x, y) -> x+y;
        int sum = calculator.sum(23, 45);
        System.out.println(sum);


        Person   person=(employee)-> System.out.println(employee.toString());
        person.makeMoney(new Employee("9527", "张萨芬", 45.6, 6));

        //  得到所有的工资在5000以上的和工龄在4年以上的所有人的员工
        FindPerson findPerson = employees -> {
            Employee [] conditionEmployees = new Employee[employees.length];
            int j = 0;
            for (int i=0; i < employees.length; i++) {
                if (employees[i].getPrice() > 5000 && employees[i].getYear() > 4) {
                    conditionEmployees[j] = employees[i];
                    j++;
                }
            }
            return conditionEmployees;
        };



    }


}
@FunctionalInterface
interface FindPerson{
    Employee [] findByCondition(Employee[] employees);
}


//@FunctionalInterface
interface  UserDao{
    boolean  add(String str);
}
interface  Calculator{
    int  sum(int x,int y );
}
interface   Person{
    void  makeMoney(Employee  employee);
}





class  Employee{
    private  String  empId;
    private  String  eName;
    private  double  price;
    private  int     year;

    public Employee() {
    }

    public Employee(String empId, String eName, double price, int year) {
        this.empId = empId;
        this.eName = eName;
        this.price = price;
        this.year = year;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId='" + empId + '\'' +
                ", eName='" + eName + '\'' +
                ", price=" + price +
                ", year=" + year +
                '}';
    }
}

