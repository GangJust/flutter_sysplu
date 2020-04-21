package cn.xinidi.sysplu.utils;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtil {
    /**
     * 文件后缀
     *
     * @param path
     * @return
     */
    public static String getSuffix(String path) {

        //Log.d("GF", "path:" + path);

        //如果文件不存在或者是文件夹
        File file = (new File(path));
        if (!file.exists() || file.isDirectory()) return null;
        String suffix = path.substring(path.lastIndexOf(".") + 1);
        /*Log.d("GF", "path.length:" + path.length());
        Log.d("GF", "suffix:" + suffix);*/

        return suffix.toLowerCase();
    }

}
