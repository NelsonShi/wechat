package com.wechat.service;

import com.wechat.base.IService;
import com.wechat.entity.Product;
import java.util.List;

/**
 * Created by Messi on 2020/7/1.
 */
public interface IProduct extends IService <Product> {
    List<Product> QueryAll();
    List<Product> FindByRecommend();
}
