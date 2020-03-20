package com.common.common.authenticator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CalendarAdjust {


    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * 时间戳转换成日期格式字符串
     * @param seconds 精确到秒的字符串
     * @param
     * @return
     */
    public static Date timeStamp2Date(long seconds) throws Exception {

        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       // long lt = new Long(s);
        Date date = new Date(seconds);
        res = simpleDateFormat.format(date);

        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(res);

    }
    /**
     * 日期格式字符串转换成时间戳
     * @param
     * @param format 如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String date2TimeStamp(String date_str,String format){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime()/1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }



    /**
     * 获得传讯时间
     *
     * @return
     */
    public static String GetSummons(int mon)throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, mon);// 0表示当前月，-2就是当前月-2
        calendar.set(Calendar.HOUR_OF_DAY, 9);
        calendar.set(Calendar.MINUTE, 30);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return  GetYear(timeStamp2Date(calendar.getTimeInMillis())).substring(0,7);
    }










    /**
     * 获取传讯通知时间 自定义
     * @return
     */
    public static long perThridMouthTime( int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, days);// 设为当前月的1号
        calendar.add(Calendar.MONTH, 0);// 0表示当前月，-2就是当前月-2
        calendar.set(Calendar.HOUR_OF_DAY, 9);
        calendar.set(Calendar.MINUTE, 30);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();

//        LocalDateTime localDateTime = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());
//        LocalDate monthlyTime = localDateTime.toLocalDate();
//        Date time = new SimpleDateFormat("yyyy-MM-dd").parse(monthlyTime.plusMonths(3).getYear() + "-" + monthlyTime.plusMonths(3).getMonthValue() + "-" + "01");
//        return time;
    }

    /**
     * 取保监居提醒时间
     * @param
     * @param
     * @return
     */
    public static long getNotificationTime(){
        Calendar calendar = Calendar.getInstance();
        //calendar.set(Calendar.DATE, 0);// 设为当前月的1号
        calendar.add(Calendar.MONTH, 0);// 0表示当前月，-2就是当前月-2
        calendar.set(Calendar.HOUR_OF_DAY, 9);
        calendar.set(Calendar.MINUTE, 30);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }



    /**
     * 时间戳转字符串
     *
     * @param timestamp 毫秒级时间戳
     * @param zoneId    如 GMT+8或UTC+08:00
     * @return
     */
    public static String timestampToStr(long timestamp, String zoneId) {
        ZoneId timezone = ZoneId.of(zoneId);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), timezone);
        return localDateTime.toString();
    }

    /**
     *      * 获取两个日期相差的月数
     *
     *      
     */

    public static int getMonthDiff(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
         int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH);
        int month2 = c2.get(Calendar.MONTH);
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        int day2 = c2.get(Calendar.DAY_OF_MONTH);
        // 获取年的差值 
          int yearInterval = year1 - year2;
        // 如果 d1的 月-日 小于 d2的 月-日 那么 yearInterval-- 这样就得到了相差的年数
        if (month1 < month2 || month1 == month2 && day1 < day2)
            yearInterval--;
        // 获取月数差值
        int monthInterval = (month1 + 12) - month2;
        if (day1 < day2)
            monthInterval--;
        monthInterval %= 12;
        int monthsDiff = Math.abs(yearInterval * 12 + monthInterval);
        return monthsDiff;

    }


    /**
     * 返回天数
     *
     * @param now
     * @param deadline
     * @return
     */
    public static long getDays(String now, String deadline) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date d1 = (Date) format.parse(now);
            Date d2 = (Date) format.parse(deadline);
            long day = d2.getTime() - d1.getTime();
            return day / 1000 / 60 / 60 / 24;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 获取指定月的最后一天
     *
     * @param month
     * @return
     */
    public static String getLastDayOfMonth1(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份 这个是三个月后的最后一天
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, (month+2));
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DATE); //这个是一定可以 我试试
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    /**
     * 获取指定月的第一天
     *
     * @param month
     * @return
     */
    public static String getFirstDayOfMonth1(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份 这个方式是下个月的第一天
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month);
        //获取某月最小天数
        int firstDay = cal.getMinimum(Calendar.DATE);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH,firstDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }


    /**
     * 获取年月日
     *
     * @param Time
     * @return
     */
    public static String GetYear(Date Time) {
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
        String time = sim.format(Time);

        return time;

    }

    public static int getYears(String date) throws ParseException{
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateFormat.parse(date));
        return calendar.get(Calendar.YEAR);
    }
    /**
     * 获取日期月份
     * @param date
     * @return
     * @throws ParseException
     */
    public static int getMonth(String date) throws ParseException{
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateFormat.parse(date));
        return (calendar.get(Calendar.MONTH) + 1);
    }

    //返回时间

    public static Date GetDate(String Time) throws ParseException{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(Time);
        return d1;
    }


    public static List<String> getDaysList(String startTime, String endTime) {

        // 返回的日期集合
        List<String> days = new ArrayList<String>();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date start = dateFormat.parse(startTime);
            Date end = dateFormat.parse(endTime);

            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(start);

            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(end);
            tempEnd.add(Calendar.DATE, +1);// 日期加1(包含结束)
            while (tempStart.before(tempEnd)) {
                days.add(dateFormat.format(tempStart.getTime()));
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return days;
    }

    public static String getDatePoor(Date endDate, Date nowDate) {

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return  hour + "小时" + min + "分钟";
    }

}
