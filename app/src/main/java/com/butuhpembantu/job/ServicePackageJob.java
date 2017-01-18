package com.butuhpembantu.job;

import android.os.AsyncTask;

import com.butuhpembantu.application.ButuhPembantuApplication;
import com.butuhpembantu.model.Persistence;
import com.butuhpembantu.model.ServicePackage;
import com.butuhpembantu.services.ServicePackageService;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * Created by akm on 1/18/17.
 */

public class ServicePackageJob extends AsyncTask<Void, Void, List<ServicePackage>> {

    private ServicePackageJobListener servicePackageJobListener;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        servicePackageJobListener.onServicePackageJobPreExecute();
    }

    @Override
    protected List<ServicePackage> doInBackground(Void... voids) {
        Retrofit retrofit = ButuhPembantuApplication.getInstance().getRetrofit();
        ServicePackageService service = retrofit.create(ServicePackageService.class);
        try {
            Call<Persistence<ServicePackage>> getServicePackages = service.getAvaliablePackages();
            Persistence<ServicePackage> servicePackage = getServicePackages.execute().body();
            List<ServicePackage> servicePackages = servicePackage.getResults();
            for (ServicePackage packages : servicePackages) {
                ServicePackage servicePackageDb = Select.from(ServicePackage.class)
                        .where(Condition.prop("ID").eq(packages.getId())).first();
                packages.setId(packages.getId());
                packages.setName(packages.getName());
                packages.setServiceId(packages.getServiceId());
                packages.setLevelMaidId(packages.getLevelMaidId());
                packages.setDollarPrice(packages.getDollarPrice());
                packages.setFilipinePrice(packages.getFilipinePrice());
                packages.setIdnPrice(packages.getIdnPrice());
                packages.setStatus(packages.getStatus());
                if (servicePackageDb == null || servicePackageDb.getId() == null) {
                    packages.save();
                }
            }
        } catch (Exception e) {

        }
        return null;
    }

    @Override
    protected void onPostExecute(List<ServicePackage> servicePackages) {
        super.onPostExecute(servicePackages);
        servicePackageJobListener.onServicePackageJobPostExecute(servicePackages);
    }

    public interface ServicePackageJobListener {
        void onServicePackageJobPreExecute();

        void onServicePackageJobPostExecute(List<ServicePackage> servicePackages);
    }

    public ServicePackageJob setServicePackageJobListener(ServicePackageJobListener servicePackageJobListener) {
        this.servicePackageJobListener = servicePackageJobListener;

        return this;
    }
}
