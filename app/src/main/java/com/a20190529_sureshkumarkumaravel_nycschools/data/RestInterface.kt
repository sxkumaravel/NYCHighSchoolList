package com.a20190529_sureshkumarkumaravel_nycschools.data

import com.a20190529_sureshkumarkumaravel_nycschools.model.HighSchool
import com.a20190529_sureshkumarkumaravel_nycschools.model.SchoolSATDetail
import java.util.ArrayList

/**
 * Created on 2019-05-29.
 *
 * @author kumars
 */
interface RestInterface {

    companion object {
        const val SCHOOLS_URL = "https://data.cityofnewyork.us/resource/s3k6-pzi2.json"
        const val SCHOOLS_SAT_URL = "https://data.cityofnewyork.us/resource/f9bf-2cp4.json"
    }

    /**
     * To get the list of all school detail in NYC.
     */
    fun getHighSchoolsData(): ArrayList<HighSchool>?

    /**
     * To get the list of all school SAT requirement details in NYC.
     */
    fun getSchoolsSATDetails(): ArrayList<SchoolSATDetail>?
}