package com.prisonapp;

import com.prisonapp.apppush.Demo;
import com.prisonapp.model.LoadModel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

@MapperScan("com.prisonapp.business.dao")
@SpringBootApplication
public class PrisonappApplication {


    /*
    {
         "appkey":"你的appkey",
         "timestamp":"你的timestamp",
         "type":"unicast",
         "production_mode":"false",
         "device_tokens":"xx(Android为44位)",
         "payload": {
            "display_type": "message",   // 消息，message
            "body": {
                "custom":"自定义custom"/{} // message类型只需填写custom即可，可以是字符串或JSON。
            }
          },
         "policy": {
            "expire_time": "2013-10-30 12:00:00"
         },
         "description":"测试单播消息-Android"
     }
   */

    public static void main(String[] args) throws Exception {

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 5);
        Date tomorrow = c.getTime();
//        System.out.println(new Date());


        String time = "2019-12-12 17:13:00";
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);

        System.out.println(date);
        Demo demo = new Demo("5dd34971570df39fe0000e36", "irieuktitblepym21ex7pav7rh27rrvv");
        int i = demo.sendAndroidUnicast("AtMqss89NJcaerkruc7N0Bgif58Zyy00PkqfWUt5j1xz", "测试单播", "安卓测试", "今天天气不错", date);
    }
}





