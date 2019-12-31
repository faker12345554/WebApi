package com.common.common.authenticator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public  class CalendarAdjust {

    /**
     * 获取当前时间的日期
     * @return
     */
    public static int GetDays(){
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.DATE);
    }

    public static int GetMonth(){
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.MONTH);
    }

    /**
     * 获得传讯时间
     * @return
     */
    public static String GetSummons(){
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改   对时间进行加减操作等
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        return year+"-"+month;
    }
    /**
     * 获取指定某一天的开始时间戳
     *
     * @param timeStamp 毫秒级时间戳
     * @param timeZone  如 GMT+8:00
     * @return
     */
    public static Long getDailyStartTime(Long timeStamp, String timeZone) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone(timeZone));
        calendar.setTimeInMillis(timeStamp);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取指定某一天的结束时间戳
     *
     * @param timeStamp 毫秒级时间戳
     * @param timeZone  如 GMT+8:00
     * @return
     */
    public static Long getDailyEndTime(Long timeStamp, String timeZone) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone(timeZone));
        calendar.setTimeInMillis(timeStamp);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取当月开始时间戳
     *
     * @param timeStamp 毫秒级时间戳
     * @param timeZone  如 GMT+8:00
     * @return
     */
    public static Long getMonthStartTime(Long timeStamp, String timeZone) {
        Calendar calendar = Calendar.getInstance();// 获取当前日期
        calendar.setTimeZone(TimeZone.getTimeZone(timeZone));
        calendar.setTimeInMillis(timeStamp);
        calendar.add(Calendar.YEAR, 0);
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取当月的结束时间戳
     *
     * @param timeStamp 毫秒级时间戳
     * @param timeZone  如 GMT+8:00
     * @return
     */
    public static Long getMonthEndTime(Long timeStamp, String timeZone) {
        Calendar calendar = Calendar.getInstance();// 获取当前日期
        calendar.setTimeZone(TimeZone.getTimeZone(timeZone));
        calendar.setTimeInMillis(timeStamp);
        calendar.add(Calendar.YEAR, 0);
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));// 获取当前月最后一天
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取当年的开始时间戳
     *
     * @param timeStamp 毫秒级时间戳
     * @param timeZone  如 GMT+8:00
     * @return
     */
    public static Long getYearStartTime(Long timeStamp, String timeZone) {
        Calendar calendar = Calendar.getInstance();// 获取当前日期
        calendar.setTimeZone(TimeZone.getTimeZone(timeZone));
        calendar.setTimeInMillis(timeStamp);
        calendar.add(Calendar.YEAR, 0);
        calendar.add(Calendar.DATE, 0);
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取当年的最后时间戳
     *
     * @param timeStamp 毫秒级时间戳
     * @param timeZone  如 GMT+8:00
     * @return
     */
    public static Long getYearEndTime(Long timeStamp, String timeZone) {
        Calendar calendar = Calendar.getInstance();// 获取当前日期
        calendar.setTimeZone(TimeZone.getTimeZone(timeZone));
        calendar.setTimeInMillis(timeStamp);
        int year = calendar.get(Calendar.YEAR);
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
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
     * 返回天数
     * @param now
     * @param deadline
     * @return
     */
    public static long getDays(String now,String deadline){
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
      try {
            Date d1 = (Date) format.parse(now);
            Date d2 = (Date) format.parse(deadline);
            long day= d2.getTime()-d1.getTime();
            return day / 1000 / 60 / 60/ 24;
          } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 获取年月日
     * @param Time
     * @return
     */
    public static String GetYear(Date Time){
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
        String time=sim.format(Time);
        System.out.println(time);
        return time;

    }


    public static void main(String[] args) {
        Long currentTime = System.currentTimeMillis();
        System.out.println("Current Time : " + currentTime + " = " + timestampToStr(currentTime, "GMT+8"));
        Long dailyStart = getDailyStartTime(currentTime, "GMT+8:00");
        Long dailyEnd = getDailyEndTime(currentTime, "GMT+8:00");
        Long monthStart = getMonthStartTime(currentTime, "GMT+8:00");
        Long monthEnd = getMonthEndTime(currentTime, "GMT+8:00");
        Long yearStart = getYearStartTime(currentTime, "GMT+8:00");
        Long yearEnd = getYearEndTime(currentTime, "GMT+8:00");

        System.out.println("Daily Start : " + dailyStart + " = " + timestampToStr(dailyStart, "GMT+8") + " Daily End : " + dailyEnd + " = " + timestampToStr(dailyEnd, "GMT+8"));
        System.out.println("Month Start : " + monthStart + " = " + timestampToStr(monthStart, "GMT+8") + " Month End : " + monthEnd + " = " + timestampToStr(monthEnd, "GMT+8"));
        System.out.println("Year Start : " + yearStart + " = " + timestampToStr(yearStart, "GMT+8") + " Year End : " + yearEnd + " = " + timestampToStr(yearEnd, "GMT+8"));
    }
}
