package com.wechat.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Nelson on 2020/6/18.
 */
@Entity
@Table(name = "weChatApi")
public class WeChatApi {
    @Id
    private String apiName;
    private String  apiUrl;
    private String  apiReplacements;
    private String  apiResponse;
    private String  splitKey;

    public String getSplitKey() {
        return splitKey;
    }

    public void setSplitKey(String splitKey) {
        this.splitKey = splitKey;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getApiReplacements() {
        return apiReplacements;
    }

    public void setApiReplacements(String apiReplacements) {
        this.apiReplacements = apiReplacements;
    }

    public String getApiResponse() {
        return apiResponse;
    }

    public void setApiResponse(String apiResponse) {
        this.apiResponse = apiResponse;
    }
}
