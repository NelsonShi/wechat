package com.wechat.service.impl;

import com.wechat.base.AbstractService;
import com.wechat.dao.IDeliveryInfoDao;
import com.wechat.entity.DeliveryInfo;
import com.wechat.service.IDeliveryInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * Created by Nelson on 2020/6/22.
 */
@Service
public class IDeliveryInfoImpl extends AbstractService<DeliveryInfo> implements IDeliveryInfo {
    @Autowired
    private IDeliveryInfoDao deliveryInfoDao;
    @Override
    public JpaRepository<DeliveryInfo, String> getRepository() {
        return deliveryInfoDao;
    }

    @Override
    public DeliveryInfo findByNickName(String nickName) {
        return deliveryInfoDao.findByNickName(nickName);
    }
}
