package com.wechat.resource;

import com.wechat.base.BaseResource;
import com.wechat.base.Result;
import com.wechat.entity.Product;
import com.wechat.service.IProduct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * Created by Messi on 2020/7/1.
 */

@RestController
@RequestMapping("/product")
public class ProductResource extends BaseResource{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IProduct productService;


    @RequestMapping(method = RequestMethod.GET,value = "/allProductList")
    public Result getAllProductList(HttpServletResponse response){
        if(logger.isDebugEnabled()){
            logger.debug("Rest Call: /product/allProductList ...");
        }
        long t1 = new Date().getTime();
        List<Product> users=productService.QueryAll();
        Result result=new Result(users);
        long t2 = new Date().getTime();
        System.out.println("GET Rest Call: /product/allProductList ..."+(t2-t1));
        return result;
    }


    @RequestMapping(method = RequestMethod.GET,value = "/recommendList")
    public Result getRecommendProductList(HttpServletResponse response){
        if(logger.isDebugEnabled()){
            logger.debug("Rest Call: /product/recommendList ...");
        }
        long t1 = new Date().getTime();
        List<Product> users=productService.FindByRecommend();
        Result result=new Result(users);
        long t2 = new Date().getTime();
        System.out.println("GET Rest Call: /product/recommendList ..."+(t2-t1));
        return result;
    }
}
