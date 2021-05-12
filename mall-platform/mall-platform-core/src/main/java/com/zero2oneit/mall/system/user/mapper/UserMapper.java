package com.zero2oneit.mall.system.user.mapper;

import com.zero2oneit.mall.system.user.entity.User;
import com.zero2oneit.mall.system.user.query.UserQueryObject;
import com.zero2oneit.mall.system.user.vo.UserVO;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface UserMapper {

    User login(@Param("account") String account, @Param("password") String password);

    int editPassword(@Param("newPwd") String newPwd, @Param("account") String account, @Param("password") String password);

    void editUser(UserVO userVO);

    void editHeadPhoto(@Param("url") String url, @Param("account") String account);

    void addUser(UserVO userVO);

    void addUserRole(@Param("id") Long id, @Param("roleCodes") String[] roleCodes);

    int selectTotal(UserQueryObject qo);

    List<HashMap<String,Object>> selectRows(UserQueryObject qo);

    void changeUserStatus(@Param("status") String status, @Param("ids") String ids);

    List<HashMap<String,Object>> getUserAllRole(Long id);

    void updateUser(UserVO userVO);

    void updateUserRole(@Param("id") Long id, @Param("roleCodes") String[] roleCodes);

    void delUserRole(Long id);

}
