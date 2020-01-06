package com.adminapp.business.controller.dw_system;

import com.adminapp.business.service.dw_system.SystemService;
import com.adminapp.config.token.tation.UserLoginToken;
import com.adminapp.model.dw_system.UpdateInformationModel;
import com.adminapp.model.dw_system.UpdateRecordModel;
import com.adminapp.model.dw_system.UpdateRecordReturnModel;
import com.common.common.result.ResultSet;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/app/admin/system")
public class SystemController {
    @Autowired
    private SystemService systemService;

    private ResultSet rs=new ResultSet();

    @UserLoginToken
    @ApiOperation(value = "获取工作人员 App 更新信息")
    @GetMapping("/getUpdateInfo")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public ResultSet getUpdateInfo(){
        List<UpdateInformationModel> getUpdateInformation=systemService.getUpdateInformation(1);
        rs.resultCode=0;
        rs.resultMsg="";
        rs.data=getUpdateInformation.get(0);
        return rs;
    }

    @UserLoginToken
    @ApiOperation(value = " 获取保外人员 App 更新信息")
    @GetMapping("/getSupervisedAppInfo")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public ResultSet getSupervisedAppInfo(){
        List<UpdateInformationModel> getUpdateInformation=systemService.getUpdateInformation(0);
        rs.resultCode=0;
        rs.resultMsg="";
        rs.data=getUpdateInformation.get(0);
        return rs;
    }

    @UserLoginToken
    @ApiOperation(value = "获取更新记录")
    @GetMapping("/getUpdateRecords")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public ResultSet getUpdateRecords(@ApiParam(name = "count",value ="已获取数据条数" )@RequestParam(required = true)int count,
                                      @ApiParam(name = "requestCount",value = "请求获取数据条数")@RequestParam(required = true)int requestCount){
        List<UpdateRecordModel> getUpdateRecord=systemService.getUpdateRecord(1);
        List<UpdateRecordModel> newUpdateRecord=new ArrayList<>();
        if (getUpdateRecord.size() > count && getUpdateRecord.size() <= count + requestCount) {
            for (int i = count; i < getUpdateRecord.size(); i++) {
                UpdateRecordModel summonsInformation = getUpdateRecord.get(i);
                newUpdateRecord.add(summonsInformation);
            }
        }
        if (getUpdateRecord.size() > count + requestCount) {
            for (int i = count; i < count + requestCount; i++) {
                UpdateRecordModel summonsInformation = getUpdateRecord.get(i);
                newUpdateRecord.add(summonsInformation);
            }
        }
        UpdateRecordReturnModel updateRecordReturnModel=new UpdateRecordReturnModel();
        updateRecordReturnModel.setTotalCount(getUpdateRecord.size());
        updateRecordReturnModel.setList(newUpdateRecord);
        rs.resultCode=0;
        rs.resultMsg="";
        rs.data=updateRecordReturnModel;
        return rs;
    }

    @PostMapping("/submitAdvice")
    public ResultSet submitAdvice(@RequestParam(required = false) MultipartFile file){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        //保存图片的路径
        String url = System.getProperty("user.dir") + "\\adminapp\\" + "\\src\\" + "\\main\\" + "\\resources\\" + "\\uploadFace\\" + formatter.format(date);
        return rs;
    }
}
