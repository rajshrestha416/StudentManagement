package com.raj.studentmanagement

import android.R
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import com.raj.studentmanagement.Model.Student
import com.raj.studentmanagement.databinding.ActivityAddStudentBinding
import com.raj.studentmanagement.databinding.ActivityDashboardBinding
import com.raj.studentmanagement.databinding.ActivityRegisterBinding

class AddStudentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddStudentBinding
    private var position = 0
    private val database = Database()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        val student = intent.getStringExtra("student")

        if (student == "Edit Student"){
            filled()
        }

        binding.btnAddStudent.setOnClickListener {
            if (binding.btnAddStudent.text == "ADD STUDENT") {
                if (validation()) {
                    val genderId = binding.radioGroup.checkedRadioButtonId
                    val gender:RadioButton = findViewById(genderId)
                    Database().appendStudent(
                        Student(
                            binding.etName.text.toString(),
                            binding.etAge.text.toString().toInt(),
                            gender.text.toString(),
                            binding.etAddress.text.toString(),
                            binding.etImageUrl.text.toString()
                        )
                    )
                    Toast.makeText(this, "Student Added", Toast.LENGTH_LONG).show()
                    clear()
                }
            } else {
                position = intent.getIntExtra("position",0)
                val genderId = binding.radioGroup.checkedRadioButtonId
                val gender:RadioButton = findViewById(genderId)
                Database().editStudent(
                    Student(
                        binding.etName.text.toString(),
                        binding.etAge.text.toString().toInt(),
                        gender.text.toString(),
                        binding.etAddress.text.toString(),
                        binding.etImageUrl.text.toString()
                    ),
                    position
                )
                binding.btnAddStudent.text = "ADD STUDENT"
                Toast.makeText(this, "Student Added", Toast.LENGTH_LONG).show()
                clear()
                startActivity(Intent(this,ViewStudentActivity::class.java))
            }
        }
    }

    private fun filled() {
        val position = intent.getIntExtra("position", 0)
        val studentList = database.returnStudent()
//        val student = database.editStudent(position)
        binding.btnAddStudent.text = "UPDATE"
        binding.etName.setText(studentList[position].studentName)
        binding.etAge.setText(studentList[position].studentAge.toString())
        binding.etAddress.setText(studentList[position].studentAddress)
        binding.etImageUrl.setText(studentList[position].profileImage)
        if (studentList[position].studentGender == "Male") {
            binding.rdoMale.isSelected = true
        } else if (studentList[position].studentGender == "Female") {
            binding.rdoFemale.isSelected = true
        } else {
            binding.rdoOther.isSelected = true
        }
    }

    private fun clear() {
        binding.etName.setText("")
        binding.etAge.setText("")
        binding.etAddress.setText("")
        binding.etImageUrl.setText("")
        binding.radioGroup.clearCheck()
        binding.rdoMale.isChecked = true
        binding.etName.requestFocus()
    }

    private fun validation(): Boolean {
        var flag = true
        if (TextUtils.isEmpty(binding.etName.text)) {
            binding.etName.error = "Please Enter Student Name"
            binding.etName.requestFocus()
            flag = false
        } else if (TextUtils.isEmpty(binding.etAddress.text)) {
            binding.etAddress.error = "Please Enter Address"
            binding.etAddress.requestFocus()
            flag = false
        } else if (TextUtils.isEmpty(binding.etAge.text)) {
            binding.etAge.error = "Please Enter the age"
            binding.etAge.requestFocus()
            flag = false
        } else if (TextUtils.isEmpty(binding.etImageUrl.text)) {
            binding.etImageUrl.error = "Please Enter provide image URL"
            binding.etImageUrl.requestFocus()
            flag = false
        }
        return flag
    }
}