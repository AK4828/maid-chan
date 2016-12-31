package com.butuhpembantu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.butuhpembantu.R;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.promo_slider)
    SliderLayout promoSlider;
    @BindView(R.id.slider_indicator)
    PagerIndicator sliderIndicator;
    @BindView(R.id.service_name)
    TextView serviceName;
    @BindView(R.id.service_name1)
    TextView serviceName1;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        HashMap<String, Integer> imageFile = new HashMap<String, Integer>();
        imageFile.put("Dimanapun Kapanpun", R.drawable.rsz_1cleaning_services);
        imageFile.put("Tenaga Kerja Proffesional", R.drawable.rsz_austin_maids);
        imageFile.put("Pelayanan Tingkat Tinggi", R.drawable.rsz_domestic_cleaning_service);

        for (String name : imageFile.keySet()) {
            TextSliderView sliderContent = new TextSliderView(this);
            sliderContent
                    .image(imageFile.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            promoSlider.addSlider(sliderContent);
        }
        promoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        promoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        promoSlider.setDuration(4000);
    }
}
