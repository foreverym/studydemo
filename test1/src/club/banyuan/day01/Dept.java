package club.banyuan.day01;

public class Dept {

    private int no;
    private String name;
    private String location;

    public Dept(int no, String name, String location) {
        this.no = no;
        this.name = name;
        this.location = location;
    }

    public int getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
