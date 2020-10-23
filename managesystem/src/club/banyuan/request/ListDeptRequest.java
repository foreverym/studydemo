package club.banyuan.request;

/**
 * @author wangyibo
 * page 当前的页数
 *         rows 每页的条数
 *         name为可选数据，用于检索，可以为空字符串，也可以不传
 */
public class ListDeptRequest {

    private Integer page;
    private Integer rows;
    private String name;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
