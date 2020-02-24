package com.admin.admin.controller.dw_log;

import com.admin.admin.service.dw_address.AddressService;
import com.admin.admin.service.dw_log.LogService;
import com.admin.model.Appstatistics.AppStatistics;
import com.admin.model.log.LogModel;
import com.admin.model.log.LogParamModel;
import com.admin.model.log.LogSearchModel;
import com.admin.page.PageBean;
import com.admin.token.tation.PassToken;
import com.admin.token.tation.UserLoginToken;
import com.common.common.authenticator.CalendarAdjust;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import com.github.mustachejava.Code;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Api(value = "操作日志controller", tags = {"查看操作日志"})
@RestController
@RequestMapping("/Log")
public class LogController {
    @Autowired
    private LogService logService;

    @Autowired
    private AddressService addressService;
    private ResponseResult result = new ResponseResult();


    //    @ApiOperation(value = "增加日志")
////    @PostMapping("/addLog")
////    public ResponseResult insertLog(@RequestBody LogInformation logInformation, HttpServletResponse response) {
////        result.setCode(ResultCode.SUCCESS.getCode());
////        result.setMessage(ResultCode.SUCCESS.getMessage());
////        return result.setData(logService.insertLog(logInformation));
////    }
   // @UserLoginToken
    @ApiOperation(value = "查询日志信息")
    @PostMapping("/getLog")
    public ResponseResult listLog(@RequestBody LogParamModel logParamModel, HttpServletResponse response) {
        PageBean pageBean = logService.listLog(logParamModel);
        if (pageBean.getItems().size() <= 0) {
            result.setCode(ResultCode.NULLDATA.getCode());
            result.setMessage(ResultCode.NULLDATA.getMessage());
            return result.setData("");
        }
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(pageBean);


    }

   // @UserLoginToken
    @ApiOperation(value = "统计取保监居APP使用人数")
    @GetMapping("/Statistics")
    public ResponseResult getNumber(@RequestParam String code, @RequestParam int level, String Days) {
        List<AppStatistics> numberList = new ArrayList<AppStatistics>();
        List<Map<String, String>> addlist = new ArrayList<>();
//        List<Map<String, String>> policelist = new ArrayList<>();
//        List<Map<String, String>> Stationlist = new ArrayList<>();
        if (level == 1) {
            addlist = addressService.getAddress(code, 2);
            for (Map<String, String> item : addlist) {
                AppStatistics model = new AppStatistics();
                for (String Key : item.keySet()) {
                    String Areacode = item.get("code");
                    Areacode = Areacode.substring(0, 6);
                    model = logService.getNumber(Areacode, Days);
                    model.setCode(item.get("code"));
                    model.setName(item.get("name"));
                }
                numberList.add(model);
            }
        } else if (level == 2) {
            addlist = addressService.getAddress(code.substring(0,6), 3);

            for (Map<String, String> item : addlist) {
                AppStatistics model = new AppStatistics();
                for (String Key : item.keySet()) {
                    String Areacode = item.get("code");
                    Areacode = Areacode.substring(0, 8);
                    model = logService.getNumber(Areacode, Days);
                    model.setCode(item.get("code"));
                    model.setName(item.get("name"));
                }
                numberList.add(model);
            }
        }
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(numberList);
    }

    @ApiOperation(value = "统计脱管人数")
    @GetMapping("/Removalrate")
    public ResponseResult Removalrate(@RequestParam String code,int level,String Starttime,String endetime) {
        List<AppStatistics> numberList = new ArrayList<AppStatistics>();
        if (level == 3) {
            code = code.substring(0, 6);
        } else if (level == 4) {
            code = code.substring(0, 8);
        }
        List<Map<String, String>> addlist = addressService.getAddress(code, level);
        for (Map<String, String> item : addlist) {
            AppStatistics model = new AppStatistics();
            for (String Key : item.keySet()) {
                String Areacode = item.get("code");
                Areacode = Areacode.substring(0, (2*level)+2);
                List<Map<String,String>> countlist = logService.Removalrate(Areacode, Starttime, endetime);
                model.setTotalnumber(logService.gettotelnumber(Areacode));
                int usernumber=0;
                for (Map<String,String> itam: countlist){

                    int Days= Integer.parseInt(itam.get("Days").toString());
                    if (Days>7){
                        usernumber+=1;
                    }

                }
                model.setUsernumber(usernumber);
                model.setName(item.get("name"));
                model.setCode(item.get("code"));

            }
            numberList.add(model);
        }
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(numberList);
    }

    @ApiOperation(value = "统计日月活率")
    @GetMapping("/Solarrate")
    public ResponseResult Solarrate(@RequestParam String code,int codelevel, int level,String Starttime,String endetime) {
        if (codelevel==1){
            code=code.substring(0,4);
        } else if (codelevel==2){
            code=code.substring(0,6);
        }

        else if (codelevel == 3) {
            code = code.substring(0, 8);
        } else if (codelevel == 4) {
            code = code.substring(0, 10);
        }
        List<AppStatistics> modelList = logService.Solarrate(code, Starttime,endetime,level);
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(modelList);
    }

    @ApiOperation(value = "获取查询日志")
    @PassToken
    @GetMapping("/listLog")
    public ResponseResult listAllLog(@RequestBody(required = true) LogSearchModel logSearchModel){
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
