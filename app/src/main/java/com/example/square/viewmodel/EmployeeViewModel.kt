package com.example.square.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.square.model.Employees
import com.example.square.network.EmployeeRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmployeeViewModel : ViewModel() {

    private var employeeRetrofit: EmployeeRetrofit = EmployeeRetrofit()
    private var employeeResponseMutableLiveData = MutableLiveData<Employees>()

    fun getEmployeeListResponse(): MutableLiveData<Employees>{
        employeeRetrofit.getEmployeeList().enqueue(object : Callback<Employees>{
            override fun onResponse(call: Call<Employees>, response: Response<Employees>) {
                if (response.isSuccessful && response.body() != null){
                    employeeResponseMutableLiveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<Employees>, t: Throwable) {
            }
        })
        return employeeResponseMutableLiveData
    }
}