package com.zero2oneit.mall.system.common.filter;

import com.zero2oneit.mall.common.utils.UploadUtil;
import org.apache.commons.io.FileUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * 图片过滤器
 */
public class ImageFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        //获取当前请求的图片路径
        String uri = req.getRequestURI();
        //判断图片目录中是否存在该图片
        File file = new File(UploadUtil.PATH, uri);
        if(file.exists()){
            //将图片响应回浏览器
            response.getOutputStream().write(FileUtils.readFileToByteArray(file));
        }else{
            //放行
            chain.doFilter(request,response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

}
