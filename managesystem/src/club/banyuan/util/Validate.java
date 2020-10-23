package club.banyuan.util;

import club.banyuan.entity.Dept;
import club.banyuan.entity.Position;
import club.banyuan.exception.BadReqException;

import java.util.List;
import java.util.stream.Collectors;

public class Validate {

    public void validateDeptAdd(List<Dept> list, String name) {
        List<Dept> collect = list.stream().filter(dept -> dept.getName().equals(name)).collect(Collectors.toList());
        if (collect.size() > 0) {
            throw new BadReqException("部门名称重复");
        }
    }

    public void validateDeptUpdate(List<Dept> list, String name, Integer id) {
        List<Dept> collect = list.stream().filter(dept -> dept.getName().equals(name) && dept.getId() != id).collect(Collectors.toList());
        if (collect.size() > 0) {
            throw new BadReqException("部门名称重复");
        }
    }

    public void validatePositionAdd(List<Position> list, String name) {
        List<Position> collect = list.stream().filter(position -> position.getName().equals(name)).collect(Collectors.toList());
        if (collect.size() > 0) {
            throw new BadReqException("职位名称重复");
        }
    }

}
