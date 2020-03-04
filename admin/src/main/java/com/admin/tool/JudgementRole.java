package com.admin.tool;

public class JudgementRole {

    public static int Distinguishroles(){
        int type=0;
       Object Role= CacheUtils.get("Role");
       if (Role.toString()=="民警"){
           type= 1;
       }else if(Role.toString()=="法制管理员"){
           type= 2;
       }else if(Role.toString()=="所长"){
           type=3;
       }
       return  type;
    }

}
