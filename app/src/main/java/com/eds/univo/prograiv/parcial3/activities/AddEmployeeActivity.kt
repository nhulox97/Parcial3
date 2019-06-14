package com.eds.univo.prograiv.parcial3.activities

import android.content.ContentValues
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.eds.univo.prograiv.parcial3.R
import com.eds.univo.prograiv.parcial3.utils.EmployeeDBHelper
import kotlinx.android.synthetic.main.activity_add_employee.*
import kotlinx.android.synthetic.main.activity_add_person.*
import kotlinx.android.synthetic.main.activity_add_person.edtDUI
import kotlinx.android.synthetic.main.activity_add_person.edtName

class AddEmployeeActivity : AppCompatActivity() {

    var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_employee)

        try {
            var bundle: Bundle? = intent.extras
            id = bundle!!.getInt("EmployeeId", 0)
            if (id != 0){
                edtNameE.setText(bundle.getString("EmployeeName"))
                edtJobE.setText(bundle.getString("EmployeeJob"))
                edtDUIE.setText(bundle.getString("EmployeeDUI"))
            }

        } catch (e: Exception){
            e.printStackTrace()
        }

        btnAddE.setOnClickListener {
            var dbHelper = EmployeeDBHelper(this)

            val values = ContentValues()
            values.put(dbHelper.COL_NAME, edtNameE.text.toString())
            values.put(dbHelper.COL_JOB, edtJobE.text.toString())
            values.put(dbHelper.COL_DUI, edtDUIE.text.toString())

            if (id == 0) {
                val mID = dbHelper.insert(values)

                if (mID > 0) {
                    Toast.makeText(this, "Add note successfully!", Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    Toast.makeText(this, "Fail to add note!", Toast.LENGTH_LONG).show()
                }
            } else {
                var selectionArs = arrayOf(id.toString())
                val mID = dbHelper.update(values, "Id=?", selectionArs)

                if (mID > 0) {
                    Toast.makeText(this, "Add note successfully!", Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    Toast.makeText(this, "Fail to add note!", Toast.LENGTH_LONG).show()
                }
            }
        }

    }

}
