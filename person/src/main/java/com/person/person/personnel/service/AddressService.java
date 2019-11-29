package com.person.person.personnel.service;

import com.person.person.personnel.dao.AddressDao;
import com.person.person.personnel.entity.AddressInformation;
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
