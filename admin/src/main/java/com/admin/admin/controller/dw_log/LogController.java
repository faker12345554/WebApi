package com.admin.admin.controller.dw_log;

import com.admin.admin.service.dw_log.LogService;
import com.admin.model.log.LogModel;
import com.admin.model.log.LogParamModel;
import com.admin.model.log.LogSearchModel;
import com.admin.page.PageBean;
import com.admin.token.tation.PassToken;
import com.admin.token.tation.UserLoginToken;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Api(value="操作日志controller",tags={"查看操作日志"})
@RestController
@RequestMapping("/Log")
public class LogController {
    @Autowired
    private LogService logService;
    private ResponseResult result = new ResponseResult();


//    @ApiOperation(value = "增加日志")
////    @PostMapping("/addLog")
////    public ResponseResult insertLog(@RequestBody LogInformation logInformation, HttpServletResponse response) {
////        result.setCode(ResultCode.SUCCESS.getCode());
////        result.setMessage(ResultCode.SUCCESS.getMessage());
////        return result.setData(logService.insertLog(logInformation));
////    }
    @UserLoginToken
    @ApiOperation(value = "查询日志信息")
    @PostMapping("/getLog")
    public ResponseResult listLog(@RequestBody LogParamModel logParamModel, HttpServletResponse response){
        PageBean pageBean=logService.listLog(logParamModel);
        if (pageBean.getItems().size()<=0){
            result.setCode(ResultCode.NULLDATA.getCode());
            result.setMessage(ResultCode.NULLDATA.getMessage());
            return result.setData("" );
        }
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(pageBean);


    }

    @ApiOperation(value = "获取查询日志")
    @PassToken
    @GetMapping("/listLog")
    public ResponseResult listAllLog(@RequestBody(required = true)LogSearchModel logSearchModel){
        PageHelper.startPage(logSearchModel.getPageIndex(), logSearchModel.getPageSize());
        List<LogModel> allItems=logService.listAllLog(logSearchModel);
        try {
            if (allItems.size() == 0) {
                result.setCode(ResultCode.NULLDATA.getCode());
                result.setMessage(ResultCode.NULLDATA.getMessage());
                return result.setData("");
            }
            PageInfo<LogModel> info = new PageInfo<>(allItems);//全部商品
            int countNums = (int) info.getTotal();            //总记录数
            PageBean<LogModel> pageData = new PageBean<>(logSearchModel.getPageIndex(), logSearchModel.getPageSize(), countNums);
            pageData.setTotalPage(info.getPages());//总页数
            pageData.setItems(allItems);
            result.setCode(ResultCode.SUCCESS.getCode());
            result.setMessage(ResultCode.SUCCESS.getMessage());
            return result.setData(pageData);
        } catch (Exception ex) {
            result.setCode(ResultCode.UNKNOW_ERROR.getCode());
            result.setMessage(ResultCode.UNKNOW_ERROR.getMessage());
            return result.setData(ex.toString());
        }
    }

    @ApiOperation(value = "删除日志")
    @PassToken
    @PostMapping("/deleteLog")
    public ResponseResult deleteLog(@RequestParam int[] number){
        for (int code:number
             ) {
            int deleteLog=logService.deleteLog(code);
        }
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData("");
    }
}
