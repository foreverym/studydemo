### **简介**

学习lambda表达式就要先知道函数式接口是什么？

函数式接口（Functional Interfaces）：<font color='red' size='5px'>如果一个接口定义个唯一一个抽象方法</font>，那么这个接口就成为函数式接口。同时，引入了一个新的注解：@FunctionalInterface。可以把他它放在一个接口前，表示这个接口是一个函数式接口。这个注解是非必须的，只要接口只包含一个方法的接口，虚拟机会自动判断，不过最好在接口上使用注解 @FunctionalInterface 进行声明。在接口中添加了 @FunctionalInterface 的接口，只允许有一个抽象方法，否则编译器也会报错。

```java
比如:
/**
  * 函数式接口
  */
@FunctionalInterface
interface Sum{
    int add(int value);
}
```

Lambda表达式：可以让你的代码更加的简洁。Lambda无法单独出现，需要一个函数式接口来盛放，<font color='red' size='4px'>可以说lambda表达式方法体是函数式接口的实现，lambda实例化函数式接口，可以将函数作为方法参数，或者将代码作为数据对待</font>。

主要优点：
1.代码变得更加简洁紧凑
2.可读性强，
3.并行操作大集合变得很方便，可以充分发挥多核cpu的优势，更加便于多核处理器编写代码等，

### **语法**

Lambda语法
(parameters)->expression 或者 (parameters)->{statements;}
Lambda表达式由三部分组成：
1.parameters:类似方法中的形参列表，这里的参数是函数式接口里的参数。这里的参数类型可以明确的声明也可不声明而由JVM隐含的推断，当只有一个推断类型时可以省略掉圆括号。
2.-> :可以理解为“被用于”的意思
3.方法体：可以是表达式也可以是代码块，实现函数式接口中的方法。这个方法体可以有返回值也可以没有返回值

```java
1.不接受参数，直接返回1
    ()->1
2.接受两个int类型的参数，返回这两个参数的和
    (int x,int y )-> x+y
3.接受x,y两个参数，JVM根据上下文推断参数的类型，返回两个参数的和
    (x,y)->x+y
4.接受一个字符串，打印该字符串，没有返回值
    (String name)->System.out.println(name)
5.接受一个参数，JVM根据上下文推断参数的类型，打印该参数，没有返回值,只有一个参数可以省略圆括号
   name->System.out.prinln(name)
6.接受两个String类型参数，分别输出，没有返回值
    (String name,String sex)->{System.out.println(name);System.out.println(sex)}
7.接受一个参数，返回它本身的2倍
    x->2*x
```

#### 案例

```java

interface  A{
  void  method();
}
interface B{
  void show(String  name);
}
interface C{
  boolean login(String username,String password);
}

//    A a = new A() {
//      @Override
//      public void method() {
//        System.out.println("我是你的大宝被子");
//      }
//    };
    //lambda 表达式写法  这个没有参数
    A a = () -> System.out.println("我是你的大宝被子");

    //只有一个参数的lambda表示法
    B   b=(name)->System.out.println("我是你的"+name);
    b.show("张大炮"); //如果只有一个参数括号可以省略
    b=name -> System.out.println("我是你的"+name); //因为只有一行代码 可以不使用{} 包裹


    //有两个参数的并且有返回值的使用lambda表达式表示
    C c=(name,password)->{
      if("张三".equals(name)&&"123456".equals(password)){
        return  true;
      }
      return false;
    };
    System.out.println(c.login("张三", "123456"));
		

```



#### 函数式接口

#####  内置函数式接口 

Function<T,R>:函数型接口

定义了 R apply(T t) 抽象方法，它接受一个 泛型T的对象，并返回一个泛型R的对象。如果需要将接收对象转换成其它对象可以使用

```java

    Function<String,Boolean>  fun=(name)->{
      if(name.equals("王五")){
        return true;
      }
      return  false;
    };

    Boolean apply = fun.apply("王五");

    System.out.println("------"+apply);


```

Consumer<T> : 消费型接口
  void accept(T t);
Consumer：定义了 void accept(T t) 抽象方法，需要访问某对象并对其进行某些操作时可以使用

