package com.mucfc.crawing;

import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by abby on 2017/9/14.
 */

public class Crawer {
    private static OkHttpClient mOkHttpClient;
    public interface OnCrawerReadyListener{
        void onReady(String raw);
    }
    public static void syncGet(String url, final OnCrawerReadyListener listener) {
        mOkHttpClient = new OkHttpClient();
        Request.Builder requestBuilder = new Request.Builder().url(url);
        requestBuilder.method("GET", null);
        final Request request = requestBuilder.build();
        Call call = mOkHttpClient.newCall(request);
        /*try {
            listener.onReady(call.execute().body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("error", "error");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                listener.onReady(response.body().string());
            }
        });
    }

}
