package com.admin.admin.service.dw_biosign;

import com.admin.admin.dao.dw_biosign.BiosignatureDao;
import com.admin.admin.entity.dw_bios.BiosignatureInformation;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BiosignatureService {

    @Autowired
    private BiosignatureDao biosignatureDao;
    private ResponseResult result = new ResponseResult();

    public ResponseResult insertBiosignature(BiosignatureInformation biosignatureInformation) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(biosignatureDao.insertBiosignature(biosignatureInformation));
    }

    public ResponseResult getBiosignature(String personid, int type) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(biosignatureDao.getBiosignature(personid, type));
    }
}
