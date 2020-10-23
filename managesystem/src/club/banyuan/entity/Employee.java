package club.banyuan.entity;

/**
 * name: 员工名称
 *                 id：员工编号
 *                 sex：性别  male 或 female
 *                 phone: 手机
 *                 email 邮箱
 *                 address 住址
 *                 education  Bachelor 或 Master 或 Doctor
 *                 birthday 年-月-日字符串
 *                 departmentId 部门id
 *                 departmentName 部门名称
 *                 positionId  职位id
 *                 positionName 职位名称
 */
public class Employee {

    private Integer id;
    private String name;
    private String sex;
    private String phone;
    private String email;
    private String address;
    private String education;
    private String birthday;
    private Integer departmentId;
    private String departmentName;
    private Integer positionId;
    private String positionName;

    public Employee(Integer id, String name, String sex, String phone, String email, String address, String education, String birthday, Integer departmentId, String departmentName, Integer positionId, String positionName) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.education = education;
        this.birthday = birthday;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.positionId = positionId;
        this.positionName = positionName;
    }

    public Employee() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", education='" + education + '\'' +
                ", birthday='" + birthday + '\'' +
                ", departmentId=" + departmentId +
                ", departmentName=" + departmentName +
                ", positionId=" + positionId +
                ", positionName='" + positionName + '\'' +
                '}';
    }

}
