package com.admin.admin.service.dw_address;

import com.admin.admin.dao.dw_address.AddressDao;
import com.admin.admin.entity.dw_address.AddressInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    private AddressDao addressDao;



    public int insertLocation(AddressInformation addressInformation) {

        return addressDao.insertLocation(addressInformation);
    }
}
