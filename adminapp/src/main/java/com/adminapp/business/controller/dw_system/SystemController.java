package com.adminapp.business.controller.dw_system;

import com.adminapp.business.service.dw_system.SystemService;
import com.adminapp.config.token.TokenUtil;
import com.adminapp.config.token.tation.PassToken;
import com.adminapp.config.token.tation.UserLoginToken;
import com.adminapp.model.demo.DemoModel;
import com.adminapp.model.dw_system.*;
import com.common.common.Uploadfiles.Upload;
import com.common.common.apppush.Demo;
import com.common.common.result.ResultSet;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Api(value="工作人员系统Controller",tags={"工作人员系统功能管理"})
@RequestMapping("/app/admin/system")
public class SystemController {
    @Autowired
    private SystemService systemService;

    //private ResultSet rs=new ResultSet();

    private Upload upload=new Upload();

    public DemoModel demo=new DemoModel();

    @PassToken
    @ApiOperation(value = "获取工作人员 App 更新信息")
    @GetMapping("/getUpdateInfo")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public ResultSet getUpdateInfo(){
        ResultSet rs=new ResultSet();
        List<UpdateInformationModel> getUpdateInformation=systemService.getUpdateInformation(1);
        rs.resultCode=0;
        rs.resultMsg="";
        rs.data=getUpdateInformation.get(0);
        return rs;
    }


    @ApiOperation(value = " 获取保外人员 App 更新信息")
    @GetMapping("/getSupervisedAppInfo")
    @PassToken
    public ResultSet getSupervisedAppInfo(){
        ResultSet rs=new ResultSet();
        List<UpdateInformationModel> getUpdateInformation=systemService.getUpdateInformation(0);
        rs.resultCode=0;
        rs.resultMsg="";
        rs.data=getUpdateInformation.get(0);
        return rs;
    }


    @ApiOperation(value = "获取更新记录")
    @GetMapping("/getUpdateRecords")
    @PassToken
    public ResultSet getUpdateRecords(@ApiParam(name = "count",value ="已获取数据条数" )@RequestParam(required = true)int count,
                                      @ApiParam(name = "requestCount",value = "请求获取数据条数")@RequestParam(required = true)int requestCount){
        ResultSet rs=new ResultSet();
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

    @UserLoginToken
    @ApiOperation(value = "意见反馈")
    @PostMapping("/submitAdvice")
    public ResultSet submitAdvice(@ApiParam(name = "type",value = "反馈类型")@RequestParam(required = true)String type,
                                  @ApiParam(name = "content",value = "反馈内容")@RequestParam(required = false)String content,
                                  @ApiParam(name="picture",value = "图片")@RequestParam(required = false) List<MultipartFile> picture,
                                  @ApiParam(name = "phone",value = "联系电话")@RequestParam(required = false)String phone){
        ResultSet rs=new ResultSet();
        if(type.equals("1")||type.equals("2")) {
            String userId = TokenUtil.getTokenUserId();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(System.currentTimeMillis());
            //保存图片的路径
            String url = System.getProperty("user.dir") + "/../webapps/mypicture/adminapp/" + formatter.format(date)+"/";
            File path = new File(url);
            if (!path.exists() && !path.isDirectory()) {
                path.setWritable(true,false);
                path.mkdirs();
            }
            String completeFilePath = "";
            if (picture.size() != 0) {
                String res = upload.multiUpload(url, picture);    //上传照片
                for (int i = 0; i < picture.size(); i++) {
                    MultipartFile file = picture.get(i);
                    String fileName = file.getOriginalFilename();
                    String serverUrl=demo.getServerUrl();
                    String filePath=serverUrl+"/mypicture/adminapp/"+ formatter.format(date)+"/"+fileName;
                    //String filePath = url + fileName;
                    if (i != 0) {
                        completeFilePath = completeFilePath + "," + filePath;
                    } else {
                        completeFilePath = completeFilePath + filePath;
                    }
                }
            }
            Date date1 = new Date();
            Timestamp timeStamp = new Timestamp(date1.getTime());
            SubmitAdviceModel submitAdviceModel = new SubmitAdviceModel();
            submitAdviceModel.setUserId(userId);
            submitAdviceModel.setType(type);
            submitAdviceModel.setContent(content);
            submitAdviceModel.setPicture(completeFilePath);
            submitAdviceModel.setPhone(phone);
            submitAdviceModel.setTimeStamp(timeStamp);
            int submitAdvice = systemService.submitAdvice(submitAdviceModel);
            SubmitAdviceReturnModel submitAdviceReturnModel = new SubmitAdviceReturnModel();
            submitAdviceReturnModel.setCode(String.valueOf(submitAdviceModel.getId()));
            rs.resultCode = 0;
            rs.resultMsg = "";
            rs.data = submitAdviceReturnModel;
        }
        else{
            rs.resultCode=1;
            rs.resultMsg="无此类型";
            rs.data=null;
        }
        return rs;
    }
}
