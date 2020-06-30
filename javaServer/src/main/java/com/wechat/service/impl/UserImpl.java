package com.wechat.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wechat.base.AbstractService;
import com.wechat.dao.IDeliveryInfoDao;
import com.wechat.dao.IUserDao;
import com.wechat.entity.AppInfo;
import com.wechat.entity.DeliveryInfo;
import com.wechat.entity.User;
import com.wechat.entity.WeChatApi;
import com.wechat.entity.vo.LoginVo;
import com.wechat.service.IDeliveryInfo;
import com.wechat.service.IUser;
import com.wechat.utils.HttpClientUtils;
import com.wechat.utils.JsonToObjectUtil;
import com.wechat.utils.wechatSecurity.WXCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Nelson on 2020/6/1.
 */
@Service
public class UserImpl extends AbstractService<User> implements IUser {
    @Autowired
    private IUserDao userDao;
    @Autowired
    private IDeliveryInfoDao deliveryInfoDao;

    @Override
    public JpaRepository<User, String> getRepository() {
        return userDao;
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public String WeChatLogin(AppInfo appInfo, WeChatApi api, String code,User user) {
        String apiUrl = api.getApiUrl();
        String[] replayments = api.getApiReplacements().split(api.getSplitKey());
        for (String replace : replayments) {
            if (StringUtils.isEmpty(replace)) continue;
            if (replace.contains("APPID")) {
                apiUrl = apiUrl.replace(replace, appInfo.getAppId());
            }
            if (replace.contains("SECRET")) {
                apiUrl = apiUrl.replace(replace, appInfo.getAppSecret());
            }
            if (replace.contains("JSCODE")) {
                apiUrl = apiUrl.replace(replace, code);
            }
        }
        JSONObject jsonObject = HttpClientUtils.doGet(apiUrl);
        String openId = jsonObject.getString("openid");
        String sessionKey = jsonObject.getString("session_key");
        String token = UUID.randomUUID().toString();
        user.setOpenId(openId);
        user.setSessionKey(sessionKey);
        user.setRdSession(token);
        user.setLoginDate(new Date());
        userDao.save(user);
        return token;
    }



    @Override
    public void SaveUser(User user) {
        userDao.save(user);
    }

    @Override
    public User findByToken(String token) {
        return userDao.findByRdSession(token);
    }

    @Override
    public String ValidateUser(LoginVo vo,String appID,User userInfo) {
        try {
            User existUser=userDao.findByNickName(userInfo.getNickName());
            String deCodeStr= WXCore.decrypt(appID,vo.getEncryptedData(),existUser.getSessionKey(),vo.getIv());
            JSONObject jsonObject= JSON.parseObject(deCodeStr);
            if(jsonObject.get("openId").equals(existUser.getOpenId())){
                String token=UUID.randomUUID().toString();
                existUser.setRdSession(token);
                existUser.setLoginDate(new Date());
                userDao.save(existUser);
                return token;
            }else {
                return "Error:Validate fails";
            }
        }catch (Exception ex){
            return "Error:Validate exception:"+ex.getMessage();
        }
    }

    @Override
    public DeliveryInfo SaveDeliveryInfo(DeliveryInfo info) {
      DeliveryInfo res= deliveryInfoDao.save(info);
      return res;
    }


}
