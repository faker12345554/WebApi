package com.admin.admin.service.dw_person;

import com.admin.admin.dao.dw_person.PersonDao;
import com.admin.admin.entity.dw_person.Personinformation;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersoinfoService {

    @Autowired
    private PersonDao personDao;
    private ResponseResult result=new ResponseResult();

    //新增
    public ResponseResult insertPersion(Personinformation personinformation){
        if (personDao.getPersonByCard(personinformation.getCard())>1){
            result.setCode(ResultCode.DATA_DUPLICATION.getCode());
            result.setMessage(ResultCode.DATA_DUPLICATION.getMessage());
            return result.setData("参数'card'重复,身份证号应是唯一凭证");
        }
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(personDao.insertPersion(personinformation));
    }
    //修改
    public ResponseResult updatePersion(Personinformation personinformation){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(personDao.updatePersion(personinformation));
    }
    //删除
    public ResponseResult deletePersion(boolean flag,String PersionId){
        if (flag==true){
            result.setCode(ResultCode.DATA_DUPLICATION.getCode());
            result.setMessage(ResultCode.DATA_DUPLICATION.getMessage());
            return result.setData("参数'flag'错误");
        }
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(personDao.deletePersion(flag,PersionId));
    }
    //获取人员信息
    public ResponseResult getPersoin(String id){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData( personDao.getPersoin(id));
    }
}
