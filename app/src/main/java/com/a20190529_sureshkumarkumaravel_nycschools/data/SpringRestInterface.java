package com.a20190529_sureshkumarkumaravel_nycschools.data;

import android.text.TextUtils;
import com.a20190529_sureshkumarkumaravel_nycschools.model.HighSchool;
import com.a20190529_sureshkumarkumaravel_nycschools.model.SchoolSATDetail;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jetbrains.annotations.Nullable;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Spring Rest implementation.
 * Created on 2019-05-29.
 *
 * @author kumars
 */
public class SpringRestInterface implements RestInterface {

    private RestTemplate mRestTemplate = new RestTemplate();

    public SpringRestInterface() {
        mRestTemplate.getMessageConverters().add(new StringHttpMessageConverter());
    }

    @Nullable
    @Override
    public ArrayList<HighSchool> getHighSchoolsData() {
        String response = mRestTemplate.getForObject(RestInterface.SCHOOLS_URL, String.class);
        if (TextUtils.isEmpty(response)) {
            return null;
        }

        Type type = new TypeToken<ArrayList<HighSchool>>() {
        }.getType();
        return new Gson().fromJson(response, type);
    }

    @Nullable
    @Override
    public ArrayList<SchoolSATDetail> getSchoolsSATDetails() {
        String response = mRestTemplate.getForObject(RestInterface.SCHOOLS_SAT_URL, String.class);
        if (TextUtils.isEmpty(response)) {
            return null;
        }

        Type type = new TypeToken<ArrayList<SchoolSATDetail>>() {
        }.getType();
        return new Gson().fromJson(response, type);
    }
}
