package com.chason.jp.pojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WuShi {

    private String ping;
    private String pian;
    private List<String> roma;

    public WuShi(String ping, String pian, String roma) {
        this.ping = ping;
        this.pian = pian;
        this.roma = new ArrayList<>();
        if (roma != null && !roma.isEmpty()) {
            this.roma.addAll(Arrays.asList(roma.split(";")));
        }
    }

    public String getPing() {
        return ping;
    }

    public String getPian() {
        return pian;
    }

    public List<String> getRoma() {
        return roma;
    }
}
