package com.open.demo.rename;

import java.io.File;
import java.util.ArrayList;

/**
 * @author chenkechao
 * @date 2020/5/7 5:34 下午
 */
public class Test {

    public static void main(String[] args) {



        String template_name = "132讲";
        //springboot2.0深度实践132讲 - 051 - 06 12 自定义 Resolver 实现
        String parentDir = "/Users/chenkeke/Downloads/springboot2.0深度实践的副本";

        //获得输入流


        File root = new File(parentDir);
        if (root.exists() || root.isDirectory()) {
            File[] files = root.listFiles();
            assert files != null && files.length != 0;
            for (File file : files) {
                //重命名
                if (file.getName().contains(template_name)) {
                    int index = file.getName().indexOf(template_name);
                    String newFileName = parentDir + "/" + "springboot2.0深度实践" + file.getName().substring(index + 9);
                    File newFile = new File(newFileName);
                    file.renameTo(newFile);
                }
            }
        }
    }
}
