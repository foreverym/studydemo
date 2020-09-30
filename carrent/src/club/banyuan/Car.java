package club.banyuan;

public class Car {

    private String year;
    private String carType;
    private String factory;
    private Boolean idRent;
    private Integer sit;

    public Car(String year, String carType, String factory, Boolean idRent, Integer sit) {
        this.year = year;
        this.carType = carType;
        this.factory = factory;
        this.idRent = idRent;
        this.sit = sit;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public Boolean getIdRent() {
        return idRent;
    }

    public void setIdRent(Boolean idRent) {
        this.idRent = idRent;
    }

    public Integer getSit() {
        return sit;
    }

    public void setSit(Integer sit) {
        this.sit = sit;
    }

    @Override
    public String toString() {
        String rentDescription;
        if (idRent) {
            rentDescription = "已出租";
        } else {
            rentDescription = "未出租";
        }
        return  year +  "年" +
                factory  +
                carType  +
                sit + "座," +
                rentDescription;
    }
}
