package club.banyaun;

public class Phone {

    private String phoneNumber;
    private int state;

    public Phone(String phoneNumber, int state) {
        this.phoneNumber = phoneNumber;
        this.state = state;
    }

    public Phone() {

    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
