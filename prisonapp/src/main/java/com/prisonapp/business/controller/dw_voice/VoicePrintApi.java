/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.prisonapp.business.controller.dw_voice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.prisonapp.business.demo.Client;
import com.prisonapp.business.demo.Request;
import com.prisonapp.business.demo.Response;
import com.prisonapp.business.demo.constant.Constants;
import com.prisonapp.business.demo.constant.ContentType;
import com.prisonapp.business.demo.constant.HttpHeader;
import com.prisonapp.business.demo.constant.HttpSchema;
import com.prisonapp.business.demo.enums.Method;
import com.prisonapp.business.demo.util.MessageDigestUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;


public class VoicePrintApi {

    public VoicePrintApi(String Key, String Secret, int t) {
        appKey = Key;
        appSecret = Secret;
        timeout = t;
    }

    public VoicePrintApi(String Key, String Secret) {
        appKey = Key;
        appSecret = Secret;
        timeout = Constants.DEFAULT_TIMEOUT;
    }

    // 获取访问权限
    public boolean getAccess() throws Exception{
        String path = this.genFullPath(String.format("/user/login"));
        Request request = this.genRequest(Method.GET, path);
        
        Response response = Client.execute(request);
        JSONObject obj = JSON.parseObject(JSON.toJSONString(response));
        int code = Integer.parseInt(obj.get("statusCode").toString());

        if (code == 200) {
            JSONObject bodyObj = JSON.parseObject(obj.get("body").toString());
            JSONObject dataObj = JSON.parseObject(bodyObj.get("data").toString());
            this.userId = dataObj.get("user_id").toString();
            this.token = dataObj.get("access_token").toString();

            return true;
        }

        return false;
    }

    // 获取算法列表
    public String getAlgoList() throws Exception {
        String path = this.genFullPath(String.format("/users/%s/algorithms", this.userId));
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(HttpHeader.HTTP_ACCESS_TOKEN, this.token);

        Request request = this.genRequest(Method.GET, path);
        request.setHeaders(headers);
        Response response = Client.execute(request);

        return JSON.toJSONString(response);
    }

    // 创建声纹库（即绑定算法，可以相同的算法创建多个声纹库）
    public String createVpstore(String algoId) throws Exception {
        if(algoId.trim().isEmpty()) {
            throw new Exception("args invailed:  algoId");
        }

        String path = this.genFullPath(String.format("/users/%s/vpstore", this.userId));

        Map<String, String> body = new HashMap<String, String>();
        body.put("algo_id", algoId);
        byte[] bodyBytes = JSON.toJSONString(body).getBytes(Constants.ENCODING);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(HttpHeader.HTTP_ACCESS_TOKEN, this.token);
        headers.put(HttpHeader.HTTP_HEADER_CONTENT_TYPE, ContentType.CONTENT_TYPE_STREAM);
        headers.put(HttpHeader.HTTP_HEADER_CONTENT_MD5, MessageDigestUtil.base64AndMD5(bodyBytes));

        Request request = this.genRequest(Method.POST_BYTES, path);
        request.setHeaders(headers);
        request.setBytesBody(bodyBytes);

        Response response = Client.execute(request);
        return JSON.toJSONString(response);
    }

    // 获取声纹库列表
    public String getVpstoreList(int offset, int limit) throws Exception {
        if(offset < 0 || limit < 0) {
            throw new Exception("args invailed:  offset or limit");
        }
        
        String path = this.genFullPath(String.format("/users/%s/vpstores", this.userId));
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(HttpHeader.HTTP_ACCESS_TOKEN, this.token);

        Map<String, String> querys = new HashMap<String, String>();
        querys.put("offset", String.valueOf(offset));
        querys.put("limit", String.valueOf(limit));

        Request request = this.genRequest(Method.GET, path);
        request.setHeaders(headers);
        request.setQuerys(querys);
        Response response = Client.execute(request);

        return JSON.toJSONString(response);
    }


    //将wav文件上传到储存空间，并返回一个key(file_id)来标识该文件, 后面注册声纹，声纹比对都会用到该key
    public String uploadFile(String bucket, String filePath, int ttl) throws Exception {
        if(bucket.trim().isEmpty()) {
            throw new Exception("args invailed:  bucket");
        }
        
        if(filePath.trim().isEmpty()) {
            throw new Exception("args invailed:  filePath");
        }

        File file = new File(filePath);
        String fileName = file.getName();
        FileInputStream fis = new FileInputStream(file);        
        ByteArrayOutputStream body = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        int len = -1;
        while((len = fis.read(buff)) != -1)
        {
            body.write(buff, 0, len);
        }
        byte[] bodyBytes = body.toByteArray();
        fis.close();

        String path = this.genFullPath(String.format("/users/%s/bucket/%s/file/%s/ttl/%d/upload", this.userId, bucket, fileName, ttl));
        Map<String, String> headers = new HashMap<String,String>();
        headers.put(HttpHeader.HTTP_ACCESS_TOKEN, this.token);
        headers.put(HttpHeader.HTTP_HEADER_CONTENT_TYPE, ContentType.CONTENT_TYPE_STREAM);
        headers.put(HttpHeader.HTTP_HEADER_CONTENT_MD5, MessageDigestUtil.base64AndMD5(bodyBytes));
        
        Request request = this.genRequest(Method.POST_BYTES, path);
        request.setHeaders(headers);
        request.setBytesBody(bodyBytes);

        Response response = Client.execute(request);
        return JSON.toJSONString(response);
    }

