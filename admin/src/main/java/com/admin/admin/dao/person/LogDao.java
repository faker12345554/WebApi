package com.admin.admin.dao.person;


import com.admin.admin.entity.person.LogInformation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogDao {

    int insertLog(LogInformation logInformation);

}
