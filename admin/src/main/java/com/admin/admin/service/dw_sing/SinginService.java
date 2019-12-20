package com.admin.admin.service.dw_sing;

import com.admin.admin.dao.dw_sing.SinginDao;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SinginService {
    @Autowired
    private SinginDao singinDao;
    private ResponseResult result = new ResponseResult();

    //获取
    public ResponseResult getSingin(int personId){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(singinDao.getSingin(personId));
    }
}
