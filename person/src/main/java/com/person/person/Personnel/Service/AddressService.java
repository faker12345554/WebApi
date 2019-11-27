package com.person.person.Personnel.Service;

import com.person.person.Personnel.Dao.AddressDao;
import com.person.person.Personnel.Entity.AddressInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    private AddressDao addressDao;

    public int Addlocation(AddressInformation addressInformation) {
        return addressDao.Addlocation(addressInformation);
    }
}
