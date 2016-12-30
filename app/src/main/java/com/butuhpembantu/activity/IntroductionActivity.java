package com.butuhpembantu.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.MenuItem;
import android.view.View;

import com.butuhpembantu.R;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

/**
 * Created by akm on 12/30/16.
 */

public class IntroductionActivity extends AppIntro {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        addSlide(AppIntroFragment.newInstance(getString(R.string.intro_title_slide1), getString(R.string.intro_description1), R.drawable.ic_arrow_back_white, getResources().getColor(R.color.colorPrimary)));
        addSlide(AppIntroFragment.newInstance(getString(R.string.intro_title_slide2), getString(R.string.intro_description2), R.drawable.ic_arrow_back_white, getResources().getColor(R.color.lightGreen500)));
        addSlide(AppIntroFragment.newInstance(getString(R.string.intro_title_slide3), getString(R.string.intro_description3), R.drawable.ic_arrow_back_white, getResources().getColor(R.color.amber600)));

        showSkipButton(false);

    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        startActivity(new Intent(IntroductionActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
