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

    private ResponseResult result = new ResponseResult();

    @GetMapping("/getRuuleList")
    public ResponseResult getRuuleList(@RequestParam String Code,int level,int codelevel,String StartTime,String EndTime) {
        List<ViotionStatics> numberList = new ArrayList<ViotionStatics>();
        if (codelevel==3){
            Code=Code.substring(0,6);
        }else if(codelevel==4){
            Code=Code.substring(0,8);
        }

        if (level==1){
            Violationfens Volation=violationService.GetByCriteria("1");

            List<Map<String, String>> addlist = addressService.getAddress(Code, codelevel);
            for (Map<String, String> item:addlist){
                ViotionStatics model=new ViotionStatics();

                 List<Map<String,String>> Listmessge= ruleStatSericve.getRuuleList(item.get("code").substring(0,(codelevel*2)+2),level,StartTime,EndTime);
                 int sum=0;
                 int num=0;
                 for (Map<String, String> itam:Listmessge){
                     if (Integer.parseInt(itam.get("sum"))>=Volation.getSlightfens() && Integer.parseInt(itam.get("sum"))<Volation.getSeriousfens()){
                         sum+=1;
                     }else if(Integer.parseInt(itam.get("sum"))>=Volation.getSeriousfens()){
                         num+=1;
                     }
                 }
                 model.setNormalNumber(ruleStatSericve.getNotout(item.get("code").substring(0,(codelevel*2)+2),StartTime,EndTime));
                 model.setMinorNumber(sum);
                 model.setCriticalNumber(num);
                 model.setAreaCode(item.get("code"));
                 model.setAreaName(item.get("name"));
                numberList.add(model);
            }
        }else if(level==2){
            Violationfens Volation=violationService.GetByCriteria("2");
            List<Map<String, String>> addlist = addressService.getAddress(Code, codelevel);
            for (Map<String, String> item:addlist){
                ViotionStatics model=new ViotionStatics();
                List<Map<String,String>> Listmessge= ruleStatSericve.getRuuleList(item.get("code").substring(0,(codelevel*2)+2),level,StartTime,EndTime);
                int sum=0;
                int num=0;
                for (Map<String,String> itam:Listmessge){

                    if (Integer.parseInt(itam.get("sum"))>=Volation.getSlightfens() && Integer.parseInt(itam.get("sum"))<Volation.getSeriousfens()){
                        sum+=1;
                    }else if(Integer.parseInt(itam.get("sum"))>=Volation.getSeriousfens()){
                        num+=1;
                    }
                }
                model.setMinorNumber(sum);
                model.setCriticalNumber(num);
                model.setAreaCode(item.get("code"));
                model.setAreaName(item.get("name"));
                numberList.add(model);
            }

        }else if(level==3){
            List<Map<String, String>> addlist = addressService.getAddress(Code.substring(0,8), 4);
        }
        return result;

    }
}
