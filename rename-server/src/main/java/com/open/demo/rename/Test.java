package com.open.demo.rename;

import java.io.File;

/**
 * @author chenkechao
 * @date 2020/5/7 5:34 下午
 */
public class Test {

    private static final String TEMPLATE_NAME = "Spring Cloud Alibaba从入门到进阶 - ";
    private static final String PARENT_DIR = "/Users/chenkeke/Desktop/study/慕课网/Spring Cloud Alibaba从入门到进阶";

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

    private static void splitFileName(File file) {
        int len = "Spring Cloud Alibaba从入门到进阶 - 011 - Spring Cloud Alibaba从入门到进阶 ".length();
        String newFileName = null;
        if (file.isDirectory()) {
            newFileName = PARENT_DIR + "/" + file.getName().substring(len);
        } else {
            newFileName = PARENT_DIR + "/" + file.getName().substring(len);
        }
        System.out.println(newFileName);
        File newFile = new File(newFileName);
        file.renameTo(newFile);
    }

}
