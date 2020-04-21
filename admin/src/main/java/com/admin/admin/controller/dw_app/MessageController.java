package com.admin.admin.controller.dw_app;


import com.admin.admin.dao.master.dw_person.PersonDao;
import com.admin.admin.service.dw_app.MessageService;
import com.admin.admin.service.dw_person.PersoinfoService;
import com.admin.admin.service.dw_task.Tasking;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private Tasking tasking;


    @Autowired
    private PersoinfoService personDao;


    //  private TokenUtil tokenUtil =new TokenUtil();


    @GetMapping("/Get")
    public ResponseResult getNotificationList(String UserId) {
        ResponseResult result = new ResponseResult();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(messageService.getNotificationList(UserId));
    }

    @ApiOperation("测试")
    @GetMapping("/Gettest")
    public void test() throws Exception {
        // tasking.GetMessage();
      // messageService.Synchronouscase();
       // messageService.Synchronizedpolice();
        personDao.getpersonid();


    }
}
