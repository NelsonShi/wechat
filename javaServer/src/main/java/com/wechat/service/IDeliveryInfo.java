package com.wechat.service;

import com.wechat.base.IService;
import com.wechat.entity.DeliveryInfo;

/**
 * Created by Nelson on 2020/6/22.
 */
public interface IDeliveryInfo extends IService<DeliveryInfo> {
    DeliveryInfo findByNickName(String nickName);
}
