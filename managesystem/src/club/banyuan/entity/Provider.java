package club.banyuan.entity;

/**
 * "id": "",
 *   "name": "华为",
 *   "desc": "供应手机",
 *   "phone": "13312345678",
 *   "contactPerson": "张三"
 */
public class Provider {

    private Integer id;
    private String name;
    private String desc;
    private String phone;
    private String contactPerson;

    public Provider(Integer id, String name, String desc, String phone, String contactPerson) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.phone = phone;
        this.contactPerson = contactPerson;
    }

    public Provider() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    @Override
    public String toString() {
        return "Provider{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", phone='" + phone + '\'' +
                ", contactPerson='" + contactPerson + '\'' +
                '}';
    }


}
