package com.jit.mail.Utils;

import java.io.File;
import java.io.FileOutputStream;

/**
 * created by  luwei
 * 2018-02-02 19:41.
 **/
public class FileUtilss {

    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}
