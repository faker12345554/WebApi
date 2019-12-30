package com.adminapp.config.token.geiuserid;

import com.adminapp.config.token.TokenUtil;
import com.prisonapp.tool.CacheUtils;

public class GetUserId {

    public String getUserId() {
        String userId = "";
        if (CacheUtils.get("UserId").toString() != null || CacheUtils.get("UserId").toString() != "") {
            userId = CacheUtils.get("UserId").toString();
        } else {
            userId = TokenUtil.getTokenUserId();
        }
        return userId;
    }
}
