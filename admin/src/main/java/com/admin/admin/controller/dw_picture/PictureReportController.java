package com.admin.admin.controller.dw_picture;

import com.admin.admin.entity.dw_person.Personinformation;
import com.admin.admin.service.dw_picture.PictureReportService;
import com.admin.token.tation.PassToken;
import com.admin.token.tation.UserLoginToken;
import com.common.common.Uploadfiles.Upload;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.aspectj.util.FileUtil.copyDir;
import static org.aspectj.util.FileUtil.copyFile;

@RestController
@RequestMapping("/person")
public class PictureReportController {
    @Autowired
    private PictureReportService pictureReportService;

    private Upload upload=new Upload();

    @UserLoginToken
    @ApiOperation(value = "人脸识别数据导入")
    @PostMapping("/postPictureReport")
    public ResponseResult postPictureReport(@RequestParam(required = true)String personid,@RequestParam(required = true)String facepath) throws IOException {
        ResponseResult result = new ResponseResult();
        Personinformation personinformation=pictureReportService.checkPersonId(personid);
        if(personinformation!=null){
            File tempFile=new File(facepath.trim());
            if(tempFile.isFile()){
                String fileName=tempFile.getName();
                File f = new File(System.getProperty("user.dir")+"/faceImage");
                if(!f.exists()){      //判断文件夹是否存在
                    f.mkdir();
                }
                if(!(new File(facepath)).isDirectory()){    //判断文件是否存在
                    copyFile(tempFile,f);
                }
                String newPath=f.toString()+"\\"+fileName;
                result.setCode(ResultCode.SUCCESS.getCode());
                result.setMessage(ResultCode.SUCCESS.getMessage());
                return result.setData(pictureReportService.postPictureReport(personid, newPath));
            }
            else{
                result.setCode(ResultCode.PARAMS_ERROR.getCode());
                result.setMessage(ResultCode.PARAMS_ERROR.getMessage());
                return result.setData("文件格式不正确");
            }
        }
        else{
            result.setCode(ResultCode.NO_PERSONID.getCode());
            result.setMessage(ResultCode.NO_PERSONID.getMessage());
            return result.setData(null);
        }
    }

    @PassToken
    @ApiOperation(value = "上传照片")
    @PostMapping("/uploadPicture")
    public ResponseResult uploadPicture(@ApiParam(name = "file",value = "照片")@RequestParam(required =true) MultipartFile file){
        ResponseResult result = new ResponseResult();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        String url = System.getProperty("user.dir") + "/../webapps/mypicture/admin/" + formatter.format(date)+"/";
        File path = new File(url);
        if (!path.exists() && !path.isDirectory()) {
            path.setWritable(true,false);
            path.mkdirs();
        }
        String res = upload.upload(url, file);
        String fileName = file.getOriginalFilename();
        String filePath="https://pardon.cnnc626.com:8443/mypicture/admin/"+ formatter.format(date)+"/"+fileName;
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(filePath);
    }

}
