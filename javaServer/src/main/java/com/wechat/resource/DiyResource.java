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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Nelson on 2020/6/25.
 */
@RestController
@RequestMapping("/diy")
public class DiyResource extends BaseResource {
    @Autowired
    private IPictureDict pictureDictService;
    @Autowired
    private IUser userService;

    @RequestMapping(value = "/picList",method = RequestMethod.POST)
    public Result GetUserPics(@RequestBody PostDataVo vo, HttpServletRequest request){
        Result result=new Result();
        if(StringUtils.isEmpty(vo.getToken())){
            result.setResultStatus(-1);
            result.setMessage("Access needed");
            return result;
        }
        User user=userService.findByToken(vo.getToken());
        List<PictureDict> pictureDictList=pictureDictService.findPictures(user.getOpenId());
        result.setResultStatus(1);
        result.setData(pictureDictList);
        return  result;
    }
}
