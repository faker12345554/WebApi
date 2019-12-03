package com.admin.admin.service.person;

import com.admin.admin.dao.person.AddressDao;
import com.admin.admin.entity.person.AddressInformation;
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
