package com.adminapp.business.controller.dw_supervise;

import com.adminapp.business.entity.dw_supervise.Personinformation;
import com.adminapp.business.service.dw_supervise.SuperviseService;
import com.adminapp.model.dw_supervise.PrisonSettingModel;
import com.common.common.result.ResultSet;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/app/admin/supervise")
public class SuperviseController {

    @Autowired
    private SuperviseService superviseService;

    private ResultSet rs=new ResultSet();

    @ApiOperation(value = "获取违规记录统计")
    @GetMapping("/getAgainstRule")
    public ResultSet getAgainstRule(@ApiParam(name = "type",value = "类别") @RequestParam(required = true)int type,@ApiParam(name = "count",value = "当前已经获取的数据条数") @RequestParam(required = true)int count,
                                    @ApiParam(name = "requestCount",value = "请求获取数据的条数") @RequestParam(required = true)int requestCount,@ApiParam(name = "key",value = "搜索关键字") @RequestParam(required = true)String key){
        List<Personinformation> personinformation=superviseService.listPersonInformation();
        for(int i=0;i<personinformation.size();i++){
            Personinformation personinformation1=personinformation.get(i);
            String personid=personinformation1.getCode();
            PrisonSettingModel prisonSettingModel=superviseService.getPrisonSetting(personid);
        }
        if(type==1){

            rs.resultCode=0;
            rs.resultMsg="";
            rs.data=personinformation;
        }


        return rs;
    }
}
