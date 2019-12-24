package com.prisonapp.business.entity.dw_supervise;

import java.util.Date;

public class GetSuperviseTaskModel {
   private String startDate;
   private String endDate;
   private int type;
   private String typeName;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        String a =String.valueOf(startDate.getTime());
        this.startDate = a;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        String a =String.valueOf(endDate.getTime());
        this.endDate = a;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
