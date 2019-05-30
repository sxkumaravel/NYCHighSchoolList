package com.a20190529_sureshkumarkumaravel_nycschools.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.a20190529_sureshkumarkumaravel_nycschools.data.SchoolsDataSource
import com.a20190529_sureshkumarkumaravel_nycschools.model.HighSchool
import com.a20190529_sureshkumarkumaravel_nycschools.util.singleArgViewModelFactory
import kotlinx.coroutines.launch

/**
 * View model for the Main List Screen. This is where business logic resides.
 * Created on 2019-05-29.
 *
 * @author kumars
 */
class MainViewModel(private val dataSource: SchoolsDataSource) : BaseViewModel() {

    companion object {
        val FACTORY = singleArgViewModelFactory(::MainViewModel)
    }

    private val _schoolListLiveDate = MutableLiveData<ArrayList<HighSchool>?>()

    val schoolListLiveDate: LiveData<ArrayList<HighSchool>?>
        get() = _schoolListLiveDate


    /**
     * To load the hight school lists in NYC.
     */
    fun loadHighSchoolLists() {
        _isLoadingLiveData.value = true // show the loading to indicate progress

        bgViewModelScope.launch {
            val nycHighSchoolsData = dataSource.nycHighSchoolsData
            _isLoadingLiveData.postValue(false)
            _schoolListLiveDate.postValue(nycHighSchoolsData)
        }
    }
}