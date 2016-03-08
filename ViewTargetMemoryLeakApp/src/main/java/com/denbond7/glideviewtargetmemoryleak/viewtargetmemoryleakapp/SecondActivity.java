package com.denbond7.glideviewtargetmemoryleak.viewtargetmemoryleakapp;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * @author Denis Bondarenko
 *         Date: 08.03.2016
 *         Time: 13:55
 *         E-mail: DenBond7@gmail.com
 */
public class SecondActivity extends AppCompatActivity {

  @Override
  public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
    super.onCreate(savedInstanceState, persistentState);
    setContentView(R.layout.activity_second);

    GalleryFragment galleryFragment = new GalleryFragment();

    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.replace(R.id.container, galleryFragment, GalleryFragment.class.getSimpleName());
    fragmentTransaction.commit();
  }
}
