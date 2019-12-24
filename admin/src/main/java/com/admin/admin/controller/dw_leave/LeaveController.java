package com.admin.admin.controller.dw_leave;


import com.admin.admin.entity.dw_auditor.AuditorInformation;
import com.admin.admin.entity.dw_leave.LeaveInformation;
import com.admin.admin.entity.dw_location.Locationmation;
import com.admin.admin.service.dw_leave.LeaveService;
import com.admin.model.leave.LeavefModel;
import com.admin.model.search.SearchModel;
import com.admin.page.PageBean;
import com.admin.page.PageUtil;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(value="审核请假记录controller",tags={"审核请假记录"})

@RestController
@RequestMapping("/Leave")
public class LeaveController {
    @Autowired
    protected LeaveService leaveService;

    private ResponseResult result = new ResponseResult();

    @ApiOperation(value = "获取全部请假信息")
    @PostMapping("/listLeave")
    public ResponseResult listLeave(@RequestParam SearchModel searchModel) {

        List<LeavefModel> allItems = leaveService.getLeave(searchModel);
        try {
            if (allItems.size() == 0) {
                result.setCode(ResultCode.NULLDATA.getCode());
                result.setMessage(ResultCode.NULLDATA.getMessage());
                return result.setData("");
            }
            //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
            PageHelper.startPage(searchModel.getPageIndex(), searchModel.getPageSize());


            PageInfo<LeavefModel> info = new PageInfo<>(allItems);//全部商品
            int countNums = (int) info.getTotal();            //总记录数
            PageBean<LeavefModel> pageData = new PageBean<>(searchModel.getPageIndex(), searchModel.getPageSize(), countNums);
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

    @ApiOperation(value = "增加审批信息")
    @PostMapping("/addAuditor")
    public ResponseResult insertAuditor(@RequestBody AuditorInformation auditorInformation, HttpServletResponse response) {
        LeaveInformation leaveInformations = leaveService.getLeaveInformation(auditorInformation.getLeaveorder());
        if (leaveInformations != null) {
            int updateLeaveStatus = leaveService.updateLeaveStatus(auditorInformation);
            result.setCode(ResultCode.SUCCESS.getCode());
            result.setMessage(ResultCode.SUCCESS.getMessage());
            return result.setData(leaveService.insertAuditor(auditorInformation));
        } else {
            result.setCode(ResultCode.PARAMS_ERROR.getCode());
            result.setMessage(ResultCode.PARAMS_ERROR.getMessage());
            return result.setData("该请假单不存在");
        }
    }

    @ApiOperation(value = "删除审批信息")
    @PostMapping("/deleteAuditor")
    public ResponseResult deleteAuditor(@ApiParam(name = "leaveOrder", value = "请假单号") @RequestParam String leaveOrder, HttpServletResponse response) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(leaveService.deleteAuditor(leaveOrder));
    }

    @ApiOperation(value = "销假")
    @PostMapping("cancelLeave")
    public ResponseResult cancelAuditor(@ApiParam(name = "leaveorder", value = "请假单号") @RequestParam(required = true) String leaveorder, HttpServletResponse response) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(leaveService.cancelAuditor(leaveorder));
    }
}
