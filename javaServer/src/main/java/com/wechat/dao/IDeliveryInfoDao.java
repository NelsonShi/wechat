package com.wechat.dao;

import com.wechat.entity.DeliveryInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Nelson on 2020/6/22.
 */
@Repository
public interface IDeliveryInfoDao extends JpaRepository<DeliveryInfo,String> {
    DeliveryInfo findByNickName(String nickName);
}
