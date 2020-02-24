package com.admin.admin.service.dw_leave;

import com.admin.admin.dao.dw_leave.LeaveDao;
import com.admin.admin.entity.dw_auditor.AuditorInformation;
import com.admin.admin.entity.dw_leave.CountResult;
import com.admin.admin.entity.dw_leave.LeaveInformation;
import com.admin.admin.entity.dw_leave.PersonInformation;
import com.admin.model.leave.LeavefModel;
import com.admin.model.search.SearchModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LeaveService {
    @Autowired
    private LeaveDao leaveDao;


    //获取
    public List<LeavefModel> getLeave(SearchModel searchModel) {

        return leaveDao.getLeave(searchModel);

    }

    //获取列表
//    public LeaveInformation listLeave() {
//        return leaveDao.listLeave();
//    }

    //更新
//    public int updateLeave(LeaveInformation leaveinformation) {
//        return leaveDao.updateLeave(leaveinformation);
//    }

//    public LeaveInformation getLeaveInformation(AuditorInformation auditorInformation){
//        return leaveDao.getLeaveInformation(auditorInformation);
//    }

    public int insertAuditor(AuditorInformation auditorInformation) {

        return leaveDao.insertAuditor(auditorInformation);
    }
    public LeaveInformation getLeaveInformation(String leaveorder){
        return leaveDao.getLeaveInformation(leaveorder);
    }
    public int  updateLeaveStatus(AuditorInformation auditorInformation){
        return leaveDao.updateLeaveStatus(auditorInformation);
    }


    public int deleteAuditor(String leaveOrder){return leaveDao.deleteAuditor(leaveOrder);}

    public int cancelAuditor(String leaveorder){
        return leaveDao.cancelAuditor(leaveorder);
    }

    public List<CountResult> countLeave(String city,String area,String startTime, String endTime) throws ParseException {

        List<CountResult> countResults = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startTimeDate=sdf.parse(startTime);
        Date endTimeDate=sdf.parse(endTime);
        int sum=0;
        if(area==null){
            List<PersonInformation> personInformationarea =leaveDao.countLeavearea(city,area);//选择市的时候获得各个区名
            for(int i =0;i<personInformationarea.size();i++){
                personInformationarea.get(i).setDetail(personInformationarea.get(i).getDetail().replace("佛山市",""));
                personInformationarea.get(i).setDetail(personInformationarea.get(i).getDetail().replace("公安局",""));
                personInformationarea.get(i).setDetail(personInformationarea.get(i).getDetail().replace("分局",""));
                personInformationarea.get(i).setDetail(personInformationarea.get(i).getDetail().replace("区",""));
                List<PersonInformation> personInformations =leaveDao.countLeavepersonid(city,personInformationarea.get(i).getDetail());//根据区名查询出该区的人员id
                for(int j=0;j<personInformations.size();j++){//循环完之后便得出该区的外出申请单数
                    List<LeaveInformation> leaveInformations =leaveDao.countLeave(personInformations.get(j).getPersonid(),startTimeDate,endTimeDate);//根据每个区的peisonid获取该区的外出申请单数
                    sum+=leaveInformations.size();
                }
                CountResult countResult = new CountResult();
                countResult.setName(personInformationarea.get(i).getDetail());
                countResult.setCount(sum);
                countResults.add(countResult);
                sum =0;
            }
        }
        else{
            List<PersonInformation> policeStation  = leaveDao.getPoliceStation(area);
            for(int i =0;i<policeStation.size();i++){
                List<PersonInformation> personInformations =leaveDao.countLeavepersonid(city,policeStation.get(i).getDetail());//根据区名查询出该区的人员id
                for(int j=0;j<personInformations.size();j++){//循环完之后便得出该区的外出申请单数
                    List<LeaveInformation> leaveInformations =leaveDao.countLeave(personInformations.get(j).getPersonid(),startTimeDate,endTimeDate);//根据每个区的peisonid获取该区的外出申请单数
                    sum+=leaveInformations.size();
                }
                CountResult countResult = new CountResult();
                countResult.setName(policeStation.get(i).getDetail());
                countResult.setCount(sum);
                countResults.add(countResult);
                sum =0;
            }
        }



        return countResults;
    }
}
