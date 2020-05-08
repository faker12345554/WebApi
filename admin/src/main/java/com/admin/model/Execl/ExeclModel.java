package com.admin.model.Execl;

import com.admin.model.search.SearchModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class ExeclModel  {
    @ApiModelProperty(value = "编号",dataType = "String")
    private String PersonId;
    @ApiModelProperty(value = "导出数据时,选中的数据集合",dataType = "String")
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
