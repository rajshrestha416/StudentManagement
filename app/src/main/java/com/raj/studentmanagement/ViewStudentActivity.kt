package com.raj.studentmanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.raj.studentmanagement.Adapter.StudentAdapter
import com.raj.studentmanagement.databinding.ActivityRegisterBinding
import com.raj.studentmanagement.databinding.ActivityViewStudentBinding

class ViewStudentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewStudentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = StudentAdapter(this)
        binding.rvStudents.layoutManager = LinearLayoutManager(this@ViewStudentActivity)
        binding.rvStudents.adapter = adapter
    }

}