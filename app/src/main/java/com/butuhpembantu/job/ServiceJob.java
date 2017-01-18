package com.butuhpembantu.job;

import android.os.AsyncTask;
import android.util.Log;

import com.butuhpembantu.application.ButuhPembantuApplication;
import com.butuhpembantu.model.Persistence;
import com.butuhpembantu.model.Service;
import com.butuhpembantu.services.ServicesTypeService;
import com.orm.query.Condition;
import com.orm.query.Select;

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

            for (Service service : serviceList) {
                Service serviceDb = Select.from(Service.class)
                        .where(Condition.prop("ID").eq(service.getId())).first();
                service.setId(service.getId());
                service.setName(service.getName());
                service.setDescription(service.getDescription());
                service.setStatus(service.getStatus());
                service.setIcon(service.getIcon());
                service.getIcon().setOriginal(service.getIcon().getOriginal());
                service.getIcon().setThumb(service.getIcon().getThumb());
                service.getIcon().setMedium(service.getIcon().getMedium());
                service.getIcon().setLarge(service.getIcon().getLarge());
                if (serviceDb == null || serviceDb.getId() == null) {
                    service.save();
                }
            }

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
