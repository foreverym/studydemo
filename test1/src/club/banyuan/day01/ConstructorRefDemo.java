package club.banyuan.day01;

interface MyFunc {
    MyClass func(int n);
}
class MyClass {
    private int val;
    private  int key;
    MyClass(int v) {
        val = v;
    }
    MyClass(int v, int k) {
        this.val = v;
        this.key = k;
    }
    MyClass() {
        val = 0;
    }
    public int getValue() {
        return val;
    }
    public int getKey() {
        return key;
    }
}
class ConstructorRefDemo {
    public static void main(String[] args) {
        //构造器的参数是根据函数式接口来决定的
        MyFunc myClassCons = MyClass::new;
        MyClass mc = myClassCons.func(30);
        System.out.println("value in mc is: " + mc.getValue());
        System.out.println(mc.getKey());
    }
}

