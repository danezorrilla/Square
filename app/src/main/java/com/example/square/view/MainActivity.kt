package com.example.square.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.square.R
import com.example.square.adapter.EmployeeAdapter
import com.example.square.model.Employees
import com.example.square.viewmodel.EmployeeViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var employeeViewModel: EmployeeViewModel
    private lateinit var employeeObserver: Observer<Employees>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        employeeViewModel = ViewModelProvider(this).get(EmployeeViewModel::class.java)
        employeeObserver = Observer { employee -> displayEmployeesList(employee) }
        employeeObserver.let {
            employeeViewModel.getEmployeeListResponse().observe(this, it)
        }

        swipeRefreshLayout.setOnRefreshListener {
            employeeViewModel.getEmployeeListResponse().value?.employees?.clear()
            employeeViewModel.getEmployeeListResponse().value?.employees?.
            addAll(employeeViewModel.getEmployeeListResponse().value!!.employees)
            employee_list.adapter?.notifyDataSetChanged()
            swipeRefreshLayout.isRefreshing = false
        }

    }

    private fun displayEmployeesList(employees: Employees){
        for (i in employees.employees.indices){
            Log.d("TAG_Z", employees.employees[i].fullName)
            employee_list.layoutManager = LinearLayoutManager(this)
            employee_list.adapter = EmployeeAdapter(employees)
        }
    }

}