package com.butuhpembantu.job;

import android.os.AsyncTask;

import com.butuhpembantu.application.ButuhPembantuApplication;
import com.butuhpembantu.model.Maid;
import com.butuhpembantu.model.Persistence;
import com.butuhpembantu.services.MaidService;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * Created by akm on 1/18/17.
 */

public class MaidJob extends AsyncTask<Void, Void, List<Maid>> {

    private MaidJobListener maidJobListener;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        maidJobListener.onMaidJobPreExecute();
    }

    @Override
    protected List<Maid> doInBackground(Void... voids) {
        Retrofit retrofit = ButuhPembantuApplication.getInstance().getRetrofit();
        MaidService service = retrofit.create(MaidService.class);
        try {
            Call<Persistence<Maid>> getMaid = service.getAvaliableMaid();
            Persistence<Maid> maids = getMaid.execute().body();
            List<Maid> maidList = maids.getResults();

            for (Maid maid : maidList) {
                Maid maidDb = Select.from(Maid.class).where(Condition.prop("ID").eq(maid.getId())).first();
                maid.setId(maid.getId());
                maid.setName(maid.getName());
                maid.setPhone(maid.getPhone());
                maid.setAddress(maid.getAddress());
                maid.setBirthDate(maid.getBirthDate());
                maid.setBirthPlace(maid.getBirthPlace());
                maid.setIdCard(maid.getIdCard());
                maid.setIdCardNumber(maid.getIdCardNumber());
                maid.setStatus(maid.getStatus());
                maid.setPhoto(maid.getPhoto());
                if (maidDb == null || maidDb.getId() == null) {
                    maid.save();
                }

            }
        } catch (Exception e) {

        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Maid> maids) {
        super.onPostExecute(maids);
        maidJobListener.onMaidJobPostExecute(maids);
    }

    public interface MaidJobListener {
        void onMaidJobPreExecute();

        void onMaidJobPostExecute(List<Maid> maids);
    }

    public MaidJob setMaidJobListener(MaidJobListener maidJobListener) {
        this.maidJobListener = maidJobListener;

        return this;
    }
}
