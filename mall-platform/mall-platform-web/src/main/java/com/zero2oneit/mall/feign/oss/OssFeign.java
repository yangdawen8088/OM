package com.zero2oneit.mall.feign.oss;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/2/3
 */
@FeignClient("oss-service")
public interface OssFeign {

    /**
     * 上传图片
     * @param file
     * @param fileDir : 文件保存目录名称
     * @return
     */
    @PostMapping(value = "/admin/oss/uploadImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadImage(@RequestPart("file") MultipartFile[] file, @RequestParam("fileDir") String fileDir) ;

    /**
     * 删除图片
     * @param urls : 图片路径
     * @return
     */
    @PostMapping(value = "/admin/oss/deleteImage")
    String deleteImage(@RequestParam("urls") String urls) ;

}
