package com.chason.jp.pojo;

public class WuShi {

    private String ping;
    private String pian;
    private String roma;

    public WuShi(String ping, String pian, String roma) {
        this.ping = ping;
        this.pian = pian;
        this.roma = roma;
    }

    public void setPing(String ping) {
        this.ping = ping;
    }

    public String getPing() {
        return ping;
    }

    public void setPian(String pian) {
        this.pian = pian;
    }

    public String getPian() {
        return pian;
    }

    public void setRoma(String roma) {
        this.roma = roma;
    }

    public String getRoma() {
        return roma;
    }
}
