package com.example.square.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.square.R
import com.example.square.model.Employee
import com.example.square.model.Employees
import kotlinx.android.synthetic.main.employee_card_info.view.*

class EmployeeAdapter(private val employees: Employees) :
    RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    class EmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var fullName: TextView = itemView.full_name_label
        var uuid: TextView = itemView.uuid_label
        var phoneNumber: TextView = itemView.phone_number_label
        var emailAddress: TextView = itemView.email_address_label
        var bio: TextView = itemView.biography_label
        var team: TextView = itemView.team_label
        var employeeType: TextView = itemView.employee_type_label
        var photo: ImageView = itemView.photo_url_label

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.employee_card_info, parent, false)
        return EmployeeViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.fullName.text = employees.employees[position].fullName
        holder.uuid.text = "UUID: " + employees.employees[position].uuid
        holder.phoneNumber.text = "Phone Number: " + employees.employees[position].phoneNumber
        holder.emailAddress.text = "Email Address: " + employees.employees[position].emailAddress
        holder.bio.text = "Biography: " + employees.employees[position].biography
        holder.team.text = "Team: " + employees.employees[position].team
        holder.employeeType.text = "Employee Type: " + employees.employees[position].employeeType
        Glide.with(holder.itemView.context)
            .load(employees.employees[position].photoUrlLarge)
            .into(holder.photo)
    }

    override fun getItemCount(): Int {
        return employees.employees.size
    }

    fun clear(){
        employees.employees.clear()
        notifyDataSetChanged()
    }

//    fun addAll(employeeList: Employees){
//        employees.employees.addAll(employeeList)
//        notifyDataSetChanged()
//    }
}