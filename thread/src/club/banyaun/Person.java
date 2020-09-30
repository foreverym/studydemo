package club.banyaun;

public class Person implements Runnable {

    private Phone phone;
    private String name;

    public Person(Phone phone, String name) {
        this.phone = phone;
        this.name = name;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "phone=" + phone +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public void run() {
        if (phone.getState() == 0) {
            System.out.println("电话占线中。。。请等待");
        }
        if (phone.getState() == 1) {
            System.out.println("正在接通。。。请等待");
        }
        if (phone.getState() == 2) {
            System.out.println("已接通");
        }

    }


}
