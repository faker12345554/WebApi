package com.admin.admin.service.dw_person;

import com.admin.admin.dao.master.dw_app.MessageDao;
import com.admin.admin.dao.master.dw_case.CaseDao;
import com.admin.admin.dao.master.dw_guaran.GuarantDao;
import com.admin.admin.dao.master.dw_person.PersonDao;
import com.admin.admin.dao.master.dw_task.TaskDao;
import com.admin.admin.dao.master.dw_violation.ViolationDao;
import com.admin.admin.dao.second.fsdao;
import com.admin.admin.entity.dw_Synchron.TSynchron;
import com.admin.admin.entity.dw_case.TCaseinfo;
import com.admin.admin.entity.dw_fsgayw.FsgaYwRyb;
import com.admin.admin.entity.dw_guarant.GuaranteeInformation;
import com.admin.admin.entity.dw_message.TMessage;
import com.admin.admin.entity.dw_person.Personinformation;
import com.admin.admin.entity.dw_prisonsetting.TPrisonsetting;
import com.admin.admin.entity.dw_user.User;
import com.admin.admin.entity.dw_violation.Violationfens;
import com.admin.model.Appstatistics.monthnumber;
import com.admin.model.search.SearchModel;
import com.admin.tool.CacheUtils;
import com.admin.tool.JudgementRole;
import com.common.common.authenticator.CalendarAdjust;
import com.common.common.md5.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PersoinfoService {

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private PersonDao personDao;

    @Autowired
    private CaseDao caseDao;

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private GuarantDao guarantDao;

    @Autowired
    private ViolationDao violationDao;
    @Autowired
    private fsdao fsdao;


    //新增
    public int insertPersion(Personinformation personinformation) {
        String PersonId = java.util.UUID.randomUUID().toString();
        GuaranteeInformation guaranteeInformation = personinformation.getGuaranteeInformation();
        guaranteeInformation.setPersonid(PersonId);
        guarantDao.insertGuarant(guaranteeInformation);
        TCaseinfo tCaseinfo = personinformation.gettCaseinfo();
        tCaseinfo.setPersonid(PersonId);
        caseDao.SaveCase(tCaseinfo);
        TMessage message = new TMessage();
        message.setModular(6);
        message.setContent("新增" + personinformation.getSuspectstatus() + "人" + personinformation.getPersonname() + "," +
                personinformation.getGender() + "," + personinformation.getAge() + "岁" + personinformation.getBailoutbegindate() + "开始执行");

        message.setPersonid(personinformation.getPersonid());
        message.setModularname("人员消息");
        message.setDetailtypename("新增" + personinformation.getSuspectstatus() + "人员");
        if (personinformation.getSuspectstatuscode().equals("1")) {
            message.setDetailtype(1);
        } else {
            message.setDetailtype(2);
        }

        message.setReadmessage(false);
        taskDao.SaveMessage(message);
        if (CacheUtils.get("UserName").toString() != null) {
            personinformation.setFounderid(CacheUtils.get("UserName").toString());
        }

        //personinformation.setFoundertime(new Date().toString());
        personinformation.setPersonid(PersonId);
        personinformation.setViolationcode("0");
        personinformation.setCaseno(tCaseinfo.getCaseno());
        return personDao.insertPersion(personinformation);
    }

    //修改
    public int updatePersion(Personinformation personinformation) {
        GuaranteeInformation guaranteeInformation = personinformation.getGuaranteeInformation();
        guarantDao.updateGuara(guaranteeInformation);
        TCaseinfo tCaseinfo = personinformation.gettCaseinfo();
        caseDao.UpdateCase(tCaseinfo);
        if (CacheUtils.get("UserName").toString() != null) {
            personinformation.setModifierid(CacheUtils.get("UserName").toString());
        }
        personinformation.setCaseno(tCaseinfo.getCaseno());
        //personinformation.setModifiertime(new Date());
        return personDao.updatePersion(personinformation);
    }

    //删除
    public int deletePersion(boolean flag, String PersionId) {

        return personDao.deletePersion(flag, PersionId);
    }

    //获取人员信息
    public Personinformation getPersoin(String id, String Caseno) throws Exception {
        Personinformation personinformation = personDao.getPersoin(id);
        GuaranteeInformation guaranteeInformation = guarantDao.getGuara(id);

        personinformation.setGuaranteeInformation(guaranteeInformation);
        TCaseinfo tCaseinfo = caseDao.GetCase(id, Caseno);

        personinformation.settCaseinfo(tCaseinfo);
        if (CacheUtils.get("UserName").toString() != null) {
            personinformation.setFounderid(CacheUtils.get("UserName").toString());
        }
        return personinformation;
    }

    /*
    根据身份证查询是否重复
     */
    public int getPersonByCard(String Card) {

        return personDao.getPersonByCard(Card);
    }

    /*
    变更主办人
     */
    public int updateSponsor(String Name, String id, String PersonId) {

        return personDao.updateSponsor(Name, id, PersonId);
    }

    /*
    配置管理方式
     */

    public int insertprison(TPrisonsetting tPrisonsetting) {


        return personDao.insertprison(tPrisonsetting);
    }

    public int Getprison(String personId, String settingname) {

        return personDao.Getprison(personId, settingname);
    }

    /**
     * 取消配置
     *
     * @param tPrisonsetting
     * @return
     */
    public int delconfig(TPrisonsetting tPrisonsetting) {
        return personDao.delconfig(tPrisonsetting);
    }

    /*
    人员信息列表
     */
    public List<Personinformation> ListPerson(SearchModel searchModel) throws Exception {
        String limit = "";
        int type = JudgementRole.Distinguishroles();
        if (type == 1) {
            limit = CacheUtils.get("accountname").toString();
        } else {
            limit = CacheUtils.get("PoliceCode").toString();
        }
        List<Personinformation> personList = personDao.ListPerson(searchModel, type, limit);//这里面是每个人的信息

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (Personinformation item : personList) {
            List<TPrisonsetting> prisonList = personDao.ListPrison(item.getPersonid());//这里就是这个人有哪几种管理方式 根据身份证号查询
            List<String> list = new ArrayList<>();

            item.setFoundertime(sdf.format(sdf.parse(item.getFoundertime())));
            item.setBailoutbegindate(sdf.format(sdf.parse(item.getBailoutbegindate())));
            item.setBailoutenddate(sdf.format(sdf.parse(item.getBailoutenddate())));

            for (TPrisonsetting itam : prisonList) {

                list.add(itam.getSettingname());
            }
            item.setManagementStyle(list);
        }


        return personList;
    }

    public List<monthnumber> getvolocation(String Personid) {
        monthnumber model = personDao.getvolocation(Personid);
        List<monthnumber> list = new ArrayList<monthnumber>();
        monthnumber number = new monthnumber();
        monthnumber number1 = new monthnumber();
        if (model.getBailnumber() != 0) {
            Violationfens violationfens = violationDao.GetByCriteria("2");
            if (violationfens.getSlightfens() <= model.getBailnumber() && model.getBailnumber() < violationfens.getSeriousfens()) {
                number.setMonth("轻微");
                number.setBailnumber(model.getBailnumber());
                list.add(number);
            } else if (model.getBailnumber() >= violationfens.getSeriousfens()) {
                number.setMonth("严重");
                number.setBailnumber(model.getBailnumber());
                list.add(number);
            }else {
                number.setBailnumber(model.getBailnumber());
                number.setMonth("正常");
                list.add(number);
            }
        } else {
            number.setBailnumber(model.getBailnumber());
            number.setMonth("正常");
            list.add(number);
        }

        if (model.getSupervisionnumber() != 0) {
            Violationfens violationfens = violationDao.GetByCriteria("1");
            if (violationfens.getSlightfens() <= model.getSupervisionnumber() && model.getSupervisionnumber() < violationfens.getSeriousfens()) {
                number1.setMonth("轻微");
                number1.setBailnumber(model.getSupervisionnumber());
                list.add(number1);
            } else if (model.getSupervisionnumber() >= violationfens.getSeriousfens()) {
                number1.setMonth("严重");
                number1.setBailnumber(model.getSupervisionnumber());
                list.add(number1);
            }else{
                number1.setMonth("正常");
                number1.setBailnumber(model.getSupervisionnumber());
                list.add(number1);
            }
        } else {
            number1.setMonth("正常");
            number1.setBailnumber(model.getSupervisionnumber());
            list.add(number1);
        }


        return list;
    }

    public List<Map<String, String>> getdetails(int id, String personid) {

        return personDao.getdetails(id, personid);
    }

    public void getlistpernson() throws Exception{
        Calendar cal = Calendar.getInstance();
        TSynchron tSynchron = caseDao.GetTsyn(3);
        List<Personinformation> list=new ArrayList<Personinformation>();
        if (tSynchron!=null){
            list=fsdao.getperson(tSynchron.getDatatime());
        }else{
            list=fsdao.getperson("1");
        }
        for (Personinformation item:list){
            item.setAge(CalendarAdjust.getAge(CalendarAdjust.dateFormat.parse(item.getBirthdate())));
            cal.setTime(CalendarAdjust.dateFormat.parse( item.getBailoutbegindate()));
            cal.add(Calendar.YEAR, 1);
            item.setBailoutenddate(CalendarAdjust.dateFormat.format(cal.getTime()));
            if (item.getSuspectstatus().indexOf("取保候审")!=-1){
                item.setSuspectstatus("取保候审");
                item.setSuspectstatuscode("1");
            }else if(item.getSuspectstatus().indexOf("监视居住")!=-1){
                item.setSuspectstatus("监视居住");
                item.setSuspectstatuscode("2");
            }
            if(item.getExectype().indexOf("保证金")!=-1){
                item.setExectype("财保");
            }else if(item.getExectype().indexOf("保证书")!=-1) {
                item.setExectype("人保");
            }
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
            if (personDao.getpersonbyguid(item.getGuid())==0){
                item.setPersonid(java.util.UUID.randomUUID().toString());
                item.setViolationcode("0");
                item.setStatus(true);

                messageDao.insertuser(user);
                personDao.insertPersion(item);
            }else{
                messageDao.updateUser(user);
                personDao.updatePersionbyguid(item);
            }


        }
        TSynchron model = new TSynchron();
        model.setName(3);
        model.setDatatime(CalendarAdjust.GetYear(new Date()));
        caseDao.insertTsyn(model);

    }

}
