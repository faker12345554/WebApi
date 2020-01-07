package com.prisonapp.tool;

import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;
import net.sf.json.JSONObject;


import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;

public class AddressResolutionUtil {
//    public static String interfaceUtil(String path,String data) {
//        String result ="";
//        try {
//
//            URL url = new URL(path);
//            //打开和url之间的连接
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            PrintWriter out = null;
//            //请求方式
////          conn.setRequestMethod("POST");
////           //设置通用的请求属性
//            conn.setRequestProperty("accept", "*/*");
//            conn.setRequestProperty("connection", "Keep-Alive");
//            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
//            //设置是否向httpUrlConnection输出，设置是否从httpUrlConnection读入，此外发送post请求必须设置这两个
//            //最常用的Http请求无非是get和post，get请求可以获取静态页面，也可以把参数放在URL字串后面，传递给servlet，
//            //post与get的 不同之处在于post的参数不是放在URL字串里面，而是放在http请求的正文内。
//            conn.setDoOutput(true);
//            conn.setDoInput(true);
//            //获取URLConnection对象对应的输出流
//            out = new PrintWriter(conn.getOutputStream());
//            //发送请求参数即数据
//            out.print(data);
//            //缓冲数据
//            out.flush();
//            //获取URLConnection对象对应的输入流
//            InputStream is = conn.getInputStream();
//            //构造一个字符流缓存
//            BufferedReader br = new BufferedReader(new InputStreamReader(is));
//            String str = "";
//
//            while ((str = br.readLine()) != null) {
//                result+=str;
//            }
//
//            //关闭流
//            is.close();
//            //断开连接，最好写上，disconnect是在底层tcp socket链接空闲时才切断。如果正在被其他线程使用就不切断。
//            //固定多线程的话，如果不disconnect，链接会增多，直到收发不出信息。写上disconnect后正常一些。
//            conn.disconnect();
//
//            System.out.println("完整结束");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }

//    public static String sendGet(String url, String ak_id, String ak_secret) throws Exception {
//        String result = "";
//        BufferedReader in = null;
//        int statusCode = 200;
//        try {
//            URL realUrl = new URL(url);
//            /*
//             * http header 参数
//             */
//            String method = "GET";
//            String accept = "application/json";
//            String content_type = "application/json";
//            String path = realUrl.getFile();
//          //  String date = toGMTString(new Date());
//            // 1.对body做MD5+BASE64加密
//            // String bodyMd5 = MD5Base64(body);
//         //   String stringToSign = method + "\n" + accept + "\n" + "" + "\n" + content_type + "\n" + date + "\n" + path;
//            // 2.计算 HMAC-SHA1
//           // String signature = HMACSha1(stringToSign, ak_secret);
//            // 3.得到 authorization header
//            String authHeader = "Dataplus " + ak_id + ":" + signature;
//            // 打开和URL之间的连接
//            URLConnection connection = realUrl.openConnection();
//            // 设置通用的请求属性
//            connection.setRequestProperty("accept", accept);
//            connection.setRequestProperty("content-type", content_type);
//         //   connection.setRequestProperty("date", date);
//            connection.setRequestProperty("Authorization", authHeader);
//            connection.setRequestProperty("Connection", "keep-alive");
//            // 建立实际的连接
//            connection.connect();
//            // 定义 BufferedReader输入流来读取URL的响应
//            statusCode = ((HttpURLConnection) connection).getResponseCode();
//            if (statusCode != 200) {
//                in = new BufferedReader(new InputStreamReader(((HttpURLConnection) connection).getErrorStream()));
//            } else {
//                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            }
//            String line;
//            while ((line = in.readLine()) != null) {
//                result += line;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (in != null) {
//                    in.close();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        if (statusCode != 200) {
//            throw new IOException("\nHttp StatusCode: " + statusCode + "\nErrorMessage: " + result);
//        }
//        return result;
//    }

    public static JSONObject getHttps(String hsUrl) {
        try {
            URL url;

            url = new URL(hsUrl);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            X509TrustManager xtm = new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    // TODO Auto-generated method stub
                    return null;
                }

                @Override
                public void checkServerTrusted(X509Certificate[] arg0, String arg1)
                        throws CertificateException {
                    // TODO Auto-generated method stub

                }

                @Override
                public void checkClientTrusted(X509Certificate[] arg0, String arg1)
                        throws CertificateException {
                    // TODO Auto-generated method stub

                }
            };

            TrustManager[] tm = {xtm};

            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(null, tm, null);

            con.setSSLSocketFactory(ctx.getSocketFactory());
            con.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });


            InputStream inStream = con.getInputStream();
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            byte[] b = outStream.toByteArray();//网页的二进制数据
            outStream.close();
            inStream.close();
            String rtn = new String(b, "utf-8");
            if (StringUtils.isNotBlank(rtn)) {
                JSONObject object = JSONObject.fromObject(rtn);
               // JSONObject object=new JSONObject() ;
                return object;
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }
}


