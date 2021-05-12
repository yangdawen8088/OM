package com.zero2oneit.mall.oss.api;

import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.oss.config.OSSProperties;
import com.zero2oneit.mall.oss.utils.AliyunOSSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/api/oss")
public class ApiUploadController {

    @Autowired
    private AliyunOSSUtils aliyunOSSUtils;

    @Autowired
    private OSSProperties prop;

    /**
     * 图片上传
     * @return
     */
    @PostMapping("/upload")
    public R upload(MultipartFile[] file, String fileDir) {
        StringBuffer sb = new StringBuffer();
        for (MultipartFile f : file) {
            sb.append(aliyunOSSUtils.upload(f, fileDir, null)+",");
        }
        String url = sb.toString().length() == 0 ? null : sb.toString().substring(0, sb.toString().length() - 1);
        return R.ok("文件上传成功", "{\"url\":\""+url+"\"}");
    }

}
