package com.zero2oneit.mall.system.common.shiro.realm;

import com.zero2oneit.mall.system.base.utils.UserContext;
import com.zero2oneit.mall.system.common.shiro.util.ShiroUtils;
import com.zero2oneit.mall.system.power.service.PowerService;
import com.zero2oneit.mall.system.role.service.RoleService;
import com.zero2oneit.mall.system.user.entity.User;
import com.zero2oneit.mall.system.user.service.UserService;
import com.zero2oneit.mall.system.user.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * 自定义授权机制
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PowerService powerService;

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        String password = "";
        if (upToken.getPassword() != null) {
            password = new String(upToken.getPassword());
        }

        User user = null;

        Md5Hash md5Hash = new Md5Hash(password);
        try {
            user = userService.login(new UserVO(username, md5Hash.toString(), UserContext.getAuthCode(UserContext.AUTH_CODE)));
        } catch (Exception e) {
            log.info("对用户[" + username + "]进行登录验证..验证未通过{}", e.getMessage());
            throw new AuthenticationException(e.getMessage(), e);
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, password, getName());

        return authenticationInfo;
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        User user = ShiroUtils.getSysUser();
        // 角色列表
        Set<String> roles = new HashSet<String>();
        // 功能列表
        Set<String> menus = new HashSet<String>();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 管理员拥有所有权限
       if (user.isAdmin()) {
            info.addRole("admin");
            info.addStringPermission("*:*:*");
        } else {
            roles = roleService.selectUserRoleByUserId(user.getId());
            menus = powerService.selectPermsByUserId(user.getId());
            // 角色加入AuthorizationInfo认证对象
            info.setRoles(roles);
            // 权限加入AuthorizationInfo认证对象
            info.setStringPermissions(menus);
        }
        return info;
    }

    /**
     * 清理缓存权限
     */
    public void clearCachedAuthorizationInfo() {
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }

    public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash("888888");
        System.out.println(md5Hash.toString());
    }

}
