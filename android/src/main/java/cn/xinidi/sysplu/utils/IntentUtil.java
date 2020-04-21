package cn.xinidi.sysplu.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.content.FileProvider;

import java.io.File;

public class IntentUtil {
    public final static String APP_TYPE = "application/vnd.android.package-archive";
    public final static String IMAGE_TYPE = "image/*";
    public final static String VIDEO_TYPE = "video/*";
    public final static String AUDIO_TYPE = "audio/*";
    public final static String TEXT_TYPE = "text/*";
    public final static String PPT_TYPE = "application/vnd.ms-powerpoint";
    public final static String EXCEL_TYPE = "application/vnd.ms-excel";
    public final static String WORD_TYPE = "application/msword";
    public final static String CHM_TYPE = "application/x-chm";
    public final static String ZIP_TYPE = "application/x-compress";
    public final static String PDF_TYPE = "application/pdf";
    public final static String ANY_TYPE = "*/*";

    /**
     * 取指定类型的Intent
     *
     * @param context
     * @param path
     * @return
     */
    public static Intent getIntent(Context context, String path) {
        String suffix = FileUtil.getSuffix(path);

        if (suffix == null) return null;

        switch (suffix) {
            //安装软件
            case "apk":
                return _getIntent(context, path, APP_TYPE);
            //文本文件
            case "asp":
            case "bat":
            case "c":
            case "cpp":
            case "cmd":
            case "dart":
            case "java":
            case "js":
            case "jsp":
            case "json":
            case "htm":
            case "html":
            case "kt":
            case "php":
            case "smali":
            case "vbs":
            case "xhtml":
            case "xml":
            case "text":
            case "txt":
                return _getIntent(context, path, TEXT_TYPE);
            //压缩文件
            case "gz":
            case "7z":
            case "rar":
            case "zip":
                return _getIntent(context, path, ZIP_TYPE);
            //图片文件
            case "bmp":
            case "eps":
            case "jpg":
            case "jpeg":
            case "png":
            case "raw":
            case "mif":
            case "miff":
            case "tiff":
            case "tif":
            case "webp":
            case "wmf":
            case "gif":
                return _getIntent(context, path, IMAGE_TYPE);
            //音频文件
            case "cda":
            case "dts":
            case "flac":
            case "mp2":
            case "mp3":
            case "mid":
            case "mka":
            case "wav":
            case "wma":
            case "m4a":
            case "ogg":
                return _getIntent(context, path, AUDIO_TYPE);
            //视频文件
            case "3gp":
            case "avi":
            case "asf":
            case "avs":
            case "flv":
            case "rm":
            case "rmvb":
            case "mp4":
            case "mpg":
            case "mpge":
            case "mkv":
            case "mov":
            case "ogm":
            case "ts":
            case "wmv":
                return _getIntent(context, path, VIDEO_TYPE);
            //PPT
            case "pps":
            case "ppt":
            case "pptx":
                return _getIntent(context, path, PPT_TYPE);
            //Word
            case "doc":
            case "docx":
                return _getIntent(context, path, WORD_TYPE);
            //excel
            case "xls":
            case "xlsx":
                return _getIntent(context, path, EXCEL_TYPE);
            //chm
            case "chm":
                return _getIntent(context, path, CHM_TYPE);
            //pdf
            case "pdf":
                return _getIntent(context, path, PDF_TYPE);
            default:
                return _getIntent(context, path, ANY_TYPE);
        }
    }

    private static Intent _getIntent(Context context, String path, String type) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //如果文件不存在或者是文件夹
        File file = (new File(path));
        if (!file.exists() || file.isDirectory()) return null;

        //版本在7.0以上是不能直接通过uri访问的
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
            // 由于没有在Activity环境下启动Activity,设置下面的标签
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
            Uri uri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileProvider", file);
            intent.setDataAndType(uri, type);
        } else {
            intent.setDataAndType(Uri.fromFile(file), type);
        }

        return intent;
    }
}
