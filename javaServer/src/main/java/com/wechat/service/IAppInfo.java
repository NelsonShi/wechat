package com.wechat.service;

import com.wechat.base.IService;
import com.wechat.entity.AppInfo;

/**
 * Created by Nelson on 2020/6/17.
 */
public interface IAppInfo extends IService<AppInfo>{
     AppInfo getDefaultApp();
}
