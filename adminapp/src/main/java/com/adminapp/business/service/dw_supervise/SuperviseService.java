package com.adminapp.business.service.dw_supervise;

import com.adminapp.business.dao.dw_supervise.SuperfineDado;
import com.adminapp.business.entity.dw_supervise.Personinformation;
import com.adminapp.model.dw_supervise.PrisonSettingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuperviseService {

    @Autowired

    private SuperfineDado superfineDado;

    public List<Personinformation> listPersonInformation(){
        return superfineDado.listPersonInformation();
    }

    public PrisonSettingModel getPrisonSetting(String personid){
        return superfineDado.getPrisonSetting(personid);
    }

}
