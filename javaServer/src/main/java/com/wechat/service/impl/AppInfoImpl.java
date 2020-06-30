package com.wechat.service.impl;

import com.wechat.base.AbstractService;
import com.wechat.dao.IAppInfoDao;
import com.wechat.entity.AppInfo;
import com.wechat.service.IAppInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * Created by Nelson on 2020/6/17.
 */
@Service
public class AppInfoImpl extends AbstractService<AppInfo> implements IAppInfo {
    @Autowired
    private IAppInfoDao appInfoDao;
    @Override
    public JpaRepository<AppInfo, String> getRepository() {
        return appInfoDao;
    }

    @Override
    public AppInfo getDefaultApp() {
        return appInfoDao.findByAppName("DIY");
    }
}
