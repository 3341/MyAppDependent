package com.byq.lilb;

import android.content.Context;
import android.content.res.AssetFileDescriptor;

import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.GsonUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Usage
 * Create directory assets/gson
 * Put json file to the directory
 * All gson file must is json format file
 */
public class GsonTools {
    private static final String GSON_SAVE_POSITION = "gsonFiles";
    private static final String GSON_ASSETS_FILE_NAME = "gson";

    public static class GsonDirNotFoundException extends RuntimeException {
        public GsonDirNotFoundException() {
            super("Gson directory not found.");
        }
    }

    public static void releaseAllGsonFiles(Context context) throws IOException {
        AssetFileDescriptor assetFileDescriptor = context.getAssets().openFd(GSON_ASSETS_FILE_NAME);
        if (assetFileDescriptor != null) {
            String[] list = context.getAssets().list(GSON_ASSETS_FILE_NAME);
            for (String name : list) {
                if (name.endsWith(".json")) {
                    File filesDir = context.getFilesDir();
                    filesDir = new File(filesDir,GSON_SAVE_POSITION);
                    if (!filesDir.isDirectory()) {
                        filesDir.mkdirs();
                    }

                    //Write to file
                    File targetFile = new File(filesDir,name);
                    targetFile.createNewFile();
                    FileTools.copyAsset(context,GSON_ASSETS_FILE_NAME+"/"+name,targetFile);
                }
            }

            return;
        }

        throw new GsonDirNotFoundException();
    }

    public static void resetGsonConfig(Context context,File file) throws IOException {
        //Index
        String[] list = context.getAssets().list(GSON_ASSETS_FILE_NAME);
        for (String name : list) {
            if (name.equals(file.getName())) {
                //Found json
                FileTools.copyAsset(context,GSON_ASSETS_FILE_NAME+"/"+name,file);
                return;
            }
        }

        throw new FileNotFoundException();
    }

    public static boolean setGsonConfig(Context context,String gson,Class gsonClass,File file) {
        //Verify is format right
        try {
            GsonUtils.fromJson(gson, gsonClass);
        } catch (Exception e) {
            //Verify failed
            return false;
        }

        //Apply
        FileIOUtils.writeFileFromString(file,gson);
        return true;
    }
}
