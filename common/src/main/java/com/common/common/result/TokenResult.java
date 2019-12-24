package com.common.common.result;

import com.common.common.md5.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

public class TokenResult {
//    @Autowired
//    TokenModel tokenModel;

    public String  GetToken(String accountName,String phone){
        Random rd =new Random();
        int randomNumber=rd.nextInt(9000)+1000;
        TokenModel tokenModel =new TokenModel();
        tokenModel.setGuid(randomNumber);
        tokenModel.setPhoneNumber(phone);
        tokenModel.setUserId(accountName);
       /* ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("template"));
        oos.writeObject(tokenModel);
        oos.close();*/
       String account =accountName;
       String phoneNumber =phone;


        String getToken =account+randomNumber+phoneNumber;
        return getToken;
    }
}
