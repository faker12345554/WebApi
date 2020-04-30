package com.admin.admin.dao.master.dw_case;

import com.admin.admin.entity.dw_Synchron.TSynchron;
import com.admin.admin.entity.dw_case.TCaseinfo;
import com.admin.admin.entity.dw_fsgayw.FsgaYwRyb;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CaseDao {

    //保存案件信息
    int SaveCase(TCaseinfo tCaseinfo);

    //修改案件信息
    int UpdateCase(TCaseinfo tCaseinfo);

     //int DeleteCase(int )
    //查询案件信息
    TCaseinfo GetCase(String PersonId,String Caseno);
    //同步案件信息
    int Synchronouscase(TCaseinfo tCaseinfo);
    //判断案件是否存在
    int recordexists(String caseno);
    //修改案件信息

    //同步警务人员信息
    int Synchronizedpolice(FsgaYwRyb fsgaYwRyb);

    //查询民警是否重复
    int getpolicenum(String mjbh);

    //修改民警信息
    int ModifyPolice(FsgaYwRyb fsgaYwRyb);
    //获取最后一次同步数据的时间
    TSynchron GetTsyn(int type);

    //记录更新时间
    int insertTsyn(TSynchron tSynchron);

    //修改案件信息表的人员编码
    int updatepersond(String Personid,String CaseNo);

    //根据案件编号修改案件信息
    int Modifycaserecord(TCaseinfo tCaseinfo);



}
