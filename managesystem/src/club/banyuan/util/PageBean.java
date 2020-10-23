package club.banyuan.util;

import club.banyuan.entity.Dept;

import java.util.List;

/**
 * @author wangyibo
 */
public class PageBean<T> {

    private Integer total;
    private List<T> list;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(int rows, int page, List<T> list) {
        int totalValue = list.size();
        int totalPage = (totalValue - 1) / rows + 1;
        List<T> t = null;
        if (page == totalPage) {
            t = list.subList((page - 1) * rows, totalValue);
        } else {
            t = list.subList((page - 1) * rows, page * rows);
        }
        this.list = t;
    }

}