```java
Consumer<Double>  consumer=(money)-> System.out.println("今天我花了"+money+"快钱");
    consumer.accept(890.0);
```



 Supplier<T> : 供给型接口
   T get(); 
 Supplier：定义了 T get() 抽象方法，不接收参数返回Lambda表达式的值

```java
Supplier<Employee>  supplier= ()-> new Employee("21", "汗", 2, 3);
    System.out.println(supplier.get().toString());
```



 Predicate<T> : 断言型接口
   boolean test(T t);
 Predicate：定义了 boolean test(T t) 抽象方法，需要表示一个涉及类型T的布尔表达式时可以使用

```java
  //案例 找出字符串数组中长度满足大于6的所有的字符串的数组
    String [] st={"da","asdawa","张三你个大保健","adwgvsdg","李四是钻石王老五"};
    Predicate<String> predicate1=(str)->str.length()>=6;

    String[] strings = get(st, predicate1);
    System.out.println("----"+Arrays.toString(strings));


public  static  String [] get(String [] strings,Predicate<String> predicate){
    String[] str=null;
    for (String s : strings)
      if (predicate.test(s)) {
        if (str == null) {
          str = new String[1];
          str[0] = s;
        } else {
          str = Arrays.copyOf(str, str.length + 1);
          str[str.length-1] = s;
        }
      }
    return  str;
  }

```



BiFunction：定义 R apply(T t, U u) 抽象方法，接收t和u参数，返回R对象，如果需要两个对象中的某些值来组装成另一个对象，
    可以使用。我把它看成Function的神级版
public static <T, U, R> R biFunction(T t,U u,BiFunction<T, U, R> biFunction){
       return biFunction.apply(t, u);
   }



BiConsumer：定义了 void accept(T t, U u) 抽象方法，我把它看成Consumer的神级版
public static <T, U> void biConsumer(T t, U u, BiConsumer<T, U> biConsumer) {
       biConsumer.accept(t, u);
   }



# 函数的引用

从上面的代码中，使用通用的函数表达式能够减少自定义函数式接口，为了进一步简化代码，lambda表达式可以改写成**函数的引用的形式**

函数的引用是lambda表达式的更简洁的一种写法，也是更能体现出函数式编程的一种形式，让我们更能理解lambda终归也是一个**“函数的对象”**。 下面我们改写一个例子：

```java
Consumer<String> c1 = r -> System.out.printf(r); 
c1.accept("1");
Consumer<String> c2 =System.out::printf;  
c2.accept("2");
```

在上面的demo中lambda表达式被我们改写成**System.out::printf**这个形式，等于我们把一个函数直接赋值给了一个c2对象，这里我们可以俗称（非官方）c2为java函数的一个对象。

## 函数引用的规则

对于Java中lambda改成函数的引用要遵循一定的规则,具体可以分为下面的四种形式：

1.**静态方法的引用** 如果函数式接口的实现恰好可以通过调用一个静态方法来实现，那么就可以使用静态方法引用

```java
	Consumer<String> c1 = r -> Integer.parseInt(r);
     c1.accept("1");
     Consumer<String> c2 =Integer::parseInt;
     c2.accept("2");
```

再来看一个案例

```java

interface StringFunc {
  String func(String n);
}
class MyStringOps {
  //静态方法： 反转字符串
  public static String strReverse(String str) {
    String result = "";
    for (int i = str.length() - 1; i >= 0; i--) {
      result += str.charAt(i);
    }
    return result;
  }
}
class MethodRefDemo {

  public static String stringOp(StringFunc sf, String s) {
    return sf.func(s);
  }

  public static void main(String[] args) {
    String inStr = "lambda add power to Java";
    //MyStringOps::strReverse 相当于实现了接口方法func() ，并在接口方法func()中作了MyStringOps.strReverse()操作
    String outStr = stringOp(MyStringOps::strReverse, inStr);
    System.out.println("Original string: " + inStr);
    System.out.println("String reserved: " + outStr);
  }
}
```

在程序中，特别注意下面这行代码：String outStr = stringOp(MyStringOps::strReverse, inStr);
其中将对MyStringOps内声明的静态方法strReverse()的引用传递给stringOp()方法的第一个参数。可以这么做，因为
strReverse与StringFunc函数式接口兼容。因此，表达式MyStringOps::strReverse的计算结果为对象引用，其中，
strReverse提供了StringFunc的func()方法的实现。

