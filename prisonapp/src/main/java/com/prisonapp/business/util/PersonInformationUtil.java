package com.prisonapp.business.util;

import com.prisonapp.business.entity.dw_supervise.TPersoninformation;
import com.prisonapp.business.service.dw_supervise.SuperviseService;
import com.prisonapp.token.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonInformationUtil {
    @Autowired
    private SuperviseService superviseService;

    public String getPersonId(){

        TPersoninformation tPersoninformation = superviseService.RelatedId(TokenUtil.getTokenUserId());//根据user中的手机号去取出personid
        String personid = tPersoninformation.getPersonid();
        return personid;
    }
}
