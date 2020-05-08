package com.admin.admin.service.dw_sing;

import com.admin.admin.dao.master.dw_sing.SinginDao;
import com.admin.admin.entity.dw_sing.SinginInformation;
import com.admin.model.search.SearchModel;
import com.admin.model.singin.SinginModel;
import com.admin.tool.CacheUtils;
import com.admin.tool.JudgementRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SinginService {

    @Autowired
    private SinginDao singinDao;

    /*
    列表
     */
    public  List<SinginModel> ListSingin(SearchModel searchModel){
        String limit = "";
        boolean type = JudgementRole.Distinguishroles();
        if (type == true) {
            limit = CacheUtils.get("PoliceCode").toString().substring(0,CacheUtils.get("PoliceCode").toString().indexOf("0"));
        } else {
            limit = CacheUtils.get("accountname").toString();
        }

        return singinDao.ListSingin(searchModel,type,limit);
    }

    /*
    查看详情
     */
    public SinginInformation getSingin(int personId){

        return singinDao.getSingin(personId);
    }

    /*
    音视频
     */
    public  List<SinginModel> ListAudio(SearchModel searchModel){
        String limit = "";
        boolean type = JudgementRole.Distinguishroles();
        if (type == true) {
            limit = CacheUtils.get("PoliceCode").toString().substring(0,CacheUtils.get("PoliceCode").toString().indexOf("0"));
        } else {
            limit = CacheUtils.get("accountname").toString();
        }
        return singinDao.ListAudio(searchModel,type,limit);
    }
}
