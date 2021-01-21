package com.raj.studentmanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.raj.studentmanagement.Model.Admin
import com.raj.studentmanagement.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adminlogged : Admin
    private val adminList = Database().returnAdmin()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            if (validate()) {
                for (admin in adminList) {
                    if (admin.username == binding.etUsername.text.toString()
                        && admin.password == binding.etPassword.text.toString()
                    ) {
                       adminlogged = admin
                    }
                }
                if (adminlogged != null){
                    Intent(this, DashboardActivity::class.java).also {
                        Toast.makeText(
                            this,
                            "Welcome ${adminlogged.firstName} ${adminlogged.lastName}",
                            Toast.LENGTH_SHORT
                        ).show()
                        startActivity(it)
                    }
                }
                else{
                    binding.etUsername.error = "Username or password incorrect"
                }
            }

        }

        val text = "No account? Register Here"
        val ss = SpannableString(text)
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                startActivity(Intent(this@MainActivity, RegisterActivity::class.java))
            }
        }

        ss.setSpan(clickableSpan, 12, 25, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.tvRegister.text = ss
        binding.tvRegister.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun validate(): Boolean {
        var flag = true
        if (TextUtils.isEmpty(binding.etPassword.text)) {
            binding.etPassword.error = "Please, enter password"
            binding.etPassword.requestFocus()
            flag = false
        } else if (TextUtils.isEmpty(binding.etUsername.text)) {
            binding.etUsername.error = "Please, enter username"
            binding.etUsername.requestFocus()
            flag = false
        }
        return flag
    }
}