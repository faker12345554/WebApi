package com.admin.model.Execl;

import com.admin.model.search.SearchModel;

import java.util.List;

public class ExeclModel  {
    private String PersonId;
    private List<String> list;

    public List<String> getList() {
        return list;
    }

    public ExeclModel setList(List<String> list) {
        this.list = list;
        return this;
    }

    public String getPersonId() {
        return PersonId;
    }

    public ExeclModel setPersonId(String personId) {
        PersonId = personId;
        return this;
    }


}
