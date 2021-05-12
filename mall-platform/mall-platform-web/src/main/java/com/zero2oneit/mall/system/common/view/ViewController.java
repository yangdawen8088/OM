package com.zero2oneit.mall.system.common.view;

import com.alibaba.fastjson.JSON;
import com.zero2oneit.mall.common.monitor.Server;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.system.base.utils.UpgradeUtil;
import com.zero2oneit.mall.system.base.utils.UserContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 视图跳转统一处理
 */
@Controller
public class ViewController {

    //登录跳转
    @GetMapping("/login.html")
    public String login(HttpServletRequest request, HttpServletResponse response) {

        String flag = request.getParameter("flag");
        // 如果是Ajax请求，返回Json字符串。
        if (UserContext.isAjaxRequest(request) && flag == null) {
            return UserContext.renderString(response, JSON.toJSONString(R.fail("gq")));
        }else if(!UserContext.isAjaxRequest(request) && flag == null){
            return UserContext.sendRedirect(response);
        }else{
          return "login";
        }

    }

    //main跳转
    @RequestMapping("/main.html")
    public String toMain() {
        return "main";
    }

    //系统维护页面
    @RequestMapping("/wh.html")
    public String toWh() {
        Integer runtimeStatus = UpgradeUtil.getRuntimeStatus();
        if(runtimeStatus == 0){
           return "wh";
        }else{
           return "redirect:/login.html";
        }
    }

    //没有权限跳转
    @RequestMapping("/error.html")
    public String toError() {
        return "common/error";
    }

    //跳转主页
    @RequestMapping("/index.html")
    public String toIndex() {
        return "index";
    }

    //修改密码
    @RequestMapping("/editPwd.html")
    public String toEditPwd() {
        return "editPwd";
    }

    //修改头像
    @RequestMapping("/editHead.html")
    public String toEditHead() {
        return "editHead";
    }

    //登录日志
    @RequestMapping("/loginLog.html")
    public String toLoginLog() {
        return "system/log/loginLog";
    }

    //操作日志
    @RequestMapping("/opLog.html")
    public String toOpLog() {
        return "system/log/opLog";
    }

    //角色管理
    @RequestMapping("/roleList.html")
    public String toRoleList() {
        return "system/role/roleList";
    }

    //部门管理
    @RequestMapping("/deptList.html")
    public String toDeptList() {
        return "system/dept/deptList";
    }

    //用户管理
    @RequestMapping("/userList.html")
    public String toUserList() {
        return "system/user/userList";
    }

    //菜单管理
    @RequestMapping("/menuList.html")
    public String toMenuList() {
        return "system/menu/menuList";
    }

    //权限管理
    @RequestMapping("/functionList.html")
    public String toFunctionList() {

        return "system/power/functionList";
    }

    //数据权限管理
    @RequestMapping("/dataPermissionList.html")
    public String toDataPermissionList() {
        return "system/power/dataPermissionList";
    }

    //公告管理
    @RequestMapping("/noticeList.html")
    public String toNoticeList() {
        return "system/notice/noticeList";
    }

    //系统维护
    @RequestMapping("/upgrade.html")
    public String toUpgrade() {
        return "system/upgrade/upgrade";
    }

    //数据字典
    @RequestMapping("/codeList.html")
    public String toCodeList() {
        return "system/dictionary/codeList";
    }

    //任务管理
    @RequestMapping("/quartzList.html")
    public String toQuartzList() {
        return "system/quartz/quartzList";
    }

    //任务日志详情
    @RequestMapping("/quartzLogList.html")
    public String toQuartzLogList() {
        return "system/quartz/quartzLogList";
    }

    //服务监控
    @RequestMapping("/server.html")
    public String toServer() {
        return "system/monitor/server";
    }

    //统一处理页面跳转
    @RequestMapping("/toView")
    public String toView(String url) {
        return url;
    }

}
