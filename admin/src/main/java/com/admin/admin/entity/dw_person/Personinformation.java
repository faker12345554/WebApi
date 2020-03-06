package com.admin.admin.entity.dw_person;

import com.admin.admin.entity.dw_case.TCaseinfo;
import com.admin.admin.entity.dw_guarant.GuaranteeInformation;
import com.admin.admin.entity.dw_prisonsetting.TPrisonsetting;
import org.apache.tomcat.jni.Time;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Personinformation {
    private String personid;
    private String personname;
    private String gender;
    private int age;
    private String birthdate;
    private String card;
    private String Workunit;
    private String Bailoutbegindate;
    private String Bailoutenddate;
    private String Sponsor;
    private String sponsoralarm;
    private String Contact;
    private String Address;
    private String wechatnumber;
    private String qqnumber;
    private boolean status;
    private String founderid;
    private String foundertime;
    private String modifierid;
    private String modifiertime;
    private String suspectstatus;
    private String marriage;
    private String policestation;
    private String casetype;
    private String facepath;
    private String persontags;

    public String getPolicecode() {
        return policecode;
    }

    public Personinformation setPolicecode(String policecode) {
        this.policecode = policecode;
        return this;
    }

    private String policecode;

    public String getPersonid() {
        return personid;
    }

    public Personinformation setPersonid(String personid) {
        this.personid = personid;
        return this;
    }

    public String getPersonname() {
        return personname;
    }

    public Personinformation setPersonname(String personname) {
        this.personname = personname;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public Personinformation setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Personinformation setAge(int age) {
        this.age = age;
        return this;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public Personinformation setBirthdate(String birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    public String getCard() {
        return card;
    }

    public Personinformation setCard(String card) {
        this.card = card;
        return this;
    }

    public String getWorkunit() {
        return Workunit;
    }

    public Personinformation setWorkunit(String workunit) {
        Workunit = workunit;
        return this;
    }

    public String getBailoutbegindate() {
        return Bailoutbegindate;
    }

    public Personinformation setBailoutbegindate(String bailoutbegindate) {
        Bailoutbegindate = bailoutbegindate;
        return this;
    }

    public String getBailoutenddate() {
        return Bailoutenddate;
    }

    public Personinformation setBailoutenddate(String bailoutenddate) {
        Bailoutenddate = bailoutenddate;
        return this;
    }

    public String getSponsor() {
        return Sponsor;
    }

    public Personinformation setSponsor(String sponsor) {
        Sponsor = sponsor;
        return this;
    }

    public String getSponsoralarm() {
        return sponsoralarm;
    }

    public Personinformation setSponsoralarm(String sponsoralarm) {
        this.sponsoralarm = sponsoralarm;
        return this;
    }

    public String getContact() {
        return Contact;
    }

    public Personinformation setContact(String contact) {
        Contact = contact;
        return this;
    }

    public String getAddress() {
        return Address;
    }

    public Personinformation setAddress(String address) {
        Address = address;
        return this;
    }

    public String getWechatnumber() {
        return wechatnumber;
    }

    public Personinformation setWechatnumber(String wechatnumber) {
        this.wechatnumber = wechatnumber;
        return this;
    }

    public String getQqnumber() {
        return qqnumber;
    }

    public Personinformation setQqnumber(String qqnumber) {
        this.qqnumber = qqnumber;
        return this;
    }

    public boolean isStatus() {
        return status;
    }

    public Personinformation setStatus(boolean status) {
        this.status = status;
        return this;
    }

    public String getFounderid() {
        return founderid;
    }

    public Personinformation setFounderid(String founderid) {
        this.founderid = founderid;
        return this;
    }

    public String getFoundertime() {
        return foundertime;
    }

    public Personinformation setFoundertime(String foundertime) {
        this.foundertime = foundertime;
        return this;
    }

    public String getModifierid() {
        return modifierid;
    }

    public Personinformation setModifierid(String modifierid) {
        this.modifierid = modifierid;
        return this;
    }

    public String getModifiertime() {
        return modifiertime;
    }

    public Personinformation setModifiertime(String modifiertime) {
        this.modifiertime = modifiertime;
        return this;
    }

    public String getSuspectstatus() {
        return suspectstatus;
    }

    public Personinformation setSuspectstatus(String suspectstatus) {
        this.suspectstatus = suspectstatus;
        return this;
    }

    public String getMarriage() {
        return marriage;
    }

    public Personinformation setMarriage(String marriage) {
        this.marriage = marriage;
        return this;
    }

    public String getPolicestation() {
        return policestation;
    }

    public Personinformation setPolicestation(String policestation) {
        this.policestation = policestation;
        return this;
    }

    public String getCasetype() {
        return casetype;
    }

    public Personinformation setCasetype(String casetype) {
        this.casetype = casetype;
        return this;
    }

    public String getFacepath() {
        return facepath;
    }

    public Personinformation setFacepath(String facepath) {
        this.facepath = facepath;
        return this;
    }

    public String getPersontags() {
        return persontags;
    }

    public Personinformation setPersontags(String persontags) {
        this.persontags = persontags;
        return this;
    }

    public int getGendercode() {
        return gendercode;
    }

    public Personinformation setGendercode(int gendercode) {
        this.gendercode = gendercode;
        return this;
    }

    public String getBeforename() {
        return beforename;
    }

    public Personinformation setBeforename(String beforename) {
        this.beforename = beforename;
        return this;
    }

    public String getNation() {
        return nation;
    }

    public Personinformation setNation(String nation) {
        this.nation = nation;
        return this;
    }

    public String getDegreeeducation() {
        return degreeeducation;
    }

    public Personinformation setDegreeeducation(String degreeeducation) {
        this.degreeeducation = degreeeducation;
        return this;
    }

    public String getNativeplace() {
        return nativeplace;
    }

    public Personinformation setNativeplace(String nativeplace) {
        this.nativeplace = nativeplace;
        return this;
    }

    public String getOccupation() {
        return occupation;
    }

    public Personinformation setOccupation(String occupation) {
        this.occupation = occupation;
        return this;
    }

    public String getNationality() {
        return nationality;
    }

    public Personinformation setNationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    public String getRegisteredarea() {
        return registeredarea;
    }

    public Personinformation setRegisteredarea(String registeredarea) {
        this.registeredarea = registeredarea;
        return this;
    }

    public String getPermanentaddress() {
        return permanentaddress;
    }

    public Personinformation setPermanentaddress(String permanentaddress) {
        this.permanentaddress = permanentaddress;
        return this;
    }

    public String getCurrentaddress() {
        return currentaddress;
    }

    public Personinformation setCurrentaddress(String currentaddress) {
        this.currentaddress = currentaddress;
        return this;
    }

    public String getNowaddress() {
        return nowaddress;
    }

    public Personinformation setNowaddress(String nowaddress) {
        this.nowaddress = nowaddress;
        return this;
    }

    public String getYjsponsoralarm() {
        return yjsponsoralarm;
    }

    public Personinformation setYjsponsoralarm(String yjsponsoralarm) {
        this.yjsponsoralarm = yjsponsoralarm;
        return this;
    }

    public String getExectype() {
        return exectype;
    }

    public Personinformation setExectype(String exectype) {
        this.exectype = exectype;
        return this;
    }

    public String getKeepaddress() {
        return keepaddress;
    }

    public Personinformation setKeepaddress(String keepaddress) {
        this.keepaddress = keepaddress;
        return this;
    }

    public boolean isAppointaddress() {
        return appointaddress;
    }

    public Personinformation setAppointaddress(boolean appointaddress) {
        this.appointaddress = appointaddress;
        return this;
    }

    public String getProvince() {
        return province;
    }

    public Personinformation setProvince(String province) {
        this.province = province;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Personinformation setCity(String city) {
        this.city = city;
        return this;
    }

    public String getArea() {
        return area;
    }

    public Personinformation setArea(String area) {
        this.area = area;
        return this;
    }

    public String getMonitoraddress() {
        return monitoraddress;
    }

    public Personinformation setMonitoraddress(String monitoraddress) {
        this.monitoraddress = monitoraddress;
        return this;
    }

    public String getSuspectstatuscode() {
        return suspectstatuscode;
    }

    public Personinformation setSuspectstatuscode(String suspectstatuscode) {
        this.suspectstatuscode = suspectstatuscode;
        return this;
    }

    public String getGuid() {
        return guid;
    }

    public Personinformation setGuid(String guid) {
        this.guid = guid;
        return this;
    }

    public String getViolationcode() {
        return violationcode;
    }

    public Personinformation setViolationcode(String violationcode) {
        this.violationcode = violationcode;
        return this;
    }

    public String getHandlepeson() {
        return handlepeson;
    }

    public Personinformation setHandlepeson(String handlepeson) {
        this.handlepeson = handlepeson;
        return this;
    }

    public TCaseinfo gettCaseinfo() {
        return tCaseinfo;
    }

    public Personinformation settCaseinfo(TCaseinfo tCaseinfo) {
        this.tCaseinfo = tCaseinfo;
        return this;
    }

    public GuaranteeInformation getGuaranteeInformation() {
        return guaranteeInformation;
    }

    public Personinformation setGuaranteeInformation(GuaranteeInformation guaranteeInformation) {
        this.guaranteeInformation = guaranteeInformation;
        return this;
    }

    public List<String> getManagementStyle() {
        return managementStyle;
    }

    public Personinformation setManagementStyle(List<String> managementStyle) {
        this.managementStyle = managementStyle;
        return this;
    }

    public String getCaseno() {
        return caseno;
    }

    public Personinformation setCaseno(String caseno) {
        this.caseno = caseno;
        return this;
    }

    private int gendercode;
    private String beforename;
    private String nation;
    private String degreeeducation;
    private String nativeplace;
    private String occupation;
    private String nationality;
    private String registeredarea;
    private String permanentaddress;
    private String currentaddress;
    private String nowaddress;
    private String yjsponsoralarm;
    private String exectype;
    private String keepaddress;
    private boolean appointaddress;
    private String province;
    private String city;
    private String area;
    private String monitoraddress;
    private String suspectstatuscode;
    private String guid;
    private String violationcode;
    private String handlepeson;
    private TCaseinfo tCaseinfo;
    private GuaranteeInformation guaranteeInformation;
    private List<String> managementStyle;
    private String caseno;


}
