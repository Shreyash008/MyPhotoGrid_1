package com.example.myphotogrid_1.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myphotogrid_1.network.GridApi
import com.example.myphotogrid_1.network.GridPhoto
import kotlinx.coroutines.launch


enum class GridApiStatus {LOADING, ERROR, DONE }

class OverviewViewModel: ViewModel() {
    // internal mutablelivedata status of the most recent request
    private val _status = MutableLiveData<GridApiStatus>()

    //external livedata for req status
    val status: LiveData<GridApiStatus> = _status



    // mutablelivedata is there to update list of photos with new value
    private val _photos  =MutableLiveData<List<GridPhoto>>()


    val photos: LiveData<List<GridPhoto>> = _photos

    init {
        getGridPhotos()
    }


    // get grid photo from GridApi retrofit and update Gridphoto, list,livedata
    private fun getGridPhotos(){
        viewModelScope.launch {
            _status.value = GridApiStatus.LOADING

            try {
                _photos.value = GridApi.retrofitService.getPhotos()
                _status.value = GridApiStatus.DONE

            }catch (e: Exception ) {
                _status.value = GridApiStatus.ERROR
                _photos.value = listOf()
            }
        }
    }
}
