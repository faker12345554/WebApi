package com.admin.admin.entity.dw_person;

import com.admin.admin.entity.dw_case.TCaseinfo;
import com.admin.admin.entity.dw_guarant.GuaranteeInformation;
import com.admin.admin.entity.dw_prisonsetting.TPrisonsetting;
import org.apache.tomcat.jni.Time;

import java.util.Date;
import java.util.List;

public class Personinformation {
    private String personid;
    private String personname;
    private String gender;
    private int age;
    private Date birthdate;
    private String card;
    private String Workunit;
    private Date Bailoutbegindate;
    private Date Bailoutenddate;
    private String Sponsor;
    private String sponsoralarm;
    private String Contact;
    private String Address;
    private String wechatnumber;
    private String qqnumber;
    private boolean status;
    private String founderid;
    private Date foundertime;
    private String modifierid;
    private Date modifiertime;
    private String suspectstatus;
    private String marriage;
    private String policestation;
    private String casetype;
    private String facepath;
    private String persontags;
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
    private String appointaddress;
    private String province;
    private String city;
    private String area;
    private String monitoraddress;
    private String suspectstatuscode;
    private TCaseinfo tCaseinfo;
    private GuaranteeInformation guaranteeInformation;
    private List<String> managementStyle;

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
    public GuaranteeInformation getGuaranteeInformation() {
        return guaranteeInformation;
    }

    public Personinformation setGuaranteeInformation(GuaranteeInformation guaranteeInformation) {
        this.guaranteeInformation = guaranteeInformation;
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

    public String getAppointaddress() {
        return appointaddress;
    }

    public Personinformation setAppointaddress(String appointaddress) {
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

    public List<String> getManagementStyle() {
        return managementStyle;
    }

    public Personinformation setManagementStyle(List<String> managementStyle) {
        this.managementStyle = managementStyle;
        return this;
    }


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

    public void setPersonname(String personname) {
        this.personname = personname;
    }

    public String getSponsoralarm() {
        return sponsoralarm;
    }

    public void setSponsoralarm(String sponsoralarm) {
        this.sponsoralarm = sponsoralarm;
    }

    public String getWechatnumber() {
        return wechatnumber;
    }

    public void setWechatnumber(String wechatnumber) {
        this.wechatnumber = wechatnumber;
    }

    public String getQqnumber() {
        return qqnumber;
    }

    public void setQqnumber(String qqnumber) {
        this.qqnumber = qqnumber;
    }

    public String getFounderid() {
        return founderid;
    }

    public Personinformation setFounderid(String founderid) {
        this.founderid = founderid;
        return this;
    }

    public Date getFoundertime() {
        return foundertime;
    }

    public Personinformation setFoundertime(Date foundertime) {
        this.foundertime = foundertime;
        return this;
    }

    public Date getModifiertime() {
        return modifiertime;
    }

    public Personinformation setModifiertime(Date modifiertime) {
        this.modifiertime = modifiertime;
        return this;
    }

    public String getModifierid() {
        return modifierid;
    }

    public Personinformation setModifierid(String modifierid) {
        this.modifierid = modifierid;
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




    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getWorkunit() {
        return Workunit;
    }

    public void setWorkunit(String workunit) {
        Workunit = workunit;
    }

    public Date getBailoutbegindate() {
        return Bailoutbegindate;
    }

    public void setBailoutbegindate(Date bailoutbegindate) {
        Bailoutbegindate = bailoutbegindate;
    }

    public Date getBailoutenddate() {
        return Bailoutenddate;
    }

    public void setBailoutenddate(Date bailoutenddate) {
        Bailoutenddate = bailoutenddate;
    }

    public String getSponsor() {
        return Sponsor;
    }

    public void setSponsor(String sponsor) {
        Sponsor = sponsor;
    }


    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    public TCaseinfo gettCaseinfo() {
        return tCaseinfo;
    }

    public Personinformation settCaseinfo(TCaseinfo tCaseinfo) {
        this.tCaseinfo = tCaseinfo;
        return this;
    }


}
