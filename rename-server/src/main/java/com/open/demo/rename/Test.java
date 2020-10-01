package com.open.demo.rename;

import java.io.File;

/**
 * @author chenkechao
 * @date 2020/5/7 5:34 下午
 */
public class Test {

    private static final String TEMPLATE_NAME = "2020-2-12 13-49-42【更多教程微信352852792】";
    private static final String PARENT_DIR = "/Users/chenkeke/Desktop/study/视频/深度解锁SpringCloud主流组件 一战解决微服务诸多难题";
    private static final int EXTRA_LEN = 4;

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
            if (listFiles != null) {
                for (File subFile : listFiles) {
                    find(subFile);
                }
            }
        } else {
            //重命名
            if (file.getName().contains(TEMPLATE_NAME)) {
                splitFileName(file);
            }
        }
    }


    private static void splitFileName(File file) {
        int len = TEMPLATE_NAME.length() + EXTRA_LEN;
        String newFileName = null;
//        if (file.isDirectory()) {
//            newFileName = PARENT_DIR + "/" + file.getName().substring(len);
//        } else {
            newFileName = PARENT_DIR + "/" + file.getName().substring(len) + ".mp4";
//        }
        System.out.println(newFileName);
//        File newFile = new File(newFileName);
//        file.renameTo(newFile);
    }

}
