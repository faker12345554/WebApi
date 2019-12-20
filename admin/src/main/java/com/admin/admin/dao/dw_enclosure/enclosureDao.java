package com.admin.admin.dao.dw_enclosure;

import com.admin.admin.entity.dw_enclosure.enclosure;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface enclosureDao {
    //新增
    int saveEnclosure(enclosure enclosure);
    //修改
    int updateEnclosure(enclosure enclosure);
    //删除
    int deleteEnclosure(String personId);
    //查询
    int selectEnclosureByPersonId(String personId);
    //查询
    List<enclosure> selectEnclosure(String personId);
}
