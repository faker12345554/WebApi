package com.person.person.Personnel.Dao;

import com.person.person.Personnel.Entity.AddressInformation;
import org.apache.ibatis.annotations.Param;

public interface AddressDao {
    int Addlocation(AddressInformation model);
}
