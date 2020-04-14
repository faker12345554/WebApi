package com.admin.admin.service.dw_app;


import com.admin.admin.dao.master.dw_app.MessageDao;
import com.admin.admin.dao.master.dw_case.CaseDao;
import com.admin.admin.dao.second.fsdao;
import com.admin.admin.entity.dw_Synchron.TSynchron;
import com.admin.admin.entity.dw_case.TCaseinfo;
import com.admin.admin.entity.dw_fsgayw.FsgaYwRyb;
import com.admin.admin.entity.dw_person.Personinformation;
import com.admin.admin.entity.dw_user.User;
import com.admin.tool.JudgementRole;
import com.common.common.authenticator.CalendarAdjust;
import com.common.common.md5.MD5Util;
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
            list=fsdao.getfslist("1");
        }

        if (list.size() != 0) {
           // List<List<FsgaYwRyb>> lists = JudgementRole.averageAssign(list, 10);
            for (FsgaYwRyb item : list) {
                User user = new User();
                user.setAccountname(item.getMjbh());
                user.setPassword(MD5Util.string2MD5("123456"));
                user.setAliasname(item.getMjmc());
                user.setPermissionid(1);
                user.setStatus(true);
                user.setCreateid(1);
                user.setCreatename("admin");
                user.setPhone(item.getSjhm());
                user.setUsersystem(1);
                user.setMailbox(item.getDzyj());
                user.setDepartment(item.getBmmc());
                user.setStation(item.getZw());
                user.setDepartmentnum(item.getBmbm());

                User user1 = new User();
                user1.setAccountname(item.getMjbh());
                user1.setPassword(MD5Util.string2MD5("123456"));
                user1.setAliasname(item.getMjmc());
                user1.setPermissionid(1);
                user1.setStatus(true);
                user1.setCreateid(1);
                user1.setCreatename("admin");
                user1.setPhone(item.getSjhm());
                user1.setUsersystem(2);
                user1.setMailbox(item.getDzyj());
                user1.setDepartment(item.getBmmc());
                user1.setStation(item.getZw());
                user1.setDepartmentnum(item.getBmbm());
                if (caseDao.getpolicenum(item.getMjbh())==1){
                    messageDao.updateUser(user);
                    messageDao.updateUser(user1);
                    caseDao.ModifyPolice(item);
                }else
                {
                    item.setState(0);

                    messageDao.insertuser(user);

                    messageDao.insertuser(user1);
                    caseDao.Synchronizedpolice(item);
                }

            }
        }
        TSynchron model = new TSynchron();
        model.setName(1);
        model.setDatatime(CalendarAdjust.GetYear(new Date()));
        caseDao.insertTsyn(model);
    }

    //同步案件信息
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


    public void getallpolice(){

            List<FsgaYwRyb> fsgalist=messageDao.getallpolice();
            for (FsgaYwRyb item:fsgalist) {



            }
    }

    public void getperson(){
        List<Personinformation> personlist=messageDao.getperson();
        for (Personinformation item:personlist){
            if (item.getContact()!=null && item.getContact()!="") {
                User user = new User();
                user.setAccountname(item.getContact());
                user.setPassword(MD5Util.string2MD5("123456"));
                user.setAliasname(item.getPersonname());
                user.setPermissionid(0);
                user.setStatus(true);
                user.setCreateid(1);
                user.setCreatename("admin");
                user.setPhone(item.getContact());
                user.setUsersystem(3);
                user.setSex(item.getGender());
                user.setDepartment(item.getPolicestation());
                user.setDepartmentnum(item.getPolicecode());
                messageDao.insertuser(user);
            }
        }
    }


}
