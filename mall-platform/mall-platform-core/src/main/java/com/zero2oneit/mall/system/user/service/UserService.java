package com.zero2oneit.mall.system.user.service;

import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.system.user.entity.User;
import com.zero2oneit.mall.system.user.query.UserQueryObject;
import com.zero2oneit.mall.system.user.vo.UserVO;

import java.util.List;

public interface UserService {

    //系统用户登录
    User login(UserVO userVO);

    void editPassword(String newPwd, String account, String password);

    void editUser(UserVO userVO);

    void editHeadPhoto(String url);

    void addUser(UserVO userVO);

    BoostrapDataGrid queryList(UserQueryObject qo);

    void changeUserStatus(String status, String ids);

    List<?> getUserAllRole(Long id);

    void updateUser(UserVO userVO);

}
