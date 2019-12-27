package com.admin.interceptor;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;

public class BlankSpaceInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        Map<String,String[]> map = httpServletRequest.getParameterMap();
        try{
            if(map.size()>0)
            {
                if (!getAllRequestParam(map)) {
                    throw new RuntimeException("参数中存在空格,请重新输入!");
                }
            }

            InputStream inputStream= httpServletRequest.getInputStream();
            if(inputStream.read()>0) {
                if (getAllRequestParamlist(inputStream)==true) {
                    throw new RuntimeException("参数中存在空格,请重新输入!");
                }
            }
        }catch (Exception ex){
            throw new RuntimeException("401");
        }
        return true;
    }

    private  boolean getAllRequestParamlist(InputStream inputStream) throws IOException {
        boolean res=true;
        StringBuilder sb = new StringBuilder();
        BufferedReader reader  = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
        String line=new String();
        try {
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                if(!line.equals("") && !line.equals("}") ) {
                    if (line.split(":")[1].indexOf(" ") > -1) {
                        res = false;
                    }
                }
            }
        }
        catch (Exception e) {
            throw new RuntimeException("数据不存在");
        }
        return  res;
    }

    private  boolean getAllRequestParam(Map<String,String[]> map)
    {
        boolean res=true;
        Set<String> keys = map.keySet();
        for (String key : keys)
        {
            String[] value = map.get(key);
            if(value[0].indexOf(" ") >-1) {
                res=  false;
            }
        }
        return  res;
    }
}
