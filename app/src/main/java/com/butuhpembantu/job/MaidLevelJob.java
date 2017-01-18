package com.butuhpembantu.job;

import android.os.AsyncTask;
import android.util.Log;

import com.butuhpembantu.application.ButuhPembantuApplication;
import com.butuhpembantu.model.MaidLevel;
import com.butuhpembantu.model.Persistence;
import com.butuhpembantu.services.MaidLevelService;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * Created by akm on 1/18/17.
 */

public class MaidLevelJob extends AsyncTask<Void, Void, List<MaidLevel>> {

    private MaidLevelJobListener maidLevelJobListener;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        maidLevelJobListener.onMaidLevelJobPreExecute();
    }

    @Override
    protected List<MaidLevel> doInBackground(Void... voids) {

        Retrofit retrofit = ButuhPembantuApplication.getInstance().getRetrofit();
        MaidLevelService service = retrofit.create(MaidLevelService.class);
        try {
            Call<Persistence<MaidLevel>> getMaidLevels = service.getAvaliableMaidLevel();
            Persistence<MaidLevel> maidLevels = getMaidLevels.execute().body();
            List<MaidLevel> maidLevelList = maidLevels.getResults();

            for (MaidLevel maidLevel : maidLevelList) {
                MaidLevel maidLevelDb = Select.from(MaidLevel.class)
                        .where(Condition.prop("ID").eq(maidLevel.getId())).first();
                maidLevel.setId(maidLevel.getId());
                maidLevel.setName(maidLevel.getName());
                maidLevel.setDescription(maidLevel.getDescription());
                maidLevel.setStatus(maidLevel.getStatus());
                if (maidLevelDb == null || maidLevelDb.getId() == null) {
                    maidLevel.save();
                }
            }
            return maidLevelList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<MaidLevel> maidLevels) {
        super.onPostExecute(maidLevels);

        maidLevelJobListener.onMaidLevelJobPostExecute(maidLevels);
    }

    public MaidLevelJob setLevelJobListener(MaidLevelJobListener maidLevelJobListener) {
        this.maidLevelJobListener = maidLevelJobListener;

        return this;
    }

    public interface MaidLevelJobListener {
        void onMaidLevelJobPreExecute();

        void onMaidLevelJobPostExecute(List<MaidLevel> services);
    }
}
