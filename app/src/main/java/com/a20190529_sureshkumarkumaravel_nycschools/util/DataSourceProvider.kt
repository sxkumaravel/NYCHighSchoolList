package com.a20190529_sureshkumarkumaravel_nycschools.util

import com.a20190529_sureshkumarkumaravel_nycschools.data.SchoolsDataSource
import com.a20190529_sureshkumarkumaravel_nycschools.data.SpringRestInterface

/**
 * Created on 2019-05-29.
 *
 * @author kumars
 */
class DataSourceProvider private constructor() {

    companion object {
        @Volatile
        private var DICTIONARY_DATA_SOURCE: SchoolsDataSource? = null

        /**
         * Singleton pattern - get the same instance
         */
        fun getSchoolInstance(): SchoolsDataSource {
            return DICTIONARY_DATA_SOURCE ?: synchronized(this) {
                SchoolsDataSource(SpringRestInterface()).also {
                    DICTIONARY_DATA_SOURCE = it
                }
            }
        }
    }
}