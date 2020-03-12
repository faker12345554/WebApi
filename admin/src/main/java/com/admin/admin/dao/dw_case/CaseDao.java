package com.admin.admin.dao.dw_case;

import com.admin.admin.entity.dw_case.TCaseinfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CaseDao {

    //保存案件信息
    int SaveCase(TCaseinfo tCaseinfo);

    //修改案件信息
    int UpdateCase(TCaseinfo tCaseinfo);

     //int DeleteCase(int )

    //查询案件信息
    TCaseinfo GetCase(String PersonId,String Caseno);
}
