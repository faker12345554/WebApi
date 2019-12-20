package com.admin.admin.controller.dw_app;


import com.admin.admin.service.dw_app.MessageService;
import com.common.common.result.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    //  private TokenUtil tokenUtil =new TokenUtil();


    @GetMapping("/Get")
    public ResponseResult getNotificationList(String UserId) {

        return messageService.getNotificationList(UserId);
    }
}
