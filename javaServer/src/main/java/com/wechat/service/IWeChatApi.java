package com.wechat.service;

import com.wechat.base.IService;
import com.wechat.entity.WeChatApi;

import java.util.List;

/**
 * Created by Nelson on 2020/6/18.
 */
public interface IWeChatApi extends IService<WeChatApi> {
    List<WeChatApi> findAllApi();
}
