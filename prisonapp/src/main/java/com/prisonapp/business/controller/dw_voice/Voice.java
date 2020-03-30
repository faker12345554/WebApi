package com.prisonapp.business.controller.dw_voice;

import net.sf.json.JSON;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.activation.MimetypesFileTypeMap;

/**
 * java通过模拟post方式提交表单实现图片上传功能实例
 * 其他文件类型可以传入 contentType 实现
 * @author zdz8207
 * @version 1.0
 */
public class Voice {

    public static void main(String[] args) {
        String a ="{\"body\":\"{\\\"data\\\":[{\\\"vp_store_id\\\":\\\"a58dc17b-92c6-4300-aef3-3c4b95f98aac\\\",\\\"voice_print_id\\\":\\\"tangjihao1\\\"}],\\\"has_error\\\":false,\\\"error_message\\\":\\\"ok\\\",\\\"error_code\\\":0,\\\"request_id\\\":\\\"bafc093c-6817-11ea-b608-00163e02dfac\\\"}\",\"contentType\":\"application/json; charset=utf-8\",\"headers\":{\"Server\":\"Tengine\",\"Access-Control-Allow-Origin\":\"*\",\"Access-Control-Allow-Methods\":\"GET,POST,PUT,DELETE,HEAD,OPTIONS,PATCH\",\"Connection\":\"keep-alive\",\"Content-Length\":\"200\",\"Access-Control-Max-Age\":\"172800\",\"X-Ca-Request-Id\":\"6F3DE2CC-B334-4958-B7AB-BEC947C1F2BF\",\"Date\":\"Tue, 17 Mar 2020 06:22:51 GMT\",\"Access-Control-Allow-Headers\":\"X-Requested-With,X-Sequence,X-Ca-Key,X-Ca-Secret,X-Ca-Version,X-Ca-Timestamp,X-Ca-Nonce,X-Ca-API-Key,X-Ca-Stage,X-Ca-Client-DeviceId,X-Ca-Client-AppId,X-Ca-Signature,X-Ca-Signature-Headers,X-Ca-Signature-Method,X-Forwarded-For,X-Ca-Date,X-Ca-Request-Mode,Authorization,Content-Type,Accept,Accept-Ranges,Cache-Control,Range,Content-MD5\",\"Content-Type\":\"application/json; charset=utf-8\"},\"requestId\":\"6F3DE2CC-B334-4958-B7AB-BEC947C1F2BF\",\"statusCode\":200}";

    }


    }

