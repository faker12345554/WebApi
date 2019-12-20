package com.admin.admin.service.dw_guaran;

import com.admin.admin.dao.dw_guaran.GuarantDao;
import com.admin.admin.entity.dw_guarant.GuaranteeInformation;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuaranService {
    @Autowired
    private GuarantDao guarantDao;
    private ResponseResult result = new ResponseResult();

    //新增
    public ResponseResult insertGuarant(GuaranteeInformation guaranteeinformation) {
        if(guarantDao.getGuaByPersonId(guaranteeinformation.getPersonid())>=1){
            result.setCode(ResultCode.DATA_DUPLICATION.getCode());
            result.setMessage(ResultCode.DATA_DUPLICATION.getMessage());
            return result.setData("该监居人员以被人担保!");
        }
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(guarantDao.insertGuarant(guaranteeinformation));
    }

    //修改
    public ResponseResult updateGuara(GuaranteeInformation guaranteeinformation) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(guarantDao.updateGuara(guaranteeinformation));
    }

    //删除
    public ResponseResult deleteGuara(boolean flag, int GuaId) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(guarantDao.deleteGuara(flag, GuaId));
    }

    //获取
    public ResponseResult getGuara(int id) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(guarantDao.getGuara(id));
    }
}
