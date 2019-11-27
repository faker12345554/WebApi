package com.person.person.Personnel.Dao;

import com.person.person.Personnel.Entity.AuditorInformation;
import com.person.person.Personnel.Entity.Leaveinformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LeaveDao {

    Leaveinformation GetLeave(@Param("person_id") int person_id);

    // 我觉得不应该指定名称，mybatis读取不到你的参数 它这里不是一样的嘛   因为你直接全额限定名的路径了com.person.person.Personnel.Entity.Leaveinformation
    int UpdateLeave( Leaveinformation leaveinformation);

    int Addauditor( AuditorInformation auditorInformation);

}
