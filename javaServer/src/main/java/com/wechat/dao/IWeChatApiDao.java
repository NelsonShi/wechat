package com.wechat.dao;

import com.wechat.entity.WeChatApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Nelson on 2020/6/18.
 */
@Repository
public interface IWeChatApiDao extends JpaRepository<WeChatApi,String> {
}
