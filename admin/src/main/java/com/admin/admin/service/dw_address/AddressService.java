package com.admin.admin.service.dw_address;

import com.admin.admin.dao.dw_address.AddressDao;
import com.admin.admin.entity.dw_address.AddressInformation;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    private AddressDao addressDao;

    private ResponseResult result = new ResponseResult();

    public ResponseResult insertLocation(AddressInformation addressInformation) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(addressDao.insertLocation(addressInformation));
    }
}
