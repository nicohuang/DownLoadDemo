package com.hwz.downloaddemo;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity
{

    private DownLoadCompleteReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String url = "http://125.91.9.72/145/24/72/muzhiwan/0/letv.cdn.muzhiwan.com/2015/05/22/com.blackpic.rotatethecube_555efb4950298.apk?crypt=58aa7f2e98550&b=1314&nlh=3072&nlt=45&bf=6000&p2p=1&video_type=apk&termid=0&tss=no&geo=CN-19-246-1&platid=0&splatid=0&its=0&qos=5&fcheck=0&proxy=244855880,244855856,1032384108&uid=244403262.rp&keyitem=GOw_33YJAAbXYE-cnQwpfLlv_b2zAkYctFVqe5bsXQpaGNn3T1-vhw..&ntm=1446817800&nkey=34a1c9241257a1ba209a4cd57517273b&nkey2=bb689b8a4427fe61342aebe19bc66de0&errc=0&gn=1010&buss=62&cips=14.145.76.62&lersrc=Y2MuY2RuLm11emhpd2FuLmNvbQ==&tag=muzhiwan&cuhost=letv.cdn.muzhiwan.com&cuid=134810&flw3x=0&sign=coopdown&fext=.apk";
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));

        /**
         * addRequestHeader(String header,String value):添加网络下载请求的http头信息
         allowScanningByMediaScanner():用于设置是否允许本MediaScanner扫描。
         setAllowedNetworkTypes(int flags):设置用于下载时的网络类型，默认任何网络都可以下载，提供的网络常量有：NETWORK_BLUETOOTH、NETWORK_MOBILE、NETWORK_WIFI。
         setAllowedOverRoaming(Boolean allowed):用于设置漫游状态下是否可以下载
         setNotificationVisibility(int visibility):用于设置下载时时候在状态栏显示通知信息
         setTitle(CharSequence):设置Notification的title信息
         setDescription(CharSequence):设置Notification的message信息
         setDestinationInExternalFilesDir、setDestinationInExternalPublicDir、 setDestinationUri等方法用于设置下载文件的存放路径，注意如果将下载文件存放在默认路径，那么在空间不足的情况下系统会将文件删除，所 以使用上述方法设置文件存放目录是十分必要的。
         */

        request.allowScanningByMediaScanner();
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
        request.setTitle("下载");
        request.setDescription("正在下载");
        request.setAllowedOverRoaming(false);

        //设置文件存放目录
        request.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS, "mydown");

        DownloadManager downloadManager =(DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        long id = downloadManager.enqueue(request);

        //取消下载
//        downloadManager.remove(id);

        IntentFilter filter = new IntentFilter();
        filter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
//        filter.addAction(DownloadManager.ACTION_NOTIFICATION_CLICKED);
        receiver = new DownLoadCompleteReceiver();
        registerReceiver(receiver, filter);
    }

    private class DownLoadCompleteReceiver extends BroadcastReceiver
    {

        @Override
        public void onReceive(Context context, Intent intent)
        {
            if(intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
            {
               long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID,-1);
                Toast.makeText(MainActivity.this,"下载完成",Toast.LENGTH_SHORT).show();
            }
            else if(intent.getAction().equals(DownloadManager.ACTION_NOTIFICATION_CLICKED))
            {
                Toast.makeText(MainActivity.this,"再点一下有惊喜哦",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
