package com.admin.admin.dao.master.dw_violation;

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
    int deleteViolation(@Param("Id") int id, @Param("flag") boolean flag);

    /*
    查看
     */
   // Violationfens selectViolation(@Param("id") int id);
    /*
    获取下月生效的记录
     */

    /*
    修改状态
     */
    int updateStatus(@Param("flag") boolean flag,@Param("enabled") boolean enabled,@Param("id") int id);

    /*
       下个月有效列表
        */
    List<Violationfens> enabledViolationList();
    /*
    当前月有效列表
     */
    List<Violationfens> ListViolation();

    /**
     *   查询单条记录
     * @param code
     * @return
     */
    Violationfens GetByCriteria(String code);

    void Execution();
}
