package com.zero2oneit.mall.member.feign;

import com.zero2oneit.mall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Description:
 *
 * @author leon
 * @date 2020/12/3 10:56
 */
@FeignClient("coupon-service")
public interface CouponFeign {

}
