package com.common.common.result;

import com.common.common.md5.MD5Util;

import java.util.Random;

public class TokenResult {

    public String  GetToken(String accountName,String phone){
        Random rd =new Random();
        int randomNumber=rd.nextInt(9000)+1000;
        String account =MD5Util.string2MD5(accountName);
        String phoneNumber  =MD5Util.string2MD5(phone);
        System.out.println(randomNumber);
        String getToken =account+randomNumber+phoneNumber;
        return getToken;
    }
}
