package com.zero2oneit.mall.service.member.remote;

import com.zero2oneit.mall.common.annotion.OperateLog;
import com.zero2oneit.mall.common.bean.goods.GoodSaleArea;
import com.zero2oneit.mall.common.bean.member.StationManage;
import com.zero2oneit.mall.common.enums.BusinessType;
import com.zero2oneit.mall.common.query.goods.GoodsCategoryQueryObject;
import com.zero2oneit.mall.common.query.member.InfoQueryObject;
import com.zero2oneit.mall.common.query.member.StationManageQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.feign.member.MemberFeign;
import com.zero2oneit.mall.feign.member.StationManageFeign;
import com.zero2oneit.mall.feign.oss.OssFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 远程调用驿站管理服务
 *
 * @author yjj
 * @date 2021/3/1 10:51
 */
@RestController
@RequestMapping("/remote/member")
public class MemberRemote {

    @Autowired
    private MemberFeign memberFeign;

    /**
     * 会员信息管理列表
     * @param qo
     * @return
     */
    @PostMapping("/list")
    public BoostrapDataGrid list(@RequestBody InfoQueryObject qo) {
        return memberFeign.list(qo);
    }

    /**
     * 重置会员登录密码
     * @param memberId
     * @return
     */
    @PostMapping("/resetPwd")
    public R resetPwd(@RequestParam("memberId") String memberId){
        return memberFeign.resetPwd(memberId);
    }


}
