package com.admin.model.Execl;

import java.util.List;

public class ExeclModel {
    private String PersonId;
    private List<Integer> list;

    public String getPersonId() {
        return PersonId;
    }

    public ExeclModel setPersonId(String personId) {
        PersonId = personId;
        return this;
    }

    public List<Integer> getList() {
        return list;
    }

    public ExeclModel setList(List<Integer> list) {
        this.list = list;
        return this;
    }
}
