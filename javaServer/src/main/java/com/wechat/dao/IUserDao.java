package com.wechat.dao;

import com.wechat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Nelson on 2020/6/1.
 */
@Repository
public interface IUserDao extends JpaRepository<User,String> {
    User findByNickName(String nickName);
    User findByRdSession(String token);
}
