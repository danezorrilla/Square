package com.example.square.network

import com.example.square.model.Employees
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EmployeeRetrofit {

    private var employeeAPI: EmployeeAPI = createEmployeeAPI(createRetrofitInstance())

    private fun createRetrofitInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://s3.amazonaws.com/sq-mobile-interview/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createEmployeeAPI(retrofit: Retrofit): EmployeeAPI{
        return retrofit.create(EmployeeAPI::class.java)
    }

    fun getEmployeeList(): Call<Employees>{
        return employeeAPI.getEmployeeList()
    }

}