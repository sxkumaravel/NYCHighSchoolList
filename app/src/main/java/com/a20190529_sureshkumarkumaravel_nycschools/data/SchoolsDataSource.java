package com.a20190529_sureshkumarkumaravel_nycschools.data;

import com.a20190529_sureshkumarkumaravel_nycschools.model.HighSchool;
import com.a20190529_sureshkumarkumaravel_nycschools.model.SchoolSATDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2019-05-29.
 *
 * @author kumars
 */
public class SchoolsDataSource {

    private RestInterface mRestInterface;
    private ArrayList<HighSchool> mHighSchoolList;
    private ArrayList<SchoolSATDetail> mSchoolSATDetailList;

    public SchoolsDataSource(RestInterface mRestInterface) {
        this.mRestInterface = mRestInterface;
    }

    /**
     * Process to load all the schools in NYC. The process makes sure to load the data only once.
     * For now it keeps holds of data locally.
     */
    public ArrayList<HighSchool> getNYCHighSchoolsData() {
        if (mHighSchoolList == null) {
            mHighSchoolList = mRestInterface.getHighSchoolsData();
        }

        return mHighSchoolList;
    }

    /**
     * Process to load all the SAT requirements by schools in NYC. The process makes sure to load the data only once.
     * For now it keeps holds of data locally.
     */
    public List<SchoolSATDetail> getNYCHighSchoolSATRequirementDetails() {
        if (mSchoolSATDetailList == null) {
            mSchoolSATDetailList = mRestInterface.getSchoolsSATDetails();
        }

        return mSchoolSATDetailList;
    }
}
