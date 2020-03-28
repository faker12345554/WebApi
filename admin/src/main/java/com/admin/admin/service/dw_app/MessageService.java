package com.admin.admin.service.dw_app;


import com.admin.admin.dao.master.dw_app.MessageDao;
import com.admin.admin.dao.master.dw_case.CaseDao;
import com.admin.admin.dao.second.fsdao;
import com.admin.admin.entity.dw_Synchron.TSynchron;
import com.admin.admin.entity.dw_case.TCaseinfo;
import com.admin.admin.entity.dw_fsgayw.FsgaYwRyb;
import com.admin.tool.JudgementRole;
import com.common.common.authenticator.CalendarAdjust;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageDao messageDao;
    @Autowired
    private CaseDao  caseDao;
    @Autowired
    private fsdao fsdao;





    public int getNotificationList(String UserId) {

        return messageDao.getNotificationList(UserId);
    }


    //同步警务人员信息
    public void Synchronizedpolice(){
        TSynchron tSynchron=caseDao.GetTsyn(1);
        List<FsgaYwRyb> list=fsdao.getfslist(tSynchron.getDatatime());
        List<List<FsgaYwRyb>>  lists=JudgementRole.averageAssign(list,10);
        for (List<FsgaYwRyb> item: lists){
            caseDao.Synchronizedpolice(item);
        }
        TSynchron model=new TSynchron();
        model.setName(1);
        model.setDatatime(CalendarAdjust.GetYear(new Date()));
        caseDao.insertTsyn(model);
    }

    private void Synchronouscase(){
        TSynchron tSynchron=caseDao.GetTsyn(2);
        List<TCaseinfo> list=fsdao.getCaseing();
        List<List<TCaseinfo>>  lists=JudgementRole.averageAssign(list,10);
        for (List<TCaseinfo> item: lists){
            caseDao.Synchronouscase(item);
        }
        TSynchron model=new TSynchron();
        model.setName(2);
        model.setDatatime(CalendarAdjust.GetYear(new Date()));
        caseDao.insertTsyn(model);
    }





}
