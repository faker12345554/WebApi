package com.admin.interceptor;

import com.admin.admin.dao.dw_user.UserDao;
import com.admin.token.tation.PassToken;
import com.admin.token.tation.UserLoginToken;
import com.admin.admin.entity.dw_user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* 这个是用来拦截的  那就是这个咯  在引用到这里的拦截*/

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    UserDao userDao;
      @Override
      public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
                 String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
                //得到参数

          Map<String,String[]> map = httpServletRequest.getParameterMap();
          if(map.size()>0)
          {
              if (getAllRequestParam(map)) {
                  throw new RuntimeException("数据中含有空格，请重新填写");
              }
          }

          InputStream inputStream= httpServletRequest.getInputStream();
          if(inputStream.read()>0) {
              if (getAllRequestParamlist(inputStream)) {
                  throw new RuntimeException("数据中含有空格，请重新填写");
              }
          }
                // 如果不是映射到方法直接通过
                 if(!(object instanceof HandlerMethod)){
                         return true;
                     }
                 HandlerMethod handlerMethod=(HandlerMethod)object;
                 Method method=handlerMethod.getMethod();
                 //检查是否有passtoken注释，有则跳过认证
                 if (method.isAnnotationPresent(PassToken.class)) {
                         PassToken passToken = method.getAnnotation(PassToken.class);
                         if (passToken.required()) {
                                 return true;
                             }
                     }
                 //检查有没有需要用户权限的注解
                 if (method.isAnnotationPresent(UserLoginToken.class)) {
                         UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
                         if (userLoginToken.required()) {
                                 // 执行认证
                                if (token == null) {
                                         throw new RuntimeException("无token，请重新登录");
                                     }
                                 // 获取 token 中的 user id
                                 String userId;
                                try {
                                         userId = JWT.decode(token).getAudience().get(0);
                                     } catch (JWTDecodeException j) {
                                         throw new RuntimeException("401");
                                     }
                                User user = userDao.getUser(Integer.valueOf(userId));
                                 if (user == null) {
                                         throw new RuntimeException("用户不存在，请重新登录");
                                     }
                                 // 验证 token
                                 JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
                                 try {
                                        jwtVerifier.verify(token);
                                     } catch (JWTVerificationException e) {
                                        throw new RuntimeException("401");
                                     }
                                return true;
                             }
                     }
                 return true;
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


        private  boolean getAllRequestParamlist(InputStream inputStream) throws IOException {
            boolean res=true;
            StringBuilder sb = new StringBuilder();
            BufferedReader reader  = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String line=new String();
            try {
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                    if(!line.equals("")) {
                        if (line.split(":")[1].indexOf(" ") > -1) {
                            res = false;
                        }
                    }
                }
            }
            catch (JWTVerificationException e) {
                throw new RuntimeException("用户不存在，请重新登录");
            }
            return  res;
        }

  /*  private Map<String, String> getAllRequestParam(final HttpServletRequest request) {
        Map<String, String> res = new HashMap<String, String>();
        Enumeration<?> temp = request.getParameterNames();
        if (null != temp) {
            while (temp.hasMoreElements()) {
                String en = (String) temp.nextElement();
                String value = request.getParameter(en);
                res.put(en, value);
                //如果字段的值为空，判断若值为空，则删除这个字段>
                if (null == res.get(en) || "".equals(res.get(en))) {
                    res.remove(en);
                }
            }
        }
        return res;
     }
   */
             @Override
     public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

             }
     @Override
     public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

             }
}
