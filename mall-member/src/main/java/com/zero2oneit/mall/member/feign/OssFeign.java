package com.zero2oneit.mall.member.feign;

import com.zero2oneit.mall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * Description:
 *
 * @author leon
 * @date 2020/11/27 17:32
 */
@FeignClient("oss-service")
public interface OssFeign {

}
