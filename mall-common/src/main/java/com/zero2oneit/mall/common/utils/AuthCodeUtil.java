package com.zero2oneit.mall.common.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Description: 验证码生成工具类
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/1/11
 */
public class AuthCodeUtil {

    private static final int WIDTH = 120;//生成图片的宽度
    private static final int HEIGHT = 38;//生成图片的高度
    private static final int WORDS_NUMBER = 1;//验证码中字符的个数

    public static BufferedImage getPicture(){
        BufferedImage bi = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_3BYTE_BGR);
        return bi;
    }

    public static String getRandomNum(BufferedImage bi){
        //得到图片
        Graphics g = bi.getGraphics();
        //设置图片的背景色
        setBackGround(g);
        //设置图片的边框
        setBorder(g);
        //在图片上画干扰线
        drawRandomLine(g);
        //在图片上放上随机字符
        String randomString = drawRandomNum(g);

        return randomString;
    }


    //设置图片背景色
    //@param g
    private static void setBackGround(Graphics g) {
        //设置颜色
        g.setColor(Color.WHITE);
        //填充区域
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }

    /*
     * 设置图片的边框
     * @param g
     * */
    private static void setBorder(Graphics g) {
        //设置边框颜色
        g.setColor(Color.BLUE);
        //边框区域
        g.drawRect(1, 1, WIDTH - 2, HEIGHT -2);
    }

    /*
     * 在图片上画随机线条
     * @param g
     * */
    private static void drawRandomLine(Graphics g) {
        //设置颜色
        g.setColor(Color.GREEN);
        //设置线条个数并画线
        for ( int i = 0 ; i < 3 ; i++ ) {
            int x1 = new Random().nextInt(WIDTH);
            int y1 = new Random().nextInt(HEIGHT);
            int x2 = new Random().nextInt(WIDTH);
            int y2 = new Random().nextInt(HEIGHT);
            g.drawLine(x1, y1, x2, y2);
        }
    }

    /*
     * 在图片上画随机字符
     * @param g
     * @return String
     * */
    private static String drawRandomNum(Graphics g) {
        //设置颜色
        g.setColor(Color.RED);
        g.setFont(new Font("宋体",Font.BOLD,20));

        //数字字母的组合
        String baseNumLetter = "123456789ABCDEFGHJKLMNPQRSTUVWXYZ";

        //截取数字和字母的组合
        return createRandomChar((Graphics2D) g,baseNumLetter);
    }

    /*
     * 创建随机字符
     * @param g
     * @param baseChar
     * @return String
     * */
    private static String createRandomChar(Graphics2D g, String baseChar) {
        StringBuffer b = new StringBuffer();
        int x = 5;
        String ch = "";
        for (int i = 0 ; i < WORDS_NUMBER ; i++) {
            //设置字体的旋转角度
            int degree = new Random().nextInt() % 30;
            ch = baseChar.charAt(new Random().nextInt(baseChar.length())) + "";
            b.append(ch);

            //正向角度
            g.rotate(degree  * Math.PI / 180 , x,20);
            g.drawString(ch, x, 20);
            //反向角度
            g.rotate(-degree  * Math.PI / 180 , x,20);
            x+=30;
        }
        return b.toString();
    }

}
