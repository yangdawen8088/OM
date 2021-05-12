package com.zero2oneit.mall.oss.utils;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectResult;
import com.zero2oneit.mall.common.utils.CommonUtils;
import com.zero2oneit.mall.common.utils.StringUtils;
import com.zero2oneit.mall.common.utils.filter.StrAttackFilter;
import com.zero2oneit.mall.common.utils.html.EscapeUtil;
import com.zero2oneit.mall.oss.config.OSSProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Date;

/**
 * Description: 阿里云 OSS 文件上传工具类
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/1/26
 */
@Slf4j
@Component
@EnableConfigurationProperties(OSSProperties.class)
public class AliyunOSSUtils {

    @Autowired
    private OSSProperties prop;

    /**
     * oss 工具客户端
     */
    private OSSClient ossClient = null;

    /**
     * 上传文件至阿里云 OSS
     * 文件上传成功,返回文件完整访问路径
     * 文件上传失败,返回 null
     *
     * @param file    待上传文件
     * @param fileDir 文件保存目录
     * @return oss 中的相对文件路径
     */
    public String upload(MultipartFile file, String fileDir,String customBucket) {
        String FILE_URL = null;
        initOSS(prop.getEndpoint(), prop.getKeyid(), prop.getKeysecret());
        StringBuilder fileUrl = new StringBuilder();
        String newBucket = prop.getBucketname();
        if(StringUtils.isNotEmpty(customBucket)){
            newBucket = customBucket;
        }
        try {
            //判断桶是否存在,不存在则创建桶
            if(!ossClient.doesBucketExist(newBucket)){
                ossClient.createBucket(newBucket);
            }
            // 获取文件名
            String orgName = EscapeUtil.unescape(file.getOriginalFilename());
            if("" == orgName){
                orgName=file.getName();
            }
            orgName = CommonUtils.getFileName(orgName);
            String fileName = orgName.substring(0, orgName.lastIndexOf(".")) + "_" + System.currentTimeMillis() + orgName.substring(orgName.indexOf("."));
            if (!fileDir.endsWith("/")) {
                fileDir = fileDir.concat("/");
            }
            //过滤上传文件夹名特殊字符，防止攻击
            fileDir= StrAttackFilter.filter(fileDir);
            fileUrl = fileUrl.append(fileDir + fileName);
            FILE_URL = "https://" + newBucket + "." + prop.getEndpoint() + "/" + fileUrl;
            PutObjectResult result = ossClient.putObject(newBucket, fileUrl.toString(), file.getInputStream());
            if (result != null) {
                log.info("------OSS文件上传成功------" + fileUrl);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return FILE_URL;
    }

    /**
     * 文件上传
     * @param file
     * @param fileDir
     * @return
     */
    public String upload(MultipartFile file, String fileDir) {
        return upload(file, fileDir,null);
    }

    /**
     * 删除文件
     * @param url
     */
    public void deleteUrl(String url) {
        deleteUrl(url,null);
    }

    /**
     * 删除文件
     * @param url
     */
    public void deleteUrl(String url,String bucket) {
        String newBucket = prop.getBucketname();
        if(StringUtils.isNotEmpty(bucket)){
            newBucket = bucket;
        }
        String bucketUrl = "https://" + newBucket + "." + prop.getEndpoint() + "/";
        url = url.replace(bucketUrl,"");
        ossClient.deleteObject(newBucket, url);
    }

    /**
     * 删除文件
     * @param fileName
     */
    public void delete(String fileName) {
        ossClient.deleteObject(prop.getBucketname(), fileName);
    }

    /**
     * 获取文件流
     * @param objectName
     * @param bucket
     * @return
     */
    public InputStream getOssFile(String objectName,String bucket){
        InputStream inputStream = null;
        try{
            String newBucket = prop.getBucketname();
            if(StringUtils.isNotEmpty(bucket)){
                newBucket = bucket;
            }
            initOSS(prop.getEndpoint(), prop.getKeyid(), prop.getKeysecret());
            OSSObject ossObject = ossClient.getObject(newBucket,objectName);
            inputStream = new BufferedInputStream(ossObject.getObjectContent());
        }catch (Exception e){
            log.info("文件获取失败" + e.getMessage());
        }
        return inputStream;
    }

    /**
     * 获取文件流
     * @param objectName
     * @return
     */
    public InputStream getOssFile(String objectName){
        return getOssFile(objectName,null);
    }

    /**
     * 获取文件外链
     * @param bucketName
     * @param objectName
     * @param expires
     * @return
     */
    public String getObjectURL(String bucketName, String objectName, Date expires) {
        initOSS(prop.getEndpoint(), prop.getKeyid(), prop.getKeysecret());
        try{
            if(ossClient.doesObjectExist(bucketName,objectName)){
                URL url = ossClient.generatePresignedUrl(bucketName,objectName,expires);
                return URLDecoder.decode(url.toString(),"UTF-8");
            }
        }catch (Exception e){
            log.info("文件路径获取失败" + e.getMessage());
        }
        return null;
    }

    /**
     * 初始化 oss 客户端
     *
     * @return
     */
    private OSSClient initOSS(String endpoint, String accessKeyId, String accessKeySecret) {
        if (ossClient == null) {
            ossClient = new OSSClient(endpoint,
                    new DefaultCredentialProvider(accessKeyId, accessKeySecret),
                    new ClientConfiguration());
        }
        return ossClient;
    }

}
