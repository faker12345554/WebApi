package com.prisonapp.business.entity.dw_system;

import java.util.List;

public class ResultGetUpdateRecordsModel {
    private int totalCount;
    private List<GetUpdateRecordsModel> getUpdateRecordsModels;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<GetUpdateRecordsModel> getGetUpdateRecordsModels() {
        return getUpdateRecordsModels;
    }

    public void setGetUpdateRecordsModels(List<GetUpdateRecordsModel> getUpdateRecordsModels) {
        this.getUpdateRecordsModels = getUpdateRecordsModels;
    }
}
