package com.flinkinfo.downloaddemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String url = "http://a.hiphotos.baidu.com/zhidao/pic/item/18d8bc3eb13533fafae9926cabd3fd1f41345b10.jpg";
        String path = Environment.getExternalStorageDirectory() + File.separator + "CommonContainer" + File.separator;

        new BaseTask(url,path){
            @Override
            protected void onPostExecute(Boolean aBoolean)
            {
                super.onPostExecute(aBoolean);
                if (aBoolean)
                {
                    Toast.makeText(MainActivity.this,"下载成功",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"下载失败",Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }

}
