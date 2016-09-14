package org.lenve.meizi.net;

import org.lenve.meizi.bean.ClassfyBean;
import org.lenve.meizi.bean.Gallery;
import org.lenve.meizi.util.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 王松 on 2016/9/13.
 */
public interface ApiService {

    @GET(Constants.CLASSFYURL)
    Call<ClassfyBean> getClassfy();

    @GET(Constants.LISTURL)
    Call<Gallery> getList(@Query("id") String id);
}
