package com.zero2oneit.mall.system.user.controller;

import com.zero2oneit.mall.common.annotion.OperateLog;
import com.zero2oneit.mall.common.enums.BusinessType;
import com.zero2oneit.mall.common.enums.LoginStatusCode;
import com.zero2oneit.mall.common.utils.Assert;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.system.base.utils.UpgradeUtil;
import com.zero2oneit.mall.system.base.utils.UserContext;
import com.zero2oneit.mall.system.log.service.LogService;
import com.zero2oneit.mall.system.user.entity.User;
import com.zero2oneit.mall.system.user.query.UserQueryObject;
import com.zero2oneit.mall.system.user.service.UserService;
import com.zero2oneit.mall.system.user.vo.UserVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private LogService logService;

    @PostMapping("/login")
    @ResponseBody
    public R login(UserVO userVO) {//VO : value-object 值对象

        //获取缓存中系统运行状态
        Assert.isNull(userVO, LoginStatusCode.USER_ISNULL.getErrorMessage());
        Assert.notNull(userVO.getAccount().trim(), LoginStatusCode.ACCOUNT_NULL.getErrorMessage());

        if(!"admin".equals(userVO.getAccount())){
            Integer runtimeStatus = UpgradeUtil.getRuntimeStatus();
            if(runtimeStatus == 0){
                return R.ok("wh");
            }
        }
        UsernamePasswordToken token = new UsernamePasswordToken(userVO.getAccount(),userVO.getPassword(), false);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        return R.ok("登录成功");
    }

    @OperateLog(title = "修改用户密码", businessType = BusinessType.UPDATE)
    @RequestMapping("/editPwd")
    @ResponseBody
    public R editPwd(UserVO userVO) {//VO : value-object 值对象
        //先从session中获取用户账号
        User currentUser = UserContext.getCurrentUser(UserContext.SYS_USER);
        userService.editPassword(userVO.getNewPwd(), currentUser.getAccount(), userVO.getPassword());
        return R.ok("密码修改成功");
    }

    @RequestMapping("/editUser")
    @ResponseBody
    public R editUser(UserVO userVO) {//VO : value-object 值对象
        //1.根据用户账号修改个人信息
        userService.editUser(userVO);
        return R.ok("用户信息修改成功");
    }

    @RequestMapping("/addOrEditUser")
    @ResponseBody
    public R addOrEditUser(UserVO userVO) {//VO : value-object 值对象
        if(userVO.getId() == null){
           userService.addUser(userVO);
        }else{
           userService.updateUser(userVO);
        }
        return R.ok("用户操作成功");
    }

    @RequestMapping("/changeUserStatus")
    @ResponseBody
    public R changeUserStatus(String status, String ids) {//VO : value-object 值对象
        userService.changeUserStatus(status, ids);
        return R.ok("改变用户状态成功");
    }

    @RequestMapping("/getUserAllRole")
    @ResponseBody
    public R getUserAllRole(Long userId) {//VO : value-object 值对象
        List<?> maps = userService.getUserAllRole(userId);
        return R.ok("用户角色", maps);
    }

    @RequestMapping("/userPage")
    @ResponseBody
    public BoostrapDataGrid userPage(@RequestBody UserQueryObject qo) {//VO : value-object 值对象
        return userService.queryList(qo);
    }

}
