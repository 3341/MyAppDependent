package com.byq.byqlib;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class FileTools {
    /**
     * 不要用来读取大文件
     * @param inputStream
     * @return
     */
    public static String readStreamContent(InputStream inputStream) throws IOException {
        Scanner scanner = new Scanner(inputStream);
        StringBuilder sb = new StringBuilder();
        while(scanner.hasNextLine()) {
            sb.append(scanner.nextLine());
        }

        if (sb.length() != 0) sb.deleteCharAt(sb.length()-1);
        inputStream.close();
        return sb.toString();
    }

    /**
     * 不要用来读取大文件
     * @param context
     * @param fileName
     * @return
     * @throws Exception
     */
    public static String readAssetsContent(Context context,String fileName) throws Exception {
        InputStream fileInputStream = context.getAssets().open(fileName);
        return readStreamContent(fileInputStream);
    }

    /**
     * Write file by input stream
     * @param in stram
     * @param file file
     * @return is success
     */
    public static boolean writeFileByInputStream(InputStream in,File file) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(in);
        FileOutputStream fo = new FileOutputStream(file);
        byte[] buffer = new byte[2048];
        int byteCount = 0;
        while ((byteCount = bufferedInputStream.read(buffer)) > 0) {
            fo.write(buffer,0,byteCount);
        }

        fo.flush();
        fo.close();
        bufferedInputStream.close();
        in.close();

        return true;
    }

    public static boolean copyAsset(Context context, String fileName, File file) throws IOException {
        if (!file.isFile() && file.createNewFile()) return false;
        return writeFileByInputStream(context.getAssets().open(fileName),file);
    }
}
