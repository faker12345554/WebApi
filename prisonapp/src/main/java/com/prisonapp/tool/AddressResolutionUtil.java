package com.prisonapp.tool;

public class AddressResolutionUtil {
//    public static  String SpecialCounty="仙桃市,潜江市,天门市,济源市,东方市,万宁市,文昌市,儋州市,琼海市,五指山市,石河子市,阿拉尔市,图木舒克市,五家渠市,北屯市,铁门关市,双河市";
//
//    /**
//     * 解析地址
//     * @param address
//     * @return
//     */
//    public static CityInfo addressResolution(String address){
//        String regex="^(?<province>[^省]+省|.+自治区)?(?<city>[^市]+市|.+自治州)?(?<county>[^县]+县|.+区|.+市)?(?<town>[^区]+区|.+镇)?(?<village>.*)";
//        Matcher m= Pattern.compile(regex).matcher(address);
//
//
//        String[] citys=SpecialCounty.split(",");
//        List<String> specialCountyList = Arrays.asList(citys);
//
//
//        String province,city,county=null;
//        CityInfo info=new CityInfo();
//        while(m.find()){
//            province=m.group("province");
//            info.setProvince(province==null?"":province.trim());
//            city=m.group("city");
//            if(StringUtil.isNotEmpty(city)&&specialCountyList.contains(city)){
//                info.setCounty(city);
//                continue;
//            }
//            info.setCity(city==null?"":city.trim());
//            county=m.group("county");
//            if(StringUtil.isNotEmpty(county)){
//                if(county.indexOf("市")>0){
//                    String  seCity=county.substring(0,county.indexOf("市")+1);
//                    info.setCounty(seCity==null?"":seCity.trim());
//                }else if(county.indexOf("区")>0){
//                    String  seCity=county.substring(0,county.indexOf("区")+1);
//
//                    info.setCounty(seCity==null?"":seCity.trim());
//                }else {
//                    String  seCity=county.substring(0,county.indexOf("县")+1);
//
//                    info.setCounty(seCity==null?"":seCity.trim());
//                }
//            }
//
//
//        }
//        return info;
//
//    }
//
//    /**
//     * 根据经纬度查询
//     * @param log
//     * @param lat
//     * @return
//     */
//    public static String getAdd(String log, String lat ){
//        //lat 小  log  大
//        //参数解释: 纬度,经度 type 001 (100代表道路，010代表POI，001代表门址，111可以同时显示前三项)
//        String urlString = "http://api.map.baidu.com/geocoder/v2/?ak=0EXAjYp9hii1DrK3Tuda8efu9vivslcX&callback=renderReverse&location="+ lat + "," + log + "&output=json&pois=001";
//        String res = "";
//        try {
//            URL url = new URL(urlString);
//            java.net.HttpURLConnection conn = (java.net.HttpURLConnection)url.openConnection();
//            conn.setDoOutput(true);
//            conn.setRequestMethod("POST");
//            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream(),"UTF-8"));
//            String line;
//            while ((line = in.readLine()) != null) {
//                res += line+"\n";
//            }
//            in.close();
//        } catch (Exception e) {
//            System.out.println("error in wapaction,and e is " + e.getMessage());
//        }
//        System.out.println(res);
//        return res;
//    }
//
//
//    public static void main(String[] args) {
//        /*  System.out.println(getAdd(119.0478515625+"",31.5785354265+""));*/
//        //System.out.println(addressResolution("河南省仙桃市"));
//        CityInfo cityInfo = addressResolution("佛山市");
//
//        String str = getAdd("113.000837","23.102182" );
//        double b=(double) 560/100;
//        System.out.println(b);
//    }



}


