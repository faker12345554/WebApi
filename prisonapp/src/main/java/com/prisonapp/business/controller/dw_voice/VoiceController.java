package com.prisonapp.business.controller.dw_voice;


import com.alibaba.druid.support.logging.JakartaCommonsLoggingImpl;
import com.common.common.result.ResultSet;
import com.prisonapp.token.tation.UserLoginToken;
import com.prisonapp.tool.AddressResolutionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.minidev.json.JSONObject;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.net.www.http.HttpClient;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

@Api(value = "声纹controller", tags = {"声纹识别"})
@RestController
@RequestMapping("/app/voice")
public class VoiceController {



    @UserLoginToken
    @ApiOperation(value = " 上传录音文件")
    @PostMapping("/upload")
    public ResultSet upload(String node, String spkid, String text, MultipartFile file){
        ResultSet resultSet = new ResultSet();

        String path = " http://114.116.227.8:8015/kst/rd/upload?node="+"node"+"&spkid="+spkid+"&wavtype=wav&channel=0&replaydetect=false&snrdetect=false&asrdetect=true&step=1&text="+text+"";
        net.sf.json.JSONObject josnResult = AddressResolutionUtil.getHttps(path);//高德api返回的结果集


        return resultSet;
    }

    @UserLoginToken
    @ApiOperation(value = " 注册声纹")
    @PostMapping("/enroll")
    public ResultSet enroll(String node, String spkid){
        ResultSet resultSet = new ResultSet();

        String path = "  http://114.116.227.8:8015/kst/rd/enroll?node="+node+"&spkid"+spkid;
        net.sf.json.JSONObject jsonResult = AddressResolutionUtil.getHttps(path);//高德api返回的结果集

        resultSet.resultCode=0;
        resultSet.resultMsg="";
        resultSet.data =jsonResult;

        return resultSet;
    }
//    @ApiOperation(value = " 上传录音文件")
//    @PostMapping("/http")

//    public static String doPostFile() {
//return doPostFile1("reqUrl","fileUrl");
//}
//
//private static String doPostFile1(String reqUrl, String fileUrl) {
//    reqUrl=" http://114.116.227.8:8015/kst/rd/upload?node=BW&spkid=56789&wavtype=wav&channel=0&replaydetect=false&snrdetect=false&asrdetect=true&step=1&text=1234";
//    fileUrl ="C:/Users/tjh/Desktop/VOICE/12345678.wav";
//HttpURLConnection url_con = null;
//String responseContent = null;
//try {
//
//URL url = new URL(reqUrl);
//
//url_con = (HttpURLConnection) url.openConnection();
//url_con.setRequestMethod("POST");
////url_con.setConnectTimeout(CONNECTTIMEOUT);
//url_con.setDoOutput(true);
//url_con.setRequestProperty("Content-type","multipart/form-data");
//
//File file = new File(fileUrl);
// InputStream ins = new FileInputStream(file);
// byte[] data = IOUtils.toByteArray(ins);
//
//url_con.getOutputStream().write(data, 0, data.length);
//url_con.getOutputStream().flush();
//url_con.getOutputStream().close();
//
//InputStream in = url_con.getInputStream();
//
//BufferedReader rd = new BufferedReader(new InputStreamReader(in));
//String tempLine = rd.readLine();
//StringBuffer tempStr = new StringBuffer();
//String crlf = System.getProperty("line.separator");
//while (tempLine != null) {
//tempStr.append(tempLine);
//tempStr.append(crlf);
//tempLine = rd.readLine();
//}
//responseContent = tempStr.toString();
//rd.close();
//in.close();
//} catch (IOException e) {
//System.err.println("网络故障");
//    JakartaCommonsLoggingImpl logger = null;
//    logger.info("--------------------->网络故障");
//} finally {
//if (url_con != null) {
//url_con.disconnect();
//}
//}
//return responseContent;
//}





/*
    public String  http(MultipartFile file) throws IOException {
        String serverURL = " http://114.116.227.8:8015/kst/rd/upload?node=BW&spkid=56789&wavtype=wav&channel=0&replaydetect=false&snrdetect=false&asrdetect=true&step=1&text=12345678";//SealInfoUtil.getSealInfoDetail().getProperty("bairong.url");//从配置文件中读取路径  可以写死     "http://。。。。。。。"
        StringBuilder sbf = new StringBuilder();
        String strRead = null;
        URL url = new URL(serverURL);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("POST");//请求post方式
        connection.setDoInput(true);
        connection.setDoOutput(true);
        //header内的的参数在这里set    connection.setRequestProperty("健, "值");
        connection.setRequestProperty("Content-Type", "application/json");
        //connection.setRequestProperty("Info_Token", idcard.getInfoToken());

        connection.connect();
        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(),"UTF-8");

        //body参数在这里put到JSONObject中

        JSONObject parm = new JSONObject();
        parm.put("file", file);
      // parm.put("idNo", idcard.getIdNo());
        writer.write(parm.toString());
        writer.flush();
        InputStream is = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        while ((strRead = reader.readLine()) != null) {
            sbf.append(strRead);
            sbf.append("\r\n");
        }
        reader.close();
        connection.disconnect();
        String results = sbf.toString();
        System.out.println(results);
        return results;
    }*/



    }


