package com.admin.admin.dao.scope;

import com.admin.admin.entity.scope.enclosure;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface enclosureDao {
    //新增
    int saveEnclosure(enclosure enclosure);
    //修改
    int updateEnclosure(enclosure enclosure);

    int deleteEnclosure(int id);
}
