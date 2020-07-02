package com.wechat.service.impl;

import com.wechat.base.AbstractService;
import com.wechat.dao.IProductDao;
import com.wechat.entity.Product;
import com.wechat.service.IProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Messi on 2020/7/1.
 */
@Service
public class IProductImpl extends AbstractService<Product> implements IProduct {
    @Autowired
    private IProductDao productDao;


    public JpaRepository<Product, String> getRepository() {
        return productDao;
    }

    public List<Product> QueryAll() {
        List<Product> productList=productDao.QueryAll();
        return productList;
    }

    @Override
    public List<Product> FindByRecommend() {
        List<Product> productList=productDao.findByRecommend(1);
        return productList;
    }

}
