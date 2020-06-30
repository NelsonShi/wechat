package com.wechat.dao;

import com.wechat.entity.AppInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Nelson on 2020/6/17.
 */
@Repository
public interface IAppInfoDao extends JpaRepository<AppInfo,String> {
    AppInfo findByAppName(String name);
}
