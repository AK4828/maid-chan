package com.butuhpembantu.application;

import android.app.Application;

import com.butuhpembantu.R;
import com.butuhpembantu.util.ConstantsURL;
import com.butuhpembantu.util.Util;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.orm.SugarContext;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by akm on 12/30/16.
 */

public class ButuhPembantuApplication extends Application {

    public static ButuhPembantuApplication instance;
    private Retrofit retrofit;
    private ObjectMapper mapper;
    private DisplayImageOptions displayImageOptions;


    public ButuhPembantuApplication() {
        instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        SugarContext.init(this);
        configureRetrofit();
    }

    private void configureRetrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(interceptor);
        builder.connectTimeout(240, TimeUnit.SECONDS);
        builder.readTimeout(240, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .baseUrl(ConstantsURL.BASE_URL)
                .client(builder.build())
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .build();
    }

    private void configureImageLoader() {
        displayImageOptions = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.no_image)
                .showImageOnFail(R.drawable.no_image)
                .resetViewBeforeLoading(false)  // default
                .build();

        File cacheDir = Util.getCacheDirectory();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .memoryCache(new WeakMemoryCache())
                .denyCacheImageMultipleSizesInMemory()
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .diskCache(new UnlimitedDiskCache(cacheDir))
                .diskCacheSize(100 * 1024 * 1024)
                .diskCacheFileCount(100)
                .defaultDisplayImageOptions(displayImageOptions)
                .build();

        ImageLoader.getInstance().init(config);
    }

    public static ButuhPembantuApplication getInstance() {
        return instance;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public ObjectMapper getMapper() {
        return mapper;
    }
}
