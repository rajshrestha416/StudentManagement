package com.raj.studentmanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.raj.studentmanagement.databinding.ActivityDashboardBinding
import com.raj.studentmanagement.databinding.ActivityRegisterBinding
import com.raj.studentmanagement.databinding.ActivityViewStudentBinding

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddStudent.setOnClickListener{
//            startActivity(Intent(this,AddStudentActivity::class.java))
            Intent(this,AddStudentActivity::class.java).also {
                it.putExtra("student", "Add Student")
                startActivity(it)
            }
        }

        binding.btnShowstudent.setOnClickListener{
            startActivity(Intent(this,ViewStudentActivity::class.java))
        }
    }
}