package club.banyuan.entity;

/**
 * name: 置为名称
 *                 id：唯一标识
 *                 description: 职位描述
 */
public class Position {

    private Integer id;
    private String name;
    private String description;

    public Position(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Position() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
