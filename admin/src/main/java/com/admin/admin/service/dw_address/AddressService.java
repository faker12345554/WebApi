package com.admin.admin.service.dw_address;

import com.admin.admin.dao.dw_address.AddressDao;
import org.apache.lucene.document.StringField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AddressService
{
    @Autowired
    private AddressDao addressDao;

    /*
    获取地址
     */
    public List<Map<String, String>> getAddress(String code,int level){
        return addressDao.getAddress(code, level);
    }
}
