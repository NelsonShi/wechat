package com.wechat.service;

import com.wechat.base.IService;
import com.wechat.entity.AppInfo;
import com.wechat.entity.DeliveryInfo;
import com.wechat.entity.User;
import com.wechat.entity.WeChatApi;
import com.wechat.entity.vo.LoginVo;

import java.util.List;

/**
 * Created by Nelson on 2020/6/1.
 */
public interface IUser extends IService<User>{
    List<User> findAll();
    String WeChatLogin(AppInfo appInfo, WeChatApi api,String code,User user);
    void SaveUser(User user);
    User findByToken(String token);
    String ValidateUser(LoginVo vo,String appID,User userInfo);
    DeliveryInfo SaveDeliveryInfo(DeliveryInfo info);
}
