package com.admin.admin.service.dw_Report;

import com.admin.admin.dao.dw_report.ReportDao;
import com.admin.admin.entity.dw_report.Reportsettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    @Autowired
    private ReportDao reportDao;

    /*
    新增
     */
    public int SaveOrUpdateReport(Reportsettings reportsettings){
        if (reportsettings.getId()!=0){
            return reportDao.UpdateReport(reportsettings);
        }
        return reportDao.SaveReport(reportsettings);
    }

    /*
    删除
     */

    public int deleteReport(int id){
        return reportDao.deleteReport(id);
    }

    /*
    查看
     */
    public Reportsettings getReport(){
        return reportDao.getReport();
    }
}
