package com.open.demo.rename;

import java.io.File;
import java.util.ArrayList;

/**
 * @author chenkechao
 * @date 2020/5/7 5:34 下午
 */
public class Test {

    public static void main(String[] args) {



        String template_name = "【更多IT教程 微信107564881】";
        String parentDir = "/Users/chenkeke/Desktop/study/慕课网/Nginx入门到实践Nginx中间件/第6章 新特性篇";

        //获得输入流


        File root = new File(parentDir);
        if (root.exists() && root.isDirectory()) {
            File[] files = root.listFiles();
            assert files != null && files.length != 0;
            for (File file : files) {
                //重命名
                if (file.getName().contains(template_name)) {
                    int index = file.getName().indexOf(template_name);
                    String newFileName = parentDir + "/" + file.getName().substring(0, index) + ".mp4";
                    System.out.println(newFileName);
                    File newFile = new File(newFileName);
                    file.renameTo(newFile);
                }
            }
        }
    }
}
