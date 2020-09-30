package club.banyuan;

public class Player {

    private String name;
    private Integer number;
    private String location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Player(String name, Integer number, String location) {
        this.name = name;
        this.number = number;
        this.location = location;
    }

    public Player() {

    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", number=" + number +
                ", location='" + location + '\'' +
                '}';
    }

}
