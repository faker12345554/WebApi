package com.prisonapp.business.entity.dw_supervise;

public class Battery {
    private boolean enable;
    private int timeSpan;
    private float alarmThreshold;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public int getTimeSpan() {
        return timeSpan;
    }

    public void setTimeSpan(String timeSpan) {
        int iTimeSpan =Integer.parseInt(timeSpan);
        this.timeSpan = iTimeSpan;
    }

    public float getAlarmThreshold() {
        return alarmThreshold;
    }

    public void setAlarmThreshold(float alarmThreshold) {
        this.alarmThreshold = alarmThreshold;
    }
}
