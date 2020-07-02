package com.wechat.dao;

import com.wechat.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Messi on 2020/7/1.
 */
public interface IProductDao extends JpaRepository<Product,String> {
    @Query(nativeQuery=true,value="SELECT * FROM product")
    List<Product> QueryAll();
    List<Product> findByRecommend(Integer id);
}
