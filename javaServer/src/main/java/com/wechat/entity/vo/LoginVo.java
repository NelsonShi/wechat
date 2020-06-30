package com.wechat.entity.vo;

import com.wechat.entity.DeliveryInfo;

/**
 * Created by Nelson on 2020/6/17.
 */
public class LoginVo {
    private String code;
    private String token;
    private String rowData;
    private String signature;
    private String encryptedData;
    private String iv;
    private DeliveryInfo deliveryInfo;

    public DeliveryInfo getDeliveryInfo() {
        return deliveryInfo;
    }

    public void setDeliveryInfo(DeliveryInfo deliveryInfo) {
        this.deliveryInfo = deliveryInfo;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public String getRowData() {
        return rowData;
    }

    public void setRowData(String rowData) {
        this.rowData = rowData;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
