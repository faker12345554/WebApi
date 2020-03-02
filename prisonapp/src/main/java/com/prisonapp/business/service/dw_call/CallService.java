package com.prisonapp.business.service.dw_call;

import com.prisonapp.business.dao.dw_call.CallDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CallService {
    @Autowired
    private CallDao callDao;
}
