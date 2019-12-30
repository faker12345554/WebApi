package com.adminapp.config.token;

import com.adminapp.business.entity.dw_user.User;
import com.adminapp.business.entity.dw_user.UserModel;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("TokenService")
public  class TokenService {

    /***
     * token 下发
     * @Title: TokenService.java
     * @author MRC
     * @date 2019年5月27日 下午5:40:25
     * @version V1.0
     */
    public String getToken(User user) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000;//7天有效时间
        Date end = new Date(currentTime);
        String token = "";

        token = JWT.create().withAudience(String.valueOf(user.getId())).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
