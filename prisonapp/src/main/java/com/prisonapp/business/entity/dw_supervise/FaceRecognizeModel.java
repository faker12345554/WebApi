package com.prisonapp.business.entity.dw_supervise;

public class FaceRecognizeModel {
    private String code;
    private boolean passed;
    private float similar;
    private String url;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public float getSimilar() {
        return similar;
    }

    public void setSimilar(float similar) {
        this.similar = similar;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
