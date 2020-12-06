package com.open.demo.rename;

import java.io.File;

/**
 * @author chenkechao
 * @date 2020/5/7 5:34 下午
 */
public class Test {

    private static final String ROOT_DIR = "/Volumes/Disk/慕课网/Java并发核心知识体系精讲";

    private static final String FIX_STR = "【更多IT教程 微信352852792】(1).mp4";

    private static final int LEN = "【更多IT教程 微信352852792】(1).mp4".length();

    public static void main(String[] args) {

        File rootFile = new File(ROOT_DIR);

        renameFile(rootFile);
    }

    private static void renameFile(File file) {
        if (file.isDirectory()) {
            //递归寻找 文件
            File[] childList = file.listFiles();
            if (childList == null || childList.length == 0) {
                return;
            }
            for (File child : childList) {
                renameFile(child);
            }
        } else {
            if (file.getName().endsWith(".mp4")) {
                //rename
                if (file.getName().contains(FIX_STR)) {
                    doRename(file);
                }
            }
        }
    }

    private static void doRename(File file) {
        String name = rename1(file.getName());
//        System.out.println(name);

        File newFile = new File(file.getParent(), name);
        file.renameTo(newFile);
//        System.out.println(newFile.getAbsolutePath());
    }

    private static String rename1(String fileName) {

        String name = fileName.substring(0, fileName.length() - LEN) + ".mp4";
        return name;
    }

}
