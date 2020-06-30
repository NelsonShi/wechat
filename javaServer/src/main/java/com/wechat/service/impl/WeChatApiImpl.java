package com.wechat.service.impl;

import com.wechat.base.AbstractService;
import com.wechat.dao.IWeChatApiDao;
import com.wechat.entity.WeChatApi;
import com.wechat.service.IWeChatApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Nelson on 2020/6/18.
 */
@Service
public class WeChatApiImpl extends AbstractService<WeChatApi> implements IWeChatApi {
    @Autowired
    private IWeChatApiDao weChatApiDao;
    @Override
    public JpaRepository<WeChatApi, String> getRepository() {
        return weChatApiDao;
    }

    @Override
    public List<WeChatApi> findAllApi() {
        return weChatApiDao.findAll();
    }
}
