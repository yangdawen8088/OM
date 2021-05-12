package com.zero2oneit.mall.auth.utils;


import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;

/**
 * @Title: WXCore
 * @Description:
 * @Auther:yjj
 * @Version: 1.0
 * @create 2020/11/7 18:14
 */
public class WXCore {

    private static final String WATERMARK = "watermark";
    private static final String APPID = "appid";

    public static String decrypt(String appId, String encryptedData, String sessionKey, String iv){
        String result = "";
        try {
            AES aes = new AES();
            byte[] resultByte = aes.decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(sessionKey), Base64.decodeBase64(iv));
            if(null != resultByte && resultByte.length > 0){
                result = new String(WxPKCS7Encoder.decode(resultByte));
                JSONObject jsonObject = JSONObject.fromObject(result);
                String decryptAppid = jsonObject.getJSONObject(WATERMARK).getString(APPID);
                if(!appId.equals(decryptAppid)){
                    result = "";
                }
            }
        } catch (Exception e) {
            result = "";
            e.printStackTrace();
        }
        return result;
    }
}
