package com.admin.tool;

public class JudgementRole {

    public static int Distinguishroles(){
        int type=0;
       Object Role= CacheUtils.get("Role");
       System.out.println(Role.toString());
       if (Role.toString().equals("民警")){
           type= 1;
       }else if(Role.toString().equals("法制管理员")){
           type= 2;
       }else if(Role.toString().equals("所长")){
           type=3;
       }
       return  type;
    }

}