    // 注册声纹， 用户自定义registerId, 如果该声纹库下已存在声纹id为registerId的声纹，若force为true则覆盖原有声纹，为false则不注册新声纹
    public String registerVoicePrint(String vpstoreId, String registerId, String[] fileIds, boolean force) throws Exception {
        if(vpstoreId.trim().isEmpty()) {
            throw new Exception("args invailed:  vpstoreId");
        }

        if(registerId.trim().isEmpty()) {
            throw new Exception("args invailed:  registerId");
        }

        if(fileIds.length == 0) {
            throw new Exception("args invailed:  fileIds");
        }

        String path = this.genFullPath(String.format("/users/%s/vpstore/%s/voiceprint/register", this.userId, vpstoreId));
        Map<String, Object> body = new HashMap<String, Object>();
        body.put("register_id", registerId);
        body.put("force", force);
        body.put("file_ids", fileIds);
        byte[] bodyBytes = JSON.toJSONString(body).getBytes(Constants.ENCODING);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(HttpHeader.HTTP_ACCESS_TOKEN, this.token);
        headers.put(HttpHeader.HTTP_HEADER_CONTENT_TYPE, ContentType.CONTENT_TYPE_STREAM);
        headers.put(HttpHeader.HTTP_HEADER_CONTENT_MD5, MessageDigestUtil.base64AndMD5(bodyBytes));

        Request request = this.genRequest(Method.POST_BYTES, path);
        request.setHeaders(headers);
        request.setBytesBody(bodyBytes);
        Response response = Client.execute(request);

        return JSON.toJSONString(response);
    }

    // 获取声纹列表
    public String getVoicePrintList(String vpstoreId, int offset, int limit) throws Exception{
        if(vpstoreId.trim().isEmpty()) {
            throw new Exception("args invailed:  vpstoreId");
        }
        
        if(offset < 0 || limit < 0) {
            throw new Exception("args invailed:  offset or limit");
        }

        String path = this.genFullPath(String.format("/users/%s/vpstore/%s/voiceprints", this.userId, vpstoreId));
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(HttpHeader.HTTP_ACCESS_TOKEN, this.token);

        Map<String, String> querys = new HashMap<String, String>();
        querys.put("offset", String.valueOf(offset));
        querys.put("limit", String.valueOf(limit));

        Request request = this.genRequest(Method.GET, path);
        request.setHeaders(headers);
        request.setQuerys(querys);
        Response response = Client.execute(request);

        return JSON.toJSONString(response);
    }

    // 声纹比对，一次最多能比对10条声纹
    public String compareVoicePrint(String vpstoreId, String fileId, String[] voicePrintIds) throws Exception {
        if(vpstoreId.trim().isEmpty()) {
            throw new Exception("args invailed:  vpstoreId");
        }

        if(fileId.trim().isEmpty()) {
            throw new Exception("args invailed:  fileId");
        }

        if(voicePrintIds.length == 0) {
            throw new Exception("args invailed:  voicePrintIds");
        }

        if(voicePrintIds.length > 10) {
            throw new Exception("args invailed:  voicePrintIds length should be <= 10 ");
        }

        String path = this.genFullPath(String.format("/users/%s/vpstore/%s/voiceprint/compare", this.userId, vpstoreId));
        Map<String, Object> body = new HashMap<String, Object>();
        body.put("file_id", fileId);
        body.put("voice_print_ids", voicePrintIds);
        byte[] bodyBytes = JSON.toJSONString(body).getBytes(Constants.ENCODING);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(HttpHeader.HTTP_ACCESS_TOKEN, this.token);
        headers.put(HttpHeader.HTTP_HEADER_CONTENT_TYPE, ContentType.CONTENT_TYPE_STREAM);
        headers.put(HttpHeader.HTTP_HEADER_CONTENT_MD5, MessageDigestUtil.base64AndMD5(bodyBytes));

        Request request = this.genRequest(Method.POST_BYTES, path);
        request.setHeaders(headers);
        request.setBytesBody(bodyBytes);
        Response response = Client.execute(request);

        return JSON.toJSONString(response);
    }

    private String appKey;      // app key
    private String appSecret;   // APP密钥
    private int timeout;

    private String userId;
    private String token;
    
    //API域名
    private final static String HOST = "speaker.market.alicloudapi.com";
    private final static String BASE_PATH = "/aliyun/vpr/api/v1";

    private String genFullPath(String path) {
        return BASE_PATH + path;
    }

    private Request genRequest(Method method, String path)
    {
        Request req = new Request(method, HttpSchema.HTTP + HOST, path, this.appKey, this.appSecret, this.timeout);
        return req;
    }
}
