package com.admin.admin.controller.app;


import com.admin.admin.service.app.MessageService;
import com.admin.token.TokenUtil;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/Message")
public class MessageController {

    @Autowired
    private MessageService messageService;
    private ResponseResult result = new ResponseResult();
    private String UserId =TokenUtil.getTokenUserId();
    @GetMapping("/Get")
    public ResponseResult getNotificationList(@RequestParam(required = false) String UserId, HttpServletResponse response){

        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(messageService.getNotificationList(UserId));
    }
}
