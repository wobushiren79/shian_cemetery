package com.shian.app.shian_cemetery.http.phpresult;


import com.shian.app.shian_cemetery.http.phpmodel.AdvertisementData;

import java.util.List;

/**
 * Created by Administrator on 2017/3/16.
 */

public class PHPHrGetAdvertisement {
    private List<AdvertisementData> items;

    public List<AdvertisementData> getItems() {
        return items;
    }

    public void setItems(List<AdvertisementData> items) {
        this.items = items;
    }
}
