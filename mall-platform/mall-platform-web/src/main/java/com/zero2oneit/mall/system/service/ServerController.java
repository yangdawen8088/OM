package com.zero2oneit.mall.system.service;

import com.zero2oneit.mall.common.monitor.Server;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.system.server.entity.SysServer;
import com.zero2oneit.mall.system.server.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ServerController {

    @Autowired
    private ServerService serverService;

    @RequestMapping("/queryServerByServerName")
    @ResponseBody
    public R queryServerByServerName(String serverName) throws Exception {
        Server server = new Server();
        server.copyTo();
        return R.ok("操作成功", server);
    }

    @RequestMapping("/serverList")
    @ResponseBody
    public List<SysServer> serverList() {
       return serverService.serverList();
    }

    @RequestMapping("/addOrEditServer")
    @ResponseBody
    public R addOrEditServer(SysServer serverVO) {
        if (serverVO.getId().length() == 0){ //添加
            serverService.add(serverVO);
        }else{//编辑
            serverService.edit(serverVO);
        }
        return R.ok("操作成功");
    }

}
