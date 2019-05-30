package com.a20190529_sureshkumarkumaravel_nycschools.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

/**
 * Created on 2019-05-29.
 *
 * @author kumars
 */
abstract class BaseViewModel: ViewModel() {

    private val viewModelJob = Job()
    protected val bgViewModelScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    protected val _isLoadingLiveData = MutableLiveData<Boolean>()

    val isLoadingLiveData: LiveData<Boolean>
        get() = _isLoadingLiveData

    override fun onCleared() {
        super.onCleared()
        // cancel the running job when the view model id cleared.
        viewModelJob.cancel()
    }
}