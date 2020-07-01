package com.wechat.service;

import com.wechat.base.IService;
import com.wechat.entity.PictureDict;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Nelson on 2020/6/25.
 */
public interface IPictureDict extends IService<PictureDict> {
    List<PictureDict> findPictures(String openId);
    PictureDict SavePic(MultipartFile file,String openId);
    Boolean deletePic(PictureDict dict);
}
