package com.admin.model.Execl;

public class ExeclModel {
    private String PersonId;
    private int[] IdList;

    public String getPersonId() {
        return PersonId;
    }

    public ExeclModel setPersonId(String personId) {
        PersonId = personId;
        return this;
    }

    public int[] getIdList() {
        return IdList;
    }

    public ExeclModel setIdList(int[] idList) {
        IdList = idList;
        return this;
    }


}
