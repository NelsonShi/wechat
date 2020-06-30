package com.wechat.utils;

import com.wechat.CacheKey;
import com.wechat.entity.AppInfo;
import com.wechat.entity.WeChatApi;
import com.wechat.service.IAppInfo;
import com.wechat.service.IWeChatApi;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Nelson on 2020/6/18.
 */
@Service
public class CacheUtil {
    @Autowired
    private IAppInfo appInfoService;
    @Autowired
    private IWeChatApi weChatApiService;

    public void InitCache() {
        LoadAppInfoToCache();
        LoadWeChatApiToCache();
    }

    private void ReInitCache(String key) {
        switch (key){
            case "AppInfo":LoadAppInfoToCache();
                             break;
            case "WeChatApi":LoadWeChatApiToCache();
                             break;
            default:break;
        }
    }

    private void LoadAppInfoToCache() {
        AppInfo app = appInfoService.getDefaultApp();
        Cache cache = SpringContextUtil.getBean(CacheKey.DicCaching, Cache.class);
        Element element = new Element(CacheKey.AppInfo, app);
        cache.put(element);
    }

    private void LoadWeChatApiToCache(){
        List<WeChatApi> weChatApiList=weChatApiService.findAllApi();
        Map<String,WeChatApi> weChatApiMap=new HashMap<>(weChatApiList.size());
        for (WeChatApi api:weChatApiList){
            weChatApiMap.put(api.getApiName(),api);
        }
        Cache cache = SpringContextUtil.getBean(CacheKey.DicCaching, Cache.class);
        Element element = new Element(CacheKey.WeChatApi, weChatApiMap);
        cache.put(element);
    }

    /**
     * 通用函数，用去获取缓存中的数据
     * @param elementKey element key value
     * @param cls
     * @param <T> 数据类型
     * @return
     */
    public <T> T getObjectValue(String elementKey, Class<T> cls) {
        Cache cache = SpringContextUtil.getBean(CacheKey.DicCaching, Cache.class);
        Element element = cache.get(elementKey);
        if (element == null || cache.isExpired(element))
            ReInitCache(elementKey);
            element = cache.get(elementKey);
        return (T) element.getObjectValue();
    }
}
