package com.admin.admin.dao.person;

import com.admin.admin.entity.person.AddressInformation;

public interface AddressDao {
    int insertLocation(AddressInformation model);
}
