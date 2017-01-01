package com.butuhpembantu.services;

import com.butuhpembantu.model.Persistence;
import com.butuhpembantu.model.Service;
import com.butuhpembantu.util.ConstantsURL;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by akm on 1/1/17.
 */

public interface ServicesTypeService {
    @Headers("Content-Type: application/json")
    @GET(ConstantsURL.SERVICE_URL)
    Call<Persistence<Service>> getAvailableService();
}
