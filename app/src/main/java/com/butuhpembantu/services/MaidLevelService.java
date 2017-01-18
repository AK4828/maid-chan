package com.butuhpembantu.services;

import com.butuhpembantu.model.MaidLevel;
import com.butuhpembantu.model.Persistence;
import com.butuhpembantu.util.ConstantsURL;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by akm on 1/18/17.
 */

public interface MaidLevelService {
    @Headers("Content-Type: application/json")
    @GET(ConstantsURL.MAID_lEVEL_URL)
    Call<Persistence<MaidLevel>> getAvaliableMaidLevel();
    
}
