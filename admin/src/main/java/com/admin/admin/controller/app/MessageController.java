package com.admin.admin.controller.app;


import com.admin.admin.service.app.MessageService;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Message")
public class MessageController {

    @Autowired
    private MessageService messageService;
    private ResponseResult result = new ResponseResult();
  //  private TokenUtil tokenUtil =new TokenUtil();


    @GetMapping("/Get")
    public ResponseResult getNotificationList(){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(messageService.getNotificationList());
    }
}
