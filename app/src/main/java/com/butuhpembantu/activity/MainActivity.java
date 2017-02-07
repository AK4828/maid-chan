package com.butuhpembantu.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.butuhpembantu.R;
import com.butuhpembantu.adapters.ServiceAdapter;
import com.butuhpembantu.job.MaidJob;
import com.butuhpembantu.job.MaidLevelJob;
import com.butuhpembantu.job.ServiceJob;
import com.butuhpembantu.job.ServicePackageJob;
import com.butuhpembantu.model.Maid;
import com.butuhpembantu.model.MaidLevel;
import com.butuhpembantu.model.Persistence;
import com.butuhpembantu.model.Service;
import com.butuhpembantu.model.ServicePackage;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ServiceJob.ServiceJobListener,
        MaidLevelJob.MaidLevelJobListener, ServicePackageJob.ServicePackageJobListener, MaidJob.MaidJobListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.promo_slider)
    SliderLayout promoSlider;
    @BindView(R.id.slider_indicator)
    PagerIndicator sliderIndicator;
    @BindView(R.id.service_recycler)
    RecyclerView serviceRecycler;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressDialog progressDialog;
    private ServiceAdapter serviceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        HashMap<String, Integer> imageFile = new HashMap<String, Integer>();
        imageFile.put("Dimanapun Kapanpun", R.drawable.rsz_1cleaning_services);
        imageFile.put("Tenaga Kerja Profesional", R.drawable.rsz_austin_maids);
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

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.message_loading));
        progressDialog.setCancelable(false);

        layoutManager = new LinearLayoutManager(this);
        serviceRecycler.setLayoutManager(layoutManager);
        serviceRecycler.setHasFixedSize(true);
        serviceRecycler.setAdapter(serviceAdapter = new ServiceAdapter(this));
        new ServiceJob().setServiceJobListener(this).execute();
        new MaidLevelJob().setLevelJobListener(this).execute();
        new ServicePackageJob().setServicePackageJobListener(this).execute();
        new MaidJob().setMaidJobListener(this).execute();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onServiceJobPreExecute() {
        if (progressDialog != null && !progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void onServiceJobPostExecute(List<Service> services) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        if (services.size() != 0) {
            serviceAdapter.addItem(services);
        }
    }

    @Override
    public void onMaidLevelJobPreExecute() {
        if (progressDialog != null && !progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void onMaidLevelJobPostExecute(List<MaidLevel> services) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onServicePackageJobPreExecute() {
        if (progressDialog != null && !progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void onServicePackageJobPostExecute(List<ServicePackage> servicePackages) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onMaidJobPreExecute() {
        if (progressDialog != null && !progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void onMaidJobPostExecute(List<Maid> maids) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