**2.实例方法**

**这种语法与用于静态方法的语法类似，只不过这里使用对象引用而不是类名。**

**组成\**语法\**格式：**instanceReference**::**methodName

对于具体（或者任意）对象的实例方法引用，在实例方法名称和其所属类型名称间加上分隔符 ：

```java
/*
* 函数式接口
* */
interface StringFunc {
    String func(String n);
}
class MyStringOps {
    //普通方法： 反转字符串
    public String strReverse(String str) {
        String result = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            result += str.charAt(i);
        }
        return result;
    }
}
class MethodRefDemo2 {
    public static String stringOp(StringFunc sf, String s) {
        return sf.func(s);
    }
    public static void main(String[] args) {
        String inStr = "lambda add power to Java";
        MyStringOps strOps = new MyStringOps();//实例对象
        //MyStringOps::strReverse 相当于实现了接口方法func() ，并在接口方法func()中作了MyStringOps.strReverse()操作
        String outStr = stringOp(strOps::strReverse, inStr);
 
        System.out.println("Original string: " + inStr);
        System.out.println("String reserved: " + outStr);
    }
}
```

在超类上的实例方法引用:

**组成语法格式：super ::**methodName

方法的名称由methodName指定

通过使用**super**，可以引用方法的超类版本。

```java
eg： super::name
this :: equals  等价于lambda表达式  x -> this.equals(x);
```

**3.构造方法引用**

构造方法引用又分构造方法引用和数组构造方法引用。

构造方法引用 （也可以称作 **构造器引用**）

组成语法格式：**Class·**::	new

构造函数本质上是静态方法，只是方法名字比较特殊，使用的是**new 关键字**。

```java
Consumer<String> n1 = r ->new BigDecimal(r);
  c1.accept("1");
  Consumer<String> n2 =BigDecimal::new;
  c2.accept("2");

```

又如:

```java

interface MyFunc {
    MyClass func(int n);
}
class MyClass {
    private int val;
    MyClass(int v) {
        val = v;
    }
    MyClass() {
        val = 0;
    }
    public int getValue() {
        return val;
    }
}
class ConstructorRefDemo {
    public static void main(String[] args) {
        MyFunc myClassCons = MyClass::new;
        MyClass mc = myClassCons.func(100);
        System.out.println("value in mc is: " + mc.getValue());
    }
}

```

**4.数组构造方法引用：**

**组成语法**格式：**TypeName[]**::**new**

int[]::new 是一个含有一个参数的构造器引用，这个参数就是数组的长度。

等价于lambda表达式 x -> new int[x]。

```java
Function<Integer, String[]> functionStringArray = String[]::new;
String[] apply = functionStringArray.apply(10);
System.out.println("数组长： " + apply.length);
```





#### Stream

```java

//Stream
    //初始化一个员工数组,找到工资大于指定工资的所有的员工
    Employee[] employees = new Employee[]{
       new Employee("9527", "周星星", 8000, 5),
       new Employee("9528", "吴孟达", 7500, 9),
       new Employee("9529", "送终鸡", 3000, 11),
       new Employee("9530", "李开心", 4000, 6),
       new Employee("9531", "周吉吉", 4500, 2),
    };
    //传统写法
    Employee[] conditionEmp = getConditionEmp(employees, 4500);
   /* for (Employee e : conditionEmp) {
      System.out.println(e.toString());
    }*/
    //lambda表达式写法
    Stream<Employee> stream = Arrays.asList(conditionEmp).stream();
    stream.filter(employee -> employee.getPrice()>=4500).forEach(System.out::println);


  }

  public   static  Employee[]  getConditionEmp(Employee [] employees,double price){
    Employee [] employees1=null;
    int index=0;int key=0;
    for (int i = 0; i < employees.length; i++) {
      if(employees[i].getPrice()>=price){
          index++;
      }
    }
    employees1=new Employee[index];
    for (int i = 0; i < employees.length; i++) {
      if(employees[i].getPrice()>=price){
        employees1[key++]=employees[i];
      }
    }
    return employees1;
  }

//在员工数组中使用lambda表达式
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

```

