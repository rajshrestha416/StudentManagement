package com.raj.studentmanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.raj.studentmanagement.Model.Admin
import com.raj.studentmanagement.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            if (validation()) {
                Database().appendAdmin(
                    Admin(
                        binding.etFirstname.text.toString(),
                        binding.etLastname.text.toString(),
                        binding.etUsername.text.toString(),
                        binding.etPassword.text.toString(),
                        binding.etOldpass.text.toString()
                    )
                )
                Toast.makeText(this, "Account Registerd", Toast.LENGTH_LONG).show()
                clear()
            }

        }
    }
    private fun clear() {
        binding.etFirstname.setText("")
        binding.etLastname.setText("")
        binding.etUsername.setText("")
        binding.etPassword.setText("")
        binding.etOldpass.setText("")
    }

    private fun validation(): Boolean {
        var flag = true
        if (TextUtils.isEmpty(binding.etFirstname.text)) {
            binding.etFirstname.error = "Please Enter Student Name"
            binding.etFirstname.requestFocus()
            flag = false
        } else if (TextUtils.isEmpty(binding.etLastname.text)) {
            binding.etLastname.error = "Please Enter Address"
            binding.etLastname.requestFocus()
            flag = false
        } else if (TextUtils.isEmpty(binding.etUsername.text)) {
            binding.etUsername.error = "Please Enter the age"
            binding.etUsername.requestFocus()
            flag = false
        } else if (TextUtils.isEmpty(binding.etPassword.text)) {
            binding.etPassword.error = "Please Enter provide image URL"
            binding.etPassword.requestFocus()
            flag = false
        } else if (TextUtils.isEmpty(binding.etOldpass.text)) {
            binding.etOldpass.error = "Please Enter provide image URL"
            binding.etOldpass.requestFocus()
            flag = false
        }
        return flag
    }
}
