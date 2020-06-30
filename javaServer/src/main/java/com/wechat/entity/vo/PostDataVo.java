package com.wechat.entity.vo;

import com.wechat.utils.JsonToObjectUtil;

/**
 * Created by Nelson on 2020/6/23.
 */
public class PostDataVo {
    private String token;
    private Object data;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    public <T> T ConvertDataToObject(Class<T> cls) throws Exception{
        String jsonStr=net.sf.json.JSONObject.fromObject(this.data).toString();
        return JsonToObjectUtil.jsonToPojo(jsonStr,cls);
    }
}
