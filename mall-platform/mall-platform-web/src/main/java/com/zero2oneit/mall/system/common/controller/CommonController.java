package com.zero2oneit.mall.system.common.controller;

import com.zero2oneit.mall.common.utils.AuthCodeUtil;
import com.zero2oneit.mall.common.utils.ImgCut;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.UploadUtil;
import com.zero2oneit.mall.common.utils.select2.Select2;
import com.zero2oneit.mall.feign.oss.OssFeign;
import com.zero2oneit.mall.system.base.utils.UserContext;
import com.zero2oneit.mall.system.common.service.CommonService;
import com.zero2oneit.mall.system.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CommonController {

    @Autowired
    private CommonService commonService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/headImgUpload")
    @ResponseBody
    public R headImgUpload(@RequestParam(value = "x") String x,
                           @RequestParam(value = "y") String y,
                           @RequestParam(value = "h") String h,
                           @RequestParam(value = "w") String w,
                           @RequestParam(value = "headimgfile") String headimgfile,
                           @RequestParam(value = "file") MultipartFile file){

        //转换数据
        int imageX = Integer.parseInt(new BigDecimal(x).setScale(0, BigDecimal.ROUND_DOWN).toString());
        int imageY = Integer.parseInt(new BigDecimal(y).setScale(0, BigDecimal.ROUND_DOWN).toString());
        int imageH = Integer.parseInt(new BigDecimal(h).setScale(0, BigDecimal.ROUND_DOWN).toString());
        int imageW = Integer.parseInt(new BigDecimal(w).setScale(0, BigDecimal.ROUND_DOWN).toString());

        //将原图片上传到指定的系统盘
        String url = UploadUtil.upload(file, UploadUtil.PATH + "/upload/"+headimgfile+"/" , headimgfile);

        //读取原图然后再进行截图操作，最后将截好的图片覆盖原图片
        ImgCut.imgCut(UploadUtil.PATH + url, imageX, imageY, imageW, imageH);

        //根据用户账号修改头像图片路径
        userService.editHeadPhoto(url);

        return R.ok("用户头像上传成功", url);
    }

    /**
     * select2 插件封装
     * @param codeTable ：对应表
     * @param codeField ：表对应的字段
     * @return
     */
    @RequestMapping("/select2")
    @ResponseBody
    public List<Select2> select2(String codeTable, String codeField) {
        return commonService.select2(codeTable, codeField);
    }

    @RequestMapping("/captcha")
    public void captcha(HttpServletResponse response, HttpSession session) throws IOException {
        //创建图片
        BufferedImage image = AuthCodeUtil.getPicture();
        //获取随机数
        String randomNum = AuthCodeUtil.getRandomNum(image);
        //将随机数存在session中
        UserContext.setAuthCode(randomNum);
        //session.setAttribute("authCode", randomNum);
        //设置响应头通知浏览器以图片的形式打开
        response.setContentType("image/jpeg");
        //设置响应头控制浏览器不要缓存
        response.setDateHeader("expries", -1);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        //将图片传给浏览器
        ImageIO.write(image, "JPEG", response.getOutputStream());
    }


    @Autowired
    private OssFeign ossFeign;

    @RequestMapping("/UEditorUploadFile")
    @ResponseBody
    public Map<String, Object> upload(@RequestParam("upfile") MultipartFile[] upfile) {
        String url = null;
        if (upfile != null && upfile.length >= 1) {
            url = ossFeign.uploadImage(upfile, "goodsDetails");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("state","SUCCESS");
        map.put("url",url);
        map.put("original","aaa");
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        return map;
    }

    @RequestMapping("/config")
    @ResponseBody
    public String uploadConfig() {
        String s = "{\n" +
                "            \"imageActionName\": \"uploadimage\",\n" +
                "                \"imageFieldName\": \"upfile\", \n" +
                "                \"imageMaxSize\": 2048000, \n" +
                "                \"imageAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"], \n" +
                "                \"imageCompressEnable\": true, \n" +
                "                \"imageCompressBorder\": 1600, \n" +
                "                \"imageInsertAlign\": \"none\", \n" +
                "                \"imageUrlPrefix\": \"\", \n" +
                "                \"imagePathFormat\": \"/{yyyy}/{mm}/{dd}\" }";
        return s;
    }

}
