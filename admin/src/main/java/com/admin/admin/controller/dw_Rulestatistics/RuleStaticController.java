package com.admin.admin.controller.dw_Rulestatistics;

import com.admin.admin.service.dwRulestatistics.RuleStatSericve;
import com.admin.model.Appstatistics.AppStatistics;
import com.common.common.result.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/RuleStatis")
public class RuleStaticController {
    @Autowired
    private RuleStatSericve ruleStatSericve;

    private ResponseResult result = new ResponseResult();

    @GetMapping("/getRuuleList")
    public ResponseResult getRuuleList(@RequestParam String Code,int level,String StartTime,String EndTime) {
        List<AppStatistics> numberList = new ArrayList<AppStatistics>();
        return result;

    }
}
