package com.zero2oneit.mall.oss.admin;

import com.zero2oneit.mall.oss.utils.AliyunOSSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Description: 平台调用上传
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/2/3
 */
@RestController
@RequestMapping("/admin/oss")
public class AdminUploadController {

    @Autowired
    private AliyunOSSUtils aliyunOSSUtils;

    /**
     * 图片上传
     * @return
     */
    @PostMapping("/uploadImage")
    public String uploadImage(MultipartFile[] file, String fileDir) {
        StringBuffer sb = new StringBuffer();
        for (MultipartFile f : file) {
            sb.append(aliyunOSSUtils.upload(f, fileDir, null)+",");
        }
        return sb.toString().length() == 0 ? null : sb.toString().substring(0, sb.toString().length() - 1);
    }

    /**
     * 删除OSS对象
     * @return
     */
    @PostMapping("/deleteImage")
    public void deleteImage(String urls) {
        for (String url : urls.split(",")){
            aliyunOSSUtils.deleteUrl(url);
        }
    }

}
