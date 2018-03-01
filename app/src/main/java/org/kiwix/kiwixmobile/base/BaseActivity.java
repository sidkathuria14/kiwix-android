package org.kiwix.kiwixmobile.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.kiwix.kiwixmobile.KiwixApplication;
import org.kiwix.kiwixmobile.di.components.ApplicationComponent;

import ly.count.android.sdk.Countly;

public abstract class BaseActivity extends AppCompatActivity {


  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setupDagger(KiwixApplication.getInstance().getApplicationComponent());
    //attachPresenter();

    Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
      @Override
      public void uncaughtException(Thread paramThread, Throwable paramThrowable) {
        Countly.sharedInstance().logException(new Exception(paramThrowable));
        System.exit(2);
      }
    });
  }

  @Override protected void onStart() {
    super.onStart();
    //presenter.onStart();
    Countly.sharedInstance().onStart(this);
  }

  @Override protected void onResume() {
    super.onResume();
    //presenter.onResume();
  }

  @Override protected void onPause() {
    super.onPause();
    //presenter.onPause();
  }

  @Override protected void onStop() {
    super.onStop();
    //presenter.onStop();
    Countly.sharedInstance().onStop();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    //presenter.onDestroy();
  }

  //protected void attachPresenter(Presenter presenter) {
  //  this.presenter = presenter;
  //}

  protected abstract void setupDagger(ApplicationComponent appComponent);

  //public abstract void attachPresenter();
}
