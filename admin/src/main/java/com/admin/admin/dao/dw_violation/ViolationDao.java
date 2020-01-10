package com.admin.admin.dao.dw_violation;

import com.admin.admin.entity.dw_violation.Violationfens;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ViolationDao {

    /*
    新增违规分数设置
     */
    int SaveViolation(Violationfens violationfens);

    /*
    修改违规分数设置
     */
    int UpdateViolation(Violationfens violationfens);

    /*
    作废违规分数
     */
    int deleteViolation(int id);

    /*
    查看
     */
   // Violationfens selectViolation(@Param("id") int id);

    /*
    列表
     */
    List<Violationfens> ListViolation();
}
