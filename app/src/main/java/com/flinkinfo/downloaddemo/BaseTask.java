package com.flinkinfo.downloaddemo;

import android.os.AsyncTask;

/**
 * Created by nico on 16/1/26.
 */
class BaseTask extends AsyncTask<Void ,Void,Boolean>
{

    String url;

    String path;

    public BaseTask(String url,String path)
    {
        this.url = url;
        this.path = path;
    }

    @Override
    protected Boolean doInBackground(Void... params)
    {
        return DownLoadUtil.download(url,path);
    }
}
