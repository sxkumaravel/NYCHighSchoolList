package com.a20190529_sureshkumarkumaravel_nycschools.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.a20190529_sureshkumarkumaravel_nycschools.data.SchoolsDataSource
import com.a20190529_sureshkumarkumaravel_nycschools.model.HighSchool
import com.a20190529_sureshkumarkumaravel_nycschools.model.SchoolSATDetail
import com.a20190529_sureshkumarkumaravel_nycschools.util.singleArgViewModelFactory
import kotlinx.coroutines.launch


/**
 * View model for the Detail Screen. This is where business logic resides.
 * Created on 2019-05-29.
 *
 * @author kumars
 */
class DetailViewModel(private val dataSource: SchoolsDataSource) : BaseViewModel() {

    companion object {
        val FACTORY = singleArgViewModelFactory(::DetailViewModel)
    }

    private val _schoolSATLiveDate = MutableLiveData<SchoolSATDetail?>()

    val schoolSATLiveDate: LiveData<SchoolSATDetail?>
        get() = _schoolSATLiveDate

    /**
     * Function to load the School SAT data.
     */
    fun loadSchoolSATData(highSchool: HighSchool) {
        _isLoadingLiveData.value = true
        bgViewModelScope.launch {
            val satList = dataSource.nycHighSchoolSATRequirementDetails
            _isLoadingLiveData.postValue(false)

            if (satList == null || satList.isEmpty()) {
                _schoolSATLiveDate.postValue(null)
                return@launch
            }

            _schoolSATLiveDate.postValue(filterSATDetail(highSchool, satList))
        }
    }

    /**
     * To filter out the SAT detail in the list based on the DBN number.
     */
    private fun filterSATDetail(highSchool: HighSchool, satDetailList: List<SchoolSATDetail>): SchoolSATDetail? {
        for (item in satDetailList) {
            if (item.dbn.equals(highSchool.dbn, true)) {
                return item
            }
        }
        return null
    }
}