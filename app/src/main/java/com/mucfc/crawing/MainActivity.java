package com.mucfc.crawing;

import android.os.CountDownTimer;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {
  public static final String URL = "http://www.wdzj.com";
  public static final String[] INDEX = {"1","2","3","5","6","7","8","9","A","B","C","D","E","F","G",
  "H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
  private boolean mFinish = true;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    for(int i = 33; i < 34; i++) {
      Log.e("index", "index:" + i);
      final int index = i;
      Crawer.syncGet(URL + "/dangan/"+ INDEX[index] + "-1.html", new Crawer.OnCrawerReadyListener() {
        @Override
        public void onReady(String raw) {
          mFinish = true;
          int totalPage = Parser.PageNumberParser(raw);
          for(int j = 1; j <= totalPage; j++) {
            final int innerIndex = j;

            Crawer.syncGet(URL + "/dangan/" + INDEX[index] + "-" + innerIndex + ".html", new Crawer.OnCrawerReadyListener() {
              @Override
              public void onReady(String raw) {
                Parser.NameGroupParser(raw);
              }
            });
          }
        }
      });
    }
  }
}