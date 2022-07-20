package com.example.square.network

import com.example.square.model.Employee
import com.example.square.model.Employees
import retrofit2.Call
import retrofit2.http.GET

interface EmployeeAPI {

    @GET("employees.json")
    fun getEmployeeList(): Call<Employees>
}