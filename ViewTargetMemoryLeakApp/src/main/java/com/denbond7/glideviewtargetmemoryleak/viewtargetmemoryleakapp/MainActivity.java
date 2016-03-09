package com.denbond7.glideviewtargetmemoryleak.viewtargetmemoryleakapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  private static final int SPLASH_TIME_OUT = 1000;
  private static final Handler HANDLER = new Handler();
  private long startTime;
  private boolean isActivityShow;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    startTime = System.currentTimeMillis();

    if (!isTaskRoot()) {
      final Intent intent = getIntent();
      final String intentAction = intent.getAction();
      if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && intentAction != null
          && intentAction.equals(Intent.ACTION_MAIN)) {
        finish();
      }
    }

    HANDLER.postDelayed(new Runnable() {
      @Override
      public void run() {
        if (isActivityShow) {
          runChooseActivity();
        }
      }
    }, SPLASH_TIME_OUT);
  }

  @Override
  public void onResume() {
    super.onResume();
    isActivityShow = true;
    if (System.currentTimeMillis() - startTime > SPLASH_TIME_OUT) {
      runChooseActivity();
    }
  }

  @Override
  public void onPause() {
    super.onPause();
    isActivityShow = false;
  }

  private void runChooseActivity() {
    startActivity(new Intent(this, SecondActivity.class));
  }

}
