package com.zero2oneit.mall.system.user.service.impl;

import com.zero2oneit.mall.common.enums.LoginStatusCode;
import com.zero2oneit.mall.common.utils.Assert;
import com.zero2oneit.mall.common.utils.IpUtils;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.system.base.manager.AsyncManager;
import com.zero2oneit.mall.system.base.task.AsyncLogFactory;
import com.zero2oneit.mall.system.base.utils.SessionBean;
import com.zero2oneit.mall.system.base.utils.UserContext;
import com.zero2oneit.mall.system.user.entity.User;
import com.zero2oneit.mall.system.user.mapper.UserMapper;
import com.zero2oneit.mall.system.user.query.UserQueryObject;
import com.zero2oneit.mall.system.user.service.UserService;
import com.zero2oneit.mall.system.user.vo.UserVO;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    //系统用户登录
    @Override
    public User login(UserVO userVO) {

        String account = userVO.getAccount();
        //Assert.notNull(account.trim(), StatusCode.ACCOUNT_NULL.getErrorMessage());
        Assert.notNull(userVO.getPassword().trim(), LoginStatusCode.PASSWORD_NULL.getErrorMessage());
        Assert.notNull(userVO.getAuthCode().trim(), LoginStatusCode.VALIDCODE_NULL.getErrorMessage());

        User user = userMapper.login(account, userVO.getPassword());

        Assert.isNull(user,LoginStatusCode.USER_ISNULL.getErrorMessage());
        Assert.isNotEquals(String.valueOf(user.getUserStatus()),"0",LoginStatusCode.USER_UNUSED.getErrorMessage());
        UserContext.setCurrentUser(user);

        String sessionId = UserContext.getSession().getId();

        //添加登录日志
        AsyncManager.me().execute(AsyncLogFactory.loginLog(UserContext.getCurrentUser(UserContext.SYS_USER).getId(), sessionId, IpUtils.getIpAddr(UserContext.getRequest())));

        //绑定用户按钮权限
        //UserContext.getSession().setAttribute("btnPermissions", InitPemissions.getPermissions().get(user.getId()));

        //实现一个账号只能在一个地方登陆，其他地方强制下线
        if (!SessionBean.getSessions().containsKey(account)) {
            SessionBean.getSessions().put(account, sessionId);
            SessionBean.getUserSession().put(user.getId(), UserContext.getSession());
        }else if(SessionBean.getSessions().containsKey(account)
                && !sessionId.equals(SessionBean.getSessions().get(account))){
            SessionBean.getSessions().remove(account);
            SessionBean.getSessions().put(account, sessionId);

            SessionBean.getUserSession().remove(user.getId());
            SessionBean.getUserSession().put(user.getId(), UserContext.getSession());
        }

        return user;
    }

    //用户修改密码
    @Override
    @Transactional(rollbackFor=Exception.class)
    public void editPassword(String newPwd, String account, String password) {
        //1.先校验判断是否为空
        Assert.notNull(newPwd, LoginStatusCode.NEWPASSWORD_NULL.getErrorMessage());
        Assert.notNull(password, LoginStatusCode.OLDPASSWORD_NULL.getErrorMessage());

        //2.判断旧密码和新密码是否相同
        Assert.isTrue(!newPwd.equals(password), LoginStatusCode.IS_SAME.getErrorMessage());

        //3.根据用户账号和原密码修改旧密码
        Md5Hash nPwd = new Md5Hash(newPwd);
        Md5Hash oPwd = new Md5Hash(password);
        int result = userMapper.editPassword(nPwd.toString(), account, oPwd.toString());

        //4.判断是否修改密码成功
        Assert.isTrue(result != 0, LoginStatusCode.EDIT_PASSWORD.getErrorMessage());

    }

    @Override
    public void editUser(UserVO userVO) {

        //1.根据用户账号修改个人信息
        Assert.notNull(userVO.getUsername(), LoginStatusCode.USERNAME_NULL.getErrorMessage());

        userMapper.editUser(userVO);
        //2.更新session中的用户信息
        //1.先获取当前用户的session
        User currentUser = UserContext.getCurrentUser(UserContext.SYS_USER);
        //2.更新用户session中的值 -- username, email, profile
        currentUser.setUsername(userVO.getUsername());
        currentUser.setEmail(userVO.getEmail());
        currentUser.setProfile(userVO.getProfile());
        //3.把修改好的用户信息再次设置会session中
        UserContext.setCurrentUser(currentUser);

    }

    @Override
    public void editHeadPhoto(String url) {

        //1.先获取当前用户的session
        User currentUser = UserContext.getCurrentUser(UserContext.SYS_USER);
        userMapper.editHeadPhoto(url, currentUser.getAccount());

        //2.更新用户session中的值 -- username, email, profile
        currentUser.setHeadPhoto(url);
        //3.把修改好的用户信息再次设置会session中
        UserContext.setCurrentUser(currentUser);

    }

    @Override
    public void addUser(UserVO userVO) {
        //校验
        Assert.notNull(userVO.getAccount().trim(), LoginStatusCode.ACCOUNT_NULL.getErrorMessage());
        Assert.notNull(userVO.getUsername().trim(), LoginStatusCode.USERNAME_NULL.getErrorMessage());

        //判断该账号是否存在
        User user = userMapper.login(userVO.getAccount(), null);
        Assert.isTrue(user == null,LoginStatusCode.USER_EXISTS.getErrorMessage());

        //用户记录添加
        Md5Hash md5Hash = new Md5Hash("888888");
        userVO.setPassword(md5Hash.toString());
        userVO.setUserId(UserContext.getCurrentUser(UserContext.SYS_USER).getId());
        userMapper.addUser(userVO);

        //用户角色中间表添加
        if(userVO.getRoleCodes() != null){
            userMapper.addUserRole(userVO.getId(), userVO.getRoleCodes());
        }
    }

    @Override
    public BoostrapDataGrid queryList(UserQueryObject qo) {
        //查询总记录数
        int total = userMapper.selectTotal(qo);
        return new BoostrapDataGrid(total, total == 0 ? Collections.EMPTY_LIST : userMapper.selectRows(qo));
    }

    @Override
    public void changeUserStatus(String status, String ids) {
        userMapper.changeUserStatus(status, ids);
    }

    @Override
    public List<?> getUserAllRole(Long id) {
        return userMapper.getUserAllRole(id);
    }

    @Override
    public void updateUser(UserVO userVO) {
        //校验
        Assert.notNull(userVO.getAccount().trim(), LoginStatusCode.ACCOUNT_NULL.getErrorMessage());
        Assert.notNull(userVO.getUsername().trim(), LoginStatusCode.USERNAME_NULL.getErrorMessage());
        //用户记录编辑
        userMapper.updateUser(userVO);
        //先删除再添加
        userMapper.delUserRole(userVO.getId());
        userMapper.updateUserRole(userVO.getId(), userVO.getRoleCodes());
    }

}
