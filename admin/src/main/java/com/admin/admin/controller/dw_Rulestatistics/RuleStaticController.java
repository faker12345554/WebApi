package com.admin.admin.controller.dw_Rulestatistics;

import com.admin.admin.entity.dw_violation.Violationfens;
import com.admin.admin.service.dw_Rulestatistics.RuleStatSericve;
import com.admin.admin.service.dw_address.AddressService;
import com.admin.admin.service.dw_violation.ViolationService;
import com.admin.model.Appstatistics.AppStatistics;
import com.admin.model.Appstatistics.ViotionStatics;
import com.common.common.result.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/RuleStatis")
public class RuleStaticController {
    @Autowired
    private RuleStatSericve ruleStatSericve;

    @Autowired
    private AddressService addressService;

    @Autowired
    private ViolationService violationService;


    @GetMapping("/getRuuleList")
    public ResponseResult getRuuleList(@RequestParam String Code,int level,int codelevel,String StartTime,String EndTime) {
        ResponseResult result = new ResponseResult();
        List<ViotionStatics> numberList = new ArrayList<ViotionStatics>();
        if (codelevel==3){
            Code=Code.substring(0,6);
        }else if(codelevel==4){
            Code=Code.substring(0,8);
        }

        List<Map<String, String>> addlist = addressService.getAddress(Code, codelevel);
        if (level==1){
          //  Violationfens Volation=violationService.GetByCriteria("1");


            for (Map<String, String> item:addlist){
                ViotionStatics model=new ViotionStatics();
                 model.setNormalNumber(ruleStatSericve.getRuuleList(item.get("code").substring(0,(codelevel*2)+2),level,StartTime,EndTime,"正常"));
                 model.setMinorNumber(ruleStatSericve.getRuuleList(item.get("code").substring(0,(codelevel*2)+2),level,StartTime,EndTime,"轻微"));
                 model.setCriticalNumber(ruleStatSericve.getRuuleList(item.get("code").substring(0,(codelevel*2)+2),level,StartTime,EndTime,"严重"));
                 model.setAreaCode(item.get("code"));
                 model.setAreaName(item.get("name"));
                numberList.add(model);
            }
        }else if(level==2){
            for (Map<String, String> item:addlist){
                ViotionStatics model=new ViotionStatics();

                model.setNormalNumber(ruleStatSericve.getRuuleList(item.get("code").substring(0,(codelevel*2)+2),level,StartTime,EndTime,"正常"));
                model.setMinorNumber(ruleStatSericve.getRuuleList(item.get("code").substring(0,(codelevel*2)+2),level,StartTime,EndTime,"轻微"));
                model.setCriticalNumber(ruleStatSericve.getRuuleList(item.get("code").substring(0,(codelevel*2)+2),level,StartTime,EndTime,"严重"));
                model.setAreaCode(item.get("code"));
                model.setAreaName(item.get("name"));
                numberList.add(model);
            }

        }else if(level==3){
            for (Map<String, String> item:addlist) {
                ViotionStatics model = new ViotionStatics();
            }
        }
        return result;

    }
}
