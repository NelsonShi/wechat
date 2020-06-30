package com.wechat.resource;

import com.wechat.CacheKey;
import com.wechat.base.BaseResource;
import com.wechat.base.Result;
import com.wechat.entity.AppInfo;
import com.wechat.entity.DeliveryInfo;
import com.wechat.entity.User;
import com.wechat.entity.WeChatApi;
import com.wechat.entity.vo.LoginVo;
import com.wechat.entity.vo.PostDataVo;
import com.wechat.service.IDeliveryInfo;
import com.wechat.service.IUser;
import com.wechat.utils.CacheUtil;
import com.wechat.utils.JsonToObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Nelson on 2020/6/1.
 */
@RestController
@RequestMapping("/user")
public class UserResource extends BaseResource{
     @Autowired
     private IUser userService;
     @Autowired
     private CacheUtil cacheUtil;
     @Autowired
     private IDeliveryInfo deliveryInfoService;

     @RequestMapping("/findAll")
     public List<User> findAllUser(){
         return userService.findAll();
     }

     @RequestMapping(value = "/login",method = RequestMethod.POST)
     public Result LoginWithToken(@RequestBody LoginVo vo, HttpServletRequest request){
         AppInfo app=cacheUtil.getObjectValue(CacheKey.AppInfo,AppInfo.class);
         Map<String,WeChatApi> apiMap=cacheUtil.getObjectValue(CacheKey.WeChatApi,Map.class);
         User user= JsonToObjectUtil.jsonToPojo(vo.getRowData(),User.class);
         String token=userService.WeChatLogin(app,apiMap.get("login"),vo.getCode(),user);
         DeliveryInfo deliveryInfo=deliveryInfoService.findByNickName(user.getNickName());
         if(deliveryInfo!=null){
             vo.setDeliveryInfo(deliveryInfo);
         }
         vo.setToken(token);
         return new Result(vo);
     }

     @RequestMapping(value = "/updateSession",method = RequestMethod.POST)
     public Result UpdateSession(@RequestBody LoginVo vo, HttpServletRequest request){
         AppInfo app=cacheUtil.getObjectValue(CacheKey.AppInfo,AppInfo.class);
         User user= JsonToObjectUtil.jsonToPojo(vo.getRowData(),User.class);
         String str= userService.ValidateUser(vo,app.getAppId(),user);
         Result result=new Result();
         if(str.contains("Error")){
              result.setResultStatus(-1);
              result.setMessage(str);
         }else {
             DeliveryInfo deliveryInfo=deliveryInfoService.findByNickName(user.getNickName());
             if(deliveryInfo!=null){
                 vo.setDeliveryInfo(deliveryInfo);
             }
              vo.setToken(str);
              result.setData(vo);
         }
         return result;
     }

    @RequestMapping(value = "/updateDelivery",method = RequestMethod.POST)
    public Result UpdateDeliveryInfo(@RequestBody PostDataVo vo, HttpServletRequest request){
         Result result=new Result();
         try {
             DeliveryInfo deliveryInfo=vo.ConvertDataToObject(DeliveryInfo.class);
             DeliveryInfo newInfo=userService.SaveDeliveryInfo(deliveryInfo);
             if(newInfo!=null){
                 result.setData(newInfo);
             }else {
                 result.setResultStatus(-1);
                 result.setMessage("update fail");
             }
         }catch (Exception ex){
             result.setResultStatus(-1);
             result.setMessage("Error:"+ex.getMessage());
         }
         return result;
     }

}
