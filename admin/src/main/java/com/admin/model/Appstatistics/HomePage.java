package com.admin.model.Appstatistics;

import com.admin.admin.entity.dw_log.LogInformation;
import com.admin.admin.entity.dw_summons.TSummons;
import com.admin.model.log.LogModel;

import java.util.List;

public class HomePage {
    private int Bailmonth;
    private int Prisonmonth;
    private int bailyear;
    private int Maturitybail;
    private int Arraignmentnumber;
    private int positionnumber;
    private int AnnualsummonsNum;
    private int Annuallocation;
    private int monthnumber;
    private List<TSummons> summons;
    private List<monthnumber> personnumber;
    private List<LogModel> logList;

    public List<com.admin.model.Appstatistics.monthnumber> getPersonnumber() {
        return personnumber;
    }

    public HomePage setPersonnumber(List<com.admin.model.Appstatistics.monthnumber> personnumber) {
        this.personnumber = personnumber;
        return this;
    }


    public List<TSummons> getSummons() {
        return summons;
    }

    public HomePage setSummons(List<TSummons> summons) {
        this.summons = summons;
        return this;
    }

    public List<LogModel> getLogList() {
        return logList;
    }

    public HomePage setLogList(List<LogModel> logList) {
        this.logList = logList;
        return this;
    }



    public int getBailmonth() {
        return Bailmonth;
    }

    public HomePage setBailmonth(int bailmonth) {
        Bailmonth = bailmonth;
        return this;
    }

    public int getPrisonmonth() {
        return Prisonmonth;
    }

    public HomePage setPrisonmonth(int prisonmonth) {
        Prisonmonth = prisonmonth;
        return this;
    }

    public int getBailyear() {
        return bailyear;
    }

    public HomePage setBailyear(int bailyear) {
        this.bailyear = bailyear;
        return this;
    }

    public int getMaturitybail() {
        return Maturitybail;
    }

    public HomePage setMaturitybail(int maturitybail) {
        Maturitybail = maturitybail;
        return this;
    }

    public int getArraignmentnumber() {
        return Arraignmentnumber;
    }

    public HomePage setArraignmentnumber(int arraignmentnumber) {
        Arraignmentnumber = arraignmentnumber;
        return this;
    }

    public int getPositionnumber() {
        return positionnumber;
    }

    public HomePage setPositionnumber(int positionnumber) {
        this.positionnumber = positionnumber;
        return this;
    }

    public int getAnnualsummonsNum() {
        return AnnualsummonsNum;
    }

    public HomePage setAnnualsummonsNum(int annualsummonsNum) {
        AnnualsummonsNum = annualsummonsNum;
        return this;
    }

    public int getAnnuallocation() {
        return Annuallocation;
    }

    public HomePage setAnnuallocation(int annuallocation) {
        Annuallocation = annuallocation;
        return this;
    }

    public int getMonthnumber() {
        return monthnumber;
    }

    public HomePage setMonthnumber(int monthnumber) {
        this.monthnumber = monthnumber;
        return this;
    }


}
