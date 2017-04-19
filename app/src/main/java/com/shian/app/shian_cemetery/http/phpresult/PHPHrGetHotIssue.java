package com.shian.app.shian_cemetery.http.phpresult;

import com.shian.app.shian_cemetery.http.phpmodel.HotIssueData;

import java.util.List;



public class PHPHrGetHotIssue {
    private List<HotIssueData> items;

    public List<HotIssueData> getItems() {
        return items;
    }

    public void setItems(List<HotIssueData> items) {
        this.items = items;
    }
}
