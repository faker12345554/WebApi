package com.admin.tool;

import java.util.ArrayList;
import java.util.List;

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

    public static <T> List<List<T>> averageAssign(List<T> source, int n){
        List<List<T>> result=new ArrayList<List<T>>();
        int remaider=source.size()%n; //(先计算出余数)
        int number=source.size()/n; //然后是商
        int offset=0;//偏移量
        for(int i=0;i<n;i++){
            List<T> value=null;
            if(remaider>0){
                value=source.subList(i*number+offset, (i+1)*number+offset+1);
                remaider--;
                offset++;
            }else{
                value=source.subList(i*number+offset, (i+1)*number+offset);
            }
            result.add(value);
        }
        return result;
    }

}
