package com.zero2oneit.mall.feign.member;

import com.zero2oneit.mall.common.query.member.InfoQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Description:
 *
 * @author yjj
 * @date 2021/3/1 16:43
 */
@FeignClient("member-service")
public interface MemberFeign {

    @PostMapping("/admin/member/list")
    BoostrapDataGrid list(@RequestBody InfoQueryObject qo);

    @PostMapping("/admin/member/resetPwd")
    R resetPwd(@RequestParam("memberId") String memberId);

}
