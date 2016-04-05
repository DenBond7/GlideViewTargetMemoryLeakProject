package com.denbond7.glideleak;

import android.app.Application;

/**
 * @author Denis Bondarenko
 *         Date: 09.03.2016
 *         Time: 11:15
 *         E-mail: DenBond7@gmail.com
 */
public class CustomApplication extends Application {

  @Override  public void onCreate() {
    super.onCreate();
//    LeakCanary.install(this);
  }
}
