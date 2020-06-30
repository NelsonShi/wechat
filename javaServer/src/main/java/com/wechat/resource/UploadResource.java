package com.wechat.resource;

import com.wechat.base.BaseResource;
import com.wechat.base.Result;
import com.wechat.entity.PictureDict;
import com.wechat.entity.User;
import com.wechat.entity.vo.PostDataVo;
import com.wechat.service.IPictureDict;
import com.wechat.service.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Nelson on 2020/6/29.
 */
@RestController
@RequestMapping("/upload")
public class UploadResource extends BaseResource {
    @Autowired
    private IPictureDict pictureDictService;
    @Autowired
    private IUser userService;

    @RequestMapping(value = "/picture",method = RequestMethod.POST)
    public Result SavePic(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        Result result=new Result();
        String token=request.getParameter("user");
        if(StringUtils.isEmpty(token)){
            result.setResultStatus(-1);
            result.setMessage("Access needed");
        }else {
            User user=userService.findByToken(token);
            PictureDict p= pictureDictService.SavePic(file,user.getOpenId());
            if(p!=null){
                result.setResultStatus(1);
                result.setData(p);
            }else {
                result.setResultStatus(-1);
                result.setMessage("Error: save picture fail");
            }
        }
        return result;
    }

    @RequestMapping(value = "/deletePic",method = RequestMethod.POST)
    public Result DeletePic(@RequestBody PostDataVo vo){
        Result result=new Result();
        return result;
    }
}
