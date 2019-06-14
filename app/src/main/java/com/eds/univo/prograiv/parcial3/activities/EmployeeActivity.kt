package com.eds.univo.prograiv.parcial3.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.eds.univo.prograiv.parcial3.R
import com.eds.univo.prograiv.parcial3.models.Employee
import com.eds.univo.prograiv.parcial3.utils.EmployeeDBHelper
import com.eds.univo.prograiv.parcial3.utils.PersonDBHelper
import kotlinx.android.synthetic.main.activity_employee.*

class EmployeeActivity : AppCompatActivity() {

    private var listEmployees = ArrayList<Employee>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee)
        loadQueryAll()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item != null){
            when (item.itemId){
                R.id.addPerson -> {
                    val intent = Intent(this, AddEmployeeActivity::class.java)
                    startActivity(intent)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        loadQueryAll()
    }

    private fun loadQueryAll(){
        var dbManager = EmployeeDBHelper(this)
        val cursor = dbManager.queryAll()

        listEmployees.clear()
        if (cursor.moveToFirst()){
            do {
                val id = cursor.getInt(cursor.getColumnIndex(dbManager.COL_ID))
                val name = cursor.getString(cursor.getColumnIndex(dbManager.COL_NAME))
                val job = cursor.getString(cursor.getColumnIndex(dbManager.COL_JOB))
                val DUI = cursor.getString(cursor.getColumnIndex(dbManager.COL_DUI))

                listEmployees.add(Employee(id, name, job, DUI))

            } while (cursor.moveToNext())

            var employeesAdapter = EmployeesAdapter(this, listEmployees)
            lvEmployees.adapter = employeesAdapter

        }
    }

    inner class EmployeesAdapter: BaseAdapter {
        override fun getItem(position: Int): Any {
            return employeesList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return employeesList.size
        }

        private var employeesList = ArrayList<Employee>()
        private var context: Context? = null

        constructor(context: Context, employeesList: ArrayList<Employee>) : super() {
            this.employeesList = employeesList
            this.context = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

            val view: View?
            val vh: ViewHolder

            if (convertView == null) {
                view = layoutInflater.inflate(R.layout.employee, parent, false)
                vh = ViewHolder(view)
                view.tag = vh
                Log.i("JSA", "set Tag for ViewHolder, position: $position")
            } else {
                view = convertView
                vh = view.tag as ViewHolder
            }

            var mEmployee = employeesList[position]

            vh.tvName.text = mEmployee.name
            vh.tvJob!!.text = mEmployee.job
            vh.tvDUI!!.text = mEmployee.DUI

            vh.ivEdit!!.setOnClickListener {
                updateEmployee(mEmployee)
            }

            vh.ivDelete!!.setOnClickListener {
                var dbManager = EmployeeDBHelper(this.context!!)
                val selectionArgs = arrayOf(mEmployee.id.toString())
                dbManager.delete("Id=?", selectionArgs)
                loadQueryAll()
            }

            return view
        }

    }

    private fun updateEmployee(employee: Employee){
        val intent = Intent(this, AddEmployeeActivity::class.java)
        intent.putExtra("EmployeeId", employee.id)
        intent.putExtra("EmployeeName", employee.name)
        intent.putExtra("EmployeeJob", employee.job)
        intent.putExtra("EmployeeDUI", employee.DUI)

        startActivity(intent)
    }

    private class ViewHolder(view: View?){
        val tvName: TextView
        val tvJob: TextView?
        val tvDUI: TextView?
        val ivEdit: ImageView
        val ivDelete: ImageView

        init {
            this.tvName = view!!.findViewById(R.id.tvNameE) as TextView
            this.tvJob = view!!.findViewById(R.id.tvJobE) as TextView?
            this.tvDUI = view!!.findViewById(R.id.tvDUIE) as TextView
            this.ivEdit = view!!.findViewById(R.id.ivEditE) as ImageView
            this.ivDelete = view!!.findViewById(R.id.ivDeleteE) as ImageView
        }
    }
}
