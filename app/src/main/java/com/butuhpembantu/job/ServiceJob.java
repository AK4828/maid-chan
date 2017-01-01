package com.butuhpembantu.job;

import android.os.AsyncTask;
import android.util.Log;

import com.butuhpembantu.application.ButuhPembantuApplication;
import com.butuhpembantu.model.Persistence;
import com.butuhpembantu.model.Service;
import com.butuhpembantu.services.ServicesTypeService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * Created by akm on 1/1/17.
 */

public class ServiceJob extends AsyncTask<Void, Void, List<Service>> {

    private ServiceJobListener serviceJobListener;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        serviceJobListener.onServiceJobPreExecute();

    }

    @Override
    protected List<Service> doInBackground(Void... voids) {
        Retrofit retrofit = ButuhPembantuApplication.getInstance().getRetrofit();
        ServicesTypeService typeService = retrofit.create(ServicesTypeService.class);

        try {
            Call<Persistence<Service>> getServiceCall = typeService.getAvailableService();
            Persistence<Service> services = getServiceCall.execute().body();
            List<Service> serviceList = services.getResults();

            return serviceList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Service> services) {
        super.onPostExecute(services);
        serviceJobListener.onServiceJobPostExecute(services);
    }

    public ServiceJob setServiceJobListener(ServiceJobListener serviceJobListener) {
        this.serviceJobListener = serviceJobListener;
        return this;
    }

    public interface ServiceJobListener {
        void onServiceJobPreExecute();

        void onServiceJobPostExecute(List<Service> services);

    }
}
