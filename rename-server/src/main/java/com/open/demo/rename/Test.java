package com.open.demo.rename;

import java.io.File;

/**
 * @author chenkechao
 * @date 2020/5/7 5:34 下午
 */
public class Test {

    private static final String TEMPLATE_NAME = "【更多IT教程 微信352852792】";
    private static final String PARENT_DIR = "/Users/chenkeke/Downloads/中小型企业通用自动化运维架构,拿来就能用";

    public static void main(String[] args) {

        File root = new File(PARENT_DIR);
        find(root);
    }

    private static void find(File file) {
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            for (File subFile : listFiles) {
                find(subFile);
            }
        } else {
            //重命名
            if (file.getName().contains(TEMPLATE_NAME)) {
                renameFile(file);
            }
        }
    }

    private static void renameFile(File file) {
        String newFileName = null;
        int index = file.getName().indexOf(TEMPLATE_NAME);
        if (file.isDirectory()) {
            newFileName = PARENT_DIR + "/" + file.getName().substring(0, index);
        } else {
            newFileName = PARENT_DIR + "/" + file.getName().substring(0, index) + ".mp4";
        }
        System.out.println(newFileName);
        File newFile = new File(newFileName);
        file.renameTo(newFile);
    }
}
