package com.zero2oneit.mall.common.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传工具
 */
public class UploadUtil {

	//存放图片的路径
	public static final String PATH = "D://cloud";


	/**
	 * 处理文件上传
	 * @param file
	 * @param basePath 文件盘目录
	 * @param folderName 文件夹
	 * @return  123.png
	 */
	public static String upload(MultipartFile file, String basePath, String folderName) {//c:/trip/upload
		//获取随机字符串
		String uuid = UUID.randomUUID().toString();
		//获取上传的文件的名称
		String orgFileName = file.getOriginalFilename();
		//获取文件后缀名
		String ext= "." + FilenameUtils.getExtension(orgFileName);
		//文件名称
		String fileName = uuid + ext;
		try {
			File targetFile = new File(basePath, fileName); //c:/trip/upload/sssss.jpg
			FileUtils.writeByteArrayToFile(targetFile, file.getBytes());
			return "/upload/" + folderName + "/"+ fileName;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}


    public static boolean removeFile(String filePath) {
		try {
			File file = new File(UploadUtil.PATH, filePath);;//根据指定的文件名创建File对象
			if (!file.exists()){  //要删除的文件不存在
				System.out.println("文件"+filePath+"不存在，删除失败！" );
				return false;
			}else{ //要删除的文件存在
				if (file.delete()){
					System.out.println("文件"+filePath+"删除成功！");
					return true;
				}else{
					System.out.println("文件"+filePath+"删除失败！");
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
    }

}
























