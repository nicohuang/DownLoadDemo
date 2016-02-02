package com.flinkinfo.downloaddemo;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 下载工具栏
 *
 * @author nico
 */
public class DownLoadUtil
{
    public static String imagePath="";

    public static boolean download(String msg,String path)
    {
        HttpURLConnection con = null;
        InputStream in = null;
        OutputStream out = null;
        int fileLength = -1;
        byte[] buf;

        try
        {
            URL url = new URL(msg);
            con = (HttpURLConnection) url.openConnection();
//            System.out.println("length--" + con.getContentLength());
            fileLength = con.getContentLength();
            if (fileLength != -1)
            {
                buf = new byte[1024];
                in = con.getInputStream();
                out = new FileOutputStream(getNewFile(msg,path));
                int size = 0;
                while ((size = in.read(buf)) != -1)
                {
                    out.write(buf, 0, size);
                }
                return true;
            }

        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return false;

    }

    public static File getNewFile(String msg,String path)
    {
        String fileName = msg.substring(msg.lastIndexOf("/") + 1);
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {
            return null;
        }
        File file = new File(path + fileName);
        imagePath = fileName;
        if (!file.getParentFile().exists())
        {
            file.getParentFile().mkdirs();

        }
        if (!file.exists())
        {
            try
            {
                file.createNewFile();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return file;
    }
}
