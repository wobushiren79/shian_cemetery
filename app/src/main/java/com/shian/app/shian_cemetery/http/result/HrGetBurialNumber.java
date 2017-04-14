package com.shian.app.shian_cemetery.http.result;

/**
 * Created by Administrator on 2017/4/14.
 */

public class HrGetBurialNumber {
    private long beriedThisMonth;
    private long unberyThisMonth;
    private long unberyToday;

    public long getBeriedThisMonth() {
        return beriedThisMonth;
    }

    public void setBeriedThisMonth(long beriedThisMonth) {
        this.beriedThisMonth = beriedThisMonth;
    }

    public long getUnberyThisMonth() {
        return unberyThisMonth;
    }

    public void setUnberyThisMonth(long unberyThisMonth) {
        this.unberyThisMonth = unberyThisMonth;
    }

    public long getUnberyToday() {
        return unberyToday;
    }

    public void setUnberyToday(long unberyToday) {
        this.unberyToday = unberyToday;
    }
}
