package com.wechat.dao;

import com.wechat.entity.PictureDict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Nelson on 2020/6/25.
 */
@Repository
public interface IPictureDictDao extends JpaRepository<PictureDict,String> {
    List<PictureDict> findByOpenId(String openId);
}
