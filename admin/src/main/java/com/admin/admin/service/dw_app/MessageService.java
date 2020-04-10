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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageDao messageDao;
    @Autowired
    private CaseDao caseDao;
    @Autowired
    private fsdao fsdao;


    public int getNotificationList(String UserId) {

        return messageDao.getNotificationList(UserId);
    }


    //同步警务人员信息
    public void Synchronizedpolice() {
        TSynchron tSynchron = caseDao.GetTsyn(1);
        List<FsgaYwRyb> list=new ArrayList<FsgaYwRyb>();
        if (tSynchron!=null){
            list = fsdao.getfslist(tSynchron.getDatatime());
        }else {
            list=fsdao.getfslist("");
        }

        if (list.size() != 0) {
           // List<List<FsgaYwRyb>> lists = JudgementRole.averageAssign(list, 10);
            for (FsgaYwRyb item : list) {
                if (caseDao.getpolicenum(item.getMjbh())==1){
                    caseDao.ModifyPolice(item);
                }else
                {
                    caseDao.Synchronizedpolice(item);
                }

            }
        }
        TSynchron model = new TSynchron();
        model.setName(1);
        model.setDatatime(CalendarAdjust.GetYear(new Date()));
        caseDao.insertTsyn(model);
    }

    public void Synchronouscase() {
        TSynchron tSynchron = caseDao.GetTsyn(2);
        List<TCaseinfo> list=new ArrayList<TCaseinfo>();
        if (tSynchron!=null){
            list = fsdao.getCaseing(tSynchron.getDatatime());
        }else{
            list = fsdao.getCaseing("");
        }

        if (list.size() != 0) {
            for (TCaseinfo item : list) {
                if (caseDao.recordexists(item.getCaseno())==1){
                    caseDao.UpdateCase(item);
                }else{
                    caseDao.Synchronouscase(item);
                }

            }
        }
        TSynchron model = new TSynchron();
        model.setName(2);
        model.setDatatime(CalendarAdjust.GetYear(new Date()));
        caseDao.insertTsyn(model);
    }


}
