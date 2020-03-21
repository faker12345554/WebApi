package com.admin.admin.service.dw_Report;

import com.admin.admin.dao.master.dw_report.ReportDao;
import com.admin.admin.entity.dw_report.Reportsettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private ReportDao reportDao;

    /*
    新增
     */
    public int SaveOrUpdateReport(List<Reportsettings> reportsettings){

        reportDao.deleteReport();

        for (Reportsettings item: reportsettings){
            item.setStatus(true);
            item.setCreattime(new Date());

            reportDao.SaveReport(item);
        }
        Reportsettings report=new Reportsettings();
        return report.getId();
    }

    /*
    删除
     */

    public int deleteReport(){
        return reportDao.deleteReport();
    }

    /*
    查看
     */
    public List<Reportsettings> getReport(){
        return reportDao.getReport();
    }
}
